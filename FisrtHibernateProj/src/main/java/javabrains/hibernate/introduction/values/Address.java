package javabrains.hibernate.introduction.values;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable // Con esta anotación indicamos que este "value object" puede ser embebido dentro de una entidad
// Se embebe dentro de una entidad porque no tendria sentido definirlo como entidad, depende de una entidad como
// nuestro usuario
public class Address {
	@Column (name="STREET_NAME")
	private String calle;
	@Column (name="CITY_NAME")
	private String ciudad;
	@Column (name="POSTAL_CODE")
	private String codigo_postal;
	@Column (name="COMMUNITY_NAME")
	private String comunidad;
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	public String getComunidad() {
		return comunidad;
	}
	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

}
