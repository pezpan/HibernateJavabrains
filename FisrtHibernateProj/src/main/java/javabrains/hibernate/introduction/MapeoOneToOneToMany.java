package javabrains.hibernate.introduction;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;

import javabrains.hibernate.introduction.entidades.Juego;
import javabrains.hibernate.introduction.entidades.Jugador;
import javabrains.hibernate.introduction.entidades.Pareja;
import javabrains.hibernate.introduction.utils.HibernateUtilities;

/**
 * Hello world!
 *
 */
public class MapeoOneToOneToMany 
{
    public static void main( String[] args )
    {
    	Jugador player1 = new Jugador();
    	player1.setNombre("Vicioso");
    	
    	Pareja pareja = new Pareja();
    	pareja.setNombre("Antolin");
    	
    	Juego juego1 = new Juego();
    	Juego juego2 = new Juego();
    	Juego juego3 = new Juego();
    	juego1.setNombre("Agricola");
    	juego2.setNombre("Puerto Rico");
    	juego3.setNombre("Caylus");
    	
    	player1.setPareja(pareja);
    	
    	ArrayList<Juego> listajuegos = new ArrayList<Juego>();
    	listajuegos.add(juego1);
    	listajuegos.add(juego2);
    	listajuegos.add(juego3);
    	player1.setColeccion(listajuegos);
    	
        Session session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();
        // Guardamos ambas entidades en la base de datos
        session.save(player1);
        session.save(juego1);
        session.save(juego2);
        session.save(juego3);
        session.save(pareja);
        session.getTransaction().commit();
        session.close();
        
    }
}
