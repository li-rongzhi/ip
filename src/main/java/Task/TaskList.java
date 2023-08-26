package Task;

import JarvisException.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<String> records) throws RecordLoadingException{
        this.taskList = new ArrayList<>();

        for (String record : records) {
            Task task = record_decoder(record);
            if (task != null) {
                this.taskList.add(task);
            }
        }
    }

    public Task record_decoder(String record) throws RecordLoadingException {
        if (record == null) {
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
                    int split_point = record.indexOf("(by:");
                    content = record.substring(7, split_point);
                    String time = record.substring(split_point+5, -1).trim();
                    LocalDateTime deadline = LocalDateTime.parse(time, formatter);
                    task = new Deadline(content, deadline, isDone);
                    break;
                case "[E]":
                    content = record.substring(7, record.indexOf(" (from: ")).trim();
                    LocalDateTime from = LocalDateTime.parse(
                            record.substring(record.indexOf(" (from: ")+8, record.indexOf(" to: ")).trim(),
                            formatter);
                    LocalDateTime to = LocalDateTime.parse(
                            record.substring(record.indexOf(" to: ")+5,record.length()-1).trim(), formatter);
                    task = new Event(content, from, to, isDone);
                    break;
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new RecordLoadingException();
        }
        return task;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int index) throws InvalidTaskIndexException {
        try {
            Task target = this.taskList.remove(index);
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    public Task markTask(int index) throws InvalidTaskIndexException {
        try {
            Task target = taskList.get(index-1);
            target.mark();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    public Task unmarkTask(int index) throws InvalidTaskIndexException {
        try {
            Task target = taskList.get(index-1);
            target.unmark();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    public String checkTask(LocalDate time) {
        ArrayList<Task> results = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task instanceof ToDo) {
                continue;
            }
            LocalDateTime[] time_collection = task.get_time_components();
            for (LocalDateTime task_time: time_collection) {
                if (task_time.toLocalDate().isEqual(time)) {
                    results.add(task);
                    indexes.add(i);
                    break;
                }
            }
        }
        String output = "";
        for (int j = 0; j < results.size(); j++) {
            output += (indexes.get(j)+1) + ". " + results.get(j).toString() + "\n";
        }
        return output;
    }

    public String count_taskList() {
        int num = this.taskList.size();
        if (num == 0) {
            return "Sir, there's nothing on the list currently.";
        } else {
            return "Now you have " + num + " tasks in the list.";
        }
    }



    public String displayList() {
        String results = "";
        int index = 0;
        for (Task task: this.taskList) {
            results += (index+1) + ". " + task.toString() + "\n";
            index++;
        }
        return results;
    }

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
