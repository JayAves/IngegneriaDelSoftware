package it.polimi.ingsw.ps29.view_client;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.view.View;
import it.polimi.ingsw.ps29.view.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.messages.BonusChoice;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.view.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.view.messages.VaticanChoice;

public class Client implements Observer{
	
	
	private View view;
	private Connection networking;
	private String name;
	private boolean end;
	
	public Client (View view, String net){
		
		this.view=view;
		this.name=view.getName();
		ConnectionFactory factory= new ConnectionFactory();
		networking=factory.getNetworking(net, name);
		networking.addObserver(this);
		new Thread(networking).start();
		
	}
	
	@Override
	public void update(Observable o, Object arg) { //riceve da view e da socket/rmi
		VisitorServerMessages svisitor= new VisitorServerMessages();
		
		if(o instanceof Connection)
			((InteractionMessage)arg).receive(svisitor);
			
		else if (o instanceof View) 
			networking.sendMessage((InteractionMessage) arg);
			
		
		else 
			throw new IllegalArgumentException();
	}

	public class VisitorServerMessages{
	    	
	    	public void receive (ActionChoice msg) {
				view.askNextAction(msg);
			}
			
	    	public void receive (Exchange msg) {
				
	    		view.askAboutExchange(msg);
	    	}
			
	    	public void receive (BonusChoice msg) {
				view.askBonusAction(msg);
			}
			
	    	public void receive(VaticanChoice msg){
				view.askAboutExcommunication(msg);
			}
			
			public void receive(PrivilegeChoice msg){
				view.askAboutPrivileges(msg);
			}
			public void receive(PlayerInfoMessage msg){
				
			}
}
}
