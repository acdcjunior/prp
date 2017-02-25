package io.github.acdcjunior.prp.legacy.domain;


public class ImpossivelExcluirException extends Exception {

	private static final long serialVersionUID = 1L;

	public ImpossivelExcluirException(String razao) {
		super(razao);
	}

}