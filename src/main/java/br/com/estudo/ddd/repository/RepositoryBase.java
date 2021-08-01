package br.com.estudo.ddd.repository;

import java.util.List;

import br.com.estudo.ddd.data.AggregateRoot;

public interface RepositoryBase<I, T extends AggregateRoot> {

	T adicionar(T entidade);

	T atualizar(T entidade);

	T buscarPorId(I id);

	List<T> buscarTodos();

}
