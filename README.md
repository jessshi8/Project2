# Project2

**Online Bookstore + Review App**
- Database of books table, users table, and user cart table 
  * Info stored in books table: Title, Picture of Cover, Summary, Author, Publisher, Date Published, Genre, ISBN, Reviews/Stars, Price
  * Info stored in users table: Firstname, Last Name, DOB, email, username, password
  * User cart info: many-to-many table that stores the ISBN and username
- Search by name, author, or ISBN
- Can filter by genre, stars (review rating)
- Users create an account 
  * Can add to cart and then purchase books
  * Purchasing process: input shipping info, confirm
  * Can review past orders
  * Can favorite books to view later
  * Can post a review on books purchased
      - Validation required
- Pages:
  * Sign-in, registration, book catalog, shopping cart, checkout, each book has its own page
