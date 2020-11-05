package seedu.intellibrary.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intellibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_REVIEWNUMBER;

import seedu.intellibrary.commons.core.Messages;
import seedu.intellibrary.commons.core.index.Index;
import seedu.intellibrary.logic.commands.DeleteReviewCommand;
import seedu.intellibrary.logic.parser.exceptions.ParseException;
import seedu.intellibrary.model.review.ReviewNumber;

/**
 * Parses input arguments and creates a new DeleteReviewCommand object
 */
public class DeleteReviewCommandParser implements Parser<DeleteReviewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteReviewCommand
     * and returns a DeleteReviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteReviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REVIEWNUMBER);

        Index index;

        if (!argMultimap.getValue(PREFIX_REVIEWNUMBER).isPresent()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteReviewCommand.MESSAGE_USAGE));
        }
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteReviewCommand.MESSAGE_USAGE), pe);
        }

        ReviewNumber reviewNumber = ParserUtil.parseReviewNumber(argMultimap.getValue(PREFIX_REVIEWNUMBER).get());

        return new DeleteReviewCommand(index, reviewNumber);
    }
}
