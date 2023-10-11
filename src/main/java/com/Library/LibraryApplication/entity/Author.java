package com.Library.LibraryApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 100, unique = true)
	private String name;

	@Column(name = "description", length = 250)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "authors")
	private List<Book> books ;


	public Author(Long id, String name, String description) {
		this.id=id;
		this.name=name;
		this.description=description;
	}

	public Author(){

	}
}
