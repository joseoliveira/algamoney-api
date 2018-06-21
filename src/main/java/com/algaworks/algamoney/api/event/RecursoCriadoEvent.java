package com.algaworks.algamoney.api.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

	private HttpServletResponse response;
	private Long codigo;

	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public RecursoCriadoEvent(final Object source, final HttpServletResponse response, final Long codigo) {
		super(source);

		this.response = response;
		this.codigo = codigo;
	}

	/**
	 * Obtem o valor do atributo response.
	 *
	 * @return Valor do atributo response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * Obtem o valor do atributo codigo.
	 *
	 * @return Valor do atributo codigo
	 */
	public Long getCodigo() {
		return codigo;
	}
}
