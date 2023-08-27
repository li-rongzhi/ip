package Jarvis;

import Jarvis.Command.*;
import Jarvis.JarvisException.InvalidCommandException;

/**
 * Parser class is used for parsing user input into a command to be executed by Jarvis chatbot.
 *
 * @@author Rongzhi
 */
public class Parser {
    enum Keyword {
        LIST("list"), MARK("mark"), UNMARK("unmark"), DELETE("delete"), CHECK("check"),
        FIND("find"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), BYE("bye");

        private String keyword;

        private Keyword(String keyword) {
            this.keyword = keyword;
        }
    }

    /**
     * Parse the user input into a command to be executed by Jarvis chatbot.
     * @param input the user input
     * @return A comand to be executed by Jarvis chatbot
     * @throws InvalidCommandException
     */
    public static Command parse(String input) throws InvalidCommandException {
        if (input.startsWith(Keyword.TODO.keyword) || input.startsWith(Keyword.DEADLINE.keyword)
                || input.startsWith(Keyword.EVENT.keyword)) {
            return new AddCommand(input);
        } else if (input.equalsIgnoreCase(Keyword.LIST.keyword)) {
            return new ListCommand();
        } else if (input.startsWith(Keyword.CHECK.keyword)) {
            return new CheckCommand(input);
        } else if (input.startsWith(Keyword.FIND.keyword)) {
            return new FindCommand(input);
        } else if (input.startsWith(Keyword.MARK.keyword + " ") && input.length() > 5
                && input.substring(5).matches("-?\\d+")) {
            return new MarkCommand(input);
        } else if (input.startsWith(Keyword.UNMARK.keyword + " ") && input.length() > 7
                && input.substring(7).matches("-?\\d+")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith(Keyword.DELETE.keyword + " ") && input.length() > 7
                && input.substring(7).matches("-?\\d+")) {
            return new DeleteCommand(input);
        } else if (input.equalsIgnoreCase(Keyword.BYE.keyword)) {
            return new ExitCommand();
        } else {
            throw new InvalidCommandException();
        }
    }
}
