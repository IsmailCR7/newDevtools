# newDevTools – проект для изучения инструментов разработки

## Quick Start

### Запуск приложения
1. Откройте проект в IntelliJ IDEA.
2. Перейдите в **Gradle Tool Window** (правая панель).
3. Раскройте **Tasks → application**.
4. Дважды щёлкните **run**.
5. Или используйте **Run Anything** (двойное `Ctrl`) и введите:
### Запуск тестов
1. В **Gradle Tool Window** раскройте **Tasks → verification**.
2. Дважды щёлкните **test**.
3. Или через **Run Anything**:

---
## Правила работы с ветками

Мы используем Git Flow с префиксом `DVT-` (DevTools).

### Основные ветки
- `master` / `main` — стабильная рабочая версия, всегда готовая к деплою
- `develop` — ветка для интеграции новых функций (если используется)

### Ветки разработки
Все новые задачи ведутся в feature-ветках по шаблону:

**Формат:** `тип/DVT-<номер_задачи>`

**Примеры:**
- `feature/DVT-3` — разработка новой функциональности для задачи DVT-3
- `feature/DVT-4` — разработка для задачи DVT-4



### Процесс работы
1. Создайте ветку от `master`: `git checkout -b feature/DVT-3`
2. Регулярно делайте коммиты с понятными сообщениями
3. После завершения работы создайте Pull Request (Merge Request)
4. После код-ревью ветка мержится в `master`
5. После создали от обновленного `master` ветку `git checkout -b feature/DVT-4`
6. Сделали коммит и создали PR.

## Git локальный цикл
1. Проверить статус
   git status
2. Добавить изменения
   git add <файлы>
3. Сделать коммит
   git commit -m "Сообщение"
4. git log --oneline
5.  Создать/переключить ветку
    git checkout -b feature/DVT-X
    git checkout master





## Описание пакета

Пакет `ru.mentee.power` содержит классы для отслеживания прогресса обучения студента (менти) в рамках спринтов по Java.

Основные классы:
- `ProgressDemo` – точка входа, демонстрация создания объекта прогресса.
- `MenteeProgress` – рекорд, представляющий данные о прогрессе менти.

---

## Поля рекорда `MenteeProgress`

| Поле       | Тип      | Описание                                      |
|------------|----------|-----------------------------------------------|
| `name`     | `String` | Имя менти                                     |
| `hours`    | `int`    | Количество часов, затраченных на спринт       |
| `finished` | `boolean`| Флаг завершения спринта                       |

---

## Методы рекорда

- `summary()` – возвращает форматированную строку с именем и часами.
- `readyForSprint()` – возвращает `true`, если менти готов к следующему спринту (≥3 часов).

---

## Ссылка на урок

Этот проект создан в рамках урока **«Инструменты разработки. Настройка окружения»**, часть спринта 2 курса **«Java с нуля до оффера»** (шаг 3 из 15).
🔗 **[Перейти к уроку](https://mentee-power.xl.ru/learn/MCIneBj4KkyH-GIRCspFvA/theory)**
---

## Структура проекта
devtools/
├── gradle/
├── src/
│ ├── main/java/ru/mentee/power/
│ │ ├── ProgressDemo.java
│ │ └── MenteeProgress.java
│ └── test/java/ru/mentee/power/
│ └── MenteeProgressTest.java
├── build.gradle
├── gradlew
├── gradlew.bat
└── README.md

---

## Зависимости

- **JUnit 5** – для модульного тестирования.
- **AssertJ** – для удобных assert-проверок в тестах.
## Feature DVT-4
Active development branch for DVT-4 task
## feature/DVT-5: Санитарный коммит очистки

### Хэш коммита очистки:
`772d40696f14e33c38b05096205df030cf103d48`

### Что сделано:
1. ✅ Обновлен `.gitignore` с правилами для мусорных файлов
2. ✅ Удалены .idea, build, .gradle из индекса Git
3. ✅ Добавлено правило «git status clean» в Runbook
4. ✅ `git check-ignore` корректно фильтрует .DS_Store, .idea/workspace.xml

### Команды для проверки:
bash
git check-ignore -v .DS_Store
git check-ignore -v .idea/workspace.xml
git status
git diff --name-status HEAD~1..HEAD

## Сценарий ручной проверки DVT-6

### Запуск приложения
1. Откройте Gradle Tool Window (View → Tool Windows → Gradle)
2. Выполните: devtools → Tasks → application → run
3. Ожидаемый вывод в Run Tool Window:
   Суммарно: пройдено 25 из 36 уроков, осталось 11 уроков

### Запуск тестов
1. Откройте Gradle Tool Window
2. Выполните: devtools → Tasks → verification → test
3. Ожидаемый вывод: BUILD SUCCESSFUL, все тесты зелёные

### Отладка через Debug
1. Установите breakpoint на строке цикла while в ProgressTracker.calculateProgress
2. Запустите Debug: кликните правой кнопкой на main → Debug 'ProgressTracker.main()'
3. Используйте Step Over (F8) для прохождения итераций
4. Проверьте Variables: counter, remainingHours должны изменяться корректно
5. Используйте Evaluate Expression (Alt+F8): вычислите remainingLessons * 2
6. Ожидаемый результат Evaluate: 14 (для completedLessons=5, totalLessons=12)

### Что делать при ошибках
- Если вывод некорректен: проверьте логику цикла через Debug
- Если тесты красные: откройте вывод теста, найдите AssertionError, скорректируйте метод
- Если Debug не останавливается: убедитесь, что breakpoint установлен (красный кружок)

## Кодстайл-гайд проекта devtools

Проект следует правилам Google Java Style Guide с адаптацией.
Автоматическая проверка: ./gradlew checkstyleMain

### 1. Именование методов: camelCase

До:    public void add_student(Student s) { }
После: public void addStudent(Student student) { }

Почему: Java Convention требует camelCase для методов.
Источник: https://google.github.io/styleguide/javaguide.html#s5.3-camel-case

### 2. Пробелы после if/for/while

До:    if(condition) {
После: if (condition) {

Почему: улучшает читаемость, отделяет ключевое слово от выражения.
Источник: Oracle Code Conventions — Whitespace

### 3. Длина строки: максимум 120 символов

До:    public List getStudentsFromSpecificCityWithVeryLongName...
После: public List getStudentsByCity(String city) {

Почему: длинные строки затрудняют чтение в редакторе и при code review.
Источник: https://google.github.io/styleguide/javaguide.html#s4.4-column-limit

### 4. Порядок импортов

До:    import java.util.List; import java.util.ArrayList; import java.io.File;
После: import java.io.File; import java.util.ArrayList; import java.util.List;

Почему: алфавитный порядок упрощает поиск импортов.
Источник: IntelliJ IDEA → Code → Optimize Imports

### 5. Фигурные скобки для if

До:    if (condition) doSomething();
После: if (condition) { doSomething(); }

Почему: скобки обязательны даже для однострочных блоков.
Источник: https://google.github.io/styleguide/javaguide.html#s4.1.1-braces-always-used







# CI/CD Status: PASSING 🎉
