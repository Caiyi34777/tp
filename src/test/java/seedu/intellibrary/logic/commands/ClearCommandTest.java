package seedu.intellibrary.logic.commands;

import static seedu.intellibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intellibrary.testutil.TypicalBooks.getTypicalLibrary;

import org.junit.jupiter.api.Test;

import seedu.intellibrary.model.Library;
import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.ModelManager;
import seedu.intellibrary.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyLibrary_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyLibrary_success() {
        Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalLibrary(), new UserPrefs());
        expectedModel.setLibrary(new Library());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
