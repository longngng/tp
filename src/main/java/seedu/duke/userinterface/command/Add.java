package seedu.duke.userinterface.command;

import seedu.duke.userinterface.AppState;

public class Add extends CliCommand {
    public static final String COMMAND_WORD = "add";
    String argument;
    AppState uiMode;

    public Add(String argument, AppState uiMode) {
        this.argument = argument;
        this.uiMode = uiMode;
    }

    public void execute() {
        parser = new InputParser();
        TaskList currentTaskList = appState.getTaskList();
        try {
            if (argument.contains("/by")) {
                String title = parser.parseTaskTitle(argument);
                String deadline = parser.parseDeadline(argument);
                currentTaskList.addTask(new Task(title, deadline));
                messages.printAddedTaskMessage(currentTaskList, title);
            } else {
                throw new TaskWrongFormatException();
            }
            // TODO Add more OOP: catch exceptions in CliUserInterface, and use an inherited printErrorMessage() method
        } catch (TaskTitleException t) {
            System.out.println("\tYour task is missing a title!");
            System.out.println("\tPlease type in the format: add /tTITLE /byDEADLINE");
        } catch (ArrayIndexOutOfBoundsException | TaskWrongFormatException w) {
            System.out.println("\tPlease type in the format: add /tTITLE /byDEADLINE");
        } catch (IncorrectDeadlineFormatException d) {
            System.out.println("\tOops! Your deadline should be in this format");
            System.out.println("\tdd/MM/yyyy HHmm where time is in 24h");
        }
    }
}
