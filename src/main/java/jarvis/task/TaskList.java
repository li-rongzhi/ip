package jarvis.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jarvis.jarvisexception.InvalidTaskIndexException;
import jarvis.jarvisexception.RecordLoadingException;

/**
 * TaskList class is an abstraction of the list of tasks.
 *
 * @author Rongzhi
 */
public class TaskList {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Construct a new TaskList instance.
     * @param records previous records represented in string.
     * @throws RecordLoadingException
     */
    public TaskList(ArrayList<String> records) throws RecordLoadingException {
        this.taskList = new ArrayList<>();

        for (String record : records) {
            Task task = record_decoder(record);
            if (task != null) {
                this.taskList.add(task);
            }
        }
    }

    /**
     * Decode the record in String into a Task object.
     * @param record a string recording a task.
     * @return a task with the recorded information.
     * @throws RecordLoadingException
     */
    public Task record_decoder(String record) throws RecordLoadingException {
        if (record == null || record.length() <= 3) {
            return null;
        }
        System.out.println(record);
        String type = record.substring(0, 3);
        // isDone indicates mark(True) or unmark(False)
        Boolean isDone = (record.substring(3, 6) == "[X]");
        Task task = null;
        String content;
        try {
            switch (type) {
            case "[T]":
                content = record.substring(7).trim();
                task = new ToDo(content, isDone);
                break;
            case "[D]":
                int splitPoint = record.indexOf("(by:");
                content = record.substring(7, splitPoint);
                String time = record.substring(splitPoint + 5, -1).trim();
                LocalDateTime deadline = LocalDateTime.parse(time, formatter);
                task = new Deadline(content, deadline, isDone);
                break;
            case "[E]":
                content = record.substring(7, record.indexOf(" (from: ")).trim();
                LocalDateTime from = LocalDateTime.parse(
                        record.substring(record.indexOf(" (from: ") + 8, record.indexOf(" to: ")).trim(),
                        formatter);
                LocalDateTime to = LocalDateTime.parse(
                        record.substring(record.indexOf(" to: ") + 5, record.length() - 1).trim(), formatter);
                task = new Event(content, from, to, isDone);
                break;
            default:
                break;
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new RecordLoadingException();
        }
        return task;
    }

    /**
     * Add the given task into the taskList.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Delete the task with given index from the taskList.
     * @param index the index of the task to be deleted.
     * @return the task deleted.
     * @throws InvalidTaskIndexException
     */
    public Task deleteTask(int index) throws InvalidTaskIndexException {
        try {
            Task target = this.taskList.remove(index);
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    /**
     * Mark the task with given index as done.
     * @param index
     * @return the task marked.
     * @throws InvalidTaskIndexException
     */
    public Task markTask(int index) throws InvalidTaskIndexException {
        try {
            Task target = taskList.get(index - 1);
            target.mark();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    /**
     * Unmark the task with given index as not done yet.
     * @param index
     * @return the task unmarked.
     * @throws InvalidTaskIndexException
     */
    public Task unmarkTask(int index) throws InvalidTaskIndexException {
        try {
            Task target = taskList.get(index - 1);
            target.unmark();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    /**
     * Check tasks on a specific date.
     * @param time
     * @return tasks on the given date in String.
     */
    public String checkTask(LocalDate time) {
        ArrayList<Task> results = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task instanceof ToDo) {
                continue;
            }
            LocalDateTime[] timeCollection = task.get_time_components();
            for (LocalDateTime taskTime: timeCollection) {
                if (taskTime.toLocalDate().isEqual(time)) {
                    results.add(task);
                    indexes.add(i);
                    break;
                }
            }
        }
        String output = "";
        for (int j = 0; j < results.size(); j++) {
            output += (indexes.get(j) + 1) + ". " + results.get(j).toString() + "\n";
        }
        return output;
    }


    /**
     * Searches for tasks in the task list that match the specified target content.
     * @param target The target content to search for in task descriptions.
     * @return A formatted string containing matching task descriptions along with their indexes.
     */
    public String findTask(String target) {
        String result = "";
        int index = 1;
        for (Task task: this.taskList) {
            if (task.checkContent(target)) {
                result += index + ". " + task.toString();
            }
        }
        return result;
    }


    /**
     * Count number of tasks currently in the taskList.
     * @return
     */

    public String count_taskList() {
        int num = this.taskList.size();
        if (num == 0) {
            return "Sir, there's nothing on the list currently.";
        } else {
            return "Now you have " + num + " tasks in the list.";
        }
    }


    /**
     * Get the whole taskList in display format.
     * @return
     */
    public String displayList() {
        String results = "";
        int index = 0;
        for (Task task: this.taskList) {
            results += (index + 1) + ". " + task.toString() + "\n";
            index++;
        }
        return results;
    }

    /**
     * Get the whole taskList in record format.
     * @return
     */
    public String toRecord() {
        String results = "";
        for (Task task: this.taskList) {
            results += task.toRecord() + "\n";
        }
        return results;
    }

    @Override
    public String toString() {
        String results = "";
        for (Task task: this.taskList) {
            results += task.toString() + "\n";
        }
        return results;
    }
}
