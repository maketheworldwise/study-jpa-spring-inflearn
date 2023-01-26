package com.example;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ALBUM")
@DiscriminatorValue(value = "ALBUM")
public class Album extends Item {
	private String artist;
}
