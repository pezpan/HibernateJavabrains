package javabrains.hibernate.introduction.entidades;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tienda {
	@Id 
	@GeneratedValue
	private long id;
	
	private String nombre;
	
	// Indicamos la relacion many to many. En este caso, siempre se va a crear una tabla nueva con ambas referencias
	@ManyToMany(mappedBy="tiendas")
	// Si ponemos en ambas clases de la relacion la anotacion @ManyToMany tal cual, se generaran dos nuevas tablas para
	// indicar la relacion, las dos iguales, pero siendo en cada caso una de las entidades la "propietaria".
	// Si no queremos que se creen dos, sino solo una, tenemos que usar la propiedad mappedBy en la que queramos como
	// "secundaria", con el nombre de la variable que se use en la otra clase para declarar la variable de esta clase
	private Collection<Jugador> jugadores = new ArrayList<Jugador>();
	
	public Collection<Jugador> getJugadores() {
		return jugadores;
	}
	public void setJugadores(Collection<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
