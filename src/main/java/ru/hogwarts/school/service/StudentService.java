package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentList;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).get();
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) { studentRepository.deleteById(studentId);
    }

    public ArrayList<Student> getStudentsByAge(int age) {
        ArrayList<Student> listOfStudents = new ArrayList<>(studentRepository.findAll());
        ArrayList<Student> listOfStudentsByAge = new ArrayList<>();
        for (Student student: listOfStudents) {
            if (student.getAge() == age) {
                listOfStudentsByAge.add(student);
            }
        }
        return listOfStudentsByAge;
    }

    public List<Student> getStudentsAll() {
//        ArrayList<Student> listOfStudents = new ArrayList<Student>(studentRepository.findAll());
//        return listOfStudents;
        return studentRepository.findAll();
    }

    public ArrayList<Student> findByAgeBetween(int ageMin, int ageMax) {
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    public int getNumberAllStudentsInSchool() {
        return studentRepository.getNumberAllStudents();
    }

    public int getAverageAgeStudents() {
        return studentRepository.getAverageAgeStudentsInSchool();
    }

    public List<Student> getLastFiveStudents() {
        return studentRepository.getLastFiveStudentsFromList();
    }
}
