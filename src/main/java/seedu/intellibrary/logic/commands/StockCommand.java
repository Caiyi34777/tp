package seedu.intellibrary.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

<<<<<<< HEAD:src/main/java/seedu/intellibrary/logic/commands/StockCommand.java
import seedu.intellibrary.commons.core.Messages;
import seedu.intellibrary.logic.commands.exceptions.CommandException;
import seedu.intellibrary.logic.parser.CliSyntax;
import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.book.Book;
import seedu.intellibrary.model.book.NameMatchesKeywordPredicate;
import seedu.intellibrary.model.book.NumberContainsKeywordPredicate;
import seedu.intellibrary.ui.Mode;
=======
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.book.NameMatchesKeywordPredicate;
import seedu.address.model.book.NumberContainsKeywordPredicate;
import seedu.address.ui.Mode;
>>>>>>> 9c21e3c244132f3455a882c26f163f7013255bf9:src/main/java/seedu/address/logic/commands/StockCommand.java

/**
 * Searches for the stocking of the corresponding book.
 */
public class StockCommand extends Command {

    public static final String COMMAND_WORD = "stock";
    public static final String SUGGESTION = "stock n/<book name>\n" + "stock i/<isbn>\n"
            + "stock n/<book name> stock i/<isbn>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Search for the stocking of all the books with"
            + "the corresponding keyword and shows them as a list.\n"
            + "Parameters: [" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_ISBN + "ISBN]\n"
            + "Example: " + COMMAND_WORD + " " + CliSyntax.PREFIX_NAME + "a brief history of time";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private Predicate<Book> predicate;

    /**
     * Creates a StockCommand to search for the stocking information in each location.
     *
     * @param names The list of names that are used as keyword.
     * @param numbers The list of numbers that are used as keyword.
     */
    public StockCommand(List<String> names, List<String> numbers) {
        NameMatchesKeywordPredicate nameMatchesKeywordsPredicate;
        NumberContainsKeywordPredicate numberContainsKeywordPredicate;
        if (names != null && !names.get(0).equals("") && numbers != null) {
            nameMatchesKeywordsPredicate = new NameMatchesKeywordPredicate(names);
            numberContainsKeywordPredicate = new NumberContainsKeywordPredicate(numbers);
            predicate = (book -> nameMatchesKeywordsPredicate.test(book)
                    || numberContainsKeywordPredicate.test(book));
        } else if (names != null && !names.get(0).equals("")) {
            predicate = new NameMatchesKeywordPredicate(names);
        } else if (numbers != null) {
            predicate = new NumberContainsKeywordPredicate(numbers);
        } else {
            predicate = Model.PREDICATE_SHOW_ALL_BOOKS;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredBookList((book -> false), Mode.NORMAL);
        model.updateFilteredBookList(predicate, Mode.DETAIL);
        logger.info("Show the stocking information in stocking book card");
        return new CommandResult(String.format(Messages.MESSAGE_BOOKS_LISTED_OVERVIEW,
                model.getFilteredBookList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof StockCommand
                    && this.predicate.equals(((StockCommand) other).predicate));
    }
}
