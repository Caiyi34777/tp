package seedu.intellibrary.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intellibrary.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.intellibrary.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static seedu.intellibrary.testutil.Assert.assertThrows;
import static seedu.intellibrary.testutil.TypicalBooks.ALICE;
import static seedu.intellibrary.testutil.TypicalBooks.getTypicalLibrary;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.intellibrary.model.book.Book;
import seedu.intellibrary.model.problem.Problem;
import seedu.intellibrary.testutil.BookBuilder;

public class LibraryTest {

    private final Library library = new Library();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), library.getBookList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> library.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyLibrary_replacesData() {
        Library newData = getTypicalLibrary();
        library.resetData(newData);
        assertEquals(newData, library);
    }


    @Test
    public void hasBook_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> library.hasBook(null));
    }

    @Test
    public void hasBook_bookNotInLibrary_returnsFalse() {
        assertFalse(library.hasBook(ALICE));
    }

    @Test
    public void hasBook_bookInLibrary_returnsTrue() {
        library.addBook(ALICE);
        assertTrue(library.hasBook(ALICE));
    }

    @Test
    public void hasBook_bookWithSameIdentityFieldsInLibrary_returnsTrue() {
        library.addBook(ALICE);
        Book editedAlice = new BookBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(library.hasBook(editedAlice));
    }

    @Test
    public void getBookList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> library.getBookList().remove(0));
    }

    /**
     * A stub ReadOnlyLibrary whose books list can violate interface constraints.
     */
    private static class LibraryStub implements ReadOnlyLibrary {
        private final ObservableList<Book> books = FXCollections.observableArrayList();

        LibraryStub(Collection<Book> books) {
            this.books.setAll(books);
        }

        @Override
        public ObservableList<Book> getBookList() {
            return books;
        }

        @Override
        public ObservableList<Problem> getProblemList() {
            return getProblemList();
        }
    }

}
