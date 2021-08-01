package br.com.estudo.ddd.bus;

import br.com.estudo.ddd.messages.Event;

public interface MediatorHandler {

	<T extends Event> void publicarEvento(T evento);
}
