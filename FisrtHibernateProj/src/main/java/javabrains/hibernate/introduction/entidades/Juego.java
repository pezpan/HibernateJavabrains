package javabrains.hibernate.introduction.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Juego {
	@Id 
	@GeneratedValue
	private long id;
	
	private String nombre;
	
	// Podemos indicar la relacion inversa a one to many, es decir, many to one
	@ManyToOne
	private Jugador jugador;
	
	
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
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
