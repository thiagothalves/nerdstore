package br.com.estudo.nerdstore.catalogo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.estudo.ddd.data.Entidade;
import br.com.estudo.ddd.data.AggregateRoot;
import br.com.estudo.ddd.exceptions.DomainException;
import br.com.estudo.ddd.validacoes.Validacoes;

@Entity(name = "Categoria")
@Table(name = "categoria")
public class Categoria extends Entidade implements AggregateRoot {

	private int codigo;
	private String nome;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<Produto> produtos = new ArrayList<>();

	public Categoria() {
		super();
	}

	public Categoria(String nome, int codigo) throws DomainException {
		super();
		this.nome = nome;
		this.codigo = codigo;

		this.validar();
	}

	@ElementCollection
	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public String getNome() {
		return this.nome;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void alterarNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return this.nome + " - " + this.codigo;
	}

	public void validar() throws DomainException {
		Validacoes.validarSeVazio(nome, "O campo Nome da categoria não pode estar vazio");
		Validacoes.validarSeIgual(codigo, 0, "O campo codigo não pode ser 0");
	}

}
