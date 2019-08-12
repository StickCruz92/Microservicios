/**
 * 
 */
package co.com.testaccenture.microservicio.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.testaccenture.microservicio.model.Cliente;
import co.com.testaccenture.microservicio.repository.ClienteRepository;

/**
 * @author stick
 *
 */
@Service("clienteService")
@Transactional
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	

	@Override
	public void saveCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteRepository.save(cliente);
	}

	@Override
	public void deleteClienteById(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteRepository.delete(cliente);
	}

	@Override
	public void updateCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> findAllCliente() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}


	@Override
	public Cliente findClienteByIdentificacion(String identificacion) {
		// TODO Auto-generated method stub
		return clienteRepository.findByIdentificacion(identificacion);
	}


	@Override
	public Collection<Cliente> findMoviesByName(String nombre) {
		// TODO Auto-generated method stub
	  return clienteRepository.findAll().stream()
                .filter(cliente -> cliente.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
	}
	
	
}
