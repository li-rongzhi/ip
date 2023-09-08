package jarvis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jarvis.jarvisexception.RecordLoadingException;
import jarvis.jarvisexception.RecordUpdateException;
import jarvis.task.TaskList;
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
     * @throws RecordLoadingException If an error occurs when loading records.
     */
    public ArrayList<String> load() throws RecordLoadingException {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            ArrayList<String> records = new ArrayList<>();
            String currRecord;
            while ((currRecord = reader.readLine()) != null) {
                records.add(currRecord);
            }
            return records;
        } catch (IOException e) {
            throw new RecordLoadingException();
        }
    }

    /**
     * Update the records.
     * @param taskList the new taskList to be stored.
     * @throws RecordUpdateException If an error occurs when updating records.
     */
    public void update(TaskList taskList) throws RecordUpdateException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.filePath))) {
            writer.println(taskList.toRecord());
        } catch (IOException e) {
            throw new RecordUpdateException();
        }
    }
}
