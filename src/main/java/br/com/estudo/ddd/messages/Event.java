package br.com.estudo.ddd.messages;

import java.time.Instant;

import br.com.estudo.ddd.notificacao.Notification;

public abstract class Event extends Message implements Notification{

	private Instant dataHora;

	public Event() {
		this.dataHora = Instant.now();
	}

	public Instant getDataHora() {
		return dataHora;
	}

}
