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
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads records from the previous storage.
     *
     * @return A list of records as strings.
     * @throws RecordLoadingException If an error occurs during record loading.
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
     * Updates the records.
     *
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
