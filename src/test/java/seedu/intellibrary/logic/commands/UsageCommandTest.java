package seedu.intellibrary.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intellibrary.commons.core.Messages.MESSAGE_USAGE_BOOK_SUCCESS;
import static seedu.intellibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intellibrary.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.intellibrary.testutil.TypicalIndexes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.ModelManager;
import seedu.intellibrary.model.UserPrefs;

public class UsageCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLibrary(), new UserPrefs());
        expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
    }

    @Test
    public void execute_firstBook_success() {
        UsageCommand usageCommand = new UsageCommand(INDEX_FIRST_BOOK);
        assertCommandSuccess(usageCommand, model, String.format(MESSAGE_USAGE_BOOK_SUCCESS, 12), expectedModel);
    }

    @Test
    public void execute_secondBook_success() {
        UsageCommand usageCommand = new UsageCommand(INDEX_SECOND_BOOK);
        assertCommandSuccess(usageCommand, model, String.format(MESSAGE_USAGE_BOOK_SUCCESS, 20), expectedModel);
    }

    @Test
    public void execute_thirdBook_success() {
        UsageCommand usageCommand = new UsageCommand(INDEX_THIRD_BOOK);
        assertCommandSuccess(usageCommand, model, String.format(MESSAGE_USAGE_BOOK_SUCCESS, 20), expectedModel);
    }

    @Test
    public void equals_sameObject_success() {
        UsageCommand usageCommand = new UsageCommand(INDEX_FIRST_BOOK);
        assertTrue(usageCommand.equals(usageCommand));
    }

    @Test
    public void equals_sameIndex_success() {
        UsageCommand usageCommand = new UsageCommand(INDEX_FIRST_BOOK);
        UsageCommand other = new UsageCommand(INDEX_FIRST_BOOK);
        assertTrue(usageCommand.equals(other));
    }

    @Test
    public void equals_failure() {
        UsageCommand usageCommand = new UsageCommand(INDEX_FIRST_BOOK);
        UsageCommand other = new UsageCommand(INDEX_SECOND_BOOK);
        assertFalse(usageCommand.equals(other));
    }


}
