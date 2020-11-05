package seedu.intellibrary.testutil;

import seedu.intellibrary.model.Library;

public class EmptyList {
    /**
     * Returns an {@code Library} with no books.
     */
    public static Library getEmptyLibrary() {
        return new Library();
    }
}
