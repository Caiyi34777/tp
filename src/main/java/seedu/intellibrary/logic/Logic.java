package seedu.intellibrary.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.intellibrary.commons.core.GuiSettings;
import seedu.intellibrary.logic.commands.CommandResult;
import seedu.intellibrary.logic.commands.exceptions.CommandException;
import seedu.intellibrary.logic.parser.exceptions.ParseException;
import seedu.intellibrary.model.ReadOnlyLibrary;
import seedu.intellibrary.model.book.Book;
import seedu.intellibrary.model.problem.Problem;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     * @throws IOException
     */
    CommandResult execute(String commandText) throws CommandException, ParseException, IOException;

    /**
     * Returns the Library.
     *
     * @see seedu.intellibrary.model.Model#getLibrary()
     */
    ReadOnlyLibrary getLibrary();

    /** Returns an unmodifiable view of the filtered list of books */
    ObservableList<Book> getFilteredBookList();

    ObservableList<Problem> getFilteredProblemReportList();

    /**
     * Returns the user prefs' library file path.
     */
    Path getLibraryFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Gets smart suggestions from user input.
     */
    List<String> getSuggestions();
}
