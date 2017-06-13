package it.polimi.ingsw.ps29.view_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public class SocketConnection extends Observable implements Connection,Runnable {
	
	  
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean connected;
    private int port;
    private String hostName;
    private String playerName= "PlayerName Not Initialized"; //per i test
	private BufferedReader br;
	private PrintWriter pw;

    public SocketConnection() throws IOException {
		connected = false;
		port=9001;
		
    }
   
    @Override
    public void connect(String hostName, String playerName) throws IOException {
        
    	if(!connected){
    		this.hostName = hostName;
    		this.playerName=playerName;
    		socket = new Socket(hostName,port);
    		br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		pw = new PrintWriter(socket.getOutputStream(), true);
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
	public void sendMessage(InteractionMessage arg) {
		// TODO Auto-generated method stub
		try {
			out.writeObject(arg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not serialize obj"+ arg.toString());
		}
	}
    
    public void run() {
	   
    	InteractionMessage msg = null;
    	String gameStart= "";	
	   	
    	try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	   	try {
			out.writeObject(playerName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Could not send playerName");
		}
	   	
    	System.out.println("PlayerName successfully sent");
		 

        
	   	 while(connected){
        		 
	   		while (!gameStart.equals("start")) {
	   			
	   			try {
					gameStart= br.readLine();
					if(gameStart == null)
						gameStart = "";
						
				
	   			} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Waiting the game to start failed");
				}
	   			
	   			try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   		}
	   		
	   		
        	try {
        		 
        		try {
						msg= (InteractionMessage) in.readObject();
        				setChanged();
						notifyObservers(msg);
						
        			 } catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						System.err.println("Could not deserialize class");
        			 }
				
        		 } catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.println("Could not receive from Server socket");
        		 	
        		 	}
        		 
        		 finally {
         			
        			 try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Could not close the client socket");
					}
         		}
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
