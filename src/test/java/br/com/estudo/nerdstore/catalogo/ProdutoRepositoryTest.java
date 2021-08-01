package br.com.estudo.nerdstore.catalogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.estudo.ddd.exceptions.DomainException;

public class ProdutoRepositoryTest {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static ProdutoRepository produtoRepository;

	@BeforeAll
	private static void iniciarHibernate() {
		entityManagerFactory = Persistence.createEntityManagerFactory("produto");
		entityManager = entityManagerFactory.createEntityManager();
		produtoRepository = new ProdutoRepositoryImpl(entityManager);

	}

	@AfterAll
	private static void finalizarHibernate() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	void deveCriarUmaCategoria() throws DomainException {
		var categoria = new Categoria("Teste", 1);
		produtoRepository.adicionar(categoria);

		assertNotNull(categoria.getId());
		assertTrue(categoria.getId() > 0);
	}

	@Test
	void deveObterTodasAsCategorias() throws DomainException {
		var categoria = new Categoria("Teste", 2);
		var categoria2 = new Categoria("Teste", 3);
		produtoRepository.adicionar(categoria);
		produtoRepository.adicionar(categoria2);

		var categorias = produtoRepository.obterCategorias();

		assertTrue(categorias.size() > 1);

	}

	@Test
	void deveAdicionarUmProduto() throws DomainException {
		Produto produto = criarProduto();

		assertNotNull(produto.getId());
		assertTrue(produto.getId() > 0);
	}

	@Test
	void deveObterProdutoPorId() throws DomainException {

		Produto produto = criarProduto();

		var findProduto = produtoRepository.buscarPorId(produto.getId());

		assertEquals(produto.getNome(), findProduto.getNome());
		assertEquals(produto.getId(), findProduto.getId());
	}

	@Test
	void deveObterTodosOsProdutos() throws DomainException {
		criarProduto();
		criarProduto();
		criarProduto();

		var produtos = produtoRepository.buscarTodos();
		assertTrue(produtos.size() > 2);
	}

	@Test
	void deveObterProdutosPorCategoria() throws DomainException {

		var produto = criarProduto();
		var categoria = produto.getCategoria();

		var produto2 = new Produto("Outro Produto", "Outro Produto", false, new BigDecimal("100"), categoria,
				LocalDate.now(), "Image", new Dimensao(1l, 1l, 1l));

		produtoRepository.adicionar(produto2);
		var findProdutos = produtoRepository.obterPorCategoria(categoria);

		assertTrue(findProdutos != null && findProdutos.size() > 0);

	}

	@Test
	void deveAtualizarUmproduto() throws DomainException {
		var produto = criarProduto();

		produto.ativar();
		produtoRepository.atualizar(produto);
		var find = produtoRepository.buscarPorId(produto.getId());

		assertTrue(produto.isAtivo() == find.isAtivo());
		assertEquals(produto.getId(), find.getId());

	}

	@Test
	void deveAtualizarUmaCategoria() throws DomainException {

		var categoria = new Categoria("Old", 1);
		produtoRepository.adicionar(categoria);
		categoria.alterarNome("Novo");
		produtoRepository.atualizar(categoria);

		var find = produtoRepository.obterCategoriaPorId(categoria.getId().toString());

		assertEquals(categoria.getId(), find.getId());
		assertTrue(categoria.getNome().equals(categoria.getNome()));
		assertEquals("Novo", categoria.getNome());

	}

	private Produto criarProduto() throws DomainException {
		var categoria = new Categoria("Teste", 3);
		produtoRepository.adicionar(categoria);
		Produto produto = new Produto("Teste", "Teste", false, new BigDecimal("100"), categoria, LocalDate.now(),
				"Image", new Dimensao(1l, 1l, 1l));
		produtoRepository.adicionar(produto);
		return produto;
	}

	@Test
	void naoDeveObterProdutoPorID() {
		var produto = produtoRepository.buscarPorId(99L);

		assertNull(produto);

	}
}
