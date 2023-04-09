package lk.ijse.gdse.util;

import lk.ijse.gdse.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryCongfiguration {

    private static SessionFactoryCongfiguration factoryConfiguration;

    public static SessionFactoryCongfiguration getInstance() {
        return (null == factoryConfiguration)
                ? factoryConfiguration = new SessionFactoryCongfiguration()
                : factoryConfiguration;
    }

    public Session getSession() throws HibernateException {
        // Creating the Service Registry
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        // Creating the Metadata Object
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Student.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(
                        ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        // Creating the Session Factory
        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();

        // Opens a new Session through the Session Factory & returns the Session created
        return sessionFactory.openSession();
    }
}
