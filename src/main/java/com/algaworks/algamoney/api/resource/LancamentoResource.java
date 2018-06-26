package com.algaworks.algamoney.api.resource;

import com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler;
import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.repository.LancamentoRepository;
import com.algaworks.algamoney.api.repository.filter.LancamentoFilter;
import com.algaworks.algamoney.api.service.LancamentoService;
import com.algaworks.algamoney.api.service.exception.PessoaInativaException;
import com.algaworks.algamoney.api.service.exception.PessoaInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public List<Lancamento> pesquisar(
			final LancamentoFilter lancamentoFilter) {
		return lancamentoRepository.filtrar(lancamentoFilter);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable final Long codigo) {
		final Optional<Lancamento> lancamentoOptional = lancamentoRepository.findById(codigo);

		return lancamentoOptional.isPresent()
				? ResponseEntity.ok(lancamentoOptional.get())
				: ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody final Lancamento lancamento, final HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}

	@ExceptionHandler({PessoaInexistenteException.class})
	public ResponseEntity<Object> handlePessoaInexistenteException(final PessoaInexistenteException ex) {
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();

		List<AlgamoneyExceptionHandler.Erro> erros = Arrays.asList(new AlgamoneyExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));

		return ResponseEntity.badRequest().body(erros);
	}

	@ExceptionHandler({PessoaInativaException.class})
	public ResponseEntity<Object> handlePessoaInativaException(final PessoaInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("pessoa.inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();

		List<AlgamoneyExceptionHandler.Erro> erros = Arrays.asList(new AlgamoneyExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));

		return ResponseEntity.badRequest().body(erros);
	}


}
