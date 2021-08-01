package br.com.estudo.nerdstore.estoque;

import br.com.estudo.ddd.bus.MediatorHandler;
import br.com.estudo.ddd.exceptions.DomainException;
import br.com.estudo.nerdstore.catalogo.ProdutoRepository;
import br.com.estudo.nerdstore.events.ProdutoAbaixoEstoqueEvent;

public class EstoqueServiceImpl implements EstoqueService {

	private final static int QUANTIDADE_DE_ESTOQUE_BAIXO = 10;

	private ProdutoRepository produtoRepository;
	private MediatorHandler mediatorHandler;

	public EstoqueServiceImpl(ProdutoRepository produtoRepository, MediatorHandler mediatorHandler) {
		this.produtoRepository = produtoRepository;
		this.mediatorHandler = mediatorHandler;
	}

	@Override
	public boolean debitarEstoque(Integer id, int quantidade) {

		var produto = this.produtoRepository.buscarPorId(Long.valueOf(id));

		try {
			if (produto == null) {
				return false;
			}
			if (!produto.possuiEstoque(quantidade)) {
				return false;
			}

			produto.debitarEstoque(quantidade);

			if (produto.getQuantidadeEstoque() < QUANTIDADE_DE_ESTOQUE_BAIXO) {
				mediatorHandler
						.publicarEvento(new ProdutoAbaixoEstoqueEvent(produto.getId(), produto.getQuantidadeEstoque()));
			}

			this.produtoRepository.atualizar(produto);

		} catch (DomainException e) {
			e.printStackTrace();
			return false;

		}

		return true;
	}

	@Override
	public boolean reporEstoque(Integer id, int quantidade) {
		var produto = this.produtoRepository.buscarPorId(Long.valueOf(id));

		if (produto == null) {
			return false;
		}

		produto.reporEstoque(quantidade);
		this.produtoRepository.atualizar(produto);

		return true;
	}

}
