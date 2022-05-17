package com.pepcus.apicrud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pepcus.apicrud.model.Student;

public interface StudentService {

	Student saveStudent(Student student);

	List<Student> getAllStudents();

	Student getStudentById(long id);

	Student updateStudent(Student student, long id);

	void deleteStudent(long id);

	List<Student> sortStudent(String field);

	Page<Student> findStudentByPagination(Integer offset, Integer pageSize);

	List<Student> getStudentByDepartment(String department);

	List<Student> getStudentByName(String name);

	
}
