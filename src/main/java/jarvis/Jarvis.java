package jarvis;

import jarvis.command.Command;
import jarvis.gui.Ui;
import jarvis.jarvisexception.JarvisException;
import jarvis.task.TaskList;

/**
 * Jarvis is the entry class of Jarvis chatbot.
 * Jarvis could be used for task tracking.
 *
 * @author Rongzhi
 */
public class Jarvis {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    /**
     * Create a Jarvis instance
     * @param filePath path of the record storage file
     */
    public Jarvis(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (JarvisException e) {
            this.taskList = new TaskList();
        }
    }

    public String getGreeting() {
        return this.ui.greet();
    }
    public String getResponse(String input) {
        try {
            Parser parser = new Parser();
            Command c = parser.parse(input);
            String output = c.execute(this.taskList, this.ui, this.storage);
            if (c.isExit()) {
                return this.ui.bye();
            }
            return output;
        } catch (JarvisException e) {
            return e.getMessage();
        }
    }
}
