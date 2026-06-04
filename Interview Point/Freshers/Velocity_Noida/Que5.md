**Write a query to list all book whose name start with K**
>>> SELECT * FROM books WHERE name LIKE 'K%';

**Find the name of book that is writen by "abc" and quantity is more than 10.**
>>> SELECT name FROM books WHERE author = 'abc' AND quantity > 10;

**Find the total number of book whose author is "abc"**
>>> SELECT COUNT(*) AS total_books FROM books WHERE author = 'abc';
