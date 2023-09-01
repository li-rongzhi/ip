package jarvis.jarvisexception;

/**
 * RecordUpdateException is a type of JarvisException.
 * It indicates that an error occurs when updating records.
 * @author Rongzhi
 */
public class RecordUpdateException extends JarvisException {
    @Override
    public String toString() {
        return super.toString() + "Error occur when updating records.";
    }
}
