package tpe.administrationMS.config;

import tpe.administrationMS.model.Roles;
import tpe.administrationMS.security.JwtConfigurer;
import tpe.administrationMS.security.TokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
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

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfiguration {

	@Autowired
    private TokenProvider tokenProvider;
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        // AGREGAMOS NUESTRA CONFIG DE JWT.
        http
                .apply( securityConfigurerAdapter() );
        http
            .csrf( AbstractHttpConfigurer::disable )
            // MANEJAMOS LOS PERMISOS A LOS ENDPOINTS.
            .authorizeHttpRequests( auth -> auth
            		.requestMatchers("/**").permitAll()
//                    .requestMatchers("/api/authenticate").permitAll() //CAMBIAR TODODS
//                    .requestMatchers("/api/register").permitAll()
//                    .requestMatchers("/api/prueba").authenticated()
//                    .requestMatchers(HttpMethod.GET, "/api/producto/**").authenticated()
//                    .requestMatchers(HttpMethod.POST, "/api/producto").hasAuthority(Roles.ADMIN)
            )
            .anonymous( AbstractHttpConfigurer::disable )
            .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
            .httpBasic( Customizer.withDefaults() );
        return http.build();
    }

    /**
     * Nuestra configuracion de JWT.
     */
    private JwtConfigurer securityConfigurerAdapter() {
        return new JwtConfigurer();
    }


    ////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    /**
     * Para la carga de datos
     */

//    @Bean
//    public ResourceDatabasePopulator databasePopulator() {
//        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScript(new ClassPathResource("db_auth.sql"));
//        return populator;
//    }

}
