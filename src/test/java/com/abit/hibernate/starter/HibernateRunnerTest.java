package com.abit.hibernate.starter;

import com.abit.hibernate.starter.entity.*;
import com.abit.hibernate.starter.utils.HibernateUtil;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Instant;

@Slf4j
class HibernateRunnerTest {

    @Test
    public void checkSecondLevelCache() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        try (var session = sessionFactory.openSession();) {
            session.beginTransaction();
            var user = session.find(User.class, 1);
            System.out.println(user);

            session.getTransaction().commit();
        }

        try (var session2 = sessionFactory.openSession();) {
            session2.beginTransaction();
            var user = session2.find(User.class, 1);
            System.out.println(user);

            session2.getTransaction().commit();
        }
    }
    @Test
    public void checkEMR() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        var payment = Payment.builder()
                .amount(125)
                .build();
        var payment2 = Payment.builder()
                .amount(110)
                .build();

             var session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(payment);
            session.save(payment2);

            session.getTransaction().commit();
    }

    @Test
    public void checkHql() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        /*var name = "ishennarkozuev07@gmail.com";
        var users = session.createQuery("""
                        select user from User user
                        where user.username = :username
                        """)
                .setParameter("username", name)
                .list();
        System.out.println(users);*/
        session.save(Company.builder()
                .name("Google")
                .build());
        var company = session.get(Company.class, 1);
        session.save(User.builder()
                .username("ishenaly")
                .company(company)
                .build());
        session.clear();
        var user = session.get(User.class, 1);
        System.out.println(user.getCompany());

        session.getTransaction().commit();
    }

    @Test
    public void checkInheritance() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        /*var company = Company.builder()
                .name("Intels")
                .build();

        session.save(company);
        var programmer = Programmer.builder()
                .language(Language.JAVA)
                .username("pavel@gmail.com")
                .company(company).build();
        session.save(programmer);
        var manager = Manager.builder()
                .project("Java Course")
                .username("ivan@gmail.com")
                .company(company).build();
        session.save(manager);

        session.flush();
        session.clear();

        var programmer2 = session.get(Programmer.class, 1);*/
        session.getTransaction().commit();
    }

    @Test
    public void checkH2() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        var company = Company.builder()
                .name("Intel")
                .build();

        session.save(company);
        session.getTransaction().commit();
    }

    @Test
    public void trainerCourse() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        var trainer = session.get(Trainer.class, 1);
        var course = session.get(Course.class, 2);
        var course2 = session.get(Course.class, 3);
        var trainerCourse = TrainerCourse.builder()
                .build();
        //session.save(trainer);
        trainerCourse.addTrainer(trainer);
        trainerCourse.addCourse(course);
        //trainerCourse.addCourse(course2);
        session.save(trainerCourse);
        session.getTransaction().commit();
    }

    @Test
    public void studentProfileDelete() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        var student = Student.builder()
                .name("Muchacho")
                .course(session.get(Course.class, 2))
                .build();
        var stProf = StudentProfile.builder()
                .grade(4)
                .student(student)
                .build();
        /*List<StudentProfile> studentProfiles = session
                .createQuery("from StudentProfile where grade < 6", StudentProfile.class)
                .list();
        for (StudentProfile studentProfile : studentProfiles) {
            var student1 = studentProfile.getStudent();
            session.delete(student1);
        }

        studentProfiles = session
                .createQuery("from StudentProfile where grade < 6", StudentProfile.class)
                .list();
        System.out.println(studentProfiles);*/
        session.save(student);
        session.save(stProf);
        session.getTransaction().commit();
    }

    @Test
    public void checkManyToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        Chat chat = session.get(Chat.class, 1);
        User user = session.get(User.class, 15);
        UserChat userChat = new UserChat();
        userChat.setCreatedAt(Instant.now());
        userChat.setCreatedBy("Ishenaly");

        userChat.addChat(chat);
        userChat.addUser(user);
        session.save(userChat);
        session.getTransaction().commit();
    }

    @Test
    public void checkStudentCourse() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        var course = session.createQuery("from Course where name = :name", Course.class)
                .setParameter("name", "Java Enterprise").getSingleResult();
        var student = Student.builder()
                .name("TilekLoh")
                .course(course)
                .build();

        session.beginTransaction();
        //List<Student> students = session.get(Course.class, 3).getStudents();
        //session.save(course);
        session.delete(course);
        //session.save(student);
        //System.out.println(students);

        session.getTransaction().commit();
    }

    @Test
    public void checkOrphalRemoval() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = session.get(Company.class, 8);
        //company.getUsers().removeIf(user -> user.getId().equals(7));

        session.getTransaction().commit();
    }

    @Test
    @SneakyThrows
    public void checkOneToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = session.get(Company.class, 1);
        System.out.println(company.getUsers());
        log.warn("papachuaa");

        session.getTransaction().commit();
    }

    @Test
    public void addNewUserAndCompany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        var company = Company.builder()
                .name("Google")
                .build();
        var user = User.builder()
                .username("ishennarkozuev07@gmail.com")
                .build();

        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
    }

    @SneakyThrows
    @Test
    public void testHibernateApi() throws SQLException {
     /*   var user = User.builder()
                .username("ishennarkozuev007@gmail.com")
                .firstname("Ishenaly")
                .lastname("Narkozuev")
                .birthDate(LocalDate.of(2007, 02, 03))
                .age(17).build();

        var sql = """
                insert into
                %s
                (%s)
                values
                (%s)
                """;
        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();
        var fieldNames = Arrays.stream(fields).map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                .map(Column::name)
                .orElse(field.getName())
        ).collect(Collectors.joining(", "));

        var columnValues = Arrays.stream(fields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "futureSQL");
        PreparedStatement preparedStatement = connection
                .prepareStatement(sql.formatted(tableName, fieldNames, columnValues));

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            preparedStatement.setObject(i + 1, fields[i].get(user));
        }

        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();*/
    }
}