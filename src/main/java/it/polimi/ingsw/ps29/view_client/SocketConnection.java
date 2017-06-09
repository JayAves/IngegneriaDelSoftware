package it.polimi.ingsw.ps29.view_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;

import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.Message;

public class SocketConnection extends Observable implements Connection,Runnable {
	
	  
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    private boolean connected;
    private int port;
    private String hostName;
    private String playerName= "PlayerName Not Initialized"; //per i test

    public SocketConnection() {
		connected = false;
		port=5555;
		
    }
   
    @Override
    public void connect(String hostName) throws IOException {
        
    	if(!connected){
	     this.hostName = hostName;
	     socket = new Socket(hostName,port);
         br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         pw = new PrintWriter(socket.getOutputStream(),true);
         connected = true;
         Thread t = new Thread(this);
         t.start();
        }
    }
    
    @Override
 	public void setPlayerName(String playerName){
 		
 		this.playerName=playerName;
 		
 	}
     
    @Override
	public void sendMessage(Message arg) {
		// TODO Auto-generated method stub
		
	}
    
    public void run() {
	   
    	String msg = ""; //holds the msg received from server
     
	   	 do{
			 try {
				
				msg= br.readLine();
			
			 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 } while (msg.equals("PlayerName?"));
		 
	   	 pw.println(playerName); //passo player name al server
        
	   	 while(connected){
        		 
        		 try {
					
        			 msg=br.readLine();
				
        		 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   		 	
        		 notifyObservers(msg);
	   		 	
        		
        	 }
          
    }

    public boolean isConnected() {
		return connected;
    }

    public void disconnect() {
		
    	if(socket != null && connected)
        {
          try {
			socket.close();
          }catch(IOException ioe) {
			//unable to close, nothing to do...
          }
          finally {
			this.connected = false;
          }
        }
    }
    
   

    public String getHostName(){
            return hostName;
        }

    public void setHostName(String hostName){
            this.hostName = hostName;
        }

	
    
    
 




}
