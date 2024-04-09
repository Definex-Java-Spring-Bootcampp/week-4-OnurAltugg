package com.kredinbizdeservice.exceptions;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class KredinbizdeException extends RuntimeException {

	public KredinbizdeException() {
		super();
	}
	
    public KredinbizdeException(String message) {
        super(message);
    }
    
	public KredinbizdeException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
