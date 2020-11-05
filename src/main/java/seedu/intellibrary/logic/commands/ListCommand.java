package seedu.intellibrary.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intellibrary.model.Model.PREDICATE_SHOW_ALL_BOOKS;

import seedu.intellibrary.model.Model;
import seedu.intellibrary.ui.Mode;

/**
 * Lists all books in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all books";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredBookList((book -> false), Mode.NORMAL);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS, Mode.NORMAL);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
