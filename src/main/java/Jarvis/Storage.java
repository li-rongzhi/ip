package Jarvis;

import Jarvis.JarvisException.RecordLoadingException;
import Jarvis.JarvisException.RecordUpdateException;
import Jarvis.Task.TaskList;

import java.io.*;
import java.util.ArrayList;

/**
 * Storage class is used for providing backup of the taskList.
 *
 * @author Rongzhi
 */
public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load records from the previous storage.
     * @return A list of records in string.
     * @throws RecordLoadingException
     */
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

    /**
     * Update the records.
     * @param taskList the new taskList to be stored
     * @throws RecordUpdateException
     */
    public void update(TaskList taskList) throws RecordUpdateException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.filePath))) {
            writer.println(taskList.toRecord());
        } catch (IOException e) {
            throw new RecordUpdateException();
        }
    }
}
