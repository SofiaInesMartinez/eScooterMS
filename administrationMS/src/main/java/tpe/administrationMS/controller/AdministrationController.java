package tpe.administrationMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import tpe.administrationMS.model.JWTToken;
import tpe.administrationMS.service.AuthService;
import tpe.administrationMS.DTO.DTOAuthRequest;
import tpe.administrationMS.DTO.DTOAuthResponse;
import tpe.administrationMS.DTO.DTOEncodeRequest;

@RestController
@RequestMapping("administration")
public class AdministrationController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authenticate( @Valid @RequestBody DTOAuthRequest request ) {
		DTOAuthResponse response = authService.authenticate(request);
        return new ResponseEntity<JWTToken>(response.getToken(), response.getHttpHeaders(), HttpStatus.OK);
    }
	
	@PutMapping("/encode")
	public ResponseEntity<String> getEncodedPassword(@Valid @RequestBody DTOEncodeRequest request) {
		return ResponseEntity.ok(authService.encodePassword(request));
	}
}
