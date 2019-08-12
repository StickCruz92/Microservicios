package co.com.testaccenture.microservicio.io;

import lombok.Data;

@Data
public class ClienteIO {

	private int idCliente;
	private String nombre;
	private String apellido;
	private String identificacion;
	private String direccion;
	private String telefono;
	private String email;
	
}
