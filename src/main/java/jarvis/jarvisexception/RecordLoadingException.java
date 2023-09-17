package jarvis.jarvisexception;

/**
 * RecordLoadingException is a type of JarvisException.
 * It indicates that an error occurs when loading records.
 *
 * @author Rongzhi
 */
public class RecordLoadingException extends JarvisException {
    @Override
    public String toString() {
        return super.toString() + "Error occur when loading records.";
    }

}
