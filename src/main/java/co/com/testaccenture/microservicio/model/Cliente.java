/**
 * 
 */
package co.com.testaccenture.microservicio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
/**
 * @author stick
 *
 */
@Data
@Document(collection = "Cliente")
public class Cliente {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    
	@Id
	private int idCliente;
	private String nombre;
	private String apellido;
	private String identificacion;
	private String direccion;
	private String telefono;
	private String email;
	

	public Cliente() {}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", identificacion="
				+ identificacion + ", direccion=" + direccion + ", telefono=" + telefono + ", email=" + email + "]";
	}

	public Cliente(int idCliente, String nombre, String apellido, String identificacion, String direccion,
			String telefono, String email) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}
	
	
}
