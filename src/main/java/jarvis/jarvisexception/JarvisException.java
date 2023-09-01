package jarvis.jarvisexception;

/**
 * A general version of exceptions from Jarvis.
 * @author Rongzhi
 */
public class JarvisException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! ";
    }
}
