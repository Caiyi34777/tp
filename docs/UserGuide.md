---
layout: page
title: User Guide
---

IntelliBrary is an **app for managing storage, purchase, borrowing, and reader review of books in NUS library via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
Targeted at users who can type fast, IntelliBrary can get your library management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `intelLibrary.jar` from [here]().

3. Copy the file to the folder you want to use as the _home folder_ for your IntelliBrary.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all books in the library.

   * **`add`**`n/Linear Algebra i/98765432 e/xxxxxx@example.com ad/xxxxx c/Science c/Math t/20 s/central library 0 science library 0 a/Victor p/pku` : Adds a Book named `Linear Algebra` to the Library.

   * **`delete`**`3` : Deletes the 3rd book shown in the current list.

   * **`clear`** : Deletes all books.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of all the commands.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Linear Algebra`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [c/CATEGORY]` can be used as `n/The Great Gatsby c/Novel` or as `n/The Great Gatsby`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[c/CATEGORY]…` can be used as (i.e. 0 times), `c/Novel`, `c/Novel c/Classic` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PUBLISHER`, `p/PUBLISHER n/NAME` is also acceptable.

</div>

### Viewing sample data

Have a look at the sample data for the application when open app for the first time.
*  The sample data will only show up if there is no local data file of IntelliBrary

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Listing all books : `list`

Shows a list of all books in the library.

Format: `list`

### Clearing all entries : `clear`

Clears all entries from the library.

Format: `clear`

### Locating books by name: `find`

