package seedu.intellibrary.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intellibrary.model.Model.PREDICATE_SHOW_ALL_PROBLEMS;

import seedu.intellibrary.model.Model;
import seedu.intellibrary.ui.Mode;

public class ViewProblemCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "View problems";
    public static final String SUGGESTION = "view";

    public ViewProblemCommand() {

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredProblemList((problem -> false), Mode.NORMAL);
        model.updateFilteredProblemList(PREDICATE_SHOW_ALL_PROBLEMS, Mode.NORMAL);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
