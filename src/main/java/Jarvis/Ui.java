package Jarvis;

import Jarvis.Task.*;
import Jarvis.JarvisException.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
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

    public void bye() {
        System.out.println("Bye! Hope to see you again, sir.");
        this.horizontal_line_printer();
    }
    public void horizontal_line_printer() {
        System.out.println("-".repeat(50));
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    public void reportTaskAdded(TaskList taskList, Task task) {
        this.horizontal_line_printer();
        System.out.println("Got it, sir. I've added this task: ");
        System.out.println(task.toString());
        System.out.println(taskList.count_taskList());
        this.horizontal_line_printer();
    }

    public void reportTaskDeleted(Task task) {
        this.horizontal_line_printer();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("\t" + task.toString());
        this.horizontal_line_printer();
    }

    public void reportTaskMarked(Task task) {
        this.horizontal_line_printer();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        this.horizontal_line_printer();
    }

    public void reportTaskUnmarked(Task task) {
        this.horizontal_line_printer();
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println(task.toString());
        this.horizontal_line_printer();
    }

    public void reportList(TaskList taskList) {
        this.horizontal_line_printer();
        System.out.println("Sir, here is your list:");
        System.out.println(taskList.displayList());
        this.horizontal_line_printer();
    }

    public void reportCheckDate(String output, LocalDate time) {
        this.horizontal_line_printer();
        System.out.println("The followings are tasks on " + time.toString() + ":\n");
        System.out.println(output);
        this.horizontal_line_printer();
    }

    public void reportError(JarvisException e) {
        System.out.println(e.getMessage());
    }

}
