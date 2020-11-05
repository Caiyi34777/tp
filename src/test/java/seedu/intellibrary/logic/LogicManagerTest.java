package seedu.intellibrary.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.intellibrary.commons.core.Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX;
import static seedu.intellibrary.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.intellibrary.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.intellibrary.logic.commands.CommandTestUtil.AUTHOR_DESC_AMY;
import static seedu.intellibrary.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.intellibrary.logic.commands.CommandTestUtil.ISBN_DESC_AMY;
import static seedu.intellibrary.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.intellibrary.logic.commands.CommandTestUtil.PUBLISHER_DESC_AMY;
import static seedu.intellibrary.logic.commands.CommandTestUtil.STOCKING_DESC_AMY;
import static seedu.intellibrary.logic.commands.CommandTestUtil.TIMES_DESC_AMY;
import static seedu.intellibrary.testutil.Assert.assertThrows;
import static seedu.intellibrary.testutil.TypicalBooks.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.intellibrary.logic.commands.AddCommand;
import seedu.intellibrary.logic.commands.CommandResult;
import seedu.intellibrary.logic.commands.ListCommand;
import seedu.intellibrary.logic.commands.exceptions.CommandException;
import seedu.intellibrary.logic.parser.exceptions.ParseException;
import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.ModelManager;
import seedu.intellibrary.model.ReadOnlyLibrary;
import seedu.intellibrary.model.UserPrefs;
import seedu.intellibrary.model.book.Book;
import seedu.intellibrary.storage.JsonLibraryStorage;
import seedu.intellibrary.storage.JsonUserPrefsStorage;
import seedu.intellibrary.storage.StorageManager;
import seedu.intellibrary.testutil.BookBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonLibraryStorage libraryStorage =
                new JsonLibraryStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(libraryStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonLibraryIoExceptionThrowingStub
        JsonLibraryStorage addressBookStorage =
                new JsonLibraryIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionLibrary.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + ISBN_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + STOCKING_DESC_AMY + TIMES_DESC_AMY + AUTHOR_DESC_AMY + PUBLISHER_DESC_AMY;
        Book expectedBook = new BookBuilder(AMY).withCategories().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addBook(expectedBook);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredBookList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredBookList().remove(0));
    }

    /**
     * Executes the command and confirms that - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in
     * {@code expectedModel} <br>
     * @throws IOException
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage, Model expectedModel)
            throws CommandException, ParseException, IOException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonLibraryIoExceptionThrowingStub extends JsonLibraryStorage {
        private JsonLibraryIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveLibrary(ReadOnlyLibrary library, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
