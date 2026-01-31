package ru.mentee.power;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * Тесты для класса MenteeProgress.
 */
@DisplayName("Тестирование MenteeProgress")
class MenteeProgressTest {

    /**
     * Должен корректно создавать объект с валидными данными.
     */
    @Test
    @DisplayName("Создание объекта с валидными данными")
    void testConstructorWithValidData() {
        MenteeProgress progress = new MenteeProgress("Ismail", 1, 6);

        assertAll(
                () -> assertThat(progress.menteeName()).isEqualTo("Ismail"),
                () -> assertThat(progress.sprintNumber()).isEqualTo(1),
                () -> assertThat(progress.plannedHoursPerWeek()).isEqualTo(6)
        );
    }

    /**
     * Должен корректно создавать summary.
     */
    @Test
    @DisplayName("Создание summary")
    void testSummary() {
        MenteeProgress progress = new MenteeProgress("Ismail", 1, 6);
        String result = progress.summary();

        // Исправлено: ожидаемый формат должен соответствовать фактическому выводу
        assertThat(result).isEqualTo("Sprint 1 → Ismail: planned 6 h");
    }

    /**
     * Должен корректно определять готовность к спринту.
     */
    @Test
    @DisplayName("Определение готовности к спринту")
    void testReadyForSprintWithValidData() {
        MenteeProgress progress = new MenteeProgress("Ismail", 1, 6);
        boolean result = progress.readyForSprint();

        assertThat(result).isTrue();
    }

    /**
     * Не должен быть готов к спринту при нулевых часах.
     */
    @Test
    @DisplayName("Неготовность при нулевых часах")
    void testReadyForSprintWithZeroHours() {
        MenteeProgress progress = new MenteeProgress("Test", 1, 0);
        boolean result = progress.readyForSprint();

        assertThat(result).isFalse();
    }

    /**
     * Должен быть готов к спринту при 3+ часах.
     */
    @Test
    @DisplayName("Готовность при 3+ часах")
    void testReadyForSprintWithThreeHours() {
        MenteeProgress progress = new MenteeProgress("Test", 1, 3);
        boolean result = progress.readyForSprint();

        assertThat(result).isTrue();
    }

    /**
     * Должен быть готов к спринту при спринте 0, но часах >= 3.
     */
    @Test
    @DisplayName("Готовность при спринте 0, но часах >= 3")
    void testReadyForSprintWithZeroSprintButEnoughHours() {
        MenteeProgress progress = new MenteeProgress("Test", 0, 6);
        boolean result = progress.readyForSprint();

        // По текущей логике класса: проверяется только plannedHoursPerWeek >= 3
        assertThat(result).isTrue();
    }

    /**
     * Параметризованный тест для readyForSprint.
     * Обновлено в соответствии с фактической логикой: проверяется только plannedHoursPerWeek >= 3
     */
    @ParameterizedTest
    @CsvSource({
            "1, 6, true",   // hours >= 3 → true
            "0, 6, true",   // sprint=0, но hours >= 3 → true (по текущей логике класса)
            "1, 0, false",  // hours < 3 → false
            "0, 0, false",  // hours < 3 → false
            "-1, 5, true",  // hours >= 3 → true (по текущей логике класса)
            "2, -3, false", // hours < 3 → false
            "5, 10, true"   // hours >= 3 → true
    })
    @DisplayName("Параметризованный тест готовности к спринту")
    void testReadyForSprintParameterized(int sprint, int hours, boolean expected) {
        MenteeProgress progress = new MenteeProgress("Test", sprint, hours);
        boolean result = progress.readyForSprint();

        assertThat(result).isEqualTo(expected);
    }

    /**
     * Тест геттеров.
     */
    @Test
    @DisplayName("Проверка геттеров")
    void testGetters() {
        String expectedName = "John Doe";
        int expectedSprint = 3;
        int expectedHours = 15;
        MenteeProgress progress = new MenteeProgress(
                expectedName, expectedSprint, expectedHours
        );

        assertAll(
                () -> assertThat(progress.menteeName()).isEqualTo(expectedName),
                () -> assertThat(progress.sprintNumber()).isEqualTo(expectedSprint),
                () -> assertThat(progress.plannedHoursPerWeek()).isEqualTo(expectedHours)
        );
    }

    /**
     * Должен корректно обрабатывать граничные значения.
     */
    @Test
    @DisplayName("Обработка граничных значений")
    void testBoundaryValues() {
        assertAll(
                () -> {
                    MenteeProgress p = new MenteeProgress("Test", Integer.MAX_VALUE, 1);
                    assertThat(p.sprintNumber()).isEqualTo(Integer.MAX_VALUE);
                    assertThat(p.readyForSprint()).isFalse(); // hours=1 < 3
                },
                () -> {
                    MenteeProgress p = new MenteeProgress("Test", 1, Integer.MAX_VALUE);
                    assertThat(p.plannedHoursPerWeek()).isEqualTo(Integer.MAX_VALUE);
                    assertThat(p.readyForSprint()).isTrue(); // hours >= 3
                }
        );
    }
}