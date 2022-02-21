package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private long counterId = 0;
    private Map<Long, Student> students = new HashMap<>();

    public Student createStudent(Student student) {
        while (students.containsKey(counterId)) {
            counterId++;
        }
        student.setStudentId(counterId);
        students.put(counterId, student);
        return student;
    }

    public Student getStudentById(Long studentId) {
        return students.get(studentId);
    }

    public Student updateStudent(Student student) {
        if (students.containsKey(student.getStudentId())) {
            students.put(student.getStudentId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(Long studentId) {
        return students.remove(studentId);
    }

}
