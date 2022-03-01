package ru.hogwarts.school.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
//@Table(name = "Faculty")//нет смысла, если назв. таблицы такое же как класс и не надо с большой буквы
public class Faculty {

    @Id
    @GeneratedValue
    private long facultyId;

    private String name;
    private String color;

    @OneToMany(mappedBy = "faculty")
    private ArrayList<Student> students;

    public Faculty() {
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
