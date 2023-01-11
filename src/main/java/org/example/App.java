package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import javax.security.auth.login.AppConfigurationEntry;
import java.util.List;


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

            List<Person> people = session.createQuery("FROM Person").getResultList();
            for (Person person:
                 people) {
                System.out.println(person);
            }

            session.getTransaction().commit();


        } finally {
            sessionFactory.close();
        }


    }
}
