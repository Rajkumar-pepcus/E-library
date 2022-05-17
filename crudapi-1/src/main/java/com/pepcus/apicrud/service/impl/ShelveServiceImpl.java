package com.pepcus.apicrud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepcus.apicrud.exception.ResourceNoteFoundException;
import com.pepcus.apicrud.model.Book;
import com.pepcus.apicrud.model.Shelve;
import com.pepcus.apicrud.repository.ShelveRepository;
import com.pepcus.apicrud.service.ShelveService;
/**
 * 
 * 
 * @author Rajkumar.Saad
 * @since 05/12/2022
 *
 */
@Service
public class ShelveServiceImpl implements ShelveService {

	@Autowired
	private ShelveRepository shelveRepository;

	public ShelveServiceImpl(ShelveRepository shelveRepository) {
		super();
		this.shelveRepository = shelveRepository;
	}

	/**
	 * To save the shelve details.
	 * 
	 * @param shelve
	 * @return shelve
	 */

	@Override
	public Shelve saveShelve(Shelve shelve) {

		return shelveRepository.save(shelve);

	}

	@Override
	public Shelve saveBook(Integer id, List<Book> bookList) {
		Optional<Shelve> existingShelveOptional = shelveRepository.findById(id);
		if (!existingShelveOptional.isPresent()) {
			throw new ResourceNoteFoundException("Not found Self with id = " + id, null, existingShelveOptional);
		}
		Shelve existingShelve = existingShelveOptional.get();
		List<Book> bookList1 = existingShelve.getBookList();
		bookList1.forEach(newBook -> bookList.add(newBook));
		existingShelve.setBookList(bookList);
		return shelveRepository.save(existingShelve);
	}

}
