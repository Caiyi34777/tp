package seedu.intellibrary.logic.parser;

import static seedu.intellibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intellibrary.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

<<<<<<< HEAD:src/main/java/seedu/intellibrary/logic/parser/LibraryParser.java
import seedu.intellibrary.logic.commands.AddCommand;
import seedu.intellibrary.logic.commands.AddProblemCommand;
import seedu.intellibrary.logic.commands.AddReviewCommand;
import seedu.intellibrary.logic.commands.ClearCommand;
import seedu.intellibrary.logic.commands.Command;
import seedu.intellibrary.logic.commands.DeleteByCommand;
import seedu.intellibrary.logic.commands.DeleteCommand;
import seedu.intellibrary.logic.commands.DeleteProblemCommand;
import seedu.intellibrary.logic.commands.DeleteReviewCommand;
import seedu.intellibrary.logic.commands.EditCommand;
import seedu.intellibrary.logic.commands.EditReviewCommand;
import seedu.intellibrary.logic.commands.ExitCommand;
import seedu.intellibrary.logic.commands.FindCommand;
import seedu.intellibrary.logic.commands.FindProblemReportCommand;
import seedu.intellibrary.logic.commands.HelpCommand;
import seedu.intellibrary.logic.commands.HistoryCommand;
import seedu.intellibrary.logic.commands.HitCommand;
import seedu.intellibrary.logic.commands.ListCommand;
import seedu.intellibrary.logic.commands.RandomCommand;
import seedu.intellibrary.logic.commands.SearchReviewCommand;
import seedu.intellibrary.logic.commands.SortCommand;
import seedu.intellibrary.logic.commands.StockCommand;
import seedu.intellibrary.logic.commands.TimesCommand;
import seedu.intellibrary.logic.commands.UsageByCommand;
import seedu.intellibrary.logic.commands.UsageCommand;
import seedu.intellibrary.logic.commands.ViewProblemCommand;
import seedu.intellibrary.logic.parser.exceptions.ParseException;
=======
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddProblemCommand;
import seedu.address.logic.commands.AddReviewCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteByCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteProblemCommand;
import seedu.address.logic.commands.DeleteReviewCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditReviewCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindMostPopularCommand;
import seedu.address.logic.commands.FindProblemCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.HitCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RandomCommand;
import seedu.address.logic.commands.SearchReviewCommand;
import seedu.address.logic.commands.StockCommand;
import seedu.address.logic.commands.TimesCommand;
import seedu.address.logic.commands.UsageByCommand;
import seedu.address.logic.commands.UsageCommand;
import seedu.address.logic.commands.ViewProblemCommand;
import seedu.address.logic.parser.exceptions.ParseException;
>>>>>>> 9c21e3c244132f3455a882c26f163f7013255bf9:src/main/java/seedu/address/logic/parser/LibraryParser.java

/**
 * Parses user input.
 */
public class LibraryParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case DeleteByCommand.COMMAND_WORD:
            return new DeleteByCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteProblemCommand.COMMAND_WORD:
            return new DeleteProblemCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindProblemCommand.COMMAND_WORD:
            return new FindProblemCommandParser().parse(arguments);

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case HitCommand.COMMAND_WORD:
            return new HitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case RandomCommand.COMMAND_WORD:
            return new RandomCommandParser().parse(arguments);

        case StockCommand.COMMAND_WORD:
            return new StockCommandParser().parse(arguments);

        case SearchReviewCommand.COMMAND_WORD:
            return new SearchReviewCommandParser().parse(arguments);

        case AddReviewCommand.COMMAND_WORD:
            return new AddReviewCommandParser().parse(arguments);

        case DeleteReviewCommand.COMMAND_WORD:
            return new DeleteReviewCommandParser().parse(arguments);

        case EditReviewCommand.COMMAND_WORD:
            return new EditReviewCommandParser().parse(arguments);

        case TimesCommand.COMMAND_WORD:
            return new TimesCommandParser().parse(arguments);

        case UsageCommand.COMMAND_WORD:
            return new UsageCommandParser().parse(arguments);

        case UsageByCommand.COMMAND_WORD:
            return new UsageByCommandParser().parse(arguments);

        case FindMostPopularCommand.COMMAND_WORD:
            return new FindMostPopularCommandParser().parse(arguments);

        case AddProblemCommand.COMMAND_WORD:
            return new AddProblemCommandParser().parse(arguments);

        case ViewProblemCommand.COMMAND_WORD:
            return new ViewProblemCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
