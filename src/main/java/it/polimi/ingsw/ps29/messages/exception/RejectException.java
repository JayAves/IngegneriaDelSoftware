package it.polimi.ingsw.ps29.messages.exception;

import java.io.Serializable;

public class RejectException extends Exception implements Serializable {
	protected String message;
	
	public String getMessage () {
		return message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6522209486698592907L;

}
