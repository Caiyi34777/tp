package seedu.intellibrary.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intellibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_REVIEW;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_REVIEWNUMBER;

import java.util.Optional;

import seedu.intellibrary.commons.core.Messages;
import seedu.intellibrary.commons.core.index.Index;
import seedu.intellibrary.logic.commands.AddReviewCommand;
import seedu.intellibrary.logic.commands.EditReviewCommand;
import seedu.intellibrary.logic.parser.exceptions.ParseException;
import seedu.intellibrary.model.review.Rating;
import seedu.intellibrary.model.review.ReviewContent;
import seedu.intellibrary.model.review.ReviewNumber;

/**
 * Parses input arguments and creates a new EditReviewCommand object
 */
public class EditReviewCommandParser implements Parser<EditReviewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddReviewCommand
     * and returns a EditReviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditReviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REVIEWNUMBER,
                PREFIX_RATING, PREFIX_REVIEW);
        Index index;

        if (!argMultimap.getValue(PREFIX_REVIEWNUMBER).isPresent()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditReviewCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getValue(PREFIX_RATING).isPresent() && !argMultimap.getValue(PREFIX_REVIEW).isPresent()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_INVALID_EDIT_REVIEW));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReviewCommand.MESSAGE_USAGE), pe);
        }
        Rating rating = null;
        ReviewContent reviewContent = null;
        ReviewNumber reviewNumber = ParserUtil.parseReviewNumber(argMultimap.getValue(PREFIX_REVIEWNUMBER).get());

        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get());
        }

        if (argMultimap.getValue(PREFIX_REVIEW).isPresent()) {
            reviewContent = ParserUtil.parseReviewContent(argMultimap.getValue(PREFIX_REVIEW).get());
        }

        return new EditReviewCommand(index, reviewNumber, Optional.ofNullable(rating),
                Optional.ofNullable(reviewContent));
    }
}
