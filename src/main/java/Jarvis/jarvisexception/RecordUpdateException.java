package Jarvis.jarvisexception;

public class RecordUpdateException extends JarvisException {
    @Override
    public String toString() {
        return super.toString() + "Error occur when updating records.";
    }
}
