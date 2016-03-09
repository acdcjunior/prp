package net.acdcjunior.prp.domain;


public class ImpossivelExcluirException extends Exception {

	private static final long serialVersionUID = 1L;

	public ImpossivelExcluirException(String razao) {
		super(razao);
	}

}