package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(final Long codigo, final Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);

		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");

		return pessoaRepository.save(pessoaSalva);
	}

	private Pessoa buscarPessoaPeloCodigo(final Long codigo) {
		Optional<Pessoa> pessoaSalvaOptional = pessoaRepository.findById(codigo);

		pessoaSalvaOptional
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return pessoaSalvaOptional.get();
	}

	public void atualizarPropriedadeAtivo(final Long codigo, final Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}
}
