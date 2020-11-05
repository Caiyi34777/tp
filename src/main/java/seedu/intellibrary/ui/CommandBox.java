package seedu.intellibrary.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.intellibrary.logic.commands.CommandResult;
import seedu.intellibrary.logic.commands.exceptions.CommandException;
import seedu.intellibrary.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        try {
            commandExecutor.execute(commandTextField.getText());
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@@author Caiyi34777-reused
    //Reused from https://github.com/AY2021S1-CS2103-F09-3/tp to implement auto-suggestion
    /**
     * Set autocomplete listener.
     *
     * @author AY2021S1-CS2103-F10-3
     * @param resultDisplay result display
     */
    public void setAutoCompleteListener(ResultDisplay resultDisplay) {
        // calls resultDisplay.showAutoCompleteResult() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, input) ->
                resultDisplay.showAutoCompleteResult(input));
    }
    //@@author

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.intellibrary.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws Exception;
    }

}
