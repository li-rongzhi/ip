package jarvis.task;

/**
 * ToDo class is a subclass of Task class.
 * ToDo is a type of Task without time information.
 *
 * @author Rongzhi
 */
public class ToDo extends Task {
    public ToDo(String content) {
        super(content);
    }

    public ToDo(String content, Boolean state) {
        super(content, state);
    }

    @Override
    public String toRecord() {
        return "[T]" + super.toRecord();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
