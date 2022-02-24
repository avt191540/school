package ru.hogwarts.school.model;

public class Faculty {
    private long facultyId;
    private String name;
    private String color;

    public Faculty(long facultyId, String name, String color) {
        this.facultyId = facultyId;
        this.name = name;
        this.color = color;
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

    public void setColor(String color) {
        this.color = color;
    }
}
