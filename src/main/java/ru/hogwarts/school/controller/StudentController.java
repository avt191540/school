package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student editStudent = studentService.updateStudent(student);
        if (editStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ok(editStudent);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ok(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ok().build();
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<ArrayList<Student>> getStudentsByAge(@PathVariable int age) {
        ArrayList<Student> listOfStudentsByAge = studentService.getStudentsByAge(age);
        if (listOfStudentsByAge.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ok(listOfStudentsByAge);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudentsAll() {
        List<Student> listOfStudents = studentService.getStudentsAll();
        if (listOfStudents.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listOfStudents);
    }

    @GetMapping("/age/between")
    public ResponseEntity<ArrayList<Student>> findStudentsByAgeBetween(@RequestParam int ageMin, @RequestParam int ageMax) {
        ArrayList<Student> listOfStudentsByAgeBetween = studentService.findByAgeBetween(ageMin, ageMax);
        if (listOfStudentsByAgeBetween.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ok(listOfStudentsByAgeBetween);
    }

    @GetMapping("/number-all")
    public String getNumberAllStudents() {
        return "Количество всех студентов в школе: " + studentService.getNumberAllStudentsInSchool();
    }

    @GetMapping("/average-age")
    public String getAverageAge() {
        return "Средний возраст студентов: " + studentService.getAverageAgeStudents();
    }

    @GetMapping("/last-five")
    public List<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }

    @GetMapping("/names/{letter}")
    public ResponseEntity<List<String>> getNamesStudentsFiltered(@PathVariable String letter) {
        List<String> namesOfStudentsFiltered = studentService.getNamesStudentsUsingFilter(letter);
        if (namesOfStudentsFiltered.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ok(namesOfStudentsFiltered);
    }

    @GetMapping("/middle-age")
    public String getMiddleAge() {
        return "Средний возраст студентов в школе: " + studentService.getMiddleAgeStudents();
    }

    @GetMapping("/name-thread")
    public ResponseEntity<String> getNameStudentsToConsole() {
        List<Student> listOfStudentName = studentService. getNameStudentsToConsole () ;
        if (listOfStudentName.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ok("See the list of student names in the console");
    }

    @GetMapping("/name-synch")
    public String getNameStudentsToConsoleSynch() {
        studentService.getNameStudentsToConsoleSynch();
        return  "See the list of student names in the console";
    }
}
