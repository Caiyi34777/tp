package seedu.intellibrary.logic.parser;

<<<<<<< HEAD:src/test/java/seedu/intellibrary/logic/parser/StockCommandParserTest.java
import static seedu.intellibrary.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.intellibrary.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.intellibrary.logic.parser.CommandParserTestUtil.assertParseSuccess;
=======
import static seedu.address.logic.commands.CommandTestUtil.ISBN_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
>>>>>>> 9c21e3c244132f3455a882c26f163f7013255bf9:src/test/java/seedu/address/logic/parser/StockCommandParserTest.java

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.intellibrary.logic.commands.StockCommand;

class StockCommandParserTest {

    private StockCommandParser stockCommandParser = new StockCommandParser();

    @Test
    void parse_validArgs_returnsStockCommand() {
        assertParseSuccess(stockCommandParser,
                NAME_DESC_AMY,
                new StockCommand(Arrays.asList(VALID_NAME_AMY.split("\\s+")), null));

        assertParseSuccess(stockCommandParser,
                ISBN_DESC_AMY,
                new StockCommand(null, Arrays.asList(VALID_ISBN_AMY.split("\\s+"))));
    }
}
