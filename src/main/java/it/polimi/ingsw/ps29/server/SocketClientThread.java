package it.polimi.ingsw.ps29.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;

import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;

public class SocketClientThread extends ClientThread {
	
    private BufferedReader br;
    private PrintWriter pw;
    private Socket socket;
    private boolean running;
    private String playerName;
    private boolean inGame;
   
    public SocketClientThread(Socket socket, String playerName) throws IOException { //costruttore
        
    	this.socket = socket;
        this.playerName= playerName;
        running = false;
        inGame=false;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            running = true;
            
            /*pw.println("Inserisci Nome giocatore:");
            playerName=br.readLine(); */
            
            
        }
        catch (IOException ioe) {
            throw ioe;
        }
    }
	
   
    public void stopClient(){
        
    	try {
		this.socket.close();
        }	catch(IOException ioe){ };
    }

    
    public void run() {
        
    	while (running && inGame) {
		    
			//provide your server's logic here//

		  
    		
		   }
		//running = false;
        
        try {
            this.socket.close(); //chiusura del socket
            System.out.println("Closing connection");
        } catch (IOException ioe) { }

        //notify the observers for cleanup 
        this.setChanged();             
        this.notifyObservers(this); 
        }
    /*Codice extra
     * BufferedReader bufferedReader =
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bufferedWriter =
					new BufferedWriter(new OutputStreamWriter
							(socket.getOutputStream())); */
    
    @Override
    public void setInGame(){
    	inGame=true;
    }

	@Override
	public String getClientName() {
		// TODO Auto-generated method stub
		return playerName;
	}


	@Override
	public void askNextAction() {
		// TODO Auto-generated method stub
		pw.println("asknextAction");
		try {
			
			br.readLine();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//deserializzo UserChioce
		setChanged();
		notify();
	}




	@Override
	public void askBonusAction(BonusActionEffect effect) {
		// TODO Auto-generated method stub
		//manda  a socket del client una stringa tipo askbonusAction++
	}


	@Override
	public void askAboutExchange(ExchangeResourcesEffect exchangeResourcesEffect) {
		// TODO Auto-generated method stub
		
	}
}

