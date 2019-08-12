package co.com.testaccenture.microservicio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.testaccenture.microservicio.model.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, Integer>{

   @Query("{ 'identificacion' : ?0 }")
   public Cliente findByIdentificacion (String identificacion);
	
}
