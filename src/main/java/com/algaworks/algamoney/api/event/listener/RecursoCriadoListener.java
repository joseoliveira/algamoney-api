package com.algaworks.algamoney.api.event.listener;

import com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {
	@Override
	public void onApplicationEvent(final RecursoCriadoEvent recursoCriadoEvent) {
		HttpServletResponse response = recursoCriadoEvent.getResponse();
		Long codigo = recursoCriadoEvent.getCodigo();

		adicionarHeaderLocation(response, codigo);

	}

	private void adicionarHeaderLocation(final HttpServletResponse pResponse, final Long pCodigo) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{codigo}")
				.buildAndExpand(pCodigo)
				.toUri();

		pResponse.setHeader("Location", uri.toASCIIString());
	}
}
