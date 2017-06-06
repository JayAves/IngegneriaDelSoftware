package it.polimi.ingsw.ps29.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class SocketConnectionInServer implements Observer {

	 private Socket socket;
	 private RoomCreator roomCreator;

	    
	    private Vector<ClientThread> clients; // salvo i clients
	    private ServerSocket ssocket;  //Server Socket
	    private StartServerThread sst; //inner class
	    private ClientThread clientThread;
	    private int port;
	    private boolean listening; 

	    public SocketConnectionInServer() {
	        this.clients = new Vector<ClientThread>();
	        this.port = 5555; //default port
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
	            java.util.Enumeration<ClientThread> e = this.clients.elements();
	            
	            while(e.hasMoreElements())
	            {
			    ClientThread ct = e.nextElement();
	                ct.stopClient();
	            }
	            this.listening = false;
	        }
	    }

	    
	    public void update(Observable observable, Object object) { // se client chiude socket, lo elimino dalle connessioni
	       
	        this.clients.removeElement(observable);
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
	            	
	            	SocketConnectionInServer.this.ssocket = new ServerSocket(SocketConnectionInServer.this.port);
	            	Calendar now = Calendar.getInstance();
	    		    SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
	    		    System.out.println("SocketHandler is up now : " + formatter.format(now.getTime()));
	            	
	            	while (this.listen) { //devo aggiungere controllo sui 4 giocatori
	            		
	            		SocketConnectionInServer.this.socket = SocketConnectionInServer.this.ssocket.accept();
	                    System.out.println("Client connected");
	                    try {
	                        SocketConnectionInServer.this.clientThread = new ClientThread(SocketConnectionInServer.this.socket);
	                        Thread t = new Thread(SocketConnectionInServer.this.clientThread);
	                        SocketConnectionInServer.this.clientThread.addObserver(SocketConnectionInServer.this);
	                        SocketConnectionInServer.this.clients.addElement(SocketConnectionInServer.this.clientThread);
	                        //passo riferimento del thread a roomCreator
	                        t.start();
	                    } catch (IOException ioe) {
	                        //errore in ClientThread
	                    	}
	                }
	            } catch (IOException ioe) {
	                //I/O errore in ServerSocket
	                this.stopServerThread();
	            }
	        }

	        public void stopServerThread() {
	            try {
	                
	            	SocketConnectionInServer.this.ssocket.close();
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
