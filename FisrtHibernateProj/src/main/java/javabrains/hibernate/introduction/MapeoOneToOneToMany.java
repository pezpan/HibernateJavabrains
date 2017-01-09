package javabrains.hibernate.introduction;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;

import javabrains.hibernate.introduction.entidades.Juego;
import javabrains.hibernate.introduction.entidades.Jugador;
import javabrains.hibernate.introduction.entidades.Pareja;
import javabrains.hibernate.introduction.entidades.Tapete;
import javabrains.hibernate.introduction.entidades.Tienda;
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
    	Jugador player2 = new Jugador();
    	player2.setNombre("Monton");
    	
    	Pareja pareja = new Pareja();
    	pareja.setNombre("Antolin");
    	
    	// Generamos la lista de juegos
    	// Se creara una nueva tabla que mapeara el id del jugador y cada id de los juegos
    	Juego juego1 = new Juego();
    	Juego juego2 = new Juego();
    	Juego juego3 = new Juego();
    	juego1.setNombre("Agricola");
    	juego2.setNombre("Puerto Rico");
    	juego3.setNombre("Caylus");
    	// Indicamos tambien el usuario al que pertenecen
    	// En principio, si se va a crear una tabla nueva para el mapeo, no es necesario indicar esta relacion
    	// para los objetos de la entidad secundaria
    	juego1.setJugador(player1);
    	juego2.setJugador(player1);
    	juego3.setJugador(player1);
    	
    	// Guardamos la pareja, para la relacion uno a uno
    	player1.setPareja(pareja);

    	ArrayList<Juego> listajuegos = new ArrayList<Juego>();
    	listajuegos.add(juego1);
    	listajuegos.add(juego2);
    	listajuegos.add(juego3);
    	player1.setColeccion(listajuegos);
    	
    	// Definimos la lista de tapetes del jugador. En este caso, al no indicar que genere una tabla nueva en las
    	// propiedades del jugador, e indicar en la clase tapete que cree una columna de mapeo, creara una columna nueva
    	// en la tabla tapete para incluir el id del jugador al que pertenece el tapete.
    	Tapete tapete_rojo = new Tapete();
    	tapete_rojo.setColor("rojo");
    	// Debemos indicar el propietario, ya que al indicar en la clase jugador que estará mapeado por una variable dentro
    	// la clase tapete, si no lo indicamos, en la tabla de la entidad tapete, el id de jugador saldrá nulo.
    	tapete_rojo.setPropietario(player1);
    	Tapete tapete_verde = new Tapete();
    	tapete_verde.setColor("verde");
    	tapete_verde.setPropietario(player1);
    	player1.getMistapetes().add(tapete_rojo);
    	player1.getMistapetes().add(tapete_verde);
    	
    	// Vamos a generar la relacion many to many
    	Tienda tienda1 = new Tienda();
    	tienda1.setNombre("Generacion X");
    	tienda1.getJugadores().add(player1);
    	tienda1.getJugadores().add(player2);
    	Tienda tienda2 = new Tienda();
    	tienda2.setNombre("Zacatrus");
    	tienda2.getJugadores().add(player1);
    	tienda2.getJugadores().add(player2);
    	
    	player1.getTiendas().add(tienda1);
    	player2.getTiendas().add(tienda2);
  
    	
        Session session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();
        // Guardamos ambas entidades en la base de datos
        session.save(player1);
        session.save(player2);
        session.save(juego1);
        session.save(juego2);
        session.save(juego3);
        session.save(tapete_verde);
        session.save(tapete_rojo);
        session.save(pareja);
        session.save(tienda1);
        session.save(tienda2);
        session.getTransaction().commit();
        session.close();
        
    }
}