Finds books whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `novel` will match `Novel`
* The order of the keywords does not matter. e.g. `Linear Algebra` will match `Algebra Linear`
* Only the names are searched.
* Only full words will be matched e.g. `Novel` will not match `Novels`
* Books matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Linear Algebra` will return `Basic Algebra`, `Linear Mathematics`

Examples:
* `find novel` returns `novel` and `Selected Novels`
* `find linear algebra` returns `Basic Algebra`, `linear math`

### Adding a book : `add`

Add a book to the book list.

Duplicate book will be rejected.

Format: `add n/NAME i/ISBN e/EMAIL ad/ADDRESS [c/CATEGORY]...t/TIMES s/STOCKINGS a/AUTHOR p/PUBLISHER`

Examples:
* `add n/Linear Algebra i/98765432 e/xxxxxx@example.com ad/xxxxx c/Science c/Math t/20 s/centralLb 0 scienceLb 0 a/Victor p/pku`

* `add n/Artificial Intelligence i/9780134610993 e/xxxxxx@example.com ad/xxxxx c/Science t/20 s/centralLb 2 scienceLb 3 a/Stuart Russell p/PEARSON`

### Editing a book : `edit`

Edits the information of an existing book in the library.

Format: `edit INDEX [n/NAME] [i/ISBN] [e/EMAIL] [ad/ADDRESS] [t/TIMES] [c/CATEGORY]… [s/STOCKING] [a/AUTHOR] [p/PUBLISHER]`

* Edits the book at the specified `INDEX`. The index refers to the index number shown in the displayed book list. The index **must be a positive integer** 1, 2, 3...
* All fields are optional but at least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing categories, the existing categories of the book will be removed i.e adding of categories is not cumulative.
* You can remove all the book’s categories by typing `c/` without specifying any categories after it.

Examples:
* `edit 2 n/A Brief History of Time e/abhot@gmail.com` Edits the name and contact email address of the 2nd book to be A Brief History of Time and abhot@gmail.com respectively.
* `edit 3 p/Scribner Publisher t/` Edits the publisher of the 3rd book to be Scribner Publisher and clears all existing tags.

#### _Additional information regarding stocking in add and edit command_

The library location name is case sensitive.

Note that only central library, science library, and HSSM library are available and no stocking information of other library can be added currently.

Given that there are only 3 locations available, the number of location argument can be at most 3.

If there are duplicate location argument, such as centralLb 10 centralLb 20, then the later one will cover the previous one.

If the stocking information of some of the libraries is not provided or if the number of stocking is 0, then the stocking information shown for the book in that location will be: `Not Available`

Additionally, to make the recorded stocking more reasonable, the stocking of a book in a location should be an integer between 0 and 99999.

Examples:
* `s/centralLb 30 scienceLb 20 HSSMLb 10`
* `s/centralLb 10`
* `s/`

### Deleting a book: `delete`

Deletes the specified book from the library.

Format: `delete INDEX`

* Deletes the book at the specified `INDEX`.
* The index refers to the index number shown in the displayed book list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd book in the library.
* `find novel` followed by `delete 1` deletes the 1st person in the results of the `find` command.

#### Deleting a book by other attributes: `deleteBy`

Delete a book from the library.

Format:  
`deleteBy [n/NAME] [i/ISBN] [t/TIMES]` (choose one of the three prefixes in the command)  

Examples:
* `deleteBy n/Linear Algebra`
* `deleteBy i/123456 `

### Modify the times

Changes the number of times that a book has been borrowed to the latest record.

Format:  
`times INDEX t/TIMES`

Examples:
* `times 1 t/25` Modifies the number of times the 1st book in the library being borrowed to 25.

### Check stocking of book in every location: `stock`

Checks the list of stocking information in every location where a certain book is stored.
Currently only the science library, central library, and HSSM library are available locations.
This means only stocking information regarding the science library, central library, and HSSM library are available.

Format: `stock [n/BOOK NAME] [i/ISBN]`

<div markdown="1" class="alert alert-primary">:bulb: **Tip:**

Both the name and the ISBN of the book are optional argument in the command.

* The book name searching follows the all match pattern, where the search name string will be split into keywords according to the white space in between, and the book name will need to contain all of the keywords in order to be included in the result list.

    * In this case, the word contain means the keyword needs to match exactly some of the words in the book name.
    
     * For example, `The Guns of August` contains `Guns`, but it does not contain `Gun`.
    
     * The keyword of book name is case insensitive.

* The ISBN name searching follows the some match pattern, where the search number string will be split into keywords according to the white space in between, and the book ISBN will need to contain some of the keywords in order to be included in the result list.

    * In this case, the word contain means the keyword needs to match the ISBN of the book partially.

    * For example, `9780553175219` contains `9780553175219` and `97805`.

The reason for the difference of behavior of the book name and ISBN is that book name can be used to check the stocking information of more specific books, while ISBN can be used to check the stocking information of a wide range of books.

If both the name and the ISBN are used in the command, then the result will be the stocking information of the books that satisfy either of the conditions.

If neither of them are present, then the command will return the stocking information of all the books.

If the value after the prefix is empty, then the command will return the stocking information of all the books.

For example, the command stock n/ and the command stock i/ will return the stocking information of all the books.
</div>

Examples:
* `stock n/A brief history of time i/9780553175219`
* `stock n/A brief history of time`
* `stock i/9780553175219`
* `stock`

### Features related to Review

#### _Introduction_

The purpose of the review functionality is for librarian to collect and record review and feedback from readers about a certain book, and estimates the general rating and popularity of the book among readers.

Given that review is recorded on a person by person basis, there can be duplicate review added since 2 different people may give the same review.

It is reasonable to assume that most review and feedback is collected anonymously in a library in real life situation.

Additionally, the purpose of recording the review is to estimate the popularity of a certain book among the readers and decide whether the library should keep more copies of the book accordingly, and it is less concerned about what the opinion of a specific reader is.

Therefore, there will be no personal information recorded in the review.

#### Search for review of book: `searchReview`

Check the list of reviews of certain book.

Format: `searchReview [n/BOOK NAME] [i/ISBN]`

<div markdown="1" class="alert alert-primary">:bulb: **Tip:**

The usage is similar to the stock command.
</div>

Examples:
* `searchReview n/A brief history of time i/9780553175219`
* `searchReview n/A brief history of time`
* `searchReview i/9780553175219`
* `searchReview`

#### Add review: `addReview`

Add a review to a certain book. 

Format: `addReview INDEX ra/RATING re/REVIEW_CONTENT`

<div markdown="1" class="alert alert-primary">:bulb: **Tip:**

The book review will be added according to the index of the book in the current shown book list.

The rating needs to be a string representing an integer from 0 to 5.

The review content should not be empty and it should not contain more than 300 characters.

If other command is executed before the add review command, then only the index corresponding to the book shown in the current book list will be valid.

If the index is not in the currently shown book list, then a corresponding exception message will be shown.
</div>

Examples:
* `addReview 1 ra/5 re/The book is interesing`

#### Delete review: `deleteReview`

Delete a review of a certain book.

Format: `deleteReview INDEX rn/REVIEW_INDEX`

<div markdown="1" class="alert alert-primary">:bulb: **Tip:**

The book review will be deleted from the review list of the book according to the index of the book and the index of the review in the review list of the book.

If other command is executed before the delete review command, then only the index corresponding to the book shown in the current book list will be valid.

If the index is not in the currently shown book list, then an exception message will be shown.
</div>

Examples:
* `deleteReview 1 rn/1`

#### Edit review: `editReview`

Edit a review of a certain book. 

Format: `editReview INDEX rn/REVIEW_INDEX [ra/RATING] [re/REVIEW_CONTENT]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

The explanation about index is similar to add review and delete review command.

If neither rating or review content is present, then an exception message will be shown.

If the edited review is the same as the original review, then a corresponding exception message will be shown. 
</div>

