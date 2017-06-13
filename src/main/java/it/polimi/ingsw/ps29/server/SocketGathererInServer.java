package it.polimi.ingsw.ps29.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view.messages.PlayerInfoMessage;

public class SocketGathererInServer extends Observable implements Observer {

	 private Socket socket;
	 private ArrayList<SocketClientThread> clients; // salvo i clients
	 private ServerSocket ssocket;  //Server Socket
	 private StartServerThread sst; //inner class
	 private SocketClientThread clientThread;
	 private int port;
	 private boolean listening; 
	 private String tempName;
	 private ObjectOutputStream out;
	 private ObjectInputStream in;
	    

	 public SocketGathererInServer() {
	       
		 	this.clients = new ArrayList<SocketClientThread>();
	        this.port = 9001; //default port
	        this.listening = false;
	      
	        
	    }

	 public void startServer() {
	        if (!listening) {
	            this.sst = new StartServerThread();
	            this.sst.start();
	            this.listening = true;
	        }
	    }

	    public void stopServer() {
	       
	    	if (this.listening) {
	            this.sst.stopServerThread();
	            
	            
	            for (SocketClientThread th: clients){
	            
	            	th.stopClient();
	            
	            this.listening = false;
	            }
	    	}
	    }

	    
	    public void update(Observable observable, Object object) { // se client chiude socket, lo elimino dalle connessioni
	       
	        this.clients.remove(observable);
	    }

	    public int getPort() {
	        return port;
	    }

	    public void setPort(int port) {
	        this.port = port;
	    }

	    

	
	    
	  private class StartServerThread extends Thread { //creo connessioni
	        private boolean listen;

	        public StartServerThread() {
	            this.listen = false;
	        }

	        public void run() {
	            this.listen = true;
	            try {
	            	SocketGathererInServer.this.setPort(9001);
	            	SocketGathererInServer.this.ssocket = new ServerSocket(SocketGathererInServer.this.port);
	            	Calendar now = Calendar.getInstance();
	    		    SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
	    		    System.out.println("Server [ Socket-side ] is up now : " + formatter.format(now.getTime()));
	            	
	            	while (this.listen) {
	            		try {
	        				sleep(1000);
	        			} catch (InterruptedException e) {
	        				// TODO Auto-generated catch block
	        				System.out.println("Could not sleep");
	        			}
	            		
	            		System.out.println("I'm listening");
	            		SocketGathererInServer.this.socket = SocketGathererInServer.this.ssocket.accept();
	            		out = new ObjectOutputStream(socket.getOutputStream());
	                    in = new ObjectInputStream(socket.getInputStream());
	                    System.out.println("Client hooked up with socket "+socket.toString());
	                    PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
	                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                   
	                    try {
							tempName= (String)in.readObject();
						
	                    } catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							System.out.println("Could not get name from Client");
						}
	                    //tempName= "player";
	                    
	                    try {
	                        clientThread = new SocketClientThread(SocketGathererInServer.this.socket, tempName);
	                        Thread t = new Thread(SocketGathererInServer.this.clientThread);
	                        SocketGathererInServer.this.clientThread.addObserver(SocketGathererInServer.this);
	                        SocketGathererInServer.this.clients.add(SocketGathererInServer.this.clientThread);
	                        setChanged();
	                        notifyObservers(SocketGathererInServer.this.clientThread);
	                        System.out.println("SocketThread successfully created");
	                        t.start();
	                    } catch (IOException ioe) {
	                        System.out.println("Could not create SocketThread");
	                    	}
	                }
	            } catch (IOException ioe) {
	                //I/O errore in ServerSocket
	                System.out.println("Could not start server socket");
	            	//this.stopServerThread();
	            }
	        }

	        public void stopServerThread() {
	            try {
	                
	            	SocketGathererInServer.this.ssocket.close();
	            }
	            catch (IOException ioe) {
	                //unable to close ServerSocket
	            }
	            
	            this.listen = false;
	        }
	    }
	  		/*codice extra
	  		 * System.out.println("in attesa su " + this.port);
			Socket socket = serverSocket.accept();
			System.out.println("ricevuta connessione: "+ socket.getInetAddress() + ":" +socket.getPort());
			(new SocketClientHandler(socket)).start(); */
}
