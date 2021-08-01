package br.com.estudo.nerdstore.catalogo;

import java.util.List;

import javax.persistence.EntityManager;

@SuppressWarnings("unchecked")
public class ProdutoRepositoryImpl implements ProdutoRepository {

	private EntityManager entityManager;

	public ProdutoRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Produto adicionar(Produto entidade) {
		entityManager.getTransaction().begin();
		entityManager.persist(entidade);
		entityManager.getTransaction().commit();
		return entidade;
	}

	@Override
	public Produto atualizar(Produto entidade) {
		return this.adicionar(entidade);
	}

	@Override
	public Produto buscarPorId(Long id) {
		return entityManager.find(getResourceClass(), Long.valueOf(id));
	}

	@Override
	public List<Produto> buscarTodos() {
		return entityManager.createQuery("from Produto").getResultList();
	}

	@Override
	public Categoria obterCategoriaPorId(String id) {
		Categoria categoria = entityManager.find(Categoria.class, Long.valueOf(id));
		return categoria != null ? categoria : null;
	}

	@Override
	public List<Produto> obterPorCategoria(Categoria categoria) {

		return entityManager.createQuery(" from Produto  where categoria =:categoria", getResourceClass())
				.setParameter("categoria", categoria).getResultList();
	}

	@Override
	public List<Categoria> obterCategorias() {
		return entityManager.createQuery("from Categoria").getResultList();
	}

	@Override
	public void adicionar(Categoria categoria) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(categoria);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void atualizar(Categoria categoria) {
		this.adicionar(categoria);

	}

}
