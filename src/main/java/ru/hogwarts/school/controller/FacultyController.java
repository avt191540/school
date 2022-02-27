package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createStudent(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculty = facultyService.updateFaculty(faculty);
        if (editFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editFaculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<ArrayList<Faculty>> getFacultiesByColor(@PathVariable String color) {
        ArrayList<Faculty> listOfFacultiesByColor = facultyService.getFacultiesByColor(color);
        if (listOfFacultiesByColor.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listOfFacultiesByColor);
    }

    @GetMapping("/all")
    public ResponseEntity<ArrayList<Faculty>> getFacultiesAll() {
        ArrayList<Faculty> listOfFaculties = facultyService.getFacultiesAll();
        if (listOfFaculties.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listOfFaculties);
    }
}
