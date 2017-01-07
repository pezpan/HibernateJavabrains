package javabrains.hibernate.introduction.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javabrains.hibernate.introduction.values.Address;
import javabrains.hibernate.introduction.values.Coche;

@Entity  // (name="USER_DETAILS") Con esta anotación indicamos que la clase es una entidad, por lo que si hemos
// definido el mapeo en el archivo de configuración, creara una tabla con el nombre de la clase, o con el nombre
// que indiquemos en la propiedad
@Table (name="USER_DETAILS") // Con esta anotación indicamos que cree una tabla en la base de datos asociada a esta clase
// con el nombre que le pasemos como parámetro, que por defecto será el nombre de la clase
public class User {
	

	@Id @GeneratedValue (strategy = GenerationType.AUTO)// Con esta anotacion indicamos que la variable es la clave primaria
	// Con la anotacion GeneratedValue, indicamos que sea hibernate quien genere la clave automáticamente. Hay 4 estrategias
	// de generacion: AUTO: hibernate genera la clave mas adecuada.
	@Column (name = "USER_ID") // Indicamos que la variable es una columna de la tabla. Tiene varios parametros,
	// como el nombre, donde indicamos el nombre de la columna. Sino, tendrá el nombre de la variable
	
	// Si hubieramos querido embeber un objeto como clave primaria, tendríamos que usar la anotacion @EmbeddedId, y
	// eliminar las anotaciones de Id y Embedded que tendriamos que haber usado
	private int idUsuario;
	// Tambien podemos añadir las anotaciones de las propiedades en los getter, pero tenemos que poner todas las anotaciones
	// o bien en la declaracion de las propiedades o en los getter, no podemos mezclar
	
	@Column (name = "USER_NAME")
	private String nombre;
	
	@Embedded // Indicamos que se trata de un objeto de una clase que se embebe dentro de la entidad, por lo que tambien
	// seran incluidos los atributos de dicha clase dentro de la tabla de la entidad
	private Address home_address;
	
	// Si definimos varios objetos de esta clase, todos las columnas que se generen por estos objetos tendran el mismo nombre
	// que hemos definido en la clase. Para poder modificarlos, podemos sobreescribirlos asi
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(column = @Column (name="OFFICE_CALLE"), name = "calle"),
		@AttributeOverride(column = @Column (name="OFFICE_CIUDAD"), name = "ciudad"),
		@AttributeOverride(column = @Column (name="OFFICE_POSTAL_CODE"), name = "codigo_postal"),
		@AttributeOverride(column = @Column (name="OFFICE_COMMUNITY"), name = "comunidad")
	})
	private Address office_address;
	
	// Para definir una coleccion como variable miembro dentro de la clase entity, usamos la anotacion @ElementCollection.
	// No necesitamos indicar @Embedded
	// Creará una tabla en la base de datos con la mezcla de los nombres del value object y la entidad, y pondrá un
	// identificador indicando a que usuario corresponde cada entrada en la tabla
	@ElementCollection (fetch=FetchType.EAGER) // Indicamos que no queremos inicializacion lazy, sino de la bbdd
	// Para darle un nombre a la tabla que se crea que no sea el de por defecto usamod @JoinTable, con lo que indicamos
	// que generará una tabla que será la fusion de dos, esta clase que representa un value object, y el id del usuario
	@JoinTable(name="USER_COCHES",
				joinColumns=@JoinColumn(name="USER_ID")) // Indicamos el nombre de la columna que sirve de nexo entre tablas
	//Set<Coche> listaCoches = new HashSet<Coche>();
	// La tabla que genera, no asigna un id unico a cada fila. Si queremos definir un ID unico a cada elemento, tenemos que
	// usar la anotacion @CollectionID, que no es de JPA sino de Hibernate
	// Para poder definir el ID, debemos indicar un generator, que podemos definir al mismo tiempo.
	// Para poder definir el ID, debemos cambiar el tipo a uno que soporte ID, como ArrayList, por eso comento Set,
	// y tambien tiene que soportar indices, y ArrayList soporta indices
	// Indicamos tambien el tipo de identificador que queremos, que en este caso sera long
	@GenericGenerator(name="sequence-gen", strategy="sequence") // sequence es un tipo de generator que ofrece hibernate
	@CollectionId(columns = { @Column(name="COCHE_ID") }, generator = "sequence-gen", type = @Type(type="long"))
	Collection<Coche> listaCoches = new ArrayList<Coche>();
	private String email;
	
	@Temporal (TemporalType.DATE) // El tipo Date guarda la fecha y la hora. Con esta anotacion le podemos decir el formato
	// del tiempo que queremos guardar
	private Date date;
	
	@Lob // Hibernate transforma por defecto la cadenas en varchar(255). Con esta anotacion le indicamos que se trata
	// de un "large object", por lo que podemos añadir mas de esa cantidad de caracteres
	private String description;
	
	
			
	public Collection<Coche> getListaCoches() {
		return listaCoches;
	}

	public void setListaCoches(Collection<Coche> listaCoches) {
		this.listaCoches = listaCoches;
	}

	@Transient // Con esta anotación le decimos que no queremos que cree una columna para esta propiedad
	private int age;
	
	
	public int getIdUsuario() {
		return idUsuario;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getHome_address() {
		return home_address;
	}

	public void setHome_address(Address home_address) {
		this.home_address = home_address;
	}

	public Address getOffice_address() {
		return office_address;
	}

	public void setOffice_address(Address office_address) {
		this.office_address = office_address;
	}
	
}
