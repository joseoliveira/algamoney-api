package com.algaworks.algamoney.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// ControllerAdvice -> Observa toda a aplicação
@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
																  final HttpHeaders headers, final HttpStatus status,
																  final WebRequest request) {

		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause().toString();

		return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
	}

	public static class Erro {
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;

		public Erro(final String pMensagemUsuario, final String pMensagemDesenvolvedor) {
			mensagemUsuario = pMensagemUsuario;
			mensagemDesenvolvedor = pMensagemDesenvolvedor;
		}

		/**
		 * Obtem o valor do atributo mensagemUsuario.
		 *
		 * @return Valor do atributo mensagemUsuario
		 */
		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		/**
		 * Obtem o valor do atributo mensagemDesenvolvedor.
		 *
		 * @return Valor do atributo mensagemDesenvolvedor
		 */
		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
	}
}
