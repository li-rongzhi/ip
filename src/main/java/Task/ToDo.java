package Task;

import Task.Task;

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
