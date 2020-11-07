package zeronote.userinterface.command.timetable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import zeronote.exceptions.InvalidTagException;
import zeronote.exceptions.ZeroNoteException;
import zeronote.tasks.Task;
import zeronote.userinterface.AppState;
import zeronote.userinterface.command.timetable.AddCommandTimetableMode;
import zeronote.userinterface.command.timetable.FindCommandTimetableMode;

class FindTaskTest {

    @Test
    void execute_keywordInput_noneFound()  {
        AppState appState = new AppState();
        String input = "/tTask 1 /by12-10-2020 2252";
        AddCommandTimetableMode a = new AddCommandTimetableMode(input, appState);
        assertDoesNotThrow(a::execute);
        FindCommandTimetableMode f = new FindCommandTimetableMode("test", "", appState);
        f.execute();
        assertEquals(f.getTasksFound().size(), 0);
    }

    @Test
    void execute_keywordInput_taskFound() {
        AppState appState = new AppState();
        Task t = new Task("Task 1", "03-11-2020 1112");
        appState.getTaskList().addTask(t);

        FindCommandTimetableMode f = new FindCommandTimetableMode("task", "", appState);
        f.execute();
        assertEquals(f.getTasksFound().size(), 1);
    }

    @Test
    void execute_tagInput_noneFound() {
        AppState appState = new AppState();
        Task t = new Task("Task 1", "03-11-2020 1112");
        try {
            t.setTag("test");
        } catch (InvalidTagException ite) {
            ite.printErrorMessage();
        }
        appState.getTaskList().addTask(t);

        FindCommandTimetableMode f = new FindCommandTimetableMode("", "task", appState);
        f.execute();
        assertEquals(f.getTasksFound().size(), 0);
    }

    @Test
    void execute_tagInput_taskFound() {
        AppState appState = new AppState();
        Task t = new Task("Task 1", "03-11-2020 1112");
        try {
            t.setTag("test");
        } catch (InvalidTagException ite) {
            ite.printErrorMessage();
        }
        appState.getTaskList().addTask(t);

        FindCommandTimetableMode f = new FindCommandTimetableMode("", "test", appState);
        f.execute();
        assertEquals(f.getTasksFound().size(), 1);
    }

    @Test
    void isPersonalised_noInput_ReturnsTrue() {
        AppState appState = new AppState();
        FindCommandTimetableMode f = new FindCommandTimetableMode("", "", appState);
        assertTrue(f.isPersonalised());
    }
}