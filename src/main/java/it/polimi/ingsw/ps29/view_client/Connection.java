package it.polimi.ingsw.ps29.view_client;

import java.io.IOException;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public interface Connection {
	
	public void connect(String hostname) throws IOException;
	
	public void setPlayerName(String playerName);

	public void sendMessage(InteractionMessage arg);
}
