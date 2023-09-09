package lk.ijse.gdse.hostel_management_system.util;

import lk.ijse.gdse.hostel_management_system.entity.Reservation;
import lk.ijse.gdse.hostel_management_system.entity.Room;
import lk.ijse.gdse.hostel_management_system.entity.Student;
import lk.ijse.gdse.hostel_management_system.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SessionFactoryConfiguration {
    private static SessionFactoryConfiguration sessionFactoryConfiguration;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfiguration() {
        sessionFactory = new Configuration()
                .mergeProperties(Utility.getProperties())
                .addAnnotatedClass(Users.class ).addAnnotatedClass(Student.class).
                addAnnotatedClass(Room.class).addAnnotatedClass(Reservation.class). buildSessionFactory();
    }
    public static SessionFactoryConfiguration getInstance(){
        return null==sessionFactoryConfiguration?sessionFactoryConfiguration=new SessionFactoryConfiguration():
                sessionFactoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
