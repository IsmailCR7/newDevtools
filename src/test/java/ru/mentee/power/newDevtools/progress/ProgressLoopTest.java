package ru.mentee.power.newDevtools.progress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Тестирование ProgressTracker")
class ProgressLoopTest {

    @Test
    @DisplayName("Должен корректно вычислить суммарный прогресс когда передан массив mentee")
    void shouldCalculateTotalProgress_whenMultipleMentees() {
        // Подготовка данных
        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("Ismail", "Санкт-Петербург", "Backend разработка", 5, 12),
                new Mentee("Iskander", "Санкт-Петербург", "Fullstack", 8, 12),
                new Mentee("Daria", "Санкт-Петербург", "Java Backend", 12, 12)
        };

        // Выполнение
        String result = tracker.calculateTotalProgress(mentees);

        // Проверка
        assertThat(result)
                .contains("пройдено 25 из 36 уроков")
                .contains("осталось 11 уроков");
    }

    @Test
    @DisplayName("Должен выбросить исключение когда completed больше total")
    void shouldThrowException_whenInvalidData() {
        // Проверяем, что создание Mentee с некорректными данными вызовет ошибку
        org.assertj.core.api.Assertions.assertThatThrownBy(() ->
                new Mentee("Ошибка", "Город", "Цель", 15, 10)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}