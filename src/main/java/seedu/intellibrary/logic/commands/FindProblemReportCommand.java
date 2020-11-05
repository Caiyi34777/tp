package seedu.intellibrary.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.intellibrary.commons.core.Messages;
import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.problem.DescriptionContainsKeywordsPredicate;
import seedu.intellibrary.ui.Mode;


public class FindProblemCommand extends Command {

    public static final String COMMAND_WORD = "findProblemReport";
    public static final String SUGGESTION = "findProblemReport <keywords>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all problem reports whose description contain "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " table";

    private final DescriptionContainsKeywordsPredicate predicate;

    public FindProblemCommand(DescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredProblemList(predicate, Mode.NORMAL);
        return new CommandResult(
                String.format(Messages.MESSAGE_REPORT_LISTED_OVERVIEW, model.getFilteredProblemList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindProblemCommand // instanceof handles nulls
                && predicate.equals(((FindProblemCommand) other).predicate)); // state check
    }
}
