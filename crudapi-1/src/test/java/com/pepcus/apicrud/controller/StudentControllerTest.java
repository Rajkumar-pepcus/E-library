package com.pepcus.apicrud.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pepcus.apicrud.model.Student;
import com.pepcus.apicrud.service.impl.StudentServiceImpl;

//@RunWith(PowerMockRunner.class)

public class StudentControllerTest {

//	@Autowired
//	private MockMvc mockMvc;
	
	@InjectMocks
	private StudentController studentController;

	@Mock
	private StudentServiceImpl studentService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveStudent() throws Exception {
		Student student = new Student();
		student.setId(1);
		student.setName("ram");
		student.setEmail("r@gmail.com");
		student.setPhoneno("9006952341");
		student.setDepartment("economics");
		student.setAddressList(null);

		Mockito.when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(student);
		ResponseEntity<Student> rs = studentController.saveStudent(student);

		Assert.assertNotNull(rs);
	}

	@Test
	public void getStudentByIdTest() throws Exception {
		Student student = new Student();
		student.setId(1);
		student.setName("ram");
		student.setEmail("r@gmail.com");
		student.setPhoneno("9006952341");
		student.setDepartment("economics");
		student.setAddressList(null);
		System.out.println("studentController: " + studentController);
		ResponseEntity<Student> rs = studentController.getStudentById(1);
		Mockito.when(studentService.getStudentById(1L)).thenReturn(student);

		Assert.assertNotNull(rs);

		/*
		 * mockMvc.perform(MockMvcRequestBuilders.get("/api/student/2"))
		 * .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ram"))
		 * .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("r@gmail.com"))
		 * .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNo").value("9006952341"))
		 * .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("economics"))
		 * .andExpect(MockMvcResultMatchers.jsonPath("$.addressList").value(null))
		 * .andExpect(status().isOk());
		 */

	}
	@SuppressWarnings("unchecked")
	@Test
	public void getAllStudentTest() {
		 
		Student student = new Student();
		student.setId(1);
		student.setName("ram");
		student.setEmail("r@gmail.com");
		student.setPhoneno("9006952341");
		student.setDepartment("economics");
		student.setAddressList(null);
		List<Student> studentList= new ArrayList<>();
		studentList.add(student);
		List<Student> rs =  studentController.getAllStudents();
		Mockito.when(studentService.getAllStudents()).thenReturn( (studentList));

		Assert.assertNotNull(rs);
		
	}
    public void updateStudentTest() {
    	
    }
	
	@Test
	public void testMe() {
		assert (true);
	}

	public void testGetStudentById() {

	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
