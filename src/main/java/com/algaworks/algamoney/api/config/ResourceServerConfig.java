package com.algaworks.algamoney.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	public void configure(final AuthenticationManagerBuilder auth) throws Exception {
		// Em memória.
		// POderia ser em um banco de dados
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("{noop}admin")
			.roles("ROLE");
	}

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/categorias").permitAll()
				.anyRequest().authenticated().and()
				// Desabilitado controle na sessão
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// csrf -> javascript injection dentro do serviço
				// como é somente API e não tem web, por isso foi desabilitado
				.csrf().disable();

	}

	@Override
	public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true);
	}
}
