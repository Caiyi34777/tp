package seedu.intellibrary.logic.parser;

import static seedu.intellibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

<<<<<<< HEAD:src/main/java/seedu/intellibrary/logic/parser/SortCommandParser.java
import seedu.intellibrary.logic.commands.SortCommand;
import seedu.intellibrary.logic.parser.exceptions.ParseException;
=======
import seedu.address.logic.commands.FindMostPopularCommand;
import seedu.address.logic.parser.exceptions.ParseException;
>>>>>>> 9c21e3c244132f3455a882c26f163f7013255bf9:src/main/java/seedu/intellibrary/logic/parser/FindMostPopularCommandParser.java


/**
 * Parses input arguments and creates a new FindMostPopularCommand object
 */
public class FindMostPopularCommandParser implements Parser<FindMostPopularCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMostPopularCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMostPopularCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMostPopularCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindMostPopularCommand(nameKeywords[nameKeywords.length - 1]);
    }

}
