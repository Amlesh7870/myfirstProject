package in.spring.springboot.model;

public class JWTResponse {

	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JWTResponse(String token) {
		super();
		this.token = token;
	}

	public JWTResponse() {
		super();
	}
	
}
