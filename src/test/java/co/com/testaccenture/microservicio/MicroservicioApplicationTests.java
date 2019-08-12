package co.com.testaccenture.microservicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import co.com.testaccenture.microservicio.io.ClienteIO;
import co.com.testaccenture.microservicio.model.Cliente;
import co.com.testaccenture.microservicio.service.ClienteService;

import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MicroservicioApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(MicroservicioApplicationTests.class);

	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;

	@Autowired
	private ClienteService clienteService;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();

		ClienteService clienteServices = Mockito.mock(ClienteService.class);

		Mockito.when(clienteServices.findAllCliente()).thenReturn(Arrays.asList(
				new Cliente(1, "calos", "prueba1", "prueba1", "direccion prueba1", "numero prueba1", "prueba1"),
				new Cliente(2, "pepe", "prueba2", "prueba2", "direccion prueba2", "numero prueba2", "prueba2"),
				new Cliente(3, "maria", "prueba3", "prueba3", "direccion prueba3", "numero prueba3", "prueba3"),
				new Cliente(4, "prueba4", "prueba4", "prueba4", "direccion prueba4", "numero prueba4", "prueba4"),
				new Cliente(5, "stick", "prueba5", "prueba5", "direccion prueba5", "numero prueba5", "prueba5"),
				new Cliente(6, "alfonzo", "prueba6", "prueba6", "direccion prueba6", "numero prueba6", "prueba6"),
				new Cliente(7, "luisa", "prueba7", "prueba7", "direccion prueba7", "numero prueba7", "prueba7"),
				new Cliente(8, "luis", "prueba8", "prueba8", "direccion prueba8", "numero prueba8", "prueba8")));

	}
	
	@Test
	public void test1_inserta_cliente() throws Exception {
		log.debug("[INVOKE] /v1/api/cliente");
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		log.debug("Current Time Stamp: " + ts);
		ClienteIO clienteRequest = new ClienteIO();
		clienteRequest.setIdCliente(93232);
		clienteRequest.setIdentificacion("213123");
		clienteRequest.setNombre("prueba");
		clienteRequest.setApellido("prueba");
		clienteRequest.setDireccion("carrera prueba # 12 34 ");
		clienteRequest.setTelefono("4219789");
		clienteRequest.setEmail("prueba@prueba.com.co");

		Gson gson = new Gson();
		String request = gson.toJson(clienteRequest);

		ResultActions resultActions = this.mvc
				.perform(post("/v1/api/cliente").content(request).contentType(MediaType.APPLICATION_JSON));
		resultActions.andDo(print());
	}

	@Test
	public void test2_listar_clientes() throws Exception {
		log.debug("[INVOKE] /v1/api/cliente");
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		log.debug("Current Time Stamp: " + ts);
		List<Cliente> clientes = clienteService.findAllCliente();

		Gson gson = new Gson();
		String request = gson.toJson(clientes);

		ResultActions resultActions = this.mvc
				.perform(get("/v1/api/cliente").content(request).contentType(MediaType.APPLICATION_JSON));
		resultActions.andDo(print());
	}

	@Test
	public void test3_return_cliente_by_name() {
		log.debug("[INVOKE] /v1/api/cliente");
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		log.debug("Current Time Stamp: " + ts);
		Collection<Cliente> clientes = clienteService.findMoviesByName("prueba");
		assertThat(getClienteIds(clientes), CoreMatchers.is(Arrays.asList(93232)));
	}

	@Test
	public void test4_buscar_cliente_identificacion() throws Exception {
		log.debug("[INVOKE] /v1/api/cliente");
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		log.debug("Current Time Stamp: " + ts);
		Cliente cliente = clienteService.findClienteByIdentificacion("213123");
		assertEquals(cliente.getIdCliente(), 93232);
  
	}

	@Test
	public void test5_actualizar_cliente() throws Exception {
		log.debug("[INVOKE] /v1/api/cliente");
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		log.debug("Current Time Stamp: " + ts);
		ClienteIO clienteRequest = new ClienteIO();
		clienteRequest.setIdCliente(93232);
		clienteRequest.setIdentificacion("213123");
		clienteRequest.setNombre("prueba 2");
		clienteRequest.setApellido("prueba 2");
		clienteRequest.setDireccion("carrera prueba # 12 34 prueba 2");
		clienteRequest.setTelefono("4219789");
		clienteRequest.setEmail("prueba@prueba.com.co");

		Gson gson = new Gson();
		String request = gson.toJson(clienteRequest);

		ResultActions resultActions = this.mvc
				.perform(put("/v1/api/cliente/{identificacion}", "213123").content(request).contentType(MediaType.APPLICATION_JSON));
		resultActions.andDo(print());
	}

	@Test
	public void test6_eliminar_cliente() throws Exception {
		log.debug("[INVOKE] /v1/api/cliente");
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		log.debug("Current Time Stamp: " + ts);
		ResultActions resultActions = this.mvc
				.perform(delete("/v1/api/cliente/{identificacion}", "213123").contentType(MediaType.APPLICATION_JSON));
		resultActions.andDo(print());
	}

	private List<Integer> getClienteIds(Collection<Cliente> clientes) {
		return clientes.stream().map(Cliente::getIdCliente).collect(Collectors.toList());
	}

}
