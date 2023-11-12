package tpe.administrationMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.validation.Valid;

import tpe.administrationMS.model.JWTToken;
import tpe.administrationMS.service.AuthService;
import tpe.administrationMS.DTO.DTOAuthRequest;
import tpe.administrationMS.DTO.DTOAuthResponse;
import tpe.administrationMS.DTO.DTOEncodeRequest;
import tpe.administrationMS.DTO.DTOValidateToken;

@RestController
@RequestMapping("administration")
public class AdministrationController {

	@Autowired
	private WebClient restClient;
	
	@Autowired
	private AuthService authService;
	
	public AdministrationController(@Qualifier("restClient") WebClient restClient) {
		this.restClient = restClient;
	}

/// USER SERVICES
	
	@PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authenticate( @Valid @RequestBody DTOAuthRequest request ) {
		DTOAuthResponse response = authService.authenticate(request);
        return new ResponseEntity<JWTToken>(response.getToken(), response.getHttpHeaders(), HttpStatus.OK);
    }
	
	@PutMapping("/encode")
	public ResponseEntity<String> getEncodedPassword(@Valid @RequestBody DTOEncodeRequest request) {
		return ResponseEntity.ok(authService.encodePassword(request));
	}
	
//	@PutMapping("/validate")
//	public ResponseEntity<Boolean> validateToken(@Valid @RequestBody DTOValidateToken request) {
//		return ResponseEntity.ok(authService.validate(request));
//	}
	
	@GetMapping("/hola")
	public String getHola() {
		return "hola";
	}
}
