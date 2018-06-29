package com.algaworks.algamoney.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("{noop}admin")
			.roles("ROLE");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/categorias")
				.permitAll()
				.anyRequest()
				.authenticated().and()
				.httpBasic().and()
				// Desabilitado controle na sessão
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// csrf -> javascript injection dentro do serviço
				// como é somente API e não tem web, por isso foi desabilitado
				.csrf().disable();

	}
}
