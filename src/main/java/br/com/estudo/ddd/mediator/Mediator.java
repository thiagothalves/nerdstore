package br.com.estudo.ddd.mediator;

import br.com.estudo.ddd.notificacao.Notification;

public interface Mediator {

	int publicar(Notification evento);

}
