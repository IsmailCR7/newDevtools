package ru.mentee.power.devtools.progress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * Тесты для класса ProgressTracker.
 */
@DisplayName("Тестирование ProgressTracker")
class ProgressLoopTest {

    /**
     * Должен корректно вычислить суммарный прогресс при передаче массива.
     */
    @Test
    @DisplayName("Вычисление суммарного прогресса для массива")
    void shouldCalculateTotalProgressWhenMultipleMentees() {
        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("Ismail", "Санкт-Петербург", "Backend разработка", 5, 12),
                new Mentee("Iskander", "Санкт-Петербург", "Fullstack", 8, 12),
                new Mentee("Daria", "Санкт-Петербург", "Java Backend", 12, 12)
        };

        String result = tracker.calculateTotalProgress(mentees);

        assertThat(result)
                .contains("пройдено 25 из 36 уроков")
                .contains("осталось 11 уроков");
    }

    /**
     * Должен выбросить исключение при некорректных данных.
     */
    @Test
    @DisplayName("Исключение при некорректных данных")
    void shouldThrowExceptionWhenInvalidData() {
        assertThatThrownBy(() ->
                new Mentee("Ошибка", "Город", "Цель", 15, 10)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Некорректные значения прогресса");
    }

    /**
     * Должен корректно обработать пустой массив.
     */
    @Test
    @DisplayName("Обработка пустого массива")
    void shouldHandleEmptyMenteesArray() {
        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = new Mentee[0];

        String result = tracker.calculateTotalProgress(mentees);

        assertThat(result).isEqualTo("Нет данных для подсчёта");
    }

    /**
     * Должен корректно вычислить прогресс для одного mentee.
     */
    @Test
    @DisplayName("Вычисление прогресса для одного mentee")
    void shouldCalculateProgressForSingleMentee() {
        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("Single", "City", "Goal", 5, 10)
        };

        String result = tracker.calculateTotalProgress(mentees);

        assertThat(result)
                .contains("пройдено 5 из 10 уроков")
                .contains("осталось 5 уроков");
    }

    /**
     * Должен корректно вычислить при полностью пройденных уроках.
     */
    @Test
    @DisplayName("Вычисление при полностью пройденных уроках")
    void shouldCalculateWhenAllLessonsCompleted() {
        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("Mentee1", "City", "Goal", 10, 10),
                new Mentee("Mentee2", "City", "Goal", 15, 15)
        };

        String result = tracker.calculateTotalProgress(mentees);

        assertThat(result)
                .contains("пройдено 25 из 25 уроков")
                .contains("осталось 0 уроков");
    }

    /**
     * Должен корректно обработать случай равенства completed и total.
     */
    @Test
    @DisplayName("Обработка равенства completed и total")
    void shouldHandleCompletedEqualsTotal() {
        Mentee mentee = new Mentee("Test", "City", "Goal", 10, 10);

        assertThat(mentee.completedLessons()).isEqualTo(10);
        assertThat(mentee.totalLessons()).isEqualTo(10);
    }

    /**
     * Должен выбросить исключение при отрицательном completed.
     */
    @Test
    @DisplayName("Исключение при отрицательном completed")
    void shouldThrowExceptionWhenNegativeCompleted() {
        assertThatThrownBy(() ->
                new Mentee("Ошибка", "Город", "Цель", -5, 10)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Некорректные значения прогресса");
    }

    /**
     * Должен выбросить исключение при отрицательном total.
     */
    @Test
    @DisplayName("Исключение при отрицательном total")
    void shouldThrowExceptionWhenNegativeTotal() {
        assertThatThrownBy(() ->
                new Mentee("Ошибка", "Город", "Цель", 5, -10)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Некорректные значения прогресса");
    }

    /**
     * Должен выбросить исключение при нулевом total.
     */
    @Test
    @DisplayName("Исключение при нулевом total")
    void shouldThrowExceptionWhenTotalIsZero() {
        assertThatThrownBy(() ->
                new Mentee("Ошибка", "Город", "Цель", 0, 0)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Некорректные значения прогресса");
    }

    /**
     * Должен создать mentee с нулевыми completed уроками.
     */
    @Test
    @DisplayName("Создание mentee с нулевыми completed уроками")
    void shouldCreateMenteeWithZeroCompleted() {
        Mentee mentee = new Mentee("Test", "City", "Goal", 0, 10);

        assertThat(mentee.completedLessons()).isEqualTo(0);
        assertThat(mentee.totalLessons()).isEqualTo(10);
    }

    /**
     * Должен корректно создавать record со всеми полями.
     */
    @Test
    @DisplayName("Создание record со всеми полями")
    void shouldCreateRecordWithAllFields() {
        Mentee mentee = new Mentee("Иван", "Москва", "Java Developer", 5, 10);

        assertAll(
                () -> assertThat(mentee.name()).isEqualTo("Иван"),
                () -> assertThat(mentee.city()).isEqualTo("Москва"),
                () -> assertThat(mentee.goal()).isEqualTo("Java Developer"),
                () -> assertThat(mentee.completedLessons()).isEqualTo(5),
                () -> assertThat(mentee.totalLessons()).isEqualTo(10)
        );
    }
}

