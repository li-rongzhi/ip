package jarvis.jarvisexception;

import java.time.format.DateTimeFormatter;

public class InvalidTimeFormatException extends JarvisException {
    private DateTimeFormatter formatter;
    public InvalidTimeFormatException(DateTimeFormatter formatter) {
        super();
        this.formatter = formatter;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Sir, the time you provided is in an invalid format. " +
                String.format("The time should be in '%s' format", this.formatter);
    }
}
