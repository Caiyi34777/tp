package seedu.intellibrary.logic.commands;

import static seedu.intellibrary.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.intellibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intellibrary.testutil.TypicalBooks.getTypicalLibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.ModelManager;
import seedu.intellibrary.model.UserPrefs;
import seedu.intellibrary.model.book.Book;
import seedu.intellibrary.testutil.BookBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLibrary(), new UserPrefs());
    }

    @Test
    public void execute_newBook_success() {
        Book validBook = new BookBuilder().build();

        Model expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
        expectedModel.addBook(validBook);

        assertCommandSuccess(new AddCommand(validBook), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validBook), expectedModel);
    }

    @Test
    public void execute_duplicateBook_throwsCommandException() {
        Book bookInList = model.getLibrary().getBookList().get(0);
        assertCommandFailure(new AddCommand(bookInList), model, AddCommand.MESSAGE_DUPLICATE_BOOK);
    }

}
