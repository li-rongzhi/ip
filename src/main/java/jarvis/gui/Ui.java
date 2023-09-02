package jarvis.gui;

import java.time.LocalDate;

import jarvis.jarvisexception.JarvisException;
import jarvis.task.Task;
import jarvis.task.TaskList;



/**
 * Ui is an interface for interaction with the user.
 *
 * @author Rongzhi
 */
public class Ui {
    /**
     * Greeting the user when chatbot is launched.
     */
    public String greet() {
        String logo = "  _____     _       ____  __        __ ____    ____    \n"
                + " |_   _|   / \\     |  _ \\ \\ \\      / /|_  _|  / ___|   \n"
                + "   | |    / _ \\    | |_) | \\ \\    / /   | |  | (___     \n"
                + "   | |   / / \\ \\   | ___/   \\ \\  / /    | |   \\___ \\ \n"
                + "  _| |  / ----- \\  | |\\ \\    \\ \\/ /    _| |_  ____) | \n"
                + " |___/ /_/     \\_\\ |_| \\_\\    \\__/    |_____||_____/  \n";
        return horizontal_line_printer() + "\n"
                + "Hello from Jarvis.Jarvis\n" + logo + "\n"
                + "What can I do for you, sir?";
    }

    /**
     * Say bye to the user when exit.
     */
    public String bye() {
        return "Bye! Hope to see you again, sir." + "\n"
                + this.horizontal_line_printer();
    }

    /**
     * Print a horizontal line.
     */
    public String horizontal_line_printer() {
        return "-".repeat(50);
    }

    /**
     * Report when a new task is added
     * @param taskList
     * @param task
     */
    public String reportTaskAdded(TaskList taskList, Task task) {
        return this.horizontal_line_printer() + "\n"
                + "Got it, sir. I've added this task: " + "\n"
                + task.toString() + "\n"
                + taskList.count_taskList() + "\n"
                + this.horizontal_line_printer();
    }

    /**
     * Report when a task is deleted.
     * @param task the deleted task.
     */
    public String reportTaskDeleted(Task task) {
        return this.horizontal_line_printer() + "\n"
                + " Noted. I've removed this task:" + "\n"
                + "\t" + task.toString() + "\n"
                + this.horizontal_line_printer();
    }

    /**
     * Report when a task is marked.
     * @param task the task marked.
     */
    public String reportTaskMarked(Task task) {
        return this.horizontal_line_printer() + "\n"
                + "Nice! I've marked this task as done:" + "\n"
                + task.toString() + "\n"
                + this.horizontal_line_printer();
    }

    /**
     * Report when a task is unmarked.
     * @param task the task unmarked.
     */
    public String reportTaskUnmarked(Task task) {
        return this.horizontal_line_printer() + "\n"
                + "Nice! I've marked this task as not done yet:" + "\n"
                + task.toString() + "\n"
                + this.horizontal_line_printer();
    }

    /**
     * Report the whole task list.
     * @param taskList
     */
    public String reportList(TaskList taskList) {
        return this.horizontal_line_printer() + "\n"
                + "Sir, here is your list:" + "\n"
                + taskList.displayList() + "\n"
                + this.horizontal_line_printer();
    }

    /**
     * Report the result of check command.
     * @param output
     * @param time
     */
    public String reportCheckDate(String output, LocalDate time) {
        if (output == "") {
            return this.horizontal_line_printer() + "\n"
                    + "There is no task on " + time.toString() + "\n"
                    + this.horizontal_line_printer();
        }
        return this.horizontal_line_printer() + "\n"
                + "The followings are tasks on " + time.toString() + ":\n"
                + output + "\n"
                + this.horizontal_line_printer();
    }

    /**
     * Report the result of find command.
     * @param output
     * @param target
     */
    public String reportFindContent(String output, String target) {
        return this.horizontal_line_printer() + "\n"
                + "Sir, here are tasks related to " + target + "\n"
                + output + "\n"
                + this.horizontal_line_printer();
    }

    /**
     * Report an error when error occurs.
     * @param e
     */
    public String reportError(JarvisException e) {
        return e.getMessage();
    }

}
