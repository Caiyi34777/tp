package seedu.intellibrary.model;

import java.nio.file.Path;

import seedu.intellibrary.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getLibraryFilePath();

}
