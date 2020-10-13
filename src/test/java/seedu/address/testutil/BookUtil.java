package seedu.address.testutil;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditBookDescriptor;
import seedu.address.model.book.Book;
import seedu.address.model.category.Category;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * A utility class for Book.
 */
public class BookUtil {

    /**
     * Returns an add command string for adding the {@code book}.
     */
    public static String getAddCommand(Book book) {
        return AddCommand.COMMAND_WORD + " " + getBookDetails(book);
    }

    /**
     * Returns the part of command string for the given {@code book}'s details.
     */
    public static String getBookDetails(Book book) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + book.getName().fullName + " ");
        sb.append(PREFIX_ISBN + book.getIsbn().value + " ");
        sb.append(PREFIX_EMAIL + book.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + book.getAddress().value + " ");
        book.getCategories().stream().forEach(
            s -> sb.append(PREFIX_CATEGORY + s.categoryName + " ")
        );
        sb.append(PREFIX_STOCKING + book.getStocking().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditBookDescriptor}'s details.
     */
    public static String getEditBookDescriptorDetails(EditBookDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getIsbn().ifPresent(isbn -> sb.append(PREFIX_ISBN).append(isbn.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getCategories().isPresent()) {
            Set<Category> categories = descriptor.getCategories().get();
            if (categories.isEmpty()) {
                sb.append(PREFIX_CATEGORY);
            } else {
                categories.forEach(s -> sb.append(PREFIX_CATEGORY).append(s.categoryName).append(" "));
            }
        }
        return sb.toString();
    }
}
