package com.depa.progettinocovid.util;

import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.jboss.jandex.Main;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.models.Provincia;

@Component
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final Configuration configuration = new Configuration();
    private static final Session session;

    static {
        try {
        	
        	Properties settings = new Properties();
        	settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/testdb");
            settings.put(Environment.USER, "ale");
            settings.put(Environment.PASS, "perigea");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            settings.put(Environment.HBM2DDL_AUTO, "create");

            configuration.setProperties(settings);
            
            configuration.addAnnotatedClass(Provincia.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            session = sessionFactory.openSession();
        } catch (Throwable ex) {
            // Log the exception.
        	LoggerFactory.getLogger(Main.class).error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public Session getSession () {
    	if (!session.getTransaction().isActive()) {
    		session.beginTransaction();
		}
    	return session;
    }
    
    public static void execute (String q) throws Exception {
    	session.createSQLQuery(q).executeUpdate();
    }

    public List<?> list (String q, Class<?> entity) {
    	return session.createSQLQuery(q).addEntity(entity).list();
    }
}