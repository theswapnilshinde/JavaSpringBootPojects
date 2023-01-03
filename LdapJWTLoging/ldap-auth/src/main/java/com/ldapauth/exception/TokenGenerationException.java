package com.ldapauth.exception;

public class TokenGenerationException extends Exception {

	private static final long serialVersionUID = 1L;

	public TokenGenerationException(String message, Exception e){
        super(message, e);
    }
}
