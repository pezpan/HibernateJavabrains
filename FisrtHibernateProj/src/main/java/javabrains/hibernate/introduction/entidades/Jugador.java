package javabrains.hibernate.introduction.entidades;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity  
@Table (name="JUGADOR")
public class Jugador {

	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "JUGADOR_ID") 
	private int idUsuario;
	
	@Column (name = "JUGADOR_NAME")
	private String nombre;
	
	// Para indicar la relacion uno a uno, usamos la siguiente anotacion
	@OneToOne
	// Con esta anotacion, le decimos a hibernate que genere una columna que enlace a la table de juegos
	// Con la anotacion JoinColumn, indicamos que esta clase es la "propietaria" de la relacion uno a uno, es decir, 
	// que en la tabla de esta entidad se va a crear una columna que enlaza a la otra entidad. Podemos indicar el nombre
	// con el que queremos que se genere la columna
	@JoinColumn(name="PAREJA_ID")
	private Pareja pareja;
	
	// Para indicar una relacion one to many usamos la siguiente anotacion
	@OneToMany
	// Para mapear la relacion se usa la anotacion JoinTable.
	// Se genera una tabla que mapea la relacion, incluyendo los identificadores de las tablas implicadas en la relacion
	// uno a varios. Podemos indicar el nombre de la tabla que se genera. 
	// joincolumns indica la columna para la foreign key en la tabla que se crea que hace referencia a tabla de la entidad 
	// propietaria de la asociacion
	// inversejoincolumn indica la columna para la foreign key en la tabla que se crea que hace referencia a tabla de la  
	// entidad que no es propietaria de la asociacion 
	@JoinTable (name="JUGADOR_LISTA", joinColumns=@JoinColumn(name="JUGADOR_ID"),
				inverseJoinColumns=@JoinColumn(name="JUEGO_ID"))
	private Collection<Juego> coleccion = new ArrayList<Juego>();
	
	// Si no queremos que en una relacion one to many se cree una tabla extra que mapee los identificadores de las
	// tablas implicadas en la relacion, no debemos indicar la opcion @JoinTable, y en la clase de la entidad secundaria,
	// indicamos la variable para la que queremos que guarde el identificador de la entidad primaria, ya que no podemos
	// indicar en la tabla jugador que puede tener muchos tapetes, pero si podemos indicar que cada tapate solo pertenece
	// a un jugador, indicando el identificador de dicho jugador.
	// Ademas, en esta entidad tenemos que indicar el nombre de la variable propietaria de esta relacion, es decir, el
	// nombre de la variable de esta clase que se encuentra definida y anotada en la otra clase de la relacion, usando 
	// la propiedad mappedBy. Con esto le decimos que no genere una tabla para esta relacion.
	@OneToMany(mappedBy="propietario")
	private Collection<Tapete> mistapetes = new ArrayList<Tapete>();
	
	// Para una relacion many to many, usamos la siguiente anotacion
	@ManyToMany
	// Como en la otra entidad hemos indicado que será la secundaria para que no genere dos tablas, aqui podemos definir
	// el nombre que queremos que tenga la tabla que se generará, asi como los nombres de las columnas. Es opcional
	@JoinTable (name="JUGADORES_TIENDAS", joinColumns=@JoinColumn(name="JUGADOR_ID"),
	inverseJoinColumns=@JoinColumn(name="TIENDA_ID"))
	private Collection<Tienda> tiendas = new ArrayList<Tienda>();

	public int getIdUsuario() {
		return idUsuario;
	}

	public Pareja getPareja() {
		return pareja;
	}

	public void setPareja(Pareja pareja) {
		this.pareja = pareja;
	}

	public Collection<Juego> getColeccion() {
		return coleccion;
	}

	public void setColeccion(Collection<Juego> coleccion) {
		this.coleccion = coleccion;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Tapete> getMistapetes() {
		return mistapetes;
	}

	public void setMistapetes(Collection<Tapete> mistapetes) {
		this.mistapetes = mistapetes;
	}

	public Collection<Tienda> getTiendas() {
		return tiendas;
	}

	public void setTiendas(Collection<Tienda> tiendas) {
		this.tiendas = tiendas;
	}
	
	
		
}
