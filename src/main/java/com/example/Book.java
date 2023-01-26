package com.example;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
@DiscriminatorValue(value = "BOOK")
public class Book extends Item {

	private String author;

	private String isbn;
}
