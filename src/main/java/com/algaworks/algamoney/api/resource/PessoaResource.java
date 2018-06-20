package com.algaworks.algamoney.api.resource;

import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<Pessoa> listar() {return pessoaRepository.findAll();}
}
