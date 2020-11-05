package seedu.intellibrary.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
import seedu.intellibrary.model.review.Rating;
import seedu.intellibrary.model.review.Review;
import seedu.intellibrary.model.review.ReviewContent;
import seedu.intellibrary.model.review.ReviewNumber;
import seedu.intellibrary.ui.Mode;

public class EditReviewCommand extends Command {

    public static final String COMMAND_WORD = "editReview";
    public static final String SUGGESTION = "editReview <index> rn/<review number> ra/<rating> re/<review content>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit the review to the book at "
            + "the corresponding position in the list, where the rating is an integer between 0 and 5.\n"
            + "Parameters: "
            + "INDEX "
            + "[" + CliSyntax.PREFIX_REVIEWNUMBER + "REVIEW_NUMBER] "
            + "[" + CliSyntax.PREFIX_RATING + "RATING] "
            + "[" + CliSyntax.PREFIX_REVIEW + "REVIEW_CONTENT]\n"
            + "Example: " + COMMAND_WORD + " 1 " + CliSyntax.PREFIX_REVIEWNUMBER + "1" + " "
            + CliSyntax.PREFIX_RATING + "5" + " " + CliSyntax.PREFIX_REVIEW
            + "The book is interesting";

    public static final String MESSAGE_EDIT_REVIEW_SUCCESS = "The review has been edited for the book %1$s";

    private final Index index;
    private final Optional<Rating> rating;
    private final Optional<ReviewContent> reviewContent;
    private final int reviewNumber;

    /**
     * Creates a add review command to add the review of the corresponding book.
     *
     * @param index The index of the book in the list.
     * @param reviewNumber The reviewNumber of the review to edit.
     * @param rating The new rating.
     * @param reviewContent The new reviewContent.
     */
    public EditReviewCommand(Index index, ReviewNumber reviewNumber,
                             Optional<Rating> rating, Optional<ReviewContent> reviewContent) {
        requireNonNull(index);
        requireNonNull(reviewNumber);

        this.index = index;
        this.rating = rating;
        this.reviewContent = reviewContent;
        this.reviewNumber = reviewNumber.reviewNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            requireNonNull(model);
            List<Book> lastShownList = model.getFilteredBookList();
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX_IN_REVIEW);
            }
            Book bookToReview = lastShownList.get(index.getZeroBased());

            if (bookToReview.getReviews().size() < reviewNumber) {
                throw new CommandException(Messages.MESSAGE_INVALID_REVIEW_DISPLAYED_INDEX);
            }
            Book reviewedBook = createdChangedBook(bookToReview, reviewNumber, rating, reviewContent);
            model.setBook(bookToReview, reviewedBook);
            List<String> keywords = new ArrayList<>(Arrays.asList((reviewedBook.getName().fullName).split(" ")));
            NameMatchesKeywordPredicate nameMacthedKeywordsPredicate = new NameMatchesKeywordPredicate(keywords);
            model.updateFilteredBookList(nameMacthedKeywordsPredicate, Mode.REVIEW);

            return new CommandResult(String.format(MESSAGE_EDIT_REVIEW_SUCCESS, reviewedBook));
        } catch (CommandException commandException) {
            throw commandException;
        } catch (Exception exception) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX_IN_REVIEW);
        }
    }

    /**
     * Creates the book with the new review.
     *
     * @param book The book to edit.
     * @param reviewNumber The review number of the review to edit.
     * @param rating The new rating.
     * @param reviewContent The new review content
     * @return The book with the new review.
     * @throws CommandException if the review is not changed
     */
    private static Book createdChangedBook(Book book, int reviewNumber,
                                           Optional<Rating> rating,
                                           Optional<ReviewContent> reviewContent) throws CommandException {
        assert book != null;
        if (!rating.isPresent() && !reviewContent.isPresent()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EDIT_REVIEW);
        }
        Review originalReview = book.getReviews().get(reviewNumber - 1);
        Rating newRating = rating.orElse(originalReview.getRating());
        ReviewContent newReviewContent = reviewContent.orElse(originalReview.getContent());
        Review newReview = new Review(newRating, newReviewContent);
        if (newReview.equals(originalReview)) {
            throw new CommandException(Messages.MESSAGE_REVIEW_NOT_EDITED);
        }
        Name name = book.getName();
        Isbn isbn = book.getIsbn();
        Email email = book.getEmail();
        Address address = book.getAddress();
        List<Review> reviews = book.getReviews();
        reviews.set(reviewNumber - 1, newReview);
        Times times = book.getTimes();
        Set<Category> categories = book.getCategories();
        Author author = book.getAuthor();
        Publisher publisher = book.getPublisher();
        Stocking stocking = book.getStocking();

        return new Book(name, isbn, email, address, times, categories, stocking, reviews, author, publisher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EditReviewCommand)) {
            return false;
        }
        EditReviewCommand other = (EditReviewCommand) o;
        return reviewNumber == other.reviewNumber
                && index.equals(other.index)
                && rating.equals(other.rating)
                && reviewContent.equals(other.reviewContent);
    }
}
