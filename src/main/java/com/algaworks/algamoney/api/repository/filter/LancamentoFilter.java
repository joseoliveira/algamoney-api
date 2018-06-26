package com.algaworks.algamoney.api.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class LancamentoFilter {
	private String descricao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVencimentoDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVencimentoAte;

	/**
	 * Obtem o valor do atributo descricao.
	 *
	 * @return Valor do atributo descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Atualiza o valor do atributo descricao.
	 *
	 * @param pDescricao
	 */
	public void setDescricao(final String pDescricao) {
		descricao = pDescricao;
	}

	/**
	 * Obtem o valor do atributo dataVencimentoDe.
	 *
	 * @return Valor do atributo dataVencimentoDe
	 */
	public LocalDate getDataVencimentoDe() {
		return dataVencimentoDe;
	}

	/**
	 * Atualiza o valor do atributo dataVencimentoDe.
	 *
	 * @param pDataVencimentoDe
	 */
	public void setDataVencimentoDe(final LocalDate pDataVencimentoDe) {
		dataVencimentoDe = pDataVencimentoDe;
	}

	/**
	 * Obtem o valor do atributo dataVencimentoAte.
	 *
	 * @return Valor do atributo dataVencimentoAte
	 */
	public LocalDate getDataVencimentoAte() {
		return dataVencimentoAte;
	}

	/**
	 * Atualiza o valor do atributo dataVencimentoAte.
	 *
	 * @param pDataVencimentoAte
	 */
	public void setDataVencimentoAte(final LocalDate pDataVencimentoAte) {
		dataVencimentoAte = pDataVencimentoAte;
	}
}
