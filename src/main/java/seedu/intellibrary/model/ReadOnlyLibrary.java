package seedu.intellibrary.model;

import javafx.collections.ObservableList;
import seedu.intellibrary.model.book.Book;
import seedu.intellibrary.model.problem.Problem;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyLibrary {

    /**
     * Returns an unmodifiable view of the books list.
     * This list will not contain any duplicate books.
     */
    ObservableList<Book> getBookList();
    ObservableList<Problem> getProblemList();


}
