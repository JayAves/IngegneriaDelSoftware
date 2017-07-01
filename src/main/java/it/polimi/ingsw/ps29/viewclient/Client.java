package it.polimi.ingsw.ps29.viewclient;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.messages.RejectMessage;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.messages.VaticanChoice;
import it.polimi.ingsw.ps29.view.View;

/**
 * Responsible of client-server communication  from client side, stands between View and Connection. Manages messages between them.
 * 
 * @author Pietro Grotti
 * @author Pietro Melzi
 *
 */

public class Client implements Observer{
	
	
	private View view;
	private Connection networking;
	private String name;
	
	
	/**
	 * Gets view's reference, creates and starts connection to server, becomes Observer of both. 
	 * @param view player's View
	 * @param net  wanted connection technology
	 */
	
	public Client (View view, String net){
		
		this.view=view;
		this.name=view.getName();
		ConnectionFactory factory= new ConnectionFactory();
		networking=factory.getNetworking(net, name);
		networking.addObserver(this);
		view.addObserver(this);
		new Thread(networking).start();
		
	}
	
	
	@Override
	public void update(Observable o, Object arg) { //riceve da view e da socket/rmi
		VisitorServerMessages visitor= new VisitorServerMessages();
		
		if(o instanceof Connection) {
			((InteractionMessage)arg).receive(visitor);
		}
			
			
		else if (o instanceof View) 
			networking.sendMessage((InteractionMessage) arg);
			
		
		else 
			throw new IllegalArgumentException();
	}

	/**
	 * Client's dispatcher for messages coming from server. Each InteractionMessage is converted in calling a specific method on View
	 * @author Pietro Melzi
	 * @author Pietro Grotti
	 *
	 */
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
			
			
			public void receive (InfoForView msg) {
				view.handleInfo (msg);
				view.getInputOutput().setTimer(msg.getTimer()-1000);
			}
			
			public void receive (TowersAndDicesForView msg) {
				view.showTowersAndDices(msg);
				
				
				
			}

			public void receive(PlayerInfoMessage playerInfoMessage) {
				// TODO Auto-generated method stub
				view.showMessage(playerInfoMessage);
			}
			
			public void receive (FirstBoardInfo msg) {
				view.showInitialInfo(msg);
				view.getInputOutput().setTimer(msg.getTimer()-1000);
				System.out.println("\nTimer: "+ view.getInputOutput().getTimer());
				
			}
			
			public void receive (RejectMessage msg) {
				view.showMessage(msg); 
			}
	}

		
}
