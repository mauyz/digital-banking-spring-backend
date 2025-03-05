package org.sid.ebankingbackend.exceptions;
public class CustomerNotFoundException extends Exception {
    private static final long serialVersionUID = -422759353351418530L;

	public CustomerNotFoundException(String message) {
        super(message);
    }
}
