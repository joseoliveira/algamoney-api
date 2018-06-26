package com.algaworks.algamoney.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pessoa")
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotNull
	private String nome;

	@Embedded
	private Endereco endereco;

	@NotNull
	private Boolean ativo;

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
	 * Obtem o valor do atributo nome.
	 *
	 * @return Valor do atributo nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Atualiza o valor do atributo nome.
	 *
	 * @param pNome
	 */
	public void setNome(final String pNome) {
		nome = pNome;
	}

	/**
	 * Obtem o valor do atributo endereco.
	 *
	 * @return Valor do atributo endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * Atualiza o valor do atributo endereco.
	 *
	 * @param pEndereco
	 */
	public void setEndereco(final Endereco pEndereco) {
		endereco = pEndereco;
	}

	/**
	 * Obtem o valor do atributo ativo.
	 *
	 * @return Valor do atributo ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * Atualiza o valor do atributo ativo.
	 *
	 * @param pAtivo
	 */
	public void setAtivo(final Boolean pAtivo) {
		ativo = pAtivo;
	}

	@JsonIgnore
	@Transient
	public Boolean isInativo() {
		return Boolean.valueOf(!this.ativo);
	}
}
