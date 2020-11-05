package seedu.intellibrary.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.intellibrary.commons.exceptions.DataConversionException;
import seedu.intellibrary.model.ReadOnlyLibrary;
import seedu.intellibrary.model.ReadOnlyUserPrefs;
import seedu.intellibrary.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends LibraryStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getLibraryFilePath();

    @Override
    Optional<ReadOnlyLibrary> readLibrary() throws DataConversionException, IOException;

    @Override
    void saveLibrary(ReadOnlyLibrary library) throws IOException;

}
