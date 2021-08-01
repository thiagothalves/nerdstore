package br.com.estudo.ddd.events;

import br.com.estudo.ddd.messages.Event;

public class DomainEvent extends Event {

	public DomainEvent(Long aggregateId) {
		this.aggregateId = aggregateId;

	}

}
