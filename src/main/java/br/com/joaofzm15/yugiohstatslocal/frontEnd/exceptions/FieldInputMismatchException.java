package br.com.joaofzm15.yugiohstatslocal.frontEnd.exceptions;

public class FieldInputMismatchException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FieldInputMismatchException(String msg) {
		super(msg);
	}
}
