package ru.mentee.power.devtools.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StudentList {

    // Исправлено: camelCase
    private List<Student> studentList;

    public StudentList() {
        studentList = new ArrayList<>();
    }

    // Исправлено: camelCase и пробел после if
    public void addStudent(Student student) {
        if (student != null) {
            studentList.add(student);
        }
    }

    // Исправлено: camelCase
    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(studentList);
    }

    // Исправлено: короткое имя в camelCase
    public List<Student> findStudentsByCity(String city) {
        return studentList.stream()
                .filter(s -> s.isFromCity(city))
                .collect(Collectors.toList());
    }

    // Исправлено: camelCase
    public int countStudents() {
        return studentList.size();
    }
}
