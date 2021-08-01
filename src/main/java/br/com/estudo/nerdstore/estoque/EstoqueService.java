package br.com.estudo.nerdstore.estoque;

public interface EstoqueService {

	boolean debitarEstoque(Integer id, int quantidade);

	boolean reporEstoque(Integer id, int quantidade);

}
