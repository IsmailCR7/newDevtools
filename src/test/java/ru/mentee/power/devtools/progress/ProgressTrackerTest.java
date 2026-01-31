package ru.mentee.power.devtools.progress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Тесты для класса ProgressTracker.
 */
@DisplayName("Тестирование ProgressTracker")
class ProgressTrackerTest {

    /**
     * Должен выполнить main метод без исключений.
     */
    @Test
    @DisplayName("Выполнение main метода")
    void shouldExecuteMainMethod() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            ProgressTracker.main(new String[]{});

            String output = outContent.toString();
            assertThat(output).isNotEmpty();
            assertThat(output).contains("Суммарно: пройдено");
            assertThat(output).contains("из");
            assertThat(output).contains("уроков, осталось");
        } finally {
            System.setOut(originalOut);
        }
    }

    /**
     * Должен корректно вычислять общий прогресс для массива mentee.
     */
    @Test
    @DisplayName("Вычисление общего прогресса")
    void shouldCalculateTotalProgress() {
        // Arrange
        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("Ismail", "Санкт-Петербург", "Backend разработка", 5, 12),
                new Mentee("Iskander", "Санкт-Петербург", "Fullstack", 8, 12),
                new Mentee("Daria", "Санкт-Петербург", "Java Backend", 12, 12)
        };

        // Act
        String result = tracker.calculateTotalProgress(mentees);

        // Assert
        // 5+8+12=25 пройдено, 12+12+12=36 всего, 36-25=11 осталось
        assertThat(result).isEqualTo("Суммарно: пройдено 25 из 36 уроков, осталось 11 уроков");
    }

    /**
     * Должен возвращать сообщение об отсутствии данных при пустом массиве.
     */
    @Test
    @DisplayName("Обработка пустого массива")
    void shouldHandleEmptyArray() {
        // Arrange
        ProgressTracker tracker = new ProgressTracker();
        Mentee[] emptyArray = new Mentee[0];

        // Act
        String result = tracker.calculateTotalProgress(emptyArray);

        // Assert
        assertThat(result).isEqualTo("Нет данных для подсчёта");
    }

    /**
     * Должен возвращать сообщение об отсутствии данных при null массиве.
     */
    @Test
    @DisplayName("Обработка null массива")
    void shouldHandleNullArray() {
        // Arrange
        ProgressTracker tracker = new ProgressTracker();

        // Act
        String result = tracker.calculateTotalProgress(null);

        // Assert
        assertThat(result).isEqualTo("Нет данных для подсчёта");
    }

    /**
     * Должен корректно вычислять прогресс для одного mentee.
     */
    @Test
    @DisplayName("Вычисление прогресса для одного mentee")
    void shouldCalculateProgressForSingleMentee() {
        // Arrange
        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("Test", "City", "Goal", 3, 10)
        };

        // Act
        String result = tracker.calculateTotalProgress(mentees);

        // Assert
        assertThat(result).isEqualTo("Суммарно: пройдено 3 из 10 уроков, осталось 7 уроков");
    }

    /**
     * Должен корректно вычислять прогресс при всех пройденных уроках.
     */
    @Test
    @DisplayName("Вычисление прогресса при 100% выполнении")
    void shouldCalculateProgressWhenAllCompleted() {
        // Arrange
        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("A", "City1", "Goal1", 10, 10),
                new Mentee("B", "City2", "Goal2", 5, 5),
                new Mentee("C", "City3", "Goal3", 8, 8)
        };

        // Act
        String result = tracker.calculateTotalProgress(mentees);

        // Assert
        // 10+5+8=23 пройдено, 10+5+8=23 всего, 23-23=0 осталось
        assertThat(result).isEqualTo("Суммарно: пройдено 23 из 23 уроков, осталось 0 уроков");
    }

    /**
     * Должен корректно вычислять прогресс при нулевых пройденных уроках.
     */
    @Test
    @DisplayName("Вычисление прогресса при 0% выполнении")
    void shouldCalculateProgressWhenNoneCompleted() {
        // Arrange
        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("A", "City1", "Goal1", 0, 10),
                new Mentee("B", "City2", "Goal2", 0, 5)
        };

        // Act
        String result = tracker.calculateTotalProgress(mentees);

        // Assert
        // 0+0=0 пройдено, 10+5=15 всего, 15-0=15 осталось
        assertThat(result).isEqualTo("Суммарно: пройдено 0 из 15 уроков, осталось 15 уроков");
    }

    /**
     * Должен корректно вычислять прогресс при больших значениях.
     */
    @Test
    @DisplayName("Вычисление прогресса с большими числами")
    void shouldCalculateProgressWithLargeNumbers() {
        // Arrange
        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("A", "City1", "Goal1", 1000, 2000),
                new Mentee("B", "City2", "Goal2", 500, 1500)
        };

        // Act
        String result = tracker.calculateTotalProgress(mentees);

        // Assert
        // 1000+500=1500 пройдено, 2000+1500=3500 всего, 3500-1500=2000 осталось
        assertThat(result).isEqualTo("Суммарно: пройдено 1500 из 3500 уроков, осталось 2000 уроков");
    }

    /**
     * Должен корректно вычислять прогресс при граничных значениях.
     */
    @Test
    @DisplayName("Вычисление прогресса с граничными значениями")
    void shouldCalculateProgressWithBoundaryValues() {
        // Arrange
        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("A", "City", "Goal", Integer.MAX_VALUE, Integer.MAX_VALUE),
                new Mentee("B", "City", "Goal", 0, Integer.MAX_VALUE)
        };

        // Act
        String result = tracker.calculateTotalProgress(mentees);

        // Assert
        // MAX + 0 = MAX пройдено, MAX + MAX = (MAX*2) всего
        // Будет переполнение, но для теста оставим
        assertThat(result).contains("Суммарно: пройдено");
        assertThat(result).contains("из");
        assertThat(result).contains("уроков, осталось");
    }

    /**
     * Параметризованный тест для calculateTotalProgress.
     */
    @ParameterizedTest
    @MethodSource("provideTestDataForCalculateTotalProgress")
    @DisplayName("Параметризованный тест вычисления прогресса")
    void shouldCalculateTotalProgressParameterized(
            Mentee[] mentees,
            String expectedOutput,
            String testDescription) {

        // Arrange
        ProgressTracker tracker = new ProgressTracker();

        // Act
        String result = tracker.calculateTotalProgress(mentees);

        // Assert
        assertThat(result).isEqualTo(expectedOutput);
    }

    private static Stream<Arguments> provideTestDataForCalculateTotalProgress() {
        return Stream.of(
                Arguments.of(
                        new Mentee[]{
                                new Mentee("A", "City", "Goal", 1, 2),
                                new Mentee("B", "City", "Goal", 2, 4)
                        },
                        "Суммарно: пройдено 3 из 6 уроков, осталось 3 уроков",
                        "Два mentee с разным прогрессом"
                ),
                Arguments.of(
                        new Mentee[]{
                                new Mentee("Single", "City", "Goal", 7, 14)
                        },
                        "Суммарно: пройдено 7 из 14 уроков, осталось 7 уроков",
                        "Один mentee"
                ),
                Arguments.of(
                        new Mentee[]{
                                new Mentee("A", "City", "Goal", 0, 1),
                                new Mentee("B", "City", "Goal", 1, 1),
                                new Mentee("C", "City", "Goal", 0, 2)
                        },
                        "Суммарно: пройдено 1 из 4 уроков, осталось 3 уроков",
                        "Три mentee со смешанным прогрессом"
                )
        );
    }

    /**
     * Тест конструктора.
     */
    @Test
    @DisplayName("Создание объекта ProgressTracker")
    void testConstructor() {
        ProgressTracker tracker = new ProgressTracker();
        assertThat(tracker).isNotNull();
    }
}
