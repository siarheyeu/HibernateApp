package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import javax.security.auth.login.AppConfigurationEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).
                addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();


        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            System.out.println("Получили человека из таблицы");
            System.out.println(person);



            session.getTransaction().commit();

            System.out.println("Cессия закончилась(session.close)");

            //Открываем сессию и транзакцию ещё раз (в любом другом месте в коде)
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            System.out.println("Внутри второй транзакции");

            person = (Person)session.merge(person);

            Hibernate.initialize(person.getItems());

            session.getTransaction().commit();

            System.out.println("Вне второй сессии");

            // это работает, так как связанные товары были загружены
            System.out.println(person.getItems());

        } finally {
            sessionFactory.close();
        }


    }
}
