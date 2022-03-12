package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentList;

import java.util.ArrayList;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    ArrayList<Student> findByAgeBetween(int ageMin, int ageMax);


    @Query(value = "SELECT COUNT (*) FROM student", nativeQuery = true)
    int getNumberAllStudents();

    @Query(value = "SELECT avg(age) FROM student", nativeQuery = true)
    int getAverageAgeStudentsInSchool();

    @Query(value = "SELECT * FROM student ORDER BY student_id DESC LIMIT 5", nativeQuery = true)
    List<StudentList> getLastFiveStudentsFromList();

}
