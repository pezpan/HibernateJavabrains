package javabrains.hibernate.introduction.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Tapete {
	@Id 
	@GeneratedValue
	private long id;
	
	private String color;
	
	// Podemos indicar la relacion inversa a one to many, es decir, many to one
	// Si no queremos que se cree una tabla que mapee el identificador de esta entidad, y el identificador de la entidad
	// "propietaria" de la relacion, lo hacemos indicando en la entidad primaria la propiedad mappedby, con el nombre de
	// esta variable, que será la que se use para mapear dicha entidad
	// Podemos indicar la columna que servirá para guardar el identificador de cada 
	// elemento de la entidad propietaria de la relacion (en este caso judador), y le podemos dar un nombre
	@ManyToOne
	@JoinColumn(name="JUGADOR_ID")
	private Jugador propietario;
	
	// Si definimos una relacion, pero no se encuentra el objeto definido en la otra tabla, va a producir un error,
	// Como por ejemplo si definimos la siguiente relacion entre tienda y tapete, sin que existan tapetes definidos en la
	// tienda. Esto es una situacion habitual, y con la siguiente anotacion indicamos que no queremos que genere una
	// excepcion en caso de que no encuentre la otra entidad
	@ManyToOne
	// Podemos indicar el tipo de accion que queremos que se haga cuando se detecte la excepcion. En este caso ignorar.
	@NotFound(action=NotFoundAction.IGNORE)
	private Tienda tienda;
	
	
	public Jugador getPropietario() {
		return propietario;
	}
	public void setPropietario(Jugador propietario) {
		this.propietario = propietario;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
