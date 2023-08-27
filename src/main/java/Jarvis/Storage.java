package Jarvis;

import Jarvis.jarvisexception.RecordLoadingException;
import Jarvis.jarvisexception.RecordUpdateException;
import Jarvis.task.TaskList;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> load() throws RecordLoadingException {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            ArrayList<String> records = new ArrayList<>();
            String curr_record;
            while ((curr_record = reader.readLine()) != null) {
                records.add(curr_record);
            }
            return records;
        } catch (IOException e) {
            throw new RecordLoadingException();
        }
    }

    public void update(TaskList taskList) throws RecordUpdateException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.filePath))) {
            writer.println(taskList.toRecord());
        } catch (IOException e) {
            throw new RecordUpdateException();
        }
    }
}
