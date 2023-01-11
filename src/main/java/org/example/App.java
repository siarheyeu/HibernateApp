package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import javax.security.auth.login.AppConfigurationEntry;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 2);
            person.setName("New name");

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


    }
}
