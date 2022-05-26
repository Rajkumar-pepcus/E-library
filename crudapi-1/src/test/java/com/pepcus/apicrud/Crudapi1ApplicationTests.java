package com.pepcus.apicrud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pepcus.apicrud.model.Student;
import com.pepcus.apicrud.service.impl.StudentServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Crudapi1ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private StudentServiceImpl studentServiceImpl;

	@Test
	public void testSaveStudent() throws Exception {

		Student mockStudent = new Student();
		mockStudent.setId(1);
		mockStudent.setName("mahendra");
		mockStudent.setEmail("m@gmail.com");
		mockStudent.setPhoneno("9756439402");
		mockStudent.setDepartment("economics");
		mockStudent.setAddressList(null);

		String inputInJson = this.mapToJson(mockStudent);

		String URI = "/api/student";

		Mockito.when(studentServiceImpl.saveStudent(Mockito.any(Student.class))).thenReturn(mockStudent);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
