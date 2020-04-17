### Learning management system
Приложение предназначено для слежением за прогрессом выполнения домашних заданий.
Присутсвует возможность регистрация новых пользователей (изначально у всех пользователей уровень доступа USER).
Подтверждение учетной записи происходит по ссылке которую получает администратор на свою почту(без этого уч. запись будет неактивна)
Пользователь с правами администратора (ADMIN) может в своей уч. записи менять права доступа пользователям.
Пользователи с правами TEACHER имеют возможность отправлять домашнее задание пользователям (USER), а также создавать группы по предметам и добавлять в них студентов(USER).
Обычные пользователи (USER) могут добавлять название домашней работы и прикрепить файл с решением.
Каждый пользователь может изменить свой email и пароль.

### Приступая к работе
Изнчально в программе присутствуют пользователи admin, teacher1, teacher2, student1, student2. У всех них пароль: 123.
Если при входе в уч. запись не видно всех элементов, то необходимо проверить базу данных на наличие у пользователей прав(таблица user_role).
Для запуска приложения необходимо открыть испоняемый файл .jar

### Создано с помощью 
* ![alt-текст](https://code.scottshipp.com/wp-content/uploads/2017/09/maven-logo-black-on-white-300x76.png "Maven")
* ![alt-текст](https://colevit.com/wp-content/uploads/2019/04/Group-242.png "Spring Framework")
* ![alt-текст](https://flywaydb.org/assets/logo/flyway-logo-tm.png "Flyway")
* ![alt-текст](https://lh3.googleusercontent.com/proxy/f7JkZB0AcnixytR3Eg-13Ii9FsfWx0zwnTZCbJU0rtn2Z3Yu3S2Mq3cKPTqVvCQFch0WNOeuNcO0Ne8U3iQzOLVS6UTRUHaSzR52ACtRO5B3a0__oBOWkDWuXg "Freemarker")
* ![alt-текст](https://upload.wikimedia.org/wikipedia/commons/2/22/Hibernate_logo_a.png "Hibernate")
* ![alt-текст](https://tapen.ru/uploads/mariadb-usa-inc.png "MariaDB")

### Автор
* Юрий Белькевич 

### Лицензия
Этот проект лицензируется в соответствии с лицензией MIT — подробности см. в файле LICENSE.