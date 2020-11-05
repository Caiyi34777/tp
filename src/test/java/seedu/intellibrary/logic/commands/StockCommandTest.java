package seedu.intellibrary.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.intellibrary.commons.core.Messages.MESSAGE_BOOKS_LISTED_OVERVIEW;
import static seedu.intellibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intellibrary.testutil.TypicalBooks.ELLE;
import static seedu.intellibrary.testutil.TypicalBooks.getTypicalLibrary;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

<<<<<<< HEAD:src/test/java/seedu/intellibrary/logic/commands/StockCommandTest.java
import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.ModelManager;
import seedu.intellibrary.model.UserPrefs;
import seedu.intellibrary.model.book.NameContainsKeywordsPredicate;
import seedu.intellibrary.ui.Mode;
=======
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.book.Book;
import seedu.address.model.book.NameMatchesKeywordPredicate;
import seedu.address.model.book.NumberContainsKeywordPredicate;
import seedu.address.ui.Mode;
>>>>>>> 9c21e3c244132f3455a882c26f163f7013255bf9:src/test/java/seedu/address/logic/commands/StockCommandTest.java

/**
 * Contains integration tests (interaction with the Model) and unit tests for StockCommand.
 */
class StockCommandTest {
    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    void execute_twoKeywords_oneBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 1);
        NameMatchesKeywordPredicate nameMatchesKeywordsPredicate =
                new NameMatchesKeywordPredicate(Arrays.asList("Elle"));
        NumberContainsKeywordPredicate numberContainsKeywordsPredicate =
                new NumberContainsKeywordPredicate(Arrays.asList("9482224"));

        Predicate<Book> predicate = (book -> nameMatchesKeywordsPredicate.test(book)
                || numberContainsKeywordsPredicate.test(book));

        expectedModel.updateFilteredBookList(predicate, Mode.NORMAL);

        StockCommand stockCommand = new StockCommand(Arrays.asList("Elle"), Arrays.asList("9482224"));
        assertCommandSuccess(stockCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredBookList());
    }

    @Test
    void execute_twoKeywords_twoBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 2);
        NameMatchesKeywordPredicate nameMatchesKeywordsPredicate =
                new NameMatchesKeywordPredicate(Arrays.asList("Elle"));
        NumberContainsKeywordPredicate numberContainsKeywordsPredicate =
                new NumberContainsKeywordPredicate(Arrays.asList("94351253"));

        Predicate<Book> predicate = (book -> nameMatchesKeywordsPredicate.test(book)
                || numberContainsKeywordsPredicate.test(book));

        expectedModel.updateFilteredBookList(predicate, Mode.NORMAL);

        StockCommand stockCommand = new StockCommand(Arrays.asList("Elle"), Arrays.asList("94351253"));
        assertCommandSuccess(stockCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_oneKeyword_oneBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 1);
        NameMatchesKeywordPredicate nameMatchesKeywordsPredicate =
                new NameMatchesKeywordPredicate(Arrays.asList("Elle"));
        expectedModel.updateFilteredBookList(nameMatchesKeywordsPredicate, Mode.NORMAL);
        StockCommand stockCommand = new StockCommand(Arrays.asList("Elle"), null);
        assertCommandSuccess(stockCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredBookList());

        NumberContainsKeywordPredicate numberContainsKeywordsPredicate =
                new NumberContainsKeywordPredicate(Arrays.asList("9482224"));
        expectedModel.updateFilteredBookList(numberContainsKeywordsPredicate, Mode.NORMAL);
        stockCommand = new StockCommand(null, Arrays.asList("9482224"));
        assertCommandSuccess(stockCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredBookList());
    }

    @Test
    void execute_noKeyWord_allBookShown() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 7);

        StockCommand stockCommand = new StockCommand(null, null);
        assertCommandSuccess(stockCommand, model, expectedMessage, expectedModel);
    }
}
