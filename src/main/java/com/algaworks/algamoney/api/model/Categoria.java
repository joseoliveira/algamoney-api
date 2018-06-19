package com.algaworks.algamoney.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoria")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String nome;

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

	@Override
	public boolean equals(final Object pO) {
		if (this == pO) return true;
		if (pO == null || getClass() != pO.getClass()) return false;
		Categoria categoria = (Categoria) pO;
		return Objects.equals(codigo, categoria.codigo);
	}

	@Override
	public int hashCode() {

		return Objects.hash(codigo);
	}
}
