package br.com.estudo.nerdstore.catalogo;

import java.util.List;

import br.com.estudo.ddd.repository.RepositoryBase;

public interface ProdutoRepository extends RepositoryBase<Long, Produto> {

	Categoria obterCategoriaPorId(String id);

	List<Produto> obterPorCategoria(Categoria categoria);

	List<Categoria> obterCategorias();

	void adicionar(Categoria categoria);

	void atualizar(Categoria categoria);

	default Class<Produto> getResourceClass() {
		return Produto.class;
	}

}
