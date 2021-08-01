package br.com.estudo.nerdstore.events;

import br.com.estudo.ddd.mediator.Mediator;
import br.com.estudo.ddd.notificacao.Notification;
import br.com.estudo.nerdstore.catalogo.ProdutoRepository;

public class ProdutoEventHandler implements Mediator {

	private ProdutoRepository produtoRepository;

	public ProdutoEventHandler(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@Override
	public int publicar(Notification mensagem) {
		var evento = (ProdutoAbaixoEstoqueEvent) mensagem;
		var produto = this.produtoRepository.buscarPorId(evento.getAggregateId());
		produto.reporEstoque(20);
		produtoRepository.atualizar(produto);
		return produto.getQuantidadeEstoque();
	}

}
