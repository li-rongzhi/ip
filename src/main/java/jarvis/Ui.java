package jarvis;

import java.time.LocalDate;
import java.util.Scanner;

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
    public void greet() {
        String logo = "  _____     _       ____  __        __ ____    ____    \n"
                + " |_   _|   / \\     |  _ \\ \\ \\      / /|_  _|  / ___|   \n"
                + "   | |    / _ \\    | |_) | \\ \\    / /   | |  | (___     \n"
                + "   | |   / / \\ \\   | ___/   \\ \\  / /    | |   \\___ \\ \n"
                + "  _| |  / ----- \\  | |\\ \\    \\ \\/ /    _| |_  ____) | \n"
                + " |___/ /_/     \\_\\ |_| \\_\\    \\__/    |_____||_____/  \n";

        horizontal_line_printer();
        System.out.println("Hello from Jarvis.Jarvis\n" + logo);
        System.out.println("What can I do for you, sir?");
    }

    /**
     * Say bye to the user when exit.
     */
    public void bye() {
        System.out.println("Bye! Hope to see you again, sir.");
        this.horizontal_line_printer();
    }

    /**
     * Print a horizontal line.
     */
    public void horizontal_line_printer() {
        System.out.println("-".repeat(50));
    }

    /**
     * Read in user input.
     * @return the user input.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    /**
     * Report when a new task is added
     * @param taskList
     * @param task
     */
    public void reportTaskAdded(TaskList taskList, Task task) {
        this.horizontal_line_printer();
        System.out.println("Got it, sir. I've added this task: ");
        System.out.println(task.toString());
        System.out.println(taskList.count_taskList());
        this.horizontal_line_printer();
    }

    /**
     * Report when a task is deleted.
     * @param task the deleted task.
     */
    public void reportTaskDeleted(Task task) {
        this.horizontal_line_printer();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("\t" + task.toString());
        this.horizontal_line_printer();
    }

    /**
     * Report when a task is marked.
     * @param task the task marked.
     */
    public void reportTaskMarked(Task task) {
        this.horizontal_line_printer();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        this.horizontal_line_printer();
    }

    /**
     * Report when a task is unmarked.
     * @param task the task unmarked.
     */
    public void reportTaskUnmarked(Task task) {
        this.horizontal_line_printer();
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println(task.toString());
        this.horizontal_line_printer();
    }

    /**
     * Report the whole task list.
     * @param taskList
     */
    public void reportList(TaskList taskList) {
        this.horizontal_line_printer();
        System.out.println("Sir, here is your list:");
        System.out.println(taskList.displayList());
        this.horizontal_line_printer();
    }

    /**
     * Report the result of check command.
     * @param output
     * @param time
     */
    public void reportCheckDate(String output, LocalDate time) {
        this.horizontal_line_printer();
        System.out.println("The followings are tasks on " + time.toString() + ":\n");
        System.out.println(output);
        this.horizontal_line_printer();
    }

    /**
     * Report the result of find command.
     * @param output
     * @param target
     */
    public void reportFindContent(String output, String target) {
        this.horizontal_line_printer();
        System.out.println("Sir, here are tasks related to " + target);
        System.out.println(output);
        this.horizontal_line_printer();
    }

    /**
     * Report an error when error occurs.
     * @param e
     */
    public void reportError(JarvisException e) {
        System.out.println(e.getMessage());
    }

}
