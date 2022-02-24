package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    private long counterId = 0;
    private Map<Long, Faculty> faculties = new HashMap<>();

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

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

    public ArrayList<Faculty> getFacultiesByColor(String color) {
        ArrayList<Faculty> listOfFaculties = new ArrayList<>(faculties.values());
        ArrayList<Faculty> listOfFacultiesByColor = new ArrayList<>();
        for (Faculty faculty: listOfFaculties) {
            if (faculty.getColor().equals(color)) {
                listOfFacultiesByColor.add(faculty);
            }
        }
        return listOfFacultiesByColor;
    }

}
