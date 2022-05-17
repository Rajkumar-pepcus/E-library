package com.pepcus.apicrud.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepcus.apicrud.model.Student;
import com.pepcus.apicrud.repository.StudentRepository;
import com.pepcus.apicrud.service.StudentService;
import com.pepcus.apicrud.student.specification.StudentSpecification;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentService studentService;

	@PostMapping
	@Transactional
	public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
		return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);
	}
	/*
	 * Get All student detail
	 */

	@GetMapping()
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
      
	/*
	 * Get All student detail
	 */
	@GetMapping("{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentId) {
		return new ResponseEntity<Student>(studentService.getStudentById(studentId), HttpStatus.OK);
	}
	/*
	 * Update existing student by id
	 */

	@PutMapping("{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
		return new ResponseEntity<Student>(studentService.updateStudent(student, id), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") long id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<String>("student deleted", HttpStatus.OK);
	}
	/*
	 * This method for sorting by field
	 */

	@GetMapping("/sorts/{field}")
	public List<Student> sortStudent(@PathVariable String field) {
		List<Student> allStudent = studentService.sortStudent(field);

		return allStudent;
	}

	/*
	 * pagination
	 */
	@GetMapping("/pagination/{offset}/{pageSize}")
	public Page<Student> sortStudent(@PathVariable Integer offset, @PathVariable Integer pageSize) {
		Page<Student> students = studentService.findStudentByPagination(offset, pageSize);

		return students;
	}
	/*
	 * Search by department
	 */

	@GetMapping("/department/{department}")
	public List<Student> getStudentByDepartment(@PathVariable("department") String department) {
		List<Student> student = studentService.getStudentByDepartment(department);
		return student;
	}
	/*
	 * Search by name
	 */

	@GetMapping("/name/{name}")
	public List<Student> getStudentByName(@PathVariable("name") String name) {
		List<Student> student = studentService.getStudentByName(name);
		return student;
	}

	@GetMapping("/n/{name}/{department}")
	public List<Student> findByNameAndDepartment(@PathVariable("name") String name,
			@PathVariable("department") String department) {
		Specification<Student> specifications = Specification
				.where(StudentSpecification.hasName(name).and(StudentSpecification.hasDepartment(department)));

		return studentRepository.findAll(specifications);
	}
	
	@GetMapping("/e/{name}/{email}")
	public List<Student> findByNameAndEmail(@PathVariable("name") String name,
			@PathVariable("email") String email) {
		Specification<Student> specifications = Specification
				.where(StudentSpecification.hasName(name).and(StudentSpecification.hasEmail(email)));

		return studentRepository.findAll(specifications);
	}
	/*
	 * find by name and city
	 */
	@GetMapping("/nc/{name}/{city}")
	public List<Student> findByNameAndCity(@PathParam("name") String name,
			@PathParam("city") String city) {
		Specification<Student> specifications = Specification
				.where(StudentSpecification.hasName(name).and(StudentSpecification.hasCity(city)));

		return studentRepository.findAll(specifications);
	}
}
