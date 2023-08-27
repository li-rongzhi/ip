package Jarvis.jarvisexception;

public class RecordLoadingException extends JarvisException {
    @Override
    public String toString() {
        return super.toString() + "Error occur when loading records.";
    }

}
