package br.com.estudo.ddd.messages;

public abstract class Message {

	protected String messageType;

	protected Long aggregateId;

	public Message() {
		this.messageType = this.getClass().getName();
	}

	public String getMessageType() {
		return messageType;
	}

	protected void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Long getAggregateId() {
		return aggregateId;
	}

	protected void setAggregateId(Long aggregateId) {
		this.aggregateId = aggregateId;
	}

}
