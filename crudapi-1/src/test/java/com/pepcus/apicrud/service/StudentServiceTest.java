package com.pepcus.apicrud.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.pepcus.apicrud.model.Student;
import com.pepcus.apicrud.repository.StudentRepository;
import com.pepcus.apicrud.service.impl.StudentServiceImpl;

public class StudentServiceTest {

	@InjectMocks
	private StudentServiceImpl studentServiceImpl;
	
	@Mock
	private StudentRepository studentRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSaveStudent(){

		Student student = new Student();
		student.setId(1);
		student.setName("ram");
		student.setEmail("r@gmail.com");
		student.setPhoneno("9006952341");
		student.setDepartment("economics");
		student.setAddressList(null);
		
	    Mockito.when(studentRepository.save(student)).thenReturn(student);
	    
	    assertThat(studentRepository.save(student)).isEqualTo(student);
	
	}
	/*
	@Test
	public void getStudentByIdTest(long id){
		Student student = new Student();
		student.setId(1);
		student.setName("ram");
		student.setEmail("r@gmail.com");
		student.setPhoneno("9006952341");
		student.setDepartment("economics");
		student.setAddressList(null);
		
	    Mockito.when(studentRepository.getById( 1L)).thenReturn(student);
	   // assertThat(StudentServiceImpl.getStudentById(1L)).isEqualTo(student);
	}
	*/
	
}
