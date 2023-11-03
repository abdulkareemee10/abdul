package com.Library.LibraryApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "categories")
public class Category {

	@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 50,nullable = false, unique = true)
	private String name;




	public Category(long id, String name) {
		this.id=id;
		this.name=name;
	}

	public Category(){

	}
}
