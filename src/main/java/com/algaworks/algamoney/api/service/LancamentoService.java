package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.LancamentoRepository;
import com.algaworks.algamoney.api.repository.PessoaRepository;
import com.algaworks.algamoney.api.service.exception.PessoaInativaException;
import com.algaworks.algamoney.api.service.exception.PessoaInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(final Lancamento lancamento) {
		Optional<Pessoa> optionalPessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());

		optionalPessoa
				.orElseThrow(() -> new PessoaInexistenteException());

		if (optionalPessoa.get().isInativo()) {
			throw new PessoaInativaException();
		}

		return lancamentoRepository.save(lancamento);
	}
}
