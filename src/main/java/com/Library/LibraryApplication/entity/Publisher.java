package com.Library.LibraryApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "publishers")
public class Publisher {

@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 100, unique = true)
	private String name;


	public Publisher(long id, String name) {
		this.id=id;
		this.name=name;
	}

	public Publisher(){

	}
}
