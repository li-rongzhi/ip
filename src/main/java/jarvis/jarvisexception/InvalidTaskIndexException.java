package jarvis.jarvisexception;

/**
 * InvalidTaskIndexException is a type of JarvisException.
 * It indicates that the target index is invalid.
 * @author Rongzhi
 */
public class InvalidTaskIndexException extends JarvisException {
    private int index;

    /**
     * Create an InvalidTaskIndexException instance.
     * @param index the invalid index.
     */
    public InvalidTaskIndexException(int index) {
        super();
        this.index = index;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "I'm sorry, sir. Your target task is not found.";
    }
}
