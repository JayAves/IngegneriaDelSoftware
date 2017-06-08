package it.polimi.ingsw.ps29.view_client;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.view.View;
import it.polimi.ingsw.ps29.view.usermessages.UserChoice;
import it.polimi.ingsw.ps29.view.usermessages.UserExchange;
import it.polimi.ingsw.ps29.view.usermessages.UserMessage;

public class Client implements Observer{
	
	
	private View_Client view;
	private Connection networking;
	private String name;
	
	public Client (View_Client view, String net) throws IOException {
		
		this.view=view;
		this.name=view.getName();
		
		/*AbstractFactory creator = new AbstractFactory();
		creator[0] = new InputFactory ();
		creator[1] = new ConnectionFactory();
		
		input = creator[0].getInput(in);
		networking = creator[1].getNetworking(net); */
		
		ConnectionFactory factory= new ConnectionFactory();
		networking=factory.getNetworking(net);
		
		networking.connect("localhost");
	}
	
	public void callView (){
		
		//a seconda del messaggio che ricevo chiamo un metodo diverso sulla view
		
	}
	
	public void handleInputAction (UserChoice arg){
		
		//ricevo dalla view input e devo passarlo via networking
	
	}
	

	

	@Override
	public void update(Observable o, Object arg) { //riceve da view e da socket/rmi
		
		// TODO Auto-generated method stub
		
	}
}
