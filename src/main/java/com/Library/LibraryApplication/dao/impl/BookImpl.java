package com.Library.LibraryApplication.dao.impl;

import com.Library.LibraryApplication.dao.AuthorRepository;
import com.Library.LibraryApplication.dao.BookRepository;
import com.Library.LibraryApplication.dao.CategoryRepository;
import com.Library.LibraryApplication.dao.PublisherRepository;
import com.Library.LibraryApplication.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookImpl implements BookRepository {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	PublisherRepository publisherRepository;

	private static final String INSERT_BOOK_QUERY = "INSERT INTO BOOKS(id, isbn, name, description) VALUES (?, ?, ?, ?)";

	private static final String UPDATE_BOOK_BY_ID_QUERY = "UPDATE BOOKS SET name=? WHERE ID=?";

	private static final String GET_BOOK_BY_ID_QUERY = "SELECT * FROM BOOKS WHERE ID =?";

	// private static final String GET_BOOK_TABLE_BY_ID_QUERY = "SELECT * FROM BOOKS
	// WHERE ID =?";
	private static final String DELETE_BOOK_BY_ID_QUERY = "DELETE FROM BOOKS WHERE ID =?";
	private static final String GET_BOOK_QUERY = "SELECT * FROM BOOKS ";


	private static final String INSERT_BOOK_AUTHOR_QUERY = "INSERT INTO books_authors (book_id, author_id) VALUES (?, ?)";
	private static final String INSERT_BOOK_CATEGORY_QUERY = "INSERT INTO books_categories (book_id, category_id) VALUES (?, ?)";
	private static final String INSERT_BOOK_PUBLISHER_QUERY = "INSERT INTO books_publishers (book_id, publisher_id) VALUES (?, ?)";
	private static final String GET_BOOK_BY_ISBN_NAME_DESCRIPTION_QUERY = "SELECT * FROM BOOKS WHERE ISBN =? AND NAME=? AND DESCRIPTION=?";

	private static final String GET_BOOK_AUTHOR_BY_BOOKID_QUERY = "SELECT author_id FROM books_authors WHERE book_id=?";

	private static final String GET_BOOK_PUBLISHER_BY_BOOKID_QUERY = "SELECT publisher_id FROM books_publishers WHERE book_id=?";

	private static final String GET_BOOK_CATEGORY_BY_BOOKID_QUERY = "SELECT category_id FROM books_categories WHERE book_id=?";


	// private static final String GET_LIBRARY = "SELECT * FROM BOOKS,CATEGORIES,
	// AUTHORS, PUBLISHER WHERE 1 = 1 AND BOOKS.ID = CATEGORIES.ID, AND BOOKS.ID =
	// AUTHORS.ID, AND BOOKS.ID = PUBISHERS.ID ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String saveBook(Book book) {
		Book bookToSave = new Book();

		bookToSave.setName(book.getName());
		bookToSave.setIsbn(book.getIsbn());
		bookToSave.setDescription(book.getDescription());

		List<Author> authors = new ArrayList<>();
		for (Author author : book.getAuthors()) {
			Author author1 = new Author();
			Author verifyAuthor = new Author();
			try {
				// get author by name
				verifyAuthor = authorRepository.getByName(author.getName());

			} catch (EmptyResultDataAccessException e) {
				verifyAuthor = null;
			}
			if (verifyAuthor != null) {
				// author exist
				authors.add(verifyAuthor);
			} else {
				// save author
				author1.setDescription(author.getDescription());
				author1.setName(author.getName());
				authorRepository.saveAuthor(author1);
				author1 = authorRepository.getByName(author.getName());
				authors.add(author1);
			}
		}
		bookToSave.setAuthors(authors);

		List<Category> categories = new ArrayList<>();
		for (Category category : book.getCategories()) {
			Category category1 = new Category();
			Category verifyCategory = new Category();
			try {
				// get author by name
				verifyCategory = categoryRepository.getByName(category.getName());

			} catch (EmptyResultDataAccessException e) {
				verifyCategory = null;
			}
			// get category by name

			if (verifyCategory != null) {
				// category exist
				categories.add(verifyCategory);
			} else {
				// save category
				category1.setName(category.getName());
				categoryRepository.saveCategory(category1);
				category1 = categoryRepository.getByName(category.getName());
				categories.add(category1);
			}
		}
		bookToSave.setCategories(categories);

		List<Publisher> publishers = new ArrayList<>();
		for (Publisher publisher : book.getPublishers()) {
			Publisher publisher1 = new Publisher();
			Publisher verifyPublisher = new Publisher();
			try {
				// get publisher by name
				verifyPublisher = publisherRepository.getByName(publisher.getName());

			} catch (EmptyResultDataAccessException e) {
				verifyPublisher = null;
			}
			// get publisher by name
			if (verifyPublisher != null) {
				// publisher exist
				publishers.add(verifyPublisher);
			} else {
				// save publisher
				publisher1.setName(publisher.getName());
				publisherRepository.savePublisher(publisher1);
				publisher1 = publisherRepository.getByName(publisher.getName());
				publishers.add(publisher1);
			}
		}
		bookToSave.setPublishers(publishers);

		jdbcTemplate.update(INSERT_BOOK_QUERY, bookToSave.getId(), bookToSave.getIsbn(), bookToSave.getName(),
				bookToSave.getDescription());
		Book bookResponse = getByIsbnNameDescription(bookToSave.getIsbn(), bookToSave.getName(),
				bookToSave.getDescription());
		for (Author aut : bookToSave.getAuthors()) {
			jdbcTemplate.update(INSERT_BOOK_AUTHOR_QUERY, bookResponse.getId(), aut.getId());
		}
		for (Category cat : bookToSave.getCategories()) {
			jdbcTemplate.update(INSERT_BOOK_CATEGORY_QUERY, bookResponse.getId(), cat.getId());
		}
		for (Publisher pub : bookToSave.getPublishers()) {
			jdbcTemplate.update(INSERT_BOOK_PUBLISHER_QUERY, bookResponse.getId(), pub.getId());
		}
		return "book was  successfully added";
	}

	@Override
	public String updateBook(Book book) {
		jdbcTemplate.update(UPDATE_BOOK_BY_ID_QUERY, book.getId(), book.getName());
		return "book was  successfully updated";
	}

	@Override
	public String deleteById(long id) {
		jdbcTemplate.update(DELETE_BOOK_BY_ID_QUERY, id);
		return "Book with id no_ " + id + " was deleted successfully";
	}

	@Override
	public Book getById(long id) {

		Book bookreturn =  jdbcTemplate.queryForObject(GET_BOOK_BY_ID_QUERY, (rs, rowNum) -> {
			return new Book(rs.getLong("id"), rs.getString("name"), rs.getString("isbn"), rs.getString("description"));
		}, id);


		List<Long> authorIds = getBookAuthorsByBookId(bookreturn.getId());
		List<Author> authorList = new ArrayList<>();
		for (Long authorId : authorIds) {
			Author author = authorRepository.getById(authorId);
			authorList.add(author);
		}
		bookreturn.setAuthors(authorList);



		List<Long> publisherIds = getBookPublishersByBookId(bookreturn.getId());
		List<Publisher> publisherList = new ArrayList<>();
		for (Long publisherId : publisherIds) {
			Publisher publisher = publisherRepository.getById(publisherId);
			publisherList.add(publisher);
		}
		bookreturn.setPublishers(publisherList);



		List<Long> categoryIds = getBookCategoriesByBookId(bookreturn.getId());
		List<Category> categoryList = new ArrayList<>();
		for (Long categoryId : categoryIds) {
			Category category = categoryRepository.getById(categoryId);

			categoryList.add(category);
		}
		bookreturn.setCategories(categoryList);





		return bookreturn;
}




	@Override
	public List<Book> allBooks() {

		List<Book> bookResponse = new ArrayList<>();
		//get all books
		List<Book> bookList = jdbcTemplate.query(GET_BOOK_QUERY, (rs, rowNum) -> {
			return new Book(rs.getLong("id"), rs.getString("name"), rs.getString("isbn"), rs.getString("description"));
		});

		//loop through the books
		for(Book book: bookList) {

			//for authors List
			List<Long> authorIds = getBookAuthorsByBookId(book.getId());
			List<Author> authorList = new ArrayList<>();
			for (Long authorId : authorIds) {
				Author author = authorRepository.getById(authorId);
				authorList.add(author);
			}
			book.setAuthors(authorList);




			List<Long> publisherIds = getBookPublishersByBookId(book.getId());
			List<Publisher> publisherList = new ArrayList<>();
			for (Long publisherId : publisherIds) {
				Publisher publisher = publisherRepository.getById(publisherId);
				publisherList.add(publisher);
			}
			book.setPublishers(publisherList);



			List<Long> categoryIds = getBookCategoriesByBookId(book.getId());
			List<Category> categoryList = new ArrayList<>();
			for (Long categoryId : categoryIds) {
				Category category = categoryRepository.getById(categoryId);

				categoryList.add(category);
			}
			book.setCategories(categoryList);


			//for catgories List


			//for publishers List



			bookResponse.add(book);
		}

		return bookResponse;
	}


	@Override
	public Book getByIsbnNameDescription(String isbn, String name, String description) {
		return jdbcTemplate.queryForObject(GET_BOOK_BY_ISBN_NAME_DESCRIPTION_QUERY, (rs, rowNum) -> {
			return new Book(rs.getInt("id"), rs.getString("name"), rs.getString("isbn"), rs.getString("description"));
		}, isbn, name, description);
	}
	
	public List<Long> getBookAuthorsByBookId(Long bookId){
		return jdbcTemplate.queryForList(GET_BOOK_AUTHOR_BY_BOOKID_QUERY, Long.class, bookId);
	}
	public List<Long> getBookCategoriesByBookId(Long bookId){
		return jdbcTemplate.queryForList(GET_BOOK_CATEGORY_BY_BOOKID_QUERY, Long.class, bookId);
	}
	public List<Long> getBookPublishersByBookId(Long bookId){
		return jdbcTemplate.queryForList(GET_BOOK_PUBLISHER_BY_BOOKID_QUERY, Long.class, bookId);
	}

}
