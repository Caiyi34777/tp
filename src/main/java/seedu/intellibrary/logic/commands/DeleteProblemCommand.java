package seedu.intellibrary.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.intellibrary.commons.core.Messages;
import seedu.intellibrary.commons.core.index.Index;
import seedu.intellibrary.logic.commands.exceptions.CommandException;
import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.problem.Problem;




public class DeleteProblemCommand extends Command {

    public static final String COMMAND_WORD = "deleteProblemReport";
    public static final String SUGGESTION = "deleteProblemReport <keyword>";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the problems identified by the index number used in the displayed problem list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PROBLEM_SUCCESS = "Deleted problem: %1$s";

    private final Index targetIndex;

    public DeleteProblemCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Problem> lastShownList = model.getFilteredProblemList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROBLEM_DISPLAYED_INDEX);
        }

        Problem problemToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteProblem(problemToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PROBLEM_SUCCESS, problemToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteProblemCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteProblemCommand) other).targetIndex)); // state check
    }
}
