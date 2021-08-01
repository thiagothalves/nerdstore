package br.com.estudo.ddd.bus;

import br.com.estudo.ddd.mediator.Mediator;
import br.com.estudo.ddd.messages.Event;

public class MediatorHandlerImpl implements MediatorHandler {

	private Mediator mediator;

	public MediatorHandlerImpl(Mediator mediator) {
		this.mediator = mediator;

	}

	@Override
	public void publicarEvento(Event evento) {
		this.mediator.publicar(evento);
	}

}
