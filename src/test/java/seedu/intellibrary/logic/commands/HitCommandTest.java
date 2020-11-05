package seedu.intellibrary.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intellibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intellibrary.logic.commands.HitCommand.SHOWING_HIT_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.intellibrary.model.Model;
import seedu.intellibrary.model.ModelManager;

public class HitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_hit_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HIT_MESSAGE);
        assertCommandSuccess(new HitCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals_success() {
        assertTrue(new HitCommand().equals(new HitCommand()));
    }
}
