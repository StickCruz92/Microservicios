/**
 * 
 */
package co.com.testaccenture.microservicio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author stick
 *
 */
@Data
@Document(collection = "database_sequences")
public class DatabaseSequence {
 
    @Id
    private String id;
 
    private long seq;
 
    //getters and setters omitted
}