package seedu.duke.userinterface.command.timetable;

import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.tasks.Task;
import seedu.duke.userinterface.AppState;
import seedu.duke.userinterface.command.CliCommand;

import java.util.ArrayList;
import java.util.Collections;

public class ListCommandTimetableMode extends CliCommand {
    public static final String COMMAND_WORD = "list";

    public ListCommandTimetableMode(String argument, AppState appState) {
        this.setAppState(appState);
        this.commandParams = argument;
    }

    @Override
    public void execute() throws InvalidCommandException {
        try {
            switch (commandParams) {
            case "/d":
                printDoneTasks();
                break;
            case "/u":
                printUndoneTasks();
                break;
            case "/urgent":
                printUrgentTasks();
                break;
            case "":
                printAllTasks();
                break;
            default:
                throw new InvalidCommandException("There not exists such options");
            }
        } catch (NullPointerException e) {
            System.out.println("The list of tasks is empty");
        }

    }

    private void printUrgentTasks() {
        ArrayList<Task> urgentTaskLists = appState.getTaskList().getTaskArrayList();
        Collections.sort(urgentTaskLists, new SortByDate());
        int i = 1;
        for (Task task : urgentTaskLists) {
            if (task.isDone() == true) {
                continue;
            }
            System.out.print(i++ + ":");
            System.out.println(task);
            if (i == 4) {
                return;
            }
        }
        if (i == 1) {
            System.out.println("The list of urgent tasks is empty");
        }
    }

    private void printAllTasks() {
        int i = 1;
        for (Task task : appState.getTaskList().getTaskArrayList()) {
            System.out.print(i++ + ":");
            System.out.println(task);
        }
        if (i == 1) {
            System.out.println("The list of tasks is empty");
        }
    }

    private void printDoneTasks() {
        int i = 1;
        for (Task task : appState.getTaskList().getTaskArrayList()) {
            if (task.isDone()) {
                System.out.print(i++ + ":");
                System.out.println(task);
            }
        }
        if (i == 1) {
            System.out.println("The list of done tasks is empty");
        }
    }

    private void printUndoneTasks() {
        int i = 1;
        for (Task task : appState.getTaskList().getTaskArrayList()) {
            if (!task.isDone()) {
                System.out.print(i++ + ":");
                System.out.println(task);
            }
        }
        assert i == (appState.getTaskList().getNumberOfTasks() + 1) : "i should be equal to number of tasks plus 1";
        if (i == 1) {
            System.out.println("The list of undone tasks is empty");
        }
    }

}
