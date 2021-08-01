package br.com.estudo.nerdstore.events;

import br.com.estudo.ddd.events.DomainEvent;

public class ProdutoAbaixoEstoqueEvent extends DomainEvent {

	private int quantidadeRestante;

	public ProdutoAbaixoEstoqueEvent(Long aggregateId, int quantidadeRestante) {
		super(aggregateId);
		this.quantidadeRestante = quantidadeRestante;

	}

	public int getQuantidadeRestante() {
		return quantidadeRestante;
	}

}
