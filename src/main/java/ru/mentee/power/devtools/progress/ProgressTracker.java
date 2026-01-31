package ru.mentee.power.devtools.progress;

public class ProgressTracker {

    /**
     * Вычисляет суммарный прогресс группы mentee.
     *
     * @param mentees массив mentee
     * @return строка с информацией о суммарном прогрессе (пройдено/осталось уроков)
     */
    public String calculateTotalProgress(Mentee[] mentees) {
        if (mentees == null || mentees.length == 0) {
            return "Нет данных для подсчёта";
        }

        // Инициализируем переменные для подсчета
        int totalCompleted = 0;  // всего пройдено уроков
        int totalTotal = 0;      // всего уроков во всех курсах
        int index = 0;           // счетчик для цикла

        // Цикл while - повторяем пока index меньше длины массива
        while (index < mentees.length) {
            // Берем данные текущего студента
            Mentee current = mentees[index];
            totalCompleted += current.completedLessons();
            totalTotal += current.totalLessons();

            // Увеличиваем счетчик - важно! без этого цикл будет бесконечным
            index++;
        }

        // Считаем сколько осталось
        int totalRemaining = totalTotal - totalCompleted;

        // Возвращаем результат
        return String.format("Суммарно: пройдено %d из %d уроков, осталось %d уроков",
                totalCompleted, totalTotal, totalRemaining);
    }
        // TODO: Реализовать логику подсчёта суммарного прогресса с использованием цикла while
        // Шаги:
        // 1. Проверить валидность массива (null, пустой)
        // 2. Инициализировать аккумуляторы: totalCompleted = 0, totalTotal = 0, index = 0
        // 3. Использовать цикл while (index < mentees.length) для перебора массива
        // 4. На каждой итерации: totalCompleted += mentees[index].completedLessons(), totalTotal += mentees[index].totalLessons(), index++
        // 5. Вычислить оставшиеся: totalRemaining = totalTotal - totalCompleted
        // 6. Вернуть строку формата: "Суммарно: пройдено X из Y уроков, осталось Z уроков"



    public static void main(String[] args) {
        ProgressTracker tracker = new ProgressTracker();

        // Создаём массив mentee (продолжение DVT-2: добавляем прогресс к личной карточке)
        Mentee[] mentees = {
                new Mentee("Ismail", "Санкт-Петербург", "Backend разработка", 5, 12),
                new Mentee("Iskander", "Санкт-Петербург", "Fullstack", 8, 12),
                new Mentee("Daria", "Санкт-Петербург", "Java Backend", 12, 12)
        };

        String progress = tracker.calculateTotalProgress(mentees);
        System.out.println(progress);
    }
}
