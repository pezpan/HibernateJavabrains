package javabrains.hibernate.introduction;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import javabrains.hibernate.introduction.entidades.Baraja;
import javabrains.hibernate.introduction.entidades.Jugador;
import javabrains.hibernate.introduction.utils.HibernateUtilities;

/**
 * Hello world!
 *
 */
public class Cascade 
{
    public static void main( String[] args )
    {
        
        Jugador player1 = new Jugador();
    	player1.setNombre("Tahur");
        
        Baraja baraja1 = new Baraja();
        baraja1.setCuantascartas(54);
        Baraja baraja2 = new Baraja();
        baraja2.setCuantascartas(40);
        Baraja baraja3 = new Baraja();
        baraja3.setCuantascartas(48);
        
        List<Baraja> misbarajas = new ArrayList<Baraja>();
        misbarajas.add(baraja1);
        misbarajas.add(baraja2);
        misbarajas.add(baraja3);
        // Guardamos las barajas en el usuario, pero no en la base de datos
        // Al indicar la opcion cascade, le decimos al sistema que genere las barajas en la base de datos por nosotros
        player1.setListaBarajas(misbarajas);
        
        Session session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();
        // En lugar de usar session.save, usamos persist
        session.persist(player1);
        
        session.getTransaction().commit();
        session.close();
     }
}
