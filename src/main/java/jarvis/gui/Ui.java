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
     * @return A greeting message.
     */
    public String greet() {
        String logo = "  _____     _       ____  __        __ ____    ____    \n"
                + " |_   _|   / \\     |  _ \\ \\ \\      / /|_  _|  / ___|   \n"
                + "   | |    / _ \\    | |_) | \\ \\    / /   | |  | (___     \n"
                + "   | |   / / \\ \\   | ___/   \\ \\  / /    | |   \\___ \\ \n"
                + "  _| |  / ----- \\  | |\\ \\    \\ \\/ /    _| |_  ____) | \n"
                + " |___/ /_/     \\_\\ |_| \\_\\    \\__/    |_____||_____/  \n";
        return printHorizontalLine() + "\n"
                + "Hello from Jarvis.Jarvis\n" + logo + "\n"
                + "What can I do for you, sir?";
    }

    /**
     * Say bye to the user when exits.
     * @return A Bye message.
     */
    public String bye() {
        return "Bye! Hope to see you again, sir." + "\n"
                + this.printHorizontalLine();
    }

    /**
     * Print a horizontal line.
     * @return A horizontal line.
     */
    public String printHorizontalLine() {
        return "-".repeat(50);
    }

    /**
     * Report when a new task is added
     * @param taskList The taskList where the new task will be added.
     * @param task The new task to be added.
     * @return A message indicating that task is added.
     */
    public String reportTaskAdded(TaskList taskList, Task task) {
        return this.printHorizontalLine() + "\n"
                + "Got it, sir. I've added this task: " + "\n"
                + task.toString() + "\n"
                + taskList.countTaskList() + "\n"
                + this.printHorizontalLine();
    }

    /**
     * Report when a task is deleted.
     * @param task The deleted task.
     * @return A message indicating hat the task is deleted.
     */
    public String reportTaskDeleted(Task task) {
        return this.printHorizontalLine() + "\n"
                + " Noted. I've removed this task:" + "\n"
                + "\t" + task.toString() + "\n"
                + this.printHorizontalLine();
    }

    /**
     * Report when a task is marked.
     * @param task The task got marked.
     * @return A message indicating that the task is marked.
     */
    public String reportTaskMarked(Task task) {
        return this.printHorizontalLine() + "\n"
                + "Nice! I've marked this task as done:" + "\n"
                + task.toString() + "\n"
                + this.printHorizontalLine();
    }

    /**
     * Report when a task is unmarked.
     * @param task The task got unmarked.
     * @return A message indicating that the task is unmarked.
     */
    public String reportTaskUnmarked(Task task) {
        return this.printHorizontalLine() + "\n"
                + "Nice! I've marked this task as not done yet:" + "\n"
                + task.toString() + "\n"
                + this.printHorizontalLine();
    }

    /**
     * Report the whole task list.
     * @param taskList The taskList to be displayed.
     * @return The taskList in display format.
     */
    public String reportList(TaskList taskList) {
        return this.printHorizontalLine() + "\n"
                + "Sir, here is your list:" + "\n"
                + taskList.displayList() + "\n"
                + this.printHorizontalLine();
    }

    /**
     * Report the result of check command.
     * @param output The output of CheckCommand.
     * @param time The time specified to check.
     * @return The output of CheckCommand in display format.
     */
    public String reportCheckDate(String output, LocalDate time) {
        if (output == "") {
            return this.printHorizontalLine() + "\n"
                    + "There is no task on " + time.toString() + "\n"
                    + this.printHorizontalLine();
        }
        return this.printHorizontalLine() + "\n"
                + "The followings are tasks on " + time.toString() + ":\n"
                + output + "\n"
                + this.printHorizontalLine();
    }

    /**
     * Report the result of find command.
     * @param output The output of FindCommand.
     * @param target The specified searching keyword.
     * @return The output of FindCommand in display format.
     */
    public String reportFindContent(String output, String target) {
        return this.printHorizontalLine() + "\n"
                + "Sir, here are tasks related to " + target + "\n"
                + output + "\n"
                + this.printHorizontalLine();
    }

    /**
     * Report an error when error occurs.
     * @param e The error to be reported.
     * @return Error in display format.
     */
    public String reportError(JarvisException e) {
        return e.getMessage();
    }

}
