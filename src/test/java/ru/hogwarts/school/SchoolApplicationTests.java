package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
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
		student.setStudentId(4L);
		student.setName("Ivan");
		student.setAge(18);

		Assertions
				.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class))
				.isNotNull();
	}

	@Test
	public void testGetStudentById() throws Exception {
		Student studentExpected = new Student();
		studentExpected.setStudentId(4L);
		studentExpected.setName("Ivan");
		studentExpected.setAge(18);

		Assertions
				.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/4", Student.class))
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

	public void testUpdateStudent() throws Exception {
		Student student = new Student();
		student.setStudentId(5L);
		student.setName("Petr");
		student.setAge(20);

		Assertions
				.assertThat(this.restTemplate.put("http://localhost:" + port + "/student/age/between?ageMin=14&ageMax=25", student);)

	}
}
