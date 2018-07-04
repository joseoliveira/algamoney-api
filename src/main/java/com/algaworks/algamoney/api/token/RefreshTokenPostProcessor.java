package com.algaworks.algamoney.api.token;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ControllerAdvice -> Observa toda a aplicação.
 * ResponseBodyAdvice<T> -> Capacita interferir após a escrita do response e antes de chegar no
 * client. <T> é o tipo do dado que pode interceptar quando estiver voltando.
 *
 */
@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

	@Override
	public boolean supports(final MethodParameter returnType, final Class<? extends HttpMessageConverter<?>> converterType) {
		// Validação para executar a regra no beforeBodyWrite()
		return returnType.getMethod().getName().equals("postAccessToken");
	}

	/**
	 * Método responsável por extrair o refreshToken e coloca-lo no cookie do retorno.
	 * @param body
	 * @param returnType
	 * @param selectedContentType
	 * @param selectedConverterType
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	public OAuth2AccessToken beforeBodyWrite(final OAuth2AccessToken body, final MethodParameter returnType,
											 final MediaType selectedContentType, final Class<? extends HttpMessageConverter<?>> selectedConverterType,
											 final ServerHttpRequest request, final ServerHttpResponse response) {

		HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;

		String refresToken = body.getRefreshToken().getValue();

		adicionarRefreshTokenNoCookie(refresToken, req, resp);
		removerRefreshTokenDoBody(token);

		return body;
	}

	private void removerRefreshTokenDoBody(final DefaultOAuth2AccessToken token) {
		token.setRefreshToken(null);
	}

	private void adicionarRefreshTokenNoCookie(final String refresToken, final HttpServletRequest req,
											   final HttpServletResponse resp) {
		Cookie refreshTokenCookie = new Cookie("refreshToken", refresToken);

		// Somente acessivel via http
		refreshTokenCookie.setHttpOnly(true);

		// Funcina apenas em https?
		refreshTokenCookie.setSecure(false); // TODO: Mudar para true em produção

		// Para qual caminho o cookie deve ser enviado automaticamente
		refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");

		// Tempo de expiração do cookie - em dias
		// 2592000 = 30 dias
		refreshTokenCookie.setMaxAge(2592000);

		// Adiciona o cookie na resposta
		resp.addCookie(refreshTokenCookie);
	}
}
