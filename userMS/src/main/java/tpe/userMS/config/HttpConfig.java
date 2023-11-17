package tpe.userMS.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import tpe.userMS.model.Roles;
import tpe.userMS.security.JwtFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class HttpConfig {


    private final JwtParser jwtParser;
    private final String secret = "QJeKx+s7XIv1WbBlj7vJ9CD3Ozj1rB3qjlNZY9ofWKJSaBNBo5r1q9Rru/OWlYb+UHV1n4/LJl1OBYYZZ7rhJEnn5peyHCd+eLJfRdArE37pc+QDIsJlabQtR7tYRa+SnvGRyL01uZsK33+gezV+/GPXBnPTj8fOojDUzJiPAvE=";
    
    @Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

    public HttpConfig() {
        final var keyBytes = Decoders.BASE64.decode(secret);
        final var key = Keys.hmacShaKeyFor( keyBytes );
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http ) throws Exception {
        http
        	.addFilterBefore( new JwtFilter( jwtParser ), UsernamePasswordAuthenticationFilter.class);
		http
		    .csrf( AbstractHttpConfigurer::disable )
		    .authorizeHttpRequests( auth -> auth
		    		.requestMatchers(new AntPathRequestMatcher("user/register")).permitAll()
		    		.requestMatchers(new AntPathRequestMatcher("user/**")).authenticated()
//		    		.requestMatchers("user/**").authenticated()
		    )
		    .anonymous( AbstractHttpConfigurer::disable )
		    .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
		http
		    .httpBasic( Customizer.withDefaults() );
		return http.build();
    }
}
