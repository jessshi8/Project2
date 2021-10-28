-- Users Table
-- drop table users cascade;
create table users(
	username varchar(50) primary key,
	password varchar(64) not null,
	firstname varchar(50) not null,
	lastname varchar(50) not null,
	email varchar(150) unique not null,
	role varchar(20) not null
);

-- Books Table
-- drop table books cascade;
create table books(
	isbn varchar(15) primary key,
	book_cover bytea,
	title varchar(50) not null,
	author varchar(50) not null,
	publisher varchar(50) not null,
	published varchar(50) not null,
	genre varchar(20) not null,
	price double precision not null,
	summary varchar(500) not null
);

-- Many-to-many Tables
--drop table carts;
create table carts(
	username varchar(50) references users(username),
	isbn varchar(15) references books(isbn),
	primary key(username, isbn)
);
-- drop table bookmarks;
create table bookmarks(
	username varchar(50) references users(username),
	isbn varchar(15) references books(isbn),
	primary key(username, isbn)
);
-- drop table orders;
create table orders(
	username varchar(50) references users(username),
	isbn varchar(15) references books(isbn),
	primary key(username, isbn)
);

-- Populate books table
create or replace function insert_book(isbn varchar(15), book_cover bytea, title varchar(50), author varchar(50), 
	publisher varchar(50), published varchar(50), genre varchar(20), price double precision, summary varchar(500))
returns varchar(25) as $$
begin
	insert into books(isbn, book_cover, title, author, publisher, published, genre, price, summary) 
		values(isbn, book_cover, title, author, publisher, published, genre, price, summary);
	return 'Successfully added book.';
end;
$$ language 'plpgsql';

--select insert_book('9781503222687', null, 'Alice''s Adventures in Wonderland', 'Lewis Carroll', 'Macmillan', '26 November 1965', 'Fantasy', 5.97, 'Alice''s Adventures in Wonderland by Lewis Carroll is a story about Alice who falls down a rabbit hole and lands into a fantasy world that is full of weird, wonderful people and animals.');
--select insert_book('9780340606513', null, 'Schindler''s List', 'Thomas Keneally', 'Simon & Schuster', '18 October 1982', 'History', 16.99, 'In the shadow of Auschwitz, a flamboyant German industrialist grew into a living legend to the Jews of Cracow. He was a womaniser, a heavy drinker and a bon viveur, but to them he became a saviour. This is the extraordinary story of Oskar Schindler, who risked his life to protect Jews in Nazi-occupied Poland and who was transformed by the war into a man with a mission, a compassionate angel of mercy.');
--select insert_book('9780743273565', null, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Charles Scriber''s Sons', '10 April 1925', 'Historical Fiction', 5.97, 'Set in the Jazz Age on Long Island, the novel depicts narrator Nick Carraway''s interactions with mysterious millionaire Jay Gatsby and Gatsby''s obsession to reunite with his former lover, Daisy Buchanan.');
--select insert_book('9781501180996', null, 'The Outsider', 'Stephen King', 'Simon & Schuster', '22 May 2018', 'Mystery', 11.20, 'An unspeakable crime takes place involving the murder and violation of a small boy. Ralph Anderson is the detective on the case, and he arrests a local man, Terry Maitland. It''s an easy arrest and their evidence is airtight. The problem is, cracks in the case begin to appear when it seems that perhaps Maitland has an equally airtight alibi.');
--select insert_book('9781402726002', null, 'Adventures of Huckleberry Finn', 'Mark Twain', 'Chatto & Windus', '10 December 1884', 'Humor', 8.99, 'The novel tells the story of Huckleberry Finn''s escape from his alcoholic and abusive father and Huck''s adventurous journey down the Mississippi River together with the runaway slave Jim.');
--select insert_book('9780142424179', null, 'The Fault in Our Stars', 'John Green', 'Penguin Books', '10 January 2012', 'Romance', 6.10, 'Despite the tumor-shrinking medical miracle that has bought her a few years, Hazel has never been anything but terminal, her final chapter inscribed upon diagnosis. But when a gorgeous plot twist named Augustus Waters suddenly appears at Cancer Kid Support Group, Hazel’s story is about to be completely rewritten.');
--select insert_book('9781841953922', null, 'Life of Pi', 'Yann Martel', 'Knopf Canada', '11 September 2001', 'Adventure', 8.99, 'After the tragic sinking of a cargo ship, a solitary lifeboat remains bobbing on the wild, blue Pacific. The only survivors from the wreck are a sixteen year-old boy named Pi, a hyena, a zebra (with a broken leg), a female orangutan - and a 450-pound Royal Bengal tiger.');
--select insert_book('9780316017930', null, 'Outliers: The Story of Success', 'Malcolm Gladwell', 'Little, Brown and Company', '18 November 2008', 'Self-Help', 14.03, 'Malcolm Gladwell takes us on an intellectual journey through the world of "outliers" - the best and the brightest, the most famous, and the most successful. He asks the question: What makes high-achievers different? His answer is that we pay too much attention to what successful people are like, and too little attention to where they are from: That is, their culture, their family, their generation, and the idiosyncratic experiences of their upbringing.');
--select insert_book('9781538728628', null, 'The Wish', 'Nicholas Sparks', 'Grand Central Publishing', '28 September 2021', 'Romance', 13.24, '1996 was the year that changed everything for Maggie Dawes. Sent away at sixteen to live with an aunt she barely knew in Ocracoke, she could think only of the friends and family she left behind until she met Bryce Trickett, one of the few teenagers on the island. Handsome, genuine, and newly admitted to West Point, Bryce showed her how much there was to love about the wind-swept beach town—and introduced her to photography, a passion that would define the rest of her life.');
--select insert_book('9780747546245', null, 'Harry Potter and the Goblet of Fire', 'J.K. Rowling', 'Pottermore Publishing', '8 July 2000', 'Fantasy', 12.99, 'It follows Harry Potter, a wizard in his fourth year at Hogwarts School of Witchcraft and Wizardry, and the mystery surrounding the entry of Harry''s name into the Triwizard Tournament, in which he is forced to compete.');

select * from users;
select * from books;
select * from carts;
select * from bookmarks;
select * from orders;