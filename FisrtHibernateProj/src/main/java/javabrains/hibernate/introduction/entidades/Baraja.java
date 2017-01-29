package javabrains.hibernate.introduction.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Baraja {
	
	@Id 
	@GeneratedValue
	private long id;
	
	private int cuantascartas;
	
	@ManyToOne
	Jugador jugador;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCuantascartas() {
		return cuantascartas;
	}
	public void setCuantascartas(int cuantascartas) {
		this.cuantascartas = cuantascartas;
	}

	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

}
