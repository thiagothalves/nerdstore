package br.com.estudo.nerdstore.catalogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.estudo.ddd.exceptions.DomainException;


class ProdutoTest {

	@Test
	void deveValidarCampoNome() {

		var ex = assertThrows(DomainException.class, () -> criarProduto("", "Descricao", false, "100", "CAT",
				LocalDate.now(), "Imagem", new Dimensao(1L, 1L, 1L)));

		assertEquals("O campo Nome do produto não pode estar vazio", ex.getMessage());
	}

	@Test
	void deveValodarCampoDescricao() {
		var ex = assertThrows(DomainException.class, () -> criarProduto("Nome", "", false, "100", "CAT",
				LocalDate.now(), "Imagem", new Dimensao(1L, 1L, 1L)));

		assertEquals("O campo Descricao do produto não pode estar vazio", ex.getMessage());

	}

	@Test
	void deveValidarCampoValor() {
		var ex = assertThrows(DomainException.class, () -> criarProduto("Nome", "Descricao", false, "0", "CATEGORIA",
				LocalDate.now(), "Imagem", new Dimensao(1L, 1L, 1L)));

		assertEquals("O campo Valor do produto não pode se menor igual a 0", ex.getMessage());

	}

	@Test
	void deveValidarCampoCategoriaNaoNula() {
		var ex = assertThrows(DomainException.class, () -> new Produto("Nome", "Descricao", false,
				new BigDecimal("100"), null, LocalDate.now(), "Imagem", new Dimensao(1L, 1L, 1L)));

		assertEquals("O campo Categoria do produto não pode estar vazio", ex.getMessage());
	}

	@Test
	void deveValidarCampoImagem() {
		var ex = assertThrows(DomainException.class, () -> criarProduto("Nome", "Descricao", false, "100", "CAT",
				LocalDate.now(), "", new Dimensao(1L, 1L, 1L)));

		assertEquals("O campo Imagem do produto não pode estar vazio", ex.getMessage());

	}

	@Test
	void deveValidarCampoAltura() {

		var ex = assertThrows(DomainException.class, () -> criarProduto("Nome", "Descricao", false, "100", "cat",
				LocalDate.now(), "Imagem", new Dimensao(0L, 1L, 1L)));

		assertEquals("O campo Altura não pode ser menor ou igual a 0", ex.getMessage());

	}

	private Produto criarProduto(String nome, String descricao, boolean ativo, String valor, String codigo,
			LocalDate dataCriacao, String imagem, Dimensao dimensoes) throws DomainException {

		return new Produto(nome, descricao, ativo, new BigDecimal(valor), new Categoria(codigo, 1), dataCriacao, imagem,
				dimensoes);

	}
}
