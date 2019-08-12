package co.com.testaccenture.microservicio.service;

import java.util.Collection;
import java.util.List;

import co.com.testaccenture.microservicio.model.Cliente;

public interface ClienteService {

    void saveCliente(Cliente cliente);
	
	void deleteClienteById(Cliente cliente);
	
	void updateCliente(Cliente cliente);
	
	List<Cliente> findAllCliente();
			
	Cliente findClienteByIdentificacion(String identificacion);

	Collection<Cliente> findMoviesByName(String string);
		
}
