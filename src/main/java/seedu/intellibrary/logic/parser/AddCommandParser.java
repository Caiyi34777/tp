package seedu.intellibrary.logic.parser;

import static seedu.intellibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_PUBLISHER;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_STOCKING;
import static seedu.intellibrary.logic.parser.CliSyntax.PREFIX_TIMES;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

import seedu.intellibrary.logic.commands.AddCommand;
import seedu.intellibrary.logic.parser.exceptions.ParseException;
import seedu.intellibrary.model.book.Address;
import seedu.intellibrary.model.book.Author;
import seedu.intellibrary.model.book.Book;
import seedu.intellibrary.model.book.Email;
import seedu.intellibrary.model.book.Isbn;
import seedu.intellibrary.model.book.Name;
import seedu.intellibrary.model.book.Publisher;
import seedu.intellibrary.model.book.Stocking;
import seedu.intellibrary.model.book.Times;
import seedu.intellibrary.model.category.Category;
import seedu.intellibrary.model.review.Review;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ISBN, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_CATEGORY, PREFIX_STOCKING, PREFIX_TIMES, PREFIX_AUTHOR, PREFIX_PUBLISHER);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ISBN, PREFIX_ADDRESS, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Isbn isbn = ParserUtil.parseIsbn(argMultimap.getValue(PREFIX_ISBN).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Times times = ParserUtil.parseTimes(argMultimap.getValue(PREFIX_TIMES).get());
        Set<Category> categoryList = ParserUtil.parseCategories(argMultimap.getAllValues(PREFIX_CATEGORY));
        Stocking stocking = ParserUtil.parseStocking(argMultimap.getValue(PREFIX_STOCKING).get());
        Author author = ParserUtil.parseAuthor(argMultimap.getValue(PREFIX_AUTHOR).get()); // to be implemented
        Publisher publisher = ParserUtil.parsePublisher(argMultimap.getValue(PREFIX_PUBLISHER).get());

        Book book = new Book(name, isbn, email, address, times, categoryList, stocking, new ArrayList<Review>(),
                author, publisher);

        return new AddCommand(book);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
