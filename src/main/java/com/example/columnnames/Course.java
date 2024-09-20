package com.example.columnnames;


import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="COURSE_DTLS")
public class Course {
	
	@Id
	private Integer cid;
	private String name;
	private double price;
}