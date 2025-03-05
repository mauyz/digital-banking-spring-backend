package org.sid.ebankingbackend.exceptions;

public class BankAccountNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

	public BankAccountNotFoundException(String message) {
        super(message);
    }
}
