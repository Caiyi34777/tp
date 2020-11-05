package seedu.intellibrary.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intellibrary.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.intellibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intellibrary.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.intellibrary.testutil.TypicalIndexes.INDEX_FIRST_BOOK;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.intellibrary.commons.core.Messages;
import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.ModelManager;
import seedu.intellibrary.model.UserPrefs;
import seedu.intellibrary.model.book.Book;



/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteByCommandTest {

    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void execute_validBookToBeDeleted_success() {
        Book bookToDelete = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        String name1 = bookToDelete.getName().fullName;
        List<Book> list = new ArrayList<>();
        list.add(bookToDelete);
        DeleteByCommand deleteByCommand = new DeleteByCommand(name1, 0);

        String expectedMessage = String.format(DeleteByCommand.MESSAGE_DELETE_BOOK_SUCCESS, list.toString());

        ModelManager expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
        expectedModel.deleteBook(bookToDelete);

        assertCommandSuccess(deleteByCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidBookName_throwsCommandException() {
        String bookName = " ";

        DeleteByCommand deleteByCommand = new DeleteByCommand(bookName, 0);

        assertCommandFailure(deleteByCommand, model, Messages.MESSAGE_INVALID_BOOK_DELETE_NAME);
    }

    @Test
    public void equals() {
        DeleteByCommand deleteFirstCommand = new DeleteByCommand("TEST", 0);
        DeleteByCommand deleteSecondCommand = new DeleteByCommand("TEST2", 0);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteByCommand deleteFirstCommandCopy = new DeleteByCommand("TEST", 0);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
