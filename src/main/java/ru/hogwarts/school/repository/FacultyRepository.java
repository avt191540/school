package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    ArrayList<Faculty> findFacultiesByColorIgnoreCase(String color);

    ArrayList<Faculty> findFacultiesByNameIgnoreCase(String name);

}
