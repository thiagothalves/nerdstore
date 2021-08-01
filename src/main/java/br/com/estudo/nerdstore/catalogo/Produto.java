package br.com.estudo.nerdstore.catalogo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.estudo.ddd.data.Entidade;
import br.com.estudo.ddd.data.AggregateRoot;
import br.com.estudo.ddd.exceptions.DomainException;
import br.com.estudo.ddd.validacoes.Validacoes;

@Entity(name = "Produto")
@Table(name = "produto")
public class Produto extends Entidade implements AggregateRoot {

	private String nome;
	private String descricao;
	private boolean ativo;
	private BigDecimal valor;
	private LocalDate dataCadastro;
	private String imagem;
	private int quantidadeEstoque;

	@ManyToOne
	@JoinColumn(name = "categoria", nullable = false)
	private Categoria categoria;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Dimensao dimensao;

	public Categoria getCategoria() {
		return categoria;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public String getImagem() {
		return imagem;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public Dimensao getDimensao() {
		return dimensao;
	}

	public Produto() {
		super();
	}

	public Produto(String nome, String descricao, boolean ativo, BigDecimal valor, Categoria categoria,
			LocalDate dataCadastro, String imagem, Dimensao dimensao) throws DomainException {

		super();
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
		this.valor = valor;
		this.dataCadastro = dataCadastro;
		this.imagem = imagem;
		this.categoria = categoria;
		this.dimensao = dimensao;

		validar();
	}

	public void ativar() {
		this.ativo = true;
	}

	public void desativar() {
		this.ativo = false;
	}

	public void alterarCategoria(Categoria categoria) {

		this.categoria = categoria;
	}

	public void alterarDescricao(String descricao) throws DomainException {

		Validacoes.validarSeVazio(descricao, "O campo descrição do produto não pode estar vazio");

		this.descricao = descricao;
	}

	public void debitarEstoque(int quantidade) throws DomainException {

		if (quantidade < 0) {
			quantidade *= -1;
		}

		if (!this.possuiEstoque(quantidade)) {
			throw new DomainException("Estoque insuficiente");
		}

		this.quantidadeEstoque -= quantidade;

	}

	public void reporEstoque(int quantidade) {
		this.quantidadeEstoque += quantidade;
	}

	public boolean possuiEstoque(int quantidade) {
		return this.quantidadeEstoque >= quantidade;
	}

	public void validar() throws DomainException {
		Validacoes.validarSeVazio(nome, "O campo Nome do produto não pode estar vazio");
		Validacoes.validarSeNulo(categoria, "O campo Categoria do produto não pode estar vazio");
		Validacoes.validarSeVazio(descricao, "O campo Descricao do produto não pode estar vazio");
		Validacoes.validarSeMenorQue(valor, BigDecimal.ONE, "O campo Valor do produto não pode se menor igual a 0");
		Validacoes.validarSeVazio(imagem, "O campo Imagem do produto não pode estar vazio");
	}

}
