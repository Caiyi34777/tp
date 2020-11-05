package seedu.intellibrary.logic.parser;

import static seedu.intellibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

<<<<<<< HEAD:src/main/java/seedu/intellibrary/logic/parser/FindProblemReportCommandParser.java
import seedu.intellibrary.logic.commands.FindProblemReportCommand;
import seedu.intellibrary.logic.parser.exceptions.ParseException;
import seedu.intellibrary.model.problem.DescriptionContainsKeywordsPredicate;
=======
import seedu.address.logic.commands.FindProblemCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.problem.DescriptionContainsKeywordsPredicate;
>>>>>>> 9c21e3c244132f3455a882c26f163f7013255bf9:src/main/java/seedu/intellibrary/logic/parser/FindProblemCommandParser.java




public class FindProblemCommandParser implements Parser<FindProblemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindProblemCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProblemCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindProblemCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
