package com.algaworks.algamoney.api.token;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

/**
 * Classe que remove o refresh token do cookie e adiciona dentro da requisição http.
 * Para que não seja necessário sempre obter uma nova requisição para o refresh_token.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // Filtro com prioridade muito alta
public class RefreshTokenCookiePreProcessorFilter implements Filter {
	static class MyServetRequestWrapper extends HttpServletRequestWrapper {

		private String refreshToken;

		/**
		 * Constructs a request object wrapping the given request.
		 *
		 * @param request The request to wrap
		 * @throws IllegalArgumentException if the request is null
		 */
		public MyServetRequestWrapper(final HttpServletRequest request, final String pRefreshToken) {
			super(request);

			this.refreshToken = pRefreshToken;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			ParameterMap<String, String[]>  map = new ParameterMap<>(getRequest().getParameterMap());
			map.put("refresh_token", new String[] { refreshToken });
			map.setLocked(true);

			return map;
		}
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		// Verifica se a requisição é para "oauth/token"
		if ("/oauth/token".equalsIgnoreCase(req.getRequestURI())
				&& "refresh_token".equalsIgnoreCase(req.getParameter("grant_type"))
				&& req.getCookies() != null) {

			for (Cookie cookie : req.getCookies()) {
				if (cookie.getName().equalsIgnoreCase("refreshToken")) {
					String refreshToken = cookie.getValue();
					req = new MyServetRequestWrapper(req, refreshToken);
				}
			}
		}

		chain.doFilter(req, response);
	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {

	}


	@Override
	public void destroy() {

	}
}
