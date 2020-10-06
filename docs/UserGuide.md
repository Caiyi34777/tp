---
layout: page
title: User Guide
---

IntelliBrary is an **app for managing storage, purchase, borrowing, and reader review of books in NUS library via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). The product is targeted at users who can type fast, IntelliBrary can get your book management tasks done faster than traditional GUI apps.
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Features

</div>

### Add books `[coming soon]` : `addBook`

Checks the list of locations of where a certain book is stored.

Format: `addBook /name:NAME /author:AUTHOR /publisher:PUBLISHER /ISBN:ISBN  /cat:CATEGORY /loc:LOCATION STORAGE`


Examples:
* `addBook /name: Numerical linear algebra [electronic resource] : an introduction /author: Holger Wendland /publisher: Cambridge University Press /ISBN: 9781316544938 /cat: Math /loc: Central Library /storage: 5`
* `addBook /name: Artificial Intelligence, A mordern approach /author: Stuart Russell /publisher: PEARSON /ISBN: 978-0-13-461099-3 /cat: Computer Science /loc: Central Library /storage: 6`


### Delete books `[coming soon]`: `deleteBookByIsbn, deleteBookByTimes, deleteBookByName`

Checks the list of locations of where a certain book is stored.

Format:  
`deleteBookByISBN /ISBN`  
`deleteBookByTimes /NUMBER_OF_TIMES_BEEN_BORROWED`  
`deleteBookByName /NAMEOFBOOK`  


Examples:
* `deleteBookByISBN /9781316544938`
* `deleteBookByTimes /0`
* `deleteBookByName /Numerical linear algebra [electronic resource] : an introduction`

### Check location `[coming soon]`: `locate`

Checks the list of locations of where a certain book is stored.

Format: `locate /name NAME [/ISBN ISBN]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The ISBN of the book is an optional argument in the command.
</div>

Examples:
* `locate /name A brief history of time /ISBN 9780553175219`
* `locate /name A brief history of time`

### Check stocking `[coming soon]`: `stock`

Check the stock of the book.

Format: `stock /name NAME [/ISBN ISBN]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The ISBN of the book is an optional argument in the command.
</div>

Examples:
* `stock /name A brief history of time /ISBN /9780553175219`
* `stock /name A brief history of time`

### View Sample Data `[coming soon]`: `view`

Have a look at the sample data for the application when open app for the first time.

Format: `view`

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Editing a book : `edit`

Edits the information of a book in the database.

Format: `edit INDEX /BOOK_NAME /NAME /AUTHOR /PUBLISHER /ISBN /CATEGORY /LOCATION /STORAGE`

* Edits the book at the specified `INDEX`. The index refers to the index number shown in the displayed book list. The index **must be a positive integer** 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing categories, the existing categories of the book will be removed i.e adding of categories is not cumulative.
* You can remove all the book’s categories by typing `t/` without
    specifying any categories after it.

Examples:
*  edit /BOOK_NAME: Numerical linear algebra [electronic resource] : an introduction /author: Holger Wendland /publisher: Cambridge University Press /ISBN: 9781316544938 /cat: Math /loc: Central Library /storage: 5

### Check borrowed status `[coming soon]`: `check`

Check the status of a certain book- whether it is available or not.

Format: `status /BOOK_NAME`

Examples:
* `status /Harry Potter`
* `status /Linear Algebra`

### Purge sample data `[coming soon]`: `purge`

Purge all sample data in one go.

Format: `purge`

Examples:
* `purge`
