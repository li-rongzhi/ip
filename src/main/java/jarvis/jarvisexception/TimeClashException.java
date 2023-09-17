package jarvis.jarvisexception;

/**
 * TimeClashException is a type of JarvisException.
 * It indicates that an Event being added clashes with another Event in the list.
 *
 * @author Rongzhi
 */
public class TimeClashException extends JarvisException {
    @Override
    public String getMessage() {
        return super.getMessage() + "There is another event clashes with the new event.";
    }
}
