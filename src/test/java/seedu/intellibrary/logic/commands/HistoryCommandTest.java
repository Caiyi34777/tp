package seedu.intellibrary.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.intellibrary.commons.core.Messages.MESSAGE_BORROWING_TIMES_HISTORY;
import static seedu.intellibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intellibrary.testutil.EmptyList.getEmptyLibrary;
import static seedu.intellibrary.testutil.TypicalBooks.getTypicalLibrary;

import org.junit.jupiter.api.Test;

import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.ModelManager;
import seedu.intellibrary.model.UserPrefs;


class HistoryCommandTest {

    private Model model;
    private Model expectedModel;

    @Test
    void execute_listOfBooks_success() {
        model = new ModelManager(getTypicalLibrary(), new UserPrefs());
        expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
        HistoryCommand historyCommand = new HistoryCommand();
        assertCommandSuccess(historyCommand, model, String.format(MESSAGE_BORROWING_TIMES_HISTORY, 132), expectedModel);
    }

    @Test
    void execute_emptyList_success() {
        model = new ModelManager(getEmptyLibrary(), new UserPrefs());
        expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
        HistoryCommand historyCommand = new HistoryCommand();
        assertCommandSuccess(historyCommand, model, String.format(MESSAGE_BORROWING_TIMES_HISTORY, 0), expectedModel);
    }

    @Test
    public void equals_sameType_success() {
        assertTrue(new HistoryCommand().equals(new HistoryCommand()));
    }

    @Test
    public void equals_sameObject_success() {
        HistoryCommand historyCommand = new HistoryCommand();
        assertTrue(historyCommand.equals(historyCommand));
    }

    @Test
    public void equals_failure() {
        assertFalse(new HistoryCommand().equals(1));
    }
}
