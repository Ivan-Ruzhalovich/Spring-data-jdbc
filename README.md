<h1 align="center">Hi there, I'm <a>Ruzhalovich Ivan</a> 
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>

<h2 align="center">This is Spring-data-jdbc</h2>

<h3 align="center">Простое приложение для управления списком книг в библиотеке с использованием Spring Data JDBC.</h3>

<h3>Сущности:</h3>

Имеется сущность Book с полями: id (автоматически генерируемый идентификатор), title (название книги), author (автор книги), publicationYear (год публикации).

<h3>Репозиторий:</h3>

Реализован интерфейс репозитория для сущности Book, используя Spring Data JDBC, а именно JdbcTemplate

<h3>Сервис:</h3>

Реализован сервисный класс для управления операциями CRUD (создание, чтение, обновление, удаление) с книгами.

<h3>REST-контроллер:</h3>

Реализован REST-контроллер для обработки запросов, связанных с управлением книгами (создание, чтение, обновление, удаление).
