package com.algaworks.algamoney.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "lancamento")
public class Lancamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotNull
	private String descricao;

	@NotNull
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;

	@NotNull
	private BigDecimal valor;

	private String observacao;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipo;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_categoria")
	private Categoria categoria;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_pessoa")
	private Pessoa pessoa;

	/**
	 * Obtem o valor do atributo codigo.
	 *
	 * @return Valor do atributo codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * Atualiza o valor do atributo codigo.
	 *
	 * @param pCodigo
	 */
	public void setCodigo(final Long pCodigo) {
		codigo = pCodigo;
	}

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
	 * Obtem o valor do atributo dataVencimento.
	 *
	 * @return Valor do atributo dataVencimento
	 */
	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * Atualiza o valor do atributo dataVencimento.
	 *
	 * @param pDataVencimento
	 */
	public void setDataVencimento(final LocalDate pDataVencimento) {
		dataVencimento = pDataVencimento;
	}

	/**
	 * Obtem o valor do atributo dataPagamento.
	 *
	 * @return Valor do atributo dataPagamento
	 */
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	/**
	 * Atualiza o valor do atributo dataPagamento.
	 *
	 * @param pDataPagamento
	 */
	public void setDataPagamento(final LocalDate pDataPagamento) {
		dataPagamento = pDataPagamento;
	}

	/**
	 * Obtem o valor do atributo valor.
	 *
	 * @return Valor do atributo valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * Atualiza o valor do atributo valor.
	 *
	 * @param pValor
	 */
	public void setValor(final BigDecimal pValor) {
		valor = pValor;
	}

	/**
	 * Obtem o valor do atributo observacao.
	 *
	 * @return Valor do atributo observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * Atualiza o valor do atributo observacao.
	 *
	 * @param pObservacao
	 */
	public void setObservacao(final String pObservacao) {
		observacao = pObservacao;
	}

	/**
	 * Obtem o valor do atributo tipo.
	 *
	 * @return Valor do atributo tipo
	 */
	public TipoLancamento getTipo() {
		return tipo;
	}

	/**
	 * Atualiza o valor do atributo tipo.
	 *
	 * @param pTipo
	 */
	public void setTipo(final TipoLancamento pTipo) {
		tipo = pTipo;
	}

	/**
	 * Obtem o valor do atributo categoria.
	 *
	 * @return Valor do atributo categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Atualiza o valor do atributo categoria.
	 *
	 * @param pCategoria
	 */
	public void setCategoria(final Categoria pCategoria) {
		categoria = pCategoria;
	}

	/**
	 * Obtem o valor do atributo pessoa.
	 *
	 * @return Valor do atributo pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * Atualiza o valor do atributo pessoa.
	 *
	 * @param pPessoa
	 */
	public void setPessoa(final Pessoa pPessoa) {
		pessoa = pPessoa;
	}

	@Override
	public boolean equals(final Object pO) {
		if (this == pO) return true;
		if (pO == null || getClass() != pO.getClass()) return false;
		Lancamento that = (Lancamento) pO;
		return Objects.equals(codigo, that.codigo);
	}

	@Override
	public int hashCode() {

		return Objects.hash(codigo);
	}
}
