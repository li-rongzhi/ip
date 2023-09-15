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
        return "Hello from Jarvis.\n" + "What can I do for you, sir?";
    }

    /**
     * Say bye to the user when exits.
     * @return A Bye message.
     */
    public String bye() {
        return "Bye! Hope to see you again, sir.";
    }

    /**
     * Report when a new task is added
     * @param taskList The taskList where the new task will be added.
     * @param task The new task to be added.
     * @return A message indicating that task is added.
     */
    public String reportTaskAdded(TaskList taskList, Task task) {
        return "Got it, sir. I've added this task: " + "\n"
                + task.toString() + "\n" + taskList.countTaskList();
    }

    /**
     * Report when a task is deleted.
     * @param task The deleted task.
     * @return A message indicating hat the task is deleted.
     */
    public String reportTaskDeleted(Task task) {
        return " Noted. I've removed this task:" + "\n"
                + "\t" + task.toString();
    }

    /**
     * Report when a task is marked.
     * @param task The task got marked.
     * @return A message indicating that the task is marked.
     */
    public String reportTaskMarked(Task task) {
        return "Nice! I've marked this task as done:" + "\n"
                + task.toString();
    }

    /**
     * Report when a task is unmarked.
     * @param task The task got unmarked.
     * @return A message indicating that the task is unmarked.
     */
    public String reportTaskUnmarked(Task task) {
        return "Nice! I've marked this task as not done yet:" + "\n"
                + task.toString();
    }

    /**
     * Report the whole task list.
     * @param taskList The taskList to be displayed.
     * @return The taskList in display format.
     */
    public String reportList(TaskList taskList) {
        return "Sir, here is your list:" + "\n"
                + taskList.displayList();
    }

    /**
     * Report the result of check command.
     * @param output The output of CheckCommand.
     * @param time The time specified to check.
     * @return The output of CheckCommand in display format.
     */
    public String reportCheckDate(String output, LocalDate time) {
        if (output == "") {
            return "There is no task on " + time.toString();
        }
        return "The followings are tasks on " + time.toString() + ":\n" + output;
    }

    /**
     * Report the result of find command.
     * @param output The output of FindCommand.
     * @param target The specified searching keyword.
     * @return The output of FindCommand in display format.
     */
    public String reportFindContent(String output, String target) {
        return "Sir, here are tasks related to " + target + "\n" + output;
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
