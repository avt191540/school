package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.debug("Called: createFaculty(Faculty faculty)");
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facultyId) {
        logger.debug("Called: getFacultyById(Long facultyId)");
        return facultyRepository.findById(facultyId).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.debug("Called: updateFaculty(Faculty faculty)");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyId) {
        logger.debug("Called: deleteFaculty(Long facultyId)");
        facultyRepository.deleteById(facultyId);
    }

    public ArrayList<Faculty> getFacultiesByColor(String color) {
        logger.debug("Called: getFacultiesByColor(String color)");
        ArrayList<Faculty> listOfFaculties = new ArrayList<>(facultyRepository.findAll());
        ArrayList<Faculty> listOfFacultiesByColor = new ArrayList<>();
        for (Faculty faculty: listOfFaculties) {
            if (faculty.getColor().equals(color)) {
                listOfFacultiesByColor.add(faculty);
            }
        }
        return listOfFacultiesByColor;
    }

    public ArrayList<Faculty> getFacultiesAll() {
        logger.debug("Called: getFacultiesAll()");
        ArrayList<Faculty> listOfFaculties = new ArrayList<>(facultyRepository.findAll());
        return listOfFaculties;
    }

    public ArrayList<Faculty> findFacultiesByColorOrName(String colorOrName) {
        logger.debug("Called: findFacultiesByColorOrName");
        ArrayList<Faculty> listFacultiesByName = facultyRepository.findFacultiesByNameIgnoreCase(colorOrName);
        if (listFacultiesByName.size() != 0) {
            return listFacultiesByName;
        }
        return facultyRepository.findFacultiesByColorIgnoreCase(colorOrName);
    }
}
