package ru.mentee.power.devtools.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {
    private StudentList studentList;
    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        studentList = new StudentList();
        student1 = new Student("Иван Петров", "Москва");
        student2 = new Student("Мария Сидорова", "Санкт-Петербург");
    }

    @Test
    void testAddAndCount() {
        assertEquals(0, studentList.countStudents());

        studentList.addStudent(student1);
        assertEquals(1, studentList.countStudents());

        studentList.addStudent(student2);
        assertEquals(2, studentList.countStudents());
    }

    @Test
    void testAddNull() {
        studentList.addStudent(null);
        assertEquals(0, studentList.countStudents());
    }

    @Test
    void testGetAllStudents() {
        studentList.addStudent(student1);
        studentList.addStudent(student2);

        List<Student> students = studentList.getAllStudents();
        assertEquals(2, students.size());
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student2));
    }

    @Test
    void testFindByCity() {
        Student student3 = new Student("Анна", "Москва");

        studentList.addStudent(student1);
        studentList.addStudent(student2);
        studentList.addStudent(student3);

        List<Student> moscow = studentList.findStudentsByCity("Москва");
        assertEquals(2, moscow.size());

        List<Student> spb = studentList.findStudentsByCity("Санкт-Петербург");
        assertEquals(1, spb.size());

        List<Student> empty = studentList.findStudentsByCity("Казань");
        assertTrue(empty.isEmpty());
    }

    @Test
    void testEmptyListOperations() {
        assertTrue(studentList.getAllStudents().isEmpty());
        assertTrue(studentList.findStudentsByCity("Москва").isEmpty());
        assertEquals(0, studentList.countStudents());
    }
}