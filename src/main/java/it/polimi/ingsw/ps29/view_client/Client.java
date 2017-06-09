package it.polimi.ingsw.ps29.view_client;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.view.View;
import it.polimi.ingsw.ps29.view.messages.BonusChoice;
import it.polimi.ingsw.ps29.view.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.Message;
import it.polimi.ingsw.ps29.view.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.view.messages.VaticanChoice;

public class Client implements Observer{
	
	
	private ViewClient view;
	private Connection networking;
	private String name;
	
	public Client (ViewClient view, String net) throws IOException {
		
		this.view=view;
		this.name=view.getName();
		ConnectionFactory factory= new ConnectionFactory();
		networking=factory.getNetworking(net);
		networking.setPlayerName(name);
		networking.connect("localhost");
	}
	
	@Override
	public void update(Observable o, Object arg) { //riceve da view e da socket/rmi
		
		// TODO Auto-generated method stub
		
		VisitorServerMessages svisitor= new VisitorServerMessages();
		
		if(o instanceof Connection)
			((Message)arg).receive(svisitor);
			
		else if (o instanceof View) {
			networking.sendMessage((Message)arg);
		}
		else 
			throw new IllegalArgumentException();
	}
	
	public class VisitorServerMessages{
	    	
	    	public void receive (ActionChoice msg) {
				view.askNextAction();
			}
			
	    	public void receive (Exchange msg) {
				//creare metodo per lo scambio risorse
	    		//view.askAboutExchange(null);
	    	}
			
	    	public void receive (BonusChoice msg) {
				//view.askAboutExchanges
			}
			public void receive(VaticanChoice msg){
				
			}
			
			public void receive(PrivilegeChoice msg){
				
			}
	    }
}
