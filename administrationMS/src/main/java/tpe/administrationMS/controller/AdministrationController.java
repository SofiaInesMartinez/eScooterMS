package tpe.administrationMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import tpe.administrationMS.model.JWTToken;
import tpe.administrationMS.service.AuthService;
import tpe.administrationMS.DTO.DTOAuthRequest;
import tpe.administrationMS.DTO.DTOAuthResponse;
import tpe.administrationMS.DTO.DTOValidateToken;

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
	
	@GetMapping("/validate")
    public ResponseEntity<DTOValidateToken> validateGet() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        
        return ResponseEntity.ok(new DTOValidateToken(true, user.getName(), authorities));
    }
}
