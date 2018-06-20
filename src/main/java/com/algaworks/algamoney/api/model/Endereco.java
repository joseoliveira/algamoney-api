package com.algaworks.algamoney.api.model;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;

	/**
	 * Obtem o valor do atributo logradouro.
	 *
	 * @return Valor do atributo logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * Atualiza o valor do atributo logradouro.
	 *
	 * @param pLogradouro
	 */
	public void setLogradouro(final String pLogradouro) {
		logradouro = pLogradouro;
	}

	/**
	 * Obtem o valor do atributo numero.
	 *
	 * @return Valor do atributo numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Atualiza o valor do atributo numero.
	 *
	 * @param pNumero
	 */
	public void setNumero(final String pNumero) {
		numero = pNumero;
	}

	/**
	 * Obtem o valor do atributo complemento.
	 *
	 * @return Valor do atributo complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Atualiza o valor do atributo complemento.
	 *
	 * @param pComplemento
	 */
	public void setComplemento(final String pComplemento) {
		complemento = pComplemento;
	}

	/**
	 * Obtem o valor do atributo bairro.
	 *
	 * @return Valor do atributo bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Atualiza o valor do atributo bairro.
	 *
	 * @param pBairro
	 */
	public void setBairro(final String pBairro) {
		bairro = pBairro;
	}

	/**
	 * Obtem o valor do atributo cep.
	 *
	 * @return Valor do atributo cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Atualiza o valor do atributo cep.
	 *
	 * @param pCep
	 */
	public void setCep(final String pCep) {
		cep = pCep;
	}

	/**
	 * Obtem o valor do atributo cidade.
	 *
	 * @return Valor do atributo cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Atualiza o valor do atributo cidade.
	 *
	 * @param pCidade
	 */
	public void setCidade(final String pCidade) {
		cidade = pCidade;
	}

	/**
	 * Obtem o valor do atributo estado.
	 *
	 * @return Valor do atributo estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Atualiza o valor do atributo estado.
	 *
	 * @param pEstado
	 */
	public void setEstado(final String pEstado) {
		estado = pEstado;
	}
}
