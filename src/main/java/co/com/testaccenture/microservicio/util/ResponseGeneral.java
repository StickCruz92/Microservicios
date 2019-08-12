/**
 * 
 */
package co.com.testaccenture.microservicio.util;

import lombok.Data;

/**
 * @author stick
 *
 */
@Data
public class ResponseGeneral {
	
	private String status;
	private String message;
	private Object resul;
	
	public ResponseGeneral() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseGeneral(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ResponseGeneral(String status, String message, Object resul) {
		super();
		this.status = status;
		this.message = message;
		this.resul = resul;
	}
	
}
