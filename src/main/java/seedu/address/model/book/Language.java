package seedu.address.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Book's language in the language book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLanguage(String)}
 */
public class Language {

    public static final String MESSAGE_CONSTRAINTS = "Languagees can take any values, and it should not be blank";

    /*
     * The first character of the language must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Language}.
     *
     * @param language A valid address.
     */
    public Language(String language) {
        requireNonNull(language);
        checkArgument(isValidLanguage(language), MESSAGE_CONSTRAINTS);
        value = language;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidLanguage(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Language // instanceof handles nulls
                && value.equals(((Language) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}