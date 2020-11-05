package seedu.intellibrary.logic.parser;

import static seedu.intellibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

<<<<<<< HEAD:src/main/java/seedu/intellibrary/logic/parser/DeleteProblemCommandParser.java
import seedu.intellibrary.commons.core.index.Index;
import seedu.intellibrary.logic.commands.DeleteCommand;
import seedu.intellibrary.logic.commands.DeleteProblemCommand;
import seedu.intellibrary.logic.parser.exceptions.ParseException;
=======
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteProblemCommand;
import seedu.address.logic.parser.exceptions.ParseException;
>>>>>>> 9c21e3c244132f3455a882c26f163f7013255bf9:src/main/java/seedu/address/logic/parser/DeleteProblemCommandParser.java


public class DeleteProblemCommandParser implements Parser<DeleteProblemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteProblemCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteProblemCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProblemCommand.MESSAGE_USAGE), pe);
        }
    }
}
