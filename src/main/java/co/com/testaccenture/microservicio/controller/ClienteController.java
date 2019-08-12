/**
 * 
 */
package co.com.testaccenture.microservicio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import co.com.testaccenture.microservicio.io.ClienteIO;
import co.com.testaccenture.microservicio.model.Cliente;
import co.com.testaccenture.microservicio.service.ClienteService;
import co.com.testaccenture.microservicio.util.ResponseGeneral;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author stick
 *
 */
@RestController
@RequestMapping("/v1/api/cliente")
@Api(tags = "cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	

	@PostMapping
	@ApiOperation(value = "Crear Cliente", notes = "Servicio para crear un nuevo cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente creado correctamente"),
			@ApiResponse(code = 400, message = "Solicitud Inválidad")})
	public ResponseEntity<?> createCliente(@RequestBody ClienteIO clienteIO, 
			UriComponentsBuilder uriComponentsBuilder) {
		
		Cliente cliente = new Cliente();
		cliente.setIdCliente(clienteIO.getIdCliente());
		cliente.setIdentificacion(clienteIO.getIdentificacion());
		cliente.setNombre(clienteIO.getNombre());
		cliente.setApellido(clienteIO.getApellido());
		cliente.setDireccion(clienteIO.getDireccion());
		cliente.setTelefono(clienteIO.getTelefono());
		cliente.setEmail(clienteIO.getEmail());
		
		clienteService.saveCliente(cliente);

		Cliente  ClienteResul = clienteService.findClienteByIdentificacion(clienteIO.getIdentificacion());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/v1/api/cliente/{identificacion}")
				.buildAndExpand(ClienteResul.getIdentificacion()).toUri());
	
		return new ResponseEntity<ResponseGeneral>(
				new ResponseGeneral("200", "Cliente Registrado operación exitosa", headers),
				HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/{identificacion}")
	@ApiOperation(value = "Actualizar Cliente", notes = "Servicio para actualizar un cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente actualizado correctamente"),
			@ApiResponse(code = 404, message = "Cliente no encontrado")})
	public ResponseEntity<ResponseGeneral> updateCliente (@PathVariable("identificacion") String identificacion,
			@RequestBody ClienteIO clienteIO) {

		Cliente cliente = clienteService.findClienteByIdentificacion(identificacion);

		if (cliente == null) {
			return new ResponseEntity<ResponseGeneral>(
					new ResponseGeneral("204", "No hay datos en la consulta"),
					HttpStatus.NOT_FOUND);
	        } else {
	    		cliente.setIdCliente(clienteIO.getIdCliente());
	    		cliente.setIdentificacion(clienteIO.getIdentificacion());
	    		cliente.setNombre(clienteIO.getNombre());
	    		cliente.setApellido(clienteIO.getApellido());
	    		cliente.setDireccion(clienteIO.getDireccion());
	    		cliente.setTelefono(clienteIO.getTelefono());
	    		cliente.setEmail(clienteIO.getEmail());
			    clienteService.updateCliente(cliente);
			
			return new ResponseEntity<ResponseGeneral>(
					new ResponseGeneral("200", "Cliente actualizado operación exitosa", cliente),
					HttpStatus.OK);
		}

	}

	@DeleteMapping("/{identificacion}")
	@ApiOperation(value = "Eliminar Cliente", notes = "Servicio para eliminar un cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente eliminado correctamente"),
			@ApiResponse(code = 404, message = "Cliente no encontrado")})
	public ResponseEntity<ResponseGeneral> RemoveCliente(@PathVariable("identificacion") String identificacion) {

		Cliente cliente = clienteService.findClienteByIdentificacion(identificacion);
		if (cliente != null) {
			clienteService.deleteClienteById(cliente);
			return new ResponseEntity<ResponseGeneral>(new ResponseGeneral("200", "Cliente eliminado Operación exitosa"),
					HttpStatus.OK);
		}
        return new ResponseEntity<ResponseGeneral>(new ResponseGeneral("204", "No hay datos asociado a la consulta"),
        		HttpStatus.NOT_FOUND);

	}
	
	@GetMapping
	@ApiOperation(value = "Listar Clientes", notes = "Servicio para listar todos los clientes")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente encontrados"),
			@ApiResponse(code = 404, message = "Cliente no encontrado")})
	public ResponseEntity<ResponseGeneral> findAll () {
		
		List<Cliente> clientes= new ArrayList<Cliente>();
		clientes = clienteService.findAllCliente();
		return new ResponseEntity<ResponseGeneral>(new ResponseGeneral("200", "Listar clientes operación exitosa", clientes),
				HttpStatus.OK);

	}
	
	
	//GET BY ID
	@GetMapping("/{identificacion}")
	@ApiOperation(value = "Buscar Clientes por identificacion", notes = "Servicio para buscar cliente por número de identificación")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Cliente encontrados"),
			@ApiResponse(code = 404, message = "Cliente no encontrado")})
	public ResponseEntity<ResponseGeneral> findClienteIdentificacion (@PathVariable("identificacion") String identificacion) {
		
		Cliente cliente = clienteService.findClienteByIdentificacion(identificacion);
	    if (cliente == null) {
	        return new ResponseEntity<ResponseGeneral>(new ResponseGeneral("204", "No hay datos asociado a la consulta"),
	        		HttpStatus.NOT_FOUND);
	    }
		return new ResponseEntity<ResponseGeneral>(
				new ResponseGeneral("200", "Mostrar cliente Operación exitosa", cliente),
				HttpStatus.OK);
	}
	
}
