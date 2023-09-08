package jarvis;

import jarvis.command.AddCommand;
import jarvis.command.CheckCommand;
import jarvis.command.Command;
import jarvis.command.DeleteCommand;
import jarvis.command.ExitCommand;
import jarvis.command.FindCommand;
import jarvis.command.ListCommand;
import jarvis.command.MarkCommand;
import jarvis.command.UnmarkCommand;
import jarvis.jarvisexception.InvalidCommandException;

/**
 * Parser class is used for parsing user input into a command to be executed by Jarvis chatbot.
 *
 * @author Rongzhi
 */
public class Parser {
    /**
     * An enum for keywords in user input.
     */
    public enum Keyword {
        LIST("list"), MARK("mark"), UNMARK("unmark"), DELETE("delete"), CHECK("check"),
        FIND("find"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), BYE("bye");

        private String keyword;

        private Keyword(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }
    }

    /**
     * Parse the user input into a command to be executed by Jarvis chatbot.
     * @param input the user input.
     * @return A command to be executed by Jarvis chatbot.
     * @throws InvalidCommandException If user input is invalid for any command.
     */
    public Command parse(String input) throws InvalidCommandException {
        if (isAddCommand(input)) {
            return new AddCommand(input);
        } else if (isListCommand(input)) {
            return new ListCommand();
        } else if (isCheckCommand(input)) {
            return new CheckCommand(input);
        } else if (isFindCommand(input)) {
            return new FindCommand(input);
        } else if (isMarkCommand(input)) {
            return new MarkCommand(input);
        } else if (isUnmarkCommand(input)) {
            return new UnmarkCommand(input);
        } else if (isDeleteCommand(input)) {
            return new DeleteCommand(input);
        } else if (isByeCommand(input)) {
            return new ExitCommand();
        } else {
            throw new InvalidCommandException();
        }

    }

    // Helper methods
    private boolean isAddCommand(String input) {
        return input.startsWith(Keyword.TODO.keyword) || input.startsWith(Keyword.DEADLINE.keyword)
                || input.startsWith(Keyword.EVENT.keyword);
    }

    private boolean isListCommand(String input) {
        return input.equalsIgnoreCase(Keyword.LIST.keyword);
    }

    private boolean isCheckCommand(String input) {
        return input.startsWith(Keyword.CHECK.keyword);
    }

    private boolean isFindCommand(String input) {
        return input.startsWith(Keyword.FIND.keyword);
    }

    private boolean isMarkCommand(String input) {
        return input.startsWith(Keyword.MARK.keyword + " ") && input.length() > 5
                && input.substring(5).matches("-?\\d+");
    }

    private boolean isUnmarkCommand(String input) {
        return input.startsWith(Keyword.UNMARK.keyword + " ") && input.length() > 7
                && input.substring(7).matches("-?\\d+");
    }

    private boolean isDeleteCommand(String input) {
        return input.startsWith(Keyword.DELETE.keyword + " ") && input.length() > 7
                && input.substring(7).matches("-?\\d+");
    }

    private boolean isByeCommand(String input) {
        return input.equalsIgnoreCase(Keyword.BYE.keyword);
    }
}
