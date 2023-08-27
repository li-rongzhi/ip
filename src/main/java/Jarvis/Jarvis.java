package Jarvis;

import Jarvis.Command.Command;
import Jarvis.JarvisException.JarvisException;
import Jarvis.Task.TaskList;

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


    public Jarvis(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (JarvisException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Run Jarvis chatbot.
     */
    public void run() {
        this.ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (JarvisException e) {
                this.ui.reportError(e);
            }
        }

    }

    public static void main(String[] args) {
        Jarvis chatbot = new Jarvis("records.txt");
        chatbot.run();
    }
}
