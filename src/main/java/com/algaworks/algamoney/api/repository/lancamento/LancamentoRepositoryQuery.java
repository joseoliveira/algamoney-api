package com.algaworks.algamoney.api.repository.lancamento;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

	List<Lancamento> filtrar(final LancamentoFilter lancamentoFilter);
}
