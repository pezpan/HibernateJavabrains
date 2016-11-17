package javabrains.hibernate.introduction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javabrains.hibernate.introduction.entidades.User;
import javabrains.hibernate.introduction.utils.HibernateUtilities;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        User usuario = new User();
        usuario.setIdUsuario(1);
        usuario.setNombre("Manolo");
        
        Session session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
        session.close();
        
        
//        SessionFactory factoria = new Configuration().configure().buildSessionFactory();
//        Session sesion = factoria.openSession();
//        sesion.beginTransaction();
//        sesion.save(usuario);
//        sesion.getTransaction().commit();
    }
}
