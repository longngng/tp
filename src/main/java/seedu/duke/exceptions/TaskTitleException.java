package seedu.duke.exceptions;

//TODO create a ZeroNoteException class that all the regular exceptions will inherit from.
/**
 * Signals that the user's input is missing a title.
 */
public class TaskTitleException extends ZeroNoteException {
    public TaskTitleException() {
        super("You my friend is missing a title");
    }
}
