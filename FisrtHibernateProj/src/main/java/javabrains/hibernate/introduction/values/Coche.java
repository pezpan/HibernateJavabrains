package javabrains.hibernate.introduction.values;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// Debemos indicar que esta clase representa un "vallue object" que podemos usar en un "entity object", por lo que
// debemos poner la anotacion embeddable
@Embeddable
public class Coche {
	
	private int años;
	private String marca;
	private String color;
	
	@Column(name="AGE")
	public int getAños() {
		return años;
	}
	public void setAños(int años) {
		this.años = años;
	}
	
	@Column(name="MARCA")
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	@Column(name="COLOR")
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	

}
