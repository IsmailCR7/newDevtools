package ru.mentee.power.devtools.student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testStudentCreation() {
        Student student = new Student("Иван Петров", "Москва");
        assertEquals("Иван Петров", student.name());
        assertEquals("Москва", student.city());
    }

    @Test
    void testIsFromCity() {
        Student student = new Student("Иван Петров", "Москва");

        assertTrue(student.isFromCity("Москва"));
        assertFalse(student.isFromCity("Санкт-Петербург"));
        assertFalse(student.isFromCity(null));
    }

    @Test
    void testStudentWithNullCity() {
        Student student = new Student("Иван", null);
        assertFalse(student.isFromCity(null));

    }

    @Test
    void testEqualsAndHashCode() {
        Student student1 = new Student("Иван", "Москва");
        Student student2 = new Student("Иван", "Москва");
        Student student3 = new Student("Мария", "Москва");

        assertEquals(student1, student2);
        assertEquals(student1.hashCode(), student2.hashCode());
        assertNotEquals(student1, student3);
    }
}
