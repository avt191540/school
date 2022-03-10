package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private StudentController studentController;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() throws Exception {
		Assertions.assertThat(studentController).isNotNull();
	}

	@Test
	public void testPostStudent() throws Exception {
		Student student = new Student();
		student.setStudentId(8L);
		student.setName("Ivan");
		student.setAge(18);

		Assertions
				.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class))
				.isNotNull();
	}

	@Test
	public void testGetStudentById() throws Exception {
		Student studentExpected = new Student();
		studentExpected.setStudentId(8L);
		studentExpected.setName("Ivan");
		studentExpected.setAge(18);

		Assertions
				.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/8", Student.class))
				.isEqualTo(studentExpected);
	}

	@Test
	public void testGetStudentByAge() throws Exception {
		Assertions
				.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age/21", ArrayList.class))
				.isInstanceOf(ArrayList.class);
	}

	@Test
	public void testGetStudentsAll() throws Exception {
		Assertions
				.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/all", ArrayList.class))
				.isExactlyInstanceOf(ArrayList.class);
	}

	@Test
	public void testFindStudentsByAgeBetween() throws Exception {
		Assertions
				.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age/between?ageMin=14&ageMax=25", ArrayList.class))
				.isExactlyInstanceOf(ArrayList.class);
	}

	@Test
	public void testUpdateStudent() throws Exception {
		Student student = new Student();
		student.setStudentId(6L);
		student.setName("Petr");
		student.setAge(20);
		HttpEntity<Student> entity = new HttpEntity<Student>(student);
		ResponseEntity<Student> response = restTemplate.exchange("http://localhost:" + port + "/student", HttpMethod.PUT, entity, Student.class);

		Assertions
				.assertThat(response.getBody()).isEqualTo(student);
	}

	@Test
	public void testDeleteStudent() throws Exception {
		Student student = new Student();
		student.setStudentId(9L);
		student.setName("Bob");
		student.setAge(23);
		HttpEntity<Student> entity = new HttpEntity<Student>(student);
		ResponseEntity<Student> response = restTemplate.exchange("http://localhost:" + port + "/student/9", HttpMethod.DELETE, entity, Student.class);

		Assertions
				.assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
	}
}
