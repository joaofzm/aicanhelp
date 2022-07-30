package br.com.joaofzm15.yugiohstatslocal.frontEnd.exceptions;

public class BlankFieldException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BlankFieldException(String msg) {
		super(msg);
	}
}
