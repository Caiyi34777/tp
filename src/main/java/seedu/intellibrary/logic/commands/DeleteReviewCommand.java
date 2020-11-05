package seedu.intellibrary.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.intellibrary.commons.core.Messages;
import seedu.intellibrary.commons.core.index.Index;
import seedu.intellibrary.logic.commands.exceptions.CommandException;
import seedu.intellibrary.logic.parser.CliSyntax;
import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.book.Address;
import seedu.intellibrary.model.book.Author;
import seedu.intellibrary.model.book.Book;
import seedu.intellibrary.model.book.Email;
import seedu.intellibrary.model.book.Isbn;
import seedu.intellibrary.model.book.Name;
import seedu.intellibrary.model.book.NameMatchesKeywordPredicate;
import seedu.intellibrary.model.book.Publisher;
import seedu.intellibrary.model.book.Stocking;
import seedu.intellibrary.model.book.Times;
import seedu.intellibrary.model.category.Category;
import seedu.intellibrary.model.review.Review;
import seedu.intellibrary.model.review.ReviewNumber;
import seedu.intellibrary.ui.Mode;

/**
 * Deletes the review of the corresponding book.
 */
public class DeleteReviewCommand extends Command {

    public static final String COMMAND_WORD = "deleteReview";
    public static final String SUGGESTION = "deleteReview <index> rn/<review number>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete the review to the book at "
            + "the corresponding position in the list.\n"
            + "Parameters: "
            + "INDEX "
            + "[" + CliSyntax.PREFIX_REVIEWNUMBER + "REVIEW_INDEX]\n"
            + "Example: " + COMMAND_WORD + " 1 " + CliSyntax.PREFIX_REVIEWNUMBER + "5";

    public static final String MESSAGE_DELETE_REVIEW_SUCCESS = "The review has been deleted for the book %1$s";

    private final Index bookIndex;
    private final int reviewIndex;

    /**
     * Creates a delete review command to delete the review of the corresponding book.
     *
     * @param bookIndex The index of the book in the list.
     * @param reviewIndex The index of the review in the review list of the book.
     */
    public DeleteReviewCommand(Index bookIndex, ReviewNumber reviewIndex) {
        requireNonNull(bookIndex);
        requireNonNull(reviewIndex);

        this.bookIndex = bookIndex;
        this.reviewIndex = reviewIndex.reviewNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert bookIndex != null;
        assert reviewIndex >= 1;
        try {
            requireNonNull(model);
            List<Book> lastShownList = model.getFilteredBookList();

            if (bookIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX_IN_REVIEW);
            }

            Book bookToReview = lastShownList.get(bookIndex.getZeroBased());

            if (bookToReview.getReviews().size() < reviewIndex) {
                throw new CommandException(Messages.MESSAGE_INVALID_REVIEW_DISPLAYED_INDEX);
            }

            Book newBook = createdChangedBook(bookToReview, reviewIndex);

            model.setBook(bookToReview, newBook);

            List<String> keywords = new ArrayList<>(Arrays.asList((newBook.getName().fullName).split(" ")));

            NameMatchesKeywordPredicate nameMacthedKeywordsPredicate = new NameMatchesKeywordPredicate(keywords);
            model.updateFilteredBookList(nameMacthedKeywordsPredicate, Mode.REVIEW);

            return new CommandResult(String.format(MESSAGE_DELETE_REVIEW_SUCCESS, newBook));
        } catch (CommandException commandException) {
            throw commandException;
        } catch (IndexOutOfBoundsException indexOutOgBoundsException) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX_IN_REVIEW);
        }
    }

    /**
     * Creates the book with the review removed from the book.
     *
     * @param book The corresponding book.
     * @param reviewIndex The review number of the review.
     * @return The book with the new review list.
     */
    private static Book createdChangedBook(Book book, int reviewIndex) {
        assert book != null;
        assert reviewIndex >= 1;
        Name name = book.getName();
        Isbn isbn = book.getIsbn();
        Email email = book.getEmail();
        Address address = book.getAddress();
        List<Review> reviewList = book.getReviews();
        reviewList.remove(reviewIndex - 1);

        Times times = book.getTimes();
        Set<Category> categories = book.getCategories();
        Author author = book.getAuthor();
        Publisher publisher = book.getPublisher();
        Stocking stocking = book.getStocking();

        return new Book(name, isbn, email, address, times, categories, stocking, reviewList, author, publisher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof DeleteReviewCommand)) {
            return false;
        } else {
            DeleteReviewCommand other = (DeleteReviewCommand) o;
            return reviewIndex == other.reviewIndex
                    && bookIndex.equals(((DeleteReviewCommand) o).bookIndex);
        }
    }
}
