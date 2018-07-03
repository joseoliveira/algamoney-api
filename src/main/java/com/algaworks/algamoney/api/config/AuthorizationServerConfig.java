package com.algaworks.algamoney.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Qualifier("authenticationManagerBean")
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * Mapeamento dos clients (aplicações que tem acesso à minha API.
	 * @param clients
	 * @throws Exception
	 */
	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				// Nome do client (aplicação)
				.withClient("angular")
				// Senha do client (aplicação)
				.secret("{noop}@ngul@r0")
				// Conseguir limitar o acesso do client (aplicação) em questão
				.scopes("read", "write")
				// Password flow -> fluxo onde a aplicação recebe o usuário e senha do usuário
				// Quando a aplicação client é de inteira confiança
				.authorizedGrantTypes("password", "refresh_token")
				// Período em segundos que o token ficará ativo
				// 1800 / 60 = 30 minutos
				.accessTokenValiditySeconds(20)
				// Refresh token fica guardado via https no cookie do browser
				// Por isso, 1 dia inteiro para expirar
				// Para que o usuário não se deslogue da aplicação e ter a necessidade de
				// fica logando
				.refreshTokenValiditySeconds(3600 * 24);
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.tokenStore(tokenStore())
				// JWT
				.accessTokenConverter(accessTokenConverter())
				// Sempre que pedir um novo accesstoken, um novo refresh token será gerado
				// Para que o usuário não se deslogue da aplicação e ter a necessidade de
				// fica logando
				.reuseRefreshTokens(false)
				.authenticationManager(authenticationManager);
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

		// Senha para validar o token
		jwtAccessTokenConverter.setSigningKey("algaworks");

		return jwtAccessTokenConverter;
	}

	/**
	 * Alterado para jwt token store.
	 * @return
	 */
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}
