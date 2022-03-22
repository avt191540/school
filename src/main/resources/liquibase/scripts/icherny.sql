-- liquibase formatted sql

-- changeSet icherny:1
CREATE INDEX student_name_index ON student (name);

-- changeSet icherny:2
CREATE INDEX faculty_nc_index ON faculty (name, color);