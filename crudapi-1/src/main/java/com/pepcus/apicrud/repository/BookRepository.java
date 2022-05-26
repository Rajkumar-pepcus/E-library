package com.pepcus.apicrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pepcus.apicrud.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
