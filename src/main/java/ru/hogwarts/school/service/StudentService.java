package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentList;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {

        logger.debug("Called: createStudent(Student student)");
        return studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        logger.debug("Called: getStudentById(Long studentId)");
        return studentRepository.findById(studentId).get();
    }

    public Student updateStudent(Student student) {
        logger.debug("Called: updateStudent(Student student)");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        logger.debug("Called: deleteStudent(Long studentId)");
        studentRepository.deleteById(studentId);
    }

    public ArrayList<Student> getStudentsByAge(int age) {
        logger.debug("Called: getStudentsByAge(int age)");
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
        logger.debug("Called: getStudentsAll()");
        return studentRepository.findAll();
    }

    public ArrayList<Student> findByAgeBetween(int ageMin, int ageMax) {
        logger.debug("Called: findByAgeBetween(int ageMin, int ageMax)");
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    public int getNumberAllStudentsInSchool() {
        logger.debug("Called: getNumberAllStudentsInSchool()");
        return studentRepository.getNumberAllStudents();
    }

    public int getAverageAgeStudents() {
        logger.debug("Called: getAverageAgeStudents()");
        return studentRepository.getAverageAgeStudentsInSchool();
    }

    public List<Student> getLastFiveStudents() {
        logger.debug("Called: getLastFiveStudents()");
        return studentRepository.getLastFiveStudentsFromList();
    }

    public List<String> getNamesStudentsUsingFilter(String letter) {
        List<String> namesOfStudents = studentRepository.findAll().stream()
                .filter((p) -> p.getName().startsWith(letter))
                .map((p) -> p.getName())
                .sorted()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return namesOfStudents;
    }

    public int getMiddleAgeStudents() {
        int middleAge = (int) studentRepository.findAll().stream()
                .mapToInt((p) -> p.getAge())
                .average()
                .getAsDouble();
        return middleAge;
    }

    public List<Student> getNameStudentsToConsole() {

        List<Student> students = studentRepository.findAll();

        Thread thread1 = new Thread(() -> {
            System.out.println("Student №3:  " + students.get(2).getName());
            System.out.println("Student №4:  " + students.get(3).getName());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Student №5:  " + students.get(4).getName());
            System.out.println("Student №6:  " + students.get(5).getName());
        });

        System.out.println("Student №1:  " + students.get(0).getName());
        System.out.println("Student №2:  " + students.get(1).getName());
        thread1.start();
        thread2.start();

        return students;
    }

    public void getNameStudentsToConsoleSynch() {
        List<Student> students = studentRepository.findAll();

        Thread thread1 = new Thread(() -> {
            printName(students,2);
            printName(students,3);
        });
        Thread thread2 = new Thread(() -> {
            printName(students,4);
            printName(students,5);
        });
        printName(students,0);
        printName(students,1);
        thread1.start();
        thread2.start();
    }

    public synchronized void printName(List<Student> students, int serialNumber) {
        String infoForPrint = "Student №" + serialNumber + ": " +
                               students.get(serialNumber).getName();
        System.out.println(infoForPrint);
    }
}
