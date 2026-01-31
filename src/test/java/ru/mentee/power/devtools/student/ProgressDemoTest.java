package ru.mentee.power.devtools.student;

import org.junit.jupiter.api.Test;
import ru.mentee.power.ProgressDemo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тесты для класса ProgressDemo.
 */
class ProgressDemoTest {

    /**
     * Должен выполнить main метод без исключений.
     */
    @Test
    void shouldExecuteMainMethod() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            ProgressDemo.main(new String[]{});

            String output = outContent.toString();

            // Проверяем, что вывод не пустой
            assertThat(output).isNotEmpty();

            // Проверяем наличие ожидаемых строк (исправлено под фактический вывод)
            assertThat(output).contains("Sprint 1 → Ismail: planned 6 h");
            assertThat(output).contains("Status:");
            assertThat(output).contains("Текущая ветка разработки:");

            // Дополнительная проверка: либо "sprint ready", либо "backlog first"
            boolean hasStatus = output.contains("sprint ready") || output.contains("backlog first");
            assertThat(hasStatus).isTrue();

        } finally {
            System.setOut(originalOut);
        }
    }

    /**
     * Должен содержать информацию о ветке разработки.
     */
    @Test
    void shouldContainBranchInfo() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            ProgressDemo.main(new String[]{});

            String output = outContent.toString();

            // Исправлено: проверяем точную строку, которая выводится
            assertThat(output).contains("Текущая ветка разработки: feature/DVT-3");

        } finally {
            System.setOut(originalOut);
        }
    }
}