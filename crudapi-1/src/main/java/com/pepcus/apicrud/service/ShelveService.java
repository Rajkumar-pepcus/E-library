package com.pepcus.apicrud.service;

import java.util.List;

import com.pepcus.apicrud.model.Book;
import com.pepcus.apicrud.model.Shelve;

public interface ShelveService {

	Shelve saveShelve(Shelve shelve);

	Shelve saveBook(Integer id, List<Book> bookList);
}
