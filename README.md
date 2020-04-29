### Learning management system
The application is for monitoring the assignments progress. 
There is a possibility to register new users (initially all of the users have USER access level).
An account is verified by the link which an administrator receives in his email (without this, account wouldn't be active). 
Users with administrator access level (ADMIN) can change access level of another account and delete any users.
Users with teacher access level (TEACHER) have an opportunity to send homework to users (USER), create groups and add users(USERS) to them.
Users (USER) can add a homework name and attach an answer file.
Each user can change their email and password.

### Getting started
Initially, the program contains users admin, teacher1, teacher2, student1, student2.They all have a password: 123.
If some elements of the interface are not visible when logging in to your account, you need to check the user_role table for availability of the access right.
To run the application, open the executable .jar file.

### Created using:
* ![alt-текст](https://code.scottshipp.com/wp-content/uploads/2017/09/maven-logo-black-on-white-300x76.png "Maven")
* ![alt-текст](https://colevit.com/wp-content/uploads/2019/04/Group-242.png "Spring Framework")
* ![alt-текст](https://flywaydb.org/assets/logo/flyway-logo-tm.png "Flyway")
* ![alt-текст](http://www.formadoresit.es/wp-content/uploads/2018/07/freemaker.png "Freemarker")
* ![alt-текст](https://upload.wikimedia.org/wikipedia/commons/2/22/Hibernate_logo_a.png "Hibernate")
* ![alt-текст](https://tapen.ru/uploads/mariadb-usa-inc.png "MariaDB")
* ![alt-текст](https://i.stack.imgur.com/dMXbE.png "Bootstrap")

### Author
* Yury Belkevich

### License
This project is licensed under a MIT license - see the LICENSE file for details.
