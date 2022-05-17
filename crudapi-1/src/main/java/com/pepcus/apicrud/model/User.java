package com.pepcus.apicrud.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "current_book_issue")
	private Integer currentBookIssue;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "registration_on")
	private Date registrationOn;
    
	@Column(name = "deactivation_on")
	private Date deactivationON;

	
	@OneToMany(cascade = CascadeType.ALL)
	
	private List<Book> bookList;

}
