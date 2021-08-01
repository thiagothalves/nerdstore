package br.com.estudo.ddd.exceptions;

public class DomainException extends Exception {

	private static final long serialVersionUID = 1L;

	public DomainException() {
		super();
	}

	public DomainException(String message) {
		super(message);
	}

	public DomainException(String message, Exception e) {
		super(message, e);
	}

}
