package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PUBLISHER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.book.Address;
import seedu.address.model.book.Author;
import seedu.address.model.book.Email;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Name;
import seedu.address.model.book.Book;
import seedu.address.model.book.Publisher;
import seedu.address.model.book.Stocking;
import seedu.address.model.book.Times;
import seedu.address.ui.Mode;

/**
 * Edits the details of an existing book in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the book identified "
            + "by the index number used in the displayed book list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ISBN + "ISBN] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_CATEGORY + "CATEGORY]...\n"
            + "[" + PREFIX_AUTHOR + "AUTHOR]"
            + "[" + PREFIX_PUBLISHER + "PUBLISHER]"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ISBN + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Book: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This book already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the book in the filtered book list to edit
     * @param editPersonDescriptor details to edit the book with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Book bookToEdit = lastShownList.get(index.getZeroBased());
        Book editedBook = createEditedPerson(bookToEdit, editPersonDescriptor);

        if (!bookToEdit.isSamePerson(editedBook) && model.hasPerson(editedBook)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(bookToEdit, editedBook);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS, Mode.NORMAL);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedBook));
    }

    /**
     * Creates and returns a {@code Book} with the details of {@code bookToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Book createEditedPerson(Book bookToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert bookToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(bookToEdit.getName());
        Isbn updatedIsbn = editPersonDescriptor.getIsbn().orElse(bookToEdit.getIsbn());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(bookToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(bookToEdit.getAddress());
        Times updatedTimes = bookToEdit.getTimes(); // edit command does not allow editing times
        Set<Category> updatedCategories = editPersonDescriptor.getCategories().orElse(bookToEdit.getCategories());
        Author updatedAuthor = editPersonDescriptor.getAuthor().orElse(bookToEdit.getAuthor());
        Publisher updatedPulisher = editPersonDescriptor.getPublisher().orElse(bookToEdit.getPublisher());
        Stocking updatedStocking = editPersonDescriptor.getStocking().orElse(bookToEdit.getStocking());

        return new Book(updatedName, updatedIsbn, updatedEmail,
                updatedAddress, updatedTimes, updatedCategories, updatedStocking, updatedAuthor, updatedPulisher);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the book with. Each non-empty field value will replace the
     * corresponding field value of the book.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Isbn isbn;
        private Email email;
        private Address address;
        private Set<Category> categories;
        private Author author;
        private Publisher publisher;
        private Stocking stocking;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code categories} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setIsbn(toCopy.isbn);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setCategories(toCopy.categories);
            setAuthor(toCopy.author);
            setPublisher(toCopy.publisher);
            setStocking(toCopy.stocking);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, isbn, email, address, categories);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setIsbn(Isbn isbn) {
            this.isbn = isbn;
        }

        public Optional<Isbn> getIsbn() {
            return Optional.ofNullable(isbn);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code categories} to this object's {@code categories}.
         * A defensive copy of {@code categories} is used internally.
         */
        public void setCategories(Set<Category> categories) {
            this.categories = (categories != null) ? new HashSet<>(categories) : null;
        }

        /**
         * Returns an unmodifiable category set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code categories} is null.
         */
        public Optional<Set<Category>> getCategories() {
            return (categories != null) ? Optional.of(Collections.unmodifiableSet(categories)) : Optional.empty();
        }


        public void setAuthor(Author author) {
            this.author = author;
        }

        public Optional<Author> getAuthor() {
            return Optional.ofNullable(author);
        }

        public void setPublisher(Publisher publisher) {
            this.publisher = publisher;
        }

        public Optional<Publisher> getPublisher() {
            return Optional.ofNullable(publisher);
        }

        public void setStocking(Stocking stocking) {
            this.stocking = stocking;
        }

        public Optional<Stocking> getStocking() {
            return Optional.ofNullable(stocking);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getIsbn().equals(e.getIsbn())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getCategories().equals(e.getCategories())
                    && getStocking().equals(e.getStocking())
                    && getAuthor().equals(e.getAuthor())
                    && getPublisher().equals(e.getPublisher());

        }
    }
}
