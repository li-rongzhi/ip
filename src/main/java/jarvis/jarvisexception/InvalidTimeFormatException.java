package jarvis.jarvisexception;

import java.time.format.DateTimeFormatter;

/**
 * InvalidTimeFormatException is a type of JarvisException.
 * It indicates that the input time format is invalid.
 *
 * @author Rongzhi
 */
public class InvalidTimeFormatException extends JarvisException {
    private final DateTimeFormatter formatter;

    /**
     * Create an InvalidTimeFormatException.
     *
     * @param formatter The formatter which is supposed to be satisfied.
     */
    public InvalidTimeFormatException(DateTimeFormatter formatter) {
        super();
        this.formatter = formatter;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Sir, the time you provided is in an invalid format. "
                + String.format("The time should be in '%s' format", this.formatter);
    }
}
