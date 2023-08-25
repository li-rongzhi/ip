import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TaskManagement {
    private static DateTimeFormatter formatter_with_time = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter formatter_without_time = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private List<Task> taskList;
    enum Keyword {

        LIST("list"), MARK("mark"), UNMARK("unmark"), DELETE("delete"), CHECK("check"),
        TODO("todo"), DEADLINE("deadline"), EVENT("event"), BYE("bye"), ECHO("echo");

        private String keyword;

        private Keyword(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return keyword;
        }
    }


    public TaskManagement(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void operate() {
        Scanner sc = new Scanner(System.in);
        this.reset_record();
        while (true) {
            String input = sc.nextLine();
            try {
                System.out.println();
                if (input.startsWith(Keyword.TODO.keyword) || input.startsWith(Keyword.DEADLINE.keyword)
                        || input.startsWith(Keyword.EVENT.keyword)) {
                    this.add_task(input);
                } else if (input.equalsIgnoreCase(Keyword.LIST.keyword)) {
                    this.list_printer();
                } else if (input.startsWith(Keyword.CHECK.keyword)) {
                    try {
                        String time = input.substring(6);
                        LocalDate formatted_time = LocalDate.parse(time, formatter_without_time);
                        this.check_tasks(formatted_time);
                    } catch (DateTimeParseException e) {
                        System.out.println(new InvalidTimeFormatException(formatter_without_time).getMessage());
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(new ContentMissingException("check"));
                    }
                } else if (input.startsWith(Keyword.MARK.keyword + " ") && input.length() > 5
                        && input.substring(5).matches("-?\\d+")) {
                    this.mark_task(input);
                } else if (input.startsWith(Keyword.UNMARK.keyword + " ") && input.length() > 7
                        && input.substring(7).matches("-?\\d+")) {
                    this.unmark_task(input);
                } else if (input.startsWith(Keyword.DELETE.keyword + " ") && input.length() > 7
                        && input.substring(7).matches("-?\\d+")){
                    this.delete_task(input);
                }
                else if (input.equalsIgnoreCase(Keyword.BYE.keyword)) {
                    break;
                } else {
                    throw new InvalidCommandException();
                }
            } catch (ContentMissingException | InvalidTaskIndexException
                     | InvalidCommandException | InvalidTimeFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    private void list_printer() {
        Jarvis.horizontal_line_printer();
        System.out.println("Sir, here is your list:");
        int index = 0;
        for (Task task : this.taskList) {
            System.out.println((index+1) + ". " + task.toString());
            index++;
        }
        Jarvis.horizontal_line_printer();
    }

    private void check_tasks(LocalDate time) {
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
        Jarvis.horizontal_line_printer();
        System.out.println("The followings are tasks on " + time.toString() + ":");
        for (int j = 0; j < results.size(); j++) {
            System.out.println(indexes.get(j) + ". " + results.get(j).toString());
        }
        Jarvis.horizontal_line_printer();
    }

    private void mark_task(String input) throws InvalidTaskIndexException {
        int index = Integer.parseInt(input.substring(5));
        try {
            Task target = this.taskList.get(index-1);
            target.mark();
            this.update_record();
            Jarvis.horizontal_line_printer();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(target.toString());
            Jarvis.horizontal_line_printer();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    private void unmark_task(String input) throws InvalidTaskIndexException {
        int index = Integer.parseInt(input.substring(7));
        try {
            Task target = this.taskList.get(index-1);
            target.unmark();
            update_record();
            Jarvis.horizontal_line_printer();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("\t" + target.toString());
            this.count_taskList();
            Jarvis.horizontal_line_printer();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    private void delete_task(String input) throws InvalidTaskIndexException{
        int index = Integer.parseInt(input.substring(7));
        try {
            Task target = this.taskList.get(index-1);
            taskList.remove(index-1);
            this.update_record();
            Jarvis.horizontal_line_printer();
            System.out.println(" Noted. I've removed this task:");
            System.out.println("\t" + target.toString());
            Jarvis.horizontal_line_printer();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    private void add_task(String input) throws ContentMissingException, InvalidCommandException, InvalidTimeFormatException {
        String keyword = input.split(" ")[0];
        String content = "";
        Task newTask = null;
        try {
            switch (keyword) {
                case "todo":
                    if (!Character.isWhitespace(input.charAt(4))) {
                        throw new InvalidCommandException();
                    }

                    content = input.substring(4).trim();

                    if (content.equals("")) {
                        throw new ContentMissingException("todo");
                    }
                    newTask = new ToDo(content);
                    this.taskList.add(newTask);
                    break;
                case "deadline":
                    int byIndex = input.indexOf("/by");
                    content = input.substring("deadline".length(), byIndex).trim();
                    String time = input.substring(byIndex + 3).trim();
                    if (content.equals("") || time.equals("")) {
                        throw new ContentMissingException("deadline");
                    }
                    LocalDateTime formatted_time = LocalDateTime.parse(time, formatter_with_time);
                    newTask = new Deadline(content, formatted_time);
                    this.taskList.add(newTask);
                    break;

                case "event":
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    content = input.substring("event".length(), fromIndex).trim();
                    String from = input.substring(fromIndex + 5, toIndex).trim();
                    String to = input.substring(toIndex + 3).trim();
                    if (content.equals("")
                            || from.equals("") || to.equals("")) {
                        throw new ContentMissingException("event");
                    }
                    LocalDateTime formatted_from = LocalDateTime.parse(from, formatter_with_time);
                    LocalDateTime formatted_to = LocalDateTime.parse(to, formatter_with_time);
                    newTask = new Event(content, formatted_from, formatted_to);
                    this.taskList.add(newTask);
                    break;

                default:
                    throw new InvalidCommandException();
            }
            this.update_record();
        } catch (IndexOutOfBoundsException e) {
            throw new ContentMissingException(keyword);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException(formatter_with_time);
        }

        Jarvis.horizontal_line_printer();
        System.out.println("Got it, sir. I've added this task: ");
        System.out.println(newTask.toString());
        count_taskList();
        Jarvis.horizontal_line_printer();
    }

    public void count_taskList() {
        int num = taskList.size();
        if (num == 0) {
            System.out.println("Sir, there's nothing on the list currently.");
        } else {
            System.out.println("Now you have " + num + " tasks in the list.");
        }
    }

    public void reset_record() {
        String filePath = "records.txt";
        // Convert the file path to a Path object
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            try {
                // Delete the file
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void update_record() {
        String filePath = "records.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task record: this.taskList) {
                writer.println(record.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
