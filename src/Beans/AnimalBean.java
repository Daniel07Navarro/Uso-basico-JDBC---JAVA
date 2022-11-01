package Beans;

public class AnimalBean {
	private int id;
	private String nombre;
	private String tipo;
	private String habitad;

	public AnimalBean() {

	}

	public AnimalBean(int id, String nombre, String tipo, String habitad) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.habitad = habitad;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getHabitad() {
		return this.habitad;
	}

	public void setHabitad(String habitad) {
		this.habitad = habitad;
	}

	@Override
	public String toString() {
		return "AnimalBean [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", habitad=" + habitad + "]";
	}

}
