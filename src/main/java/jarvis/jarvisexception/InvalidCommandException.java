package jarvis.jarvisexception;

/**
 * InvalidCommandException is a type of JarvisException.
 * It indicates that the input command is invalid.
 *
 * @author Rongzhi
 */
public class InvalidCommandException extends JarvisException {

    @Override
    public String getMessage() {
        return super.getMessage() + "I'm sorry, sir. I am still learning "
            + "but currently I don't know what that means :-(";
    }
}
