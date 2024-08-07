# Task Management System

## Описание проекта

Этот проект представляет собой систему управления задачами, которая позволяет создавать, редактировать, удалять и просматривать задачи. Система включает функционал для фильтрации задач по дате, поиска по заголовку, сортировки по дате и других операций.

## Стек технологий

- **Java**: Язык программирования, используемый для разработки приложения.
- **Spring Boot 3.3.2**: Основной фреймворк для создания веб-приложений.
- **Hibernate**: ORM для работы с базой данных.
- **MySQL**: Реляционная база данных для хранения данных.

## Запуск приложения

Чтобы запустить приложение, используйте следующую команду:
java -jar task-manager-0.0.1-SNAPSHOT.jar

Примеры запросов
Создание задачи (POST)
Создайте новую задачу, отправив POST-запрос на /api/tasks:

$headers = @{
    "Content-Type" = "application/json"
}
$body = @{
    title = "Finish Homework"
    description = "Complete the assignment for the Java course."
    date = "2024-08-10"
    completed = $true
} | ConvertTo-Json

Invoke-WebRequest -Uri "http://localhost:8080/api/tasks" -Method Post -Headers $headers -Body $body

Получение всех задач (GET)
Получите все задачи, отправив GET-запрос на /api/tasks:

Invoke-WebRequest -Uri "http://localhost:8080/api/tasks" -Method Get
Поиск задач по заголовку (GET)
Поиск задач по заголовку с помощью GET-запроса на /api/tasks/search:

Invoke-WebRequest -Uri "http://localhost:8080/api/tasks/search?title=Homework" -Method Get
Получение задач на сегодня (GET)
Получите задачи, которые должны быть выполнены сегодня, отправив GET-запрос на /api/tasks/today:

Invoke-WebRequest -Uri "http://localhost:8080/api/tasks/today" -Method Get
Получение задач за неделю (GET)
Получите задачи за текущую неделю с помощью GET-запроса на /api/tasks/week:

Invoke-WebRequest -Uri "http://localhost:8080/api/tasks/week" -Method Get
Получение задач, отсортированных по дате (GET)
Получите задачи, отсортированные по дате, отправив GET-запрос на /api/tasks/sorted:

Invoke-WebRequest -Uri "http://localhost:8080/api/tasks/sorted" -Method Get
Получение задач за определенный диапазон дат (GET)
Получите задачи за определенный диапазон дат с помощью GET-запроса на /api/tasks/range:

Invoke-WebRequest -Uri "http://localhost:8080/api/tasks/range?startDate=2024-08-01&endDate=2024-08-31" -Method Get
