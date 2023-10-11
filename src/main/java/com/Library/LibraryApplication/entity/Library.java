package com.Library.LibraryApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "isbn",  unique = true)
    private String isbn;

    @Column(name = "book")
    private String book;

@Column(name = "category")
    private String category;

@Column(name = "author")
    private String author;

@Column(name = "description")

    private String description;

@Column(name = "publisher")
    private String publisher;

    public Library(Long id, String isbn, String book, String category, String author, String description, String publisher) {
        this.id = id;
        this.isbn = isbn;
        this.book = book;
        this.category = category;
        this.author = author;
        this.description = description;
        this.publisher = publisher;
    }

    public Library(long id, String name, String isbn, String description) {
    }
    public Library(){

    }

	public Library(long id, String isbn, String book, String description, String category, String author,
			String publihser) {

		this.id = id;
		this.isbn = isbn;
		this.book = book;
		this.category = category;
		this.author = author;
		this.description = description;
		this.publisher = publisher;
	}
}
