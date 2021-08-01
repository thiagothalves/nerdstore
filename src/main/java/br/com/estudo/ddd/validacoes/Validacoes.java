package br.com.estudo.ddd.validacoes;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import br.com.estudo.ddd.exceptions.DomainException;

public class Validacoes {

	private Validacoes() {
		super();
	}

	public static void validarSeIgual(Object object1, Object object2, String mensagem) throws DomainException {

		if (object1.equals(object2)) {
			throw new DomainException(mensagem);
		}
	}

	public static void validarSeDiferente(Object object1, Object object2, String mensagem) throws DomainException {

		if (!object1.equals(object2)) {
			throw new DomainException(mensagem);
		}
	}

	public static void validarCaracteres(String valor, int maximo, String mensagem) throws DomainException {
		var length = valor.trim().length();
		if (length > maximo) {
			throw new DomainException(mensagem);

		}
	}

	public static void validarCaracteres(String valor, int minimo, int maximo, String mensagem) throws DomainException {
		var length = valor.trim().length();
		if (length < minimo || length > maximo) {
			throw new DomainException(mensagem);

		}
	}

	public static void validarExpressao(String pattern, String valor, String mensagem) throws DomainException {

		var regex = Pattern.compile(pattern);
		var matcher = regex.matcher(valor);

		var matchFound = matcher.find();

		if (!matchFound) {
			throw new DomainException(mensagem);
		}

	}

	public static void validarSeVazio(String valor, String mensagem) throws DomainException {

		if (valor == null || valor.trim().length() == 0) {
			throw new DomainException(mensagem);

		}
	}

	public static void validarSeNulo(Object object1, String mensagem) throws DomainException {

		if (object1 == null) {
			throw new DomainException(mensagem);

		}
	}

	public static void validarMinimoMaximo(double valor, double minimo, double maximo, String mensagem)
			throws DomainException {

		if (valor < minimo || valor > maximo) {
			throw new DomainException(mensagem);

		}
	}

	public static void validarMinimoMaximo(float valor, float minimo, float maximo, String mensagem)
			throws DomainException {
		if (valor < minimo || valor > maximo) {
			throw new DomainException(mensagem);
		}
	}

	public static void validarMinimoMaximo(int valor, int minimo, int maximo, String mensagem) throws DomainException {
		if (valor < minimo || valor > maximo) {
			throw new DomainException(mensagem);
		}
	}

	public static void validarMinimoMaximo(long valor, long minimo, long maximo, String mensagem)
			throws DomainException {
		if (valor < minimo || valor > maximo) {
			throw new DomainException(mensagem);
		}
	}

	public static void validarSeMenorQue(long valor, long minimo, String mensagem) throws DomainException {
		if (valor < minimo) {
			throw new DomainException(mensagem);
		}
	}

	public static void validarSeMenorQue(double valor, double minimo, String mensagem) throws DomainException {
		if (valor < minimo) {
			throw new DomainException(mensagem);
		}
	}

	public static void validarSeMenorQue(BigDecimal valor, BigDecimal minimo, String mensagem) throws DomainException {
		if (valor.compareTo(minimo) < 0) {
			throw new DomainException(mensagem);
		}
	}

	public static void validarSeFalso(boolean booleanvalor, String mensagem) throws DomainException {
		if (!booleanvalor) {
			throw new DomainException(mensagem);
		}
	}

	public static void validarSeVerdadeiro(boolean booleanvalor, String mensagem) throws DomainException {
		if (booleanvalor) {
			throw new DomainException(mensagem);
		}
	}

}
