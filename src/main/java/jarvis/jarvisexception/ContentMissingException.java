package jarvis.jarvisexception;

/**
 * ContentMissingException is a type of JarvisException.
 * It indicates that content is missing in the user input.
 * @author Rongzhi
 */
public class ContentMissingException extends JarvisException {
    private String type;

    /**
     * Create a ContentMissingException instance.
     * @param type the type of the task
     */
    public ContentMissingException(String type) {
        super();
        this.type = type;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "I'm sorry, sir. The content of a " + this.type + " cannot be empty.";
    }
}
