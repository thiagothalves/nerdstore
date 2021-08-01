package br.com.estudo.nerdstore.catalogo;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.estudo.ddd.data.Entidade;
import br.com.estudo.ddd.exceptions.DomainException;
import br.com.estudo.ddd.validacoes.Validacoes;

@Entity(name = "Dimensao")
@Table(name = "dimensao")
public class Dimensao extends Entidade {

	private Long altura;
	private Long largura;
	private Long profundidade;

	public Long getAltura() {
		return altura;
	}

	public Long getLargura() {
		return largura;
	}

	public Long getProfundidade() {
		return profundidade;
	}

	public Dimensao() {
		super();
	}

	public Dimensao(Long altura, Long largura, Long profundidade) throws DomainException {

		Validacoes.validarSeMenorQue(altura, 1, "O campo Altura não pode ser menor ou igual a 0");
		Validacoes.validarSeMenorQue(largura, 1, "O campo Largura não pode ser menor ou igual a 0");
		Validacoes.validarSeMenorQue(profundidade, 1, "O campo Profundidade não pode ser menor ou igual a 0");

		this.altura = altura;
		this.largura = largura;
		this.profundidade = profundidade;
	}

	public String descricaoFormatada() {
		return "LxAxP: " + largura + "x" + altura + "x" + profundidade;
	}

	@Override
	public String toString() {
		return this.descricaoFormatada();
	}
}
