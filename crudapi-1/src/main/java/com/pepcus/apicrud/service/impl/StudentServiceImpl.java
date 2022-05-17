package com.pepcus.apicrud.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pepcus.apicrud.exception.ResourceNoteFoundException;
import com.pepcus.apicrud.model.Student;
import com.pepcus.apicrud.repository.StudentRepository;
import com.pepcus.apicrud.service.StudentService;

/**
 * 
 * 
 * @author Rajkumar.Saad
 * @since 07/04/2022
 *
 */
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	/**
	 * To save the Student details.
	 * 
	 * @param student
	 * @return Student
	 */
	@Override
	@Transactional
	public Student saveStudent(Student student) {

//		List<Address> addressList = new ArrayList<>();
//		student.getAddressList().stream().map(a -> addressList.add(a)).collect(Collectors.toList());
//		student.setAddressList(addressList);

		return studentRepository.save(student);
	}

	/**
	 * To get the all student details
	 * 
	 */
	@Override
	public List<Student> getAllStudents() {

		return studentRepository.findAll();
	}

	/**
	 * To get the student details by id
	 */
	@Override
	public Student getStudentById(long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			return student.get();
		} else {
			throw new ResourceNoteFoundException("student", "id", id);
		}

	}

	/**
	 * To update the student details by id
	 */
	@Override
	public Student updateStudent(Student student, long id) {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNoteFoundException("Student", "Id", id));
		existingStudent.setName(student.getName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setPhoneno(student.getPhoneno());
		existingStudent.setAddressList(student.getAddressList());
		studentRepository.save(existingStudent);
		return existingStudent;
	}

	/**
	 * To delete the student details
	 * 
	 */
	@Override
	public void deleteStudent(long id) {
		studentRepository.findById(id).orElseThrow(() -> new ResourceNoteFoundException("Student", "Id", id));
		studentRepository.deleteById(id);
	}

	/**
	 * To sorting the student details by field
	 */
	@Override
	public List<Student> sortStudent(String field) {

		return studentRepository.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	/**
	 * To pagination of student details
	 * 
	 */
	@Override

	public Page<Student> findStudentByPagination(Integer offset, Integer pageSize) {

		Page<Student> pages = studentRepository.findAll(PageRequest.of(offset, pageSize));
		return pages;
	}
     
	/**
	 * search by department 
	 * 
	 */
	@Override
	public List<Student> getStudentByDepartment(String department) {
		return studentRepository.findByDepartment(department);

	}

	/*
	 * get student by name
	 */
	@Override
	public List<Student> getStudentByName(String name) {
		return studentRepository.findByName(name);

	}

}
