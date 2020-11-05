package seedu.intellibrary.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intellibrary.logic.commands.CommandTestUtil.VALID_TIMES_AMY;
import static seedu.intellibrary.logic.commands.CommandTestUtil.VALID_TIMES_BOB;
import static seedu.intellibrary.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.intellibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intellibrary.logic.commands.CommandTestUtil.showBookAtIndex;
import static seedu.intellibrary.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.intellibrary.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.intellibrary.testutil.TypicalIndexes.INDEX_SECOND_BOOK;

import org.junit.jupiter.api.Test;

import seedu.intellibrary.commons.core.Messages;
import seedu.intellibrary.commons.core.index.Index;
import seedu.intellibrary.model.Library;
import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.ModelManager;
import seedu.intellibrary.model.UserPrefs;
import seedu.intellibrary.model.book.Book;
import seedu.intellibrary.model.book.Times;
import seedu.intellibrary.testutil.BookBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for TimesCommand.
 */
public class TimesCommandTest {

    private static final String TIMES_STUB = "12345";

    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Book firstBook = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        Book editedBook = new BookBuilder(firstBook).withTimes(TIMES_STUB).build();

        TimesCommand remarkCommand = new TimesCommand(INDEX_FIRST_BOOK, new Times(editedBook.getTimes().value));

        String expectedMessage = String.format(TimesCommand.MESSAGE_ADD_TIMES_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(firstBook, editedBook);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);

        Book firstBook = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        Book editedBook = new BookBuilder(model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased()))
                .withTimes(TIMES_STUB).build();

        TimesCommand remarkCommand = new TimesCommand(INDEX_FIRST_BOOK, new Times(editedBook.getTimes().value));

        String expectedMessage = String.format(TimesCommand.MESSAGE_ADD_TIMES_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(firstBook, editedBook);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidBookIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        TimesCommand remarkCommand = new TimesCommand(outOfBoundIndex, new Times(VALID_TIMES_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidBookIndexFilteredList_failure() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);
        Index outOfBoundIndex = INDEX_SECOND_BOOK;
        // ensures that outOfBoundIndex is still in bounds of library list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLibrary().getBookList().size());

        TimesCommand remarkCommand = new TimesCommand(outOfBoundIndex, new Times(VALID_TIMES_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final TimesCommand standardCommand = new TimesCommand(INDEX_FIRST_BOOK, new Times(VALID_TIMES_AMY));

        // same values -> returns true
        TimesCommand commandWithSameValues = new TimesCommand(INDEX_FIRST_BOOK, new Times (VALID_TIMES_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TimesCommand(INDEX_SECOND_BOOK, new Times(VALID_TIMES_AMY))));

        // different times -> returns false
        assertFalse(standardCommand.equals(new TimesCommand(INDEX_FIRST_BOOK, new Times(VALID_TIMES_BOB))));
    }
}
