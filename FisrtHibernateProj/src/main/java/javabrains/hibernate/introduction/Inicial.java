package javabrains.hibernate.introduction;

import java.util.Date;

import org.hibernate.Session;

import javabrains.hibernate.introduction.entidades.User;
import javabrains.hibernate.introduction.utils.HibernateUtilities;

/**
 * Hello world!
 *
 */
public class Inicial 
{
    public static void main( String[] args )
    {
        User usuario = new User();
        User usuario2 = new User();
        //usuario.setIdUsuario(1); Esto no es necesario si le decimos a hibernate que lo genere automaticamente
        usuario.setNombre("Manolo");
        usuario.setEmail("manolo@yahoo.es");
        usuario.setDate(new Date());
        usuario.setDescription("Primer usuario");
        usuario.setAge(25);
        
        usuario2.setNombre("Javier");
        usuario2.setEmail("javi@gmail.com");
        usuario2.setDate(new Date());
        usuario2.setDescription("Segundo usuario");
        usuario2.setAge(42);
        
        Session session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(usuario);
        session.save(usuario2);
        session.getTransaction().commit();
        session.close();
        
        // Vamos a obtener el usuario que acabamos de guardar
        usuario = null;
        session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();
        usuario = (User) session.get(User.class, 1);
        System.out.println("El usuario obtenido es: " + usuario.getNombre());
        session.close();
        
        
        
//        SessionFactory factoria = new Configuration().configure().buildSessionFactory();
//        Session sesion = factoria.openSession();
//        sesion.beginTransaction();
//        sesion.save(usuario);
//        sesion.getTransaction().commit();
    }
}
