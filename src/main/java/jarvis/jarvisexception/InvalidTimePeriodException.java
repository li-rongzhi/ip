package jarvis.jarvisexception;

/**
 * InvalidTimePeriodException is a type of JarvisException.
 * It indicates that the start time of the event is later than the end time.
 * @author Rongzhi
 */
public class InvalidTimePeriodException extends JarvisException {

    @Override
    public String getMessage() {
        return super.getMessage() + "Your input start date/time is later than end/time.";
    }
}

