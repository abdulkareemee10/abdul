package com.Library.LibraryApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "isbn", nullable = false, length = 50, unique = true)
	private String isbn;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "description", nullable = false, length = 250)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "books_authors", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "author_id") })
	private List<Author> authors;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "books_categories", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "category_id") })
	private List<Category> categories;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "books_publishers", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "publisher_id") })
	private List<Publisher> publishers;

	public Book(long id, String name, String isbn, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.isbn = isbn;

	}

	public Book() {
	}

	public Book(Long id, String isbn, String name, String description, List<Author> authors, List<Category> categories,
			List<Publisher> publishers) {
		this.id = id;
		this.isbn = isbn;
		this.name = name;
		this.description = description;
		this.authors = authors;
		this.categories = categories;
		this.publishers = publishers;
	}

	public void addAuthors(Author authors) {
		this.authors.add(authors);
		authors.getBooks().add(this);
	}

	public void removeAuthors(Author authors) {
		this.authors.remove(authors);
		authors.getBooks().remove(this);
	}

	public void addCategories(Category categories) {
		this.categories.add(categories);
		categories.getBooks().add(this);
	}

	public void removeCategories(Category categories) {
		this.categories.remove(categories);
		categories.getBooks().remove(this);
	}

	public void addPublishers(Publisher publishers) {
		this.publishers.add(publishers);
		publishers.getBooks().add(this);
	}

	public void removePublishers(Publisher publishers) {
		this.publishers.remove(publishers);
		publishers.getBooks().remove(this);
	}

}
