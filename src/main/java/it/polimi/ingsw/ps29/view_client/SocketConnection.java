package it.polimi.ingsw.ps29.view_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;

import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.Message;

public class SocketConnection extends Observable implements Connection,Runnable {
	
	  
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    private ObjectInputStream in;
    private ObjectOutputStream out;
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
         in = new ObjectInputStream(socket.getInputStream());
         out= new ObjectOutputStream(socket.getOutputStream());
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
		try {
			out.writeObject(arg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not serialize obj"+ arg.toString());
		}
	}
    
    public void run() {
	   
    	Message msg = null;
    	String nameCatch= "";	
     
	   	 do{
			 try {
				
				nameCatch= br.readLine();
			
			 } catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Could not read Name from Server");
			}
			 
		 } while (nameCatch.equals("PlayerName?"));
		 
	   	 pw.println(playerName); //passo player name al server
        
	   	 while(connected){
        		 
        		 try {
					
        			 try {
						
        				 msg= (Message) in.readObject();
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