Examples:
* `editReview 1 rn/7 ra/5 re/The book is interesing`
* `editReview 1 rn/7 ra/5`
* `editReview 1 rn/7 re/The book is interesing`

### Check usage

Checks usage times of a certain book specified by user. Book is specified by any of the followings:
* one base index in storage.
* book isbn
* book name

#### by Index
Format:
* `usage [INDEX]`

Examples:
* `usage 2`

#### by Book Name or ISBN
Format: 
* `usageBy i/[ISBN]`
* `usageBy n/[BOOK_NAME]`

Examples:
* `usageBy i/9780141439518`
* `usageBy n/Pride and Prejudice`

### Check history

Checks borrowing times during the whole timeline.

Format: `history`

Examples:
* `history`

### Features related to reprting problems

#### Report problems: `report`

Report a problem found in library.

Format: `report severity/SEVERITY problem/PROBLEM`

Note that `SEVERITY` is limited to `high`, `medium`, and `low`, case-insensitive.

Problem description should only contain alphanumeric characters and spaces, and it should not be blank.

Duplicate problems will be rejected. Note that two problems are considered to be the same if and only if 
they have **both** the same severity and the same description.

Examples:
* `report severity/high problem/book is broken`

#### View problems: `view`

View all reported problems.

Format: `view`

Examples:
* `view`


#### Locating reports by keyword: `findpr`

Finds reports whose descriptions contain any of the given keywords.

Format: `findpr KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `chair` will match `Chair`
* The order of the keywords does not matter. e.g. `table chair` will match `chair table`
* Only the description is searched.
* Only full words will be matched e.g. `chair` will not match `chairs`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `table chair` will return `chair light`, `light table`

Examples:
* `findpr chair` returns report containing `chair` and `fix chair`
* `findpr table chair` returns `table`, `chair`

#### Deleting a report : `deletepr`

Deletes the specified person from the address book.

Format: `deletepr INDEX`

* Deletes the report at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `findpr chair` followed by `deletepr 1` deletes the 1st report in the results of the `findpr` command.`

--------------------------------------------------------------------------------------------------------------------

## Additional information regarding categories and book cover

The list of relevant categories are given below in ascending order with respect to the priority of the category.

Note that categories that are not in the list is still a valid category, but they will not help to give a specific book cover to the book.

* `General`
* `Novels`
* `History`
* `Science`
* `AncientHistory`
* `ModernHistory`
* `AncientWar`
* `ModernWar`
* `Math`
* `Chemistry`
* `Physics`

The book cover of a book depends on the categories of the book.

The category name is case insensitive, but there cannot be white space in between the category words.

If more than 1 category is given, the book cover will depend on the category with higher priority.

If no category is given or none of the categories given is in the list above, then the book will have a book cover corresponding to the general category.

For example, the book with categories Physics and Science will have a book cover corresponding to Physics

To make the book cover more diversified, 2 books with the same categories may have different book cover corresponding to the categories, but they may also have the same book cover.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Exit** | `exit`
**List** | `list`
**Clear** | `clear`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Edit** | `edit INDEX [n/NAME] [i/ISBN] [e/EMAIL] [ad/ADDRESS] [t/TIMES] [c/CATEGORY]… [s/STOCKING] [a/AUTHOR] [p/PUBLISHER]`<br> e.g.,`edit 3 p/Scribner Publisher c/`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Times**| `times INDEX t/TIMES` <br> e.g., `times 1 t/5`
**Stock** | `stock [n/BOOK NAME] [i/ISBN]` <br> e.g., `stock n/A brief history of time i/9780553175219`
**SearchReview** | `searchReview [n/BOOK NAME] [i/ISBN]` <br> e.g., `searchReview n/A brief history of time i/9780553175219`
**AddReview** | `addReview INDEX ra/RATING re/REVIEW_CONTENT` <br> e.g., `addReview 1 ra/5 re/The book is interesing`
**DeleteReview** | `deleteReview INDEX rn/REVIEW_INDEX` <br> e.g., `deleteReview 1 rn/1`
**EditReview** | `editReview INDEX rn/REVIEW_INDEX [ra/RATING] [re/REVIEW_CONTENT]` <br> e.g., `editReview 1 rn/7 ra/5 re/The book is interesing`
**Usage**| `usage [INDEX]` <br> e.g., `usage 1`
**UsageBy**| `usageBy i/[ISBN]` `usageBy n/[BOOK_NAME]` <br> e.g., `usageBy i/9780141439518` `usageBy n/Pride and Prejudice`
**History**| `history`
**ReportProblem** | `report severity/SEVERITY problem/DESCRIPTION` <br> e.g., `report severity/medium problem/book is broken`
**ViewProblems** | `view`
