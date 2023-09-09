package jarvis.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
     * @throws RecordLoadingException If an error occurs when loading records.
     */
    public TaskList(ArrayList<String> records) throws RecordLoadingException {
        this.taskList = new ArrayList<>();
        String[] recordArray = records.toArray(new String[records.size()]);
        decodeRecordInBatch(recordArray);
    }

    /**
     * Decode record in batch.
     * @param records
     */
    private void decodeRecordInBatch(String... records) throws RecordLoadingException {
        for (String record: records) {
            Task task = this.decodeRecord(record);
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
    private Task decodeRecord(String record) throws RecordLoadingException {
        if (record == null || record.length() <= 3) {
            return null;
        }
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
                int splitPoint = record.indexOf(" (by: ");
                content = record.substring(7, splitPoint).trim();
                String time = record.substring(splitPoint + 6, record.length() - 1).trim();
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
     * @throws InvalidTaskIndexException If the specified target index is out of range.
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
     * @param index The specified target index.
     * @return the task marked.
     * @throws InvalidTaskIndexException If the specifies target index is out of range.
     */
    public Task markTask(int index) throws InvalidTaskIndexException {
        try {
            Task target = this.taskList.get(index - 1);
            target.mark();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    /**
     * Unmark the task with given index as not done yet.
     * @param index The specified target index.
     * @return the task unmarked.
     * @throws InvalidTaskIndexException If the specifies target index is out of range.
     */
    public Task unmarkTask(int index) throws InvalidTaskIndexException {
        try {
            Task target = this.taskList.get(index - 1);
            target.unmark();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    /**
     * Check tasks on a specific date.
     * @param time The specified time to be checked.
     * @return tasks on the given date in String.
     */
    public String checkTask(LocalDate time) {
        ArrayList<Task> results = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
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
        return IntStream.range(0, results.size())
                .mapToObj(j -> (indexes.get(j) + 1) + ". " + results.get(j).toString())
                .collect(Collectors.joining("\n"));
    }


    /**
     * Searches for tasks in the task list that match the specified target content.
     * @param target The target content to search for in task descriptions.
     * @return A formatted string containing matching task descriptions along with their indexes.
     */
    public String findTask(String target) {
        return IntStream.range(0, this.taskList.size())
                .filter(index -> this.taskList.get(index).checkContent(target))
                .mapToObj(index -> (index + 1) + ". " + this.taskList.get(index).toString())
                .collect(Collectors.joining("\n"));
    }


    /**
     * Count number of tasks currently in the taskList.
     * @return The number of tasks in the taskList.
     */
    public String countTaskList() {
        int num = this.taskList.size();
        if (num == 0) {
            return "Sir, there's nothing on the list currently.";
        } else {
            return "Now you have " + num + " tasks in the list.";
        }
    }


    /**
     * Get the whole taskList in display format.
     * @return The taskList in display format.
     */
    public String displayList() {
        return IntStream.range(0, this.taskList.size())
                .mapToObj(index -> (index + 1) + ". " + this.taskList.get(index).toString())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Get the whole taskList in record format.
     * @return The taskList in record format.
     */
    public String toRecord() {
        return this.taskList.stream()
                .map(Task::toRecord)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Check if there exists any Event clashes with the new Event.
     * @param timeComponents The start and end time of Event
     * @return A string of the list of task that clashes with the new Event.
     */
    public String detectAnomalies(LocalDateTime[] timeComponents) {
        ArrayList<Task> results = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        boolean isNoAnomaly = true;
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            if (task instanceof Event) {
                LocalDateTime[] existingEventTime = task.get_time_components();
                if (existingEventTime[1].isBefore(timeComponents[0])
                        || timeComponents[1].isBefore(existingEventTime[0])) {
                    // No clash, intervals do not overlap
                } else {
                    results.add(task);
                    indexes.add(i);
                    isNoAnomaly = true;
                }
            }
        }
        if (isNoAnomaly) {
            return "";
        }
        return IntStream.range(0, results.size())
                .mapToObj(j -> (indexes.get(j) + 1) + ". " + results.get(j).toString())
                .collect(Collectors.joining("\n"));
    }
    @Override
    public String toString() {
        return this.taskList.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }
}
