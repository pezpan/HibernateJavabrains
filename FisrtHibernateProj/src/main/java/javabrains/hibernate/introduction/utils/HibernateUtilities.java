package javabrains.hibernate.introduction.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtilities {
	
	private static SessionFactory sessionFactory;
	
	static{
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			System.out.println("Creamos la sessionFactory");
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			System.out.println("Capturada la excepcion: " + e.getMessage());
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}
