package seedu.intellibrary.logic.parser;

import static seedu.intellibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.intellibrary.commons.core.Messages;
import seedu.intellibrary.commons.core.index.Index;
import seedu.intellibrary.logic.commands.UsageCommand;
import seedu.intellibrary.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UsageCommand object
 */
public class UsageCommandParser implements Parser<UsageCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UsageCommand
     * and returns a UsageCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UsageCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UsageCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, Messages.MESSAGE_USAGE), pe);
        }
    }

}
