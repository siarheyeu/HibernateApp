package org.example;

import org.example.model.Item;
import org.example.model.Person;
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
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

           Person person = new Person ("Test person", 30);

           Item newItem = new Item("Item from Hibernate 2", person);

           person.setItems(new ArrayList<>(Collections.singletonList(newItem)));

           session.save(person);

           session.save(newItem);


            session.getTransaction().commit();


        } finally {
            sessionFactory.close();
        }


    }
}
