package seedu.intellibrary.logic.parser;

import static seedu.intellibrary.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.intellibrary.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.intellibrary.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.intellibrary.logic.commands.DeleteByCommand;


class DeleteByCommandParserTest {

    private DeleteByCommandParser parser = new DeleteByCommandParser();

    @Test
    void parse_validArgs_returnsStockCommand() {
        assertParseSuccess(parser, NAME_DESC_BOB, new DeleteByCommand(VALID_NAME_BOB, 0));
    }
}
