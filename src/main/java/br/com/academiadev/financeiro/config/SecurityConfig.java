package br.com.academiadev.financeiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import br.com.academiadev.financeiro.model.Usuario;
import br.com.academiadev.financeiro.repository.UsuarioRepository;

@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth, UsuarioRepository repository) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setSenha( "adminadmin" );
		usuario.setEmail( "admin@admin.com" );
		usuario.setNome( "Administrador do sistema" );

		if (repository.count() == 0)
			repository.saveAndFlush( usuario );

		auth.userDetailsService( email -> {
			return repository.findByEmail( email );
		} );
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers( HttpMethod.GET, //
				"/", //
				"/webjars/**", //
				"/*.html", //
				"/favicon.ico", //
				"/**/*.html", //
				"/v2/api-docs", //
				"/configuration/ui", //
				"/swagger-resources/**", //
				"/configuration/**", //
				"/swagger-ui.html", //
				"/webjars/**", //
				"/**/*.css", //
				"/**/*.js"//
		);

	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher( "/**" )
				.authorizeRequests()
				.antMatchers( "/", "/oauth**", "/webjars/**", "/error**", "/usuario" ).permitAll()
				.anyRequest()
				.authenticated();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
