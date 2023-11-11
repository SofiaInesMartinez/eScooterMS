package tpe.administrationMS.DTO;

import org.springframework.http.HttpHeaders;

import tpe.administrationMS.model.JWTToken;

public class DTOAuthResponse {
	private JWTToken token;
	private HttpHeaders httpHeaders;
	
	public DTOAuthResponse() {
		super();
	}
	
	public DTOAuthResponse(JWTToken token, HttpHeaders httpHeaders) {
		this.token = token;
		this.httpHeaders = httpHeaders;
	}

	public JWTToken getToken() {
		return token;
	}

	public void setToken(JWTToken token) {
		this.token = token;
	}

	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}

	public void setHttpHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;
	}
}
