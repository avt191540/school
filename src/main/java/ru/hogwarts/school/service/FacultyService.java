package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {

    private long counterId = 0;
    private Map<Long, Faculty> faculties = new HashMap<>();

    public Faculty createFaculty(Faculty faculty) {
        while (faculties.containsKey(counterId)) {
            counterId++;
        }
        faculty.setFacultyId(counterId);
        faculties.put(counterId, faculty);
        return faculty;
    }

    public Faculty getFacultyById(Long facultyId) {
        return faculties.get(facultyId);
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getFacultyId())) {
            faculties.put(faculty.getFacultyId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(Long facultyId) {
        return faculties.remove(facultyId);
    }

}
