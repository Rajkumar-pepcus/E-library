package com.pepcus.apicrud.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepcus.apicrud.model.Book;
import com.pepcus.apicrud.model.Shelve;
import com.pepcus.apicrud.service.ShelveService;

@RestController
@RequestMapping("/api/books")
public class ShelveController {

	@Autowired
	private ShelveService shelveService;

	/*
	 * param
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<Shelve> saveShelve(@Valid @RequestBody Shelve shelve) {

		return new ResponseEntity<Shelve>(shelveService.saveShelve(shelve), HttpStatus.CREATED);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Shelve> saveBook(@PathVariable("id") Integer id, @RequestBody List<Book> bookList) {
		return new ResponseEntity<Shelve>(shelveService.saveBook(id, bookList), HttpStatus.OK);
	}
}
