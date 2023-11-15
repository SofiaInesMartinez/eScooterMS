package tpe.administrationMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tpe.administrationMS.DTO.DTOAuthRequest;
import tpe.administrationMS.DTO.DTOAuthResponse;
import tpe.administrationMS.model.JWTToken;
import tpe.administrationMS.security.JWTFilter;
import tpe.administrationMS.security.TokenProvider;

@Service("authService")
@Transactional
public class AuthService {
	
	@Autowired
	private TokenProvider tokenProvider;
	
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	
	public AuthService(AuthenticationManagerBuilder authenticationManagerBuilder) {
		this.authenticationManagerBuilder = authenticationManagerBuilder;
	}

	public DTOAuthResponse authenticate(DTOAuthRequest request) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( request.getEmail(), request.getPassword() );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var jwt = tokenProvider.createToken (authentication );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add( JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt );
        return new DTOAuthResponse(new JWTToken(jwt), httpHeaders);
	}
}
