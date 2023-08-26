import Command.Command;
import JarvisException.JarvisException;
import Storage.Storage;
import Task.TaskList;
import UI.Ui;

public class Jarvis {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jarvis(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JarvisException e) {
            ui.reportError(e);
            tasks = new Task.TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JarvisException e) {
                ui.reportError(e);
            }
        }

    }

    public static void main(String[] args) {
        Jarvis robot = new Jarvis("records.txt");
        robot.run();
    }
}
