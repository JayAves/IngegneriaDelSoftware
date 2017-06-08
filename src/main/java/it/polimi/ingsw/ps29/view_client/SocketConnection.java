package it.polimi.ingsw.ps29.view_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketConnection implements Connection,Runnable {
	
	  
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    private boolean connected;
    private int port;
    private String hostName;

    public SocketConnection() {
		connected = false;
		port=5555;
    }
   
    @Override
    public void connect(String hostName) throws IOException {
        if(!connected)
        {
	     this.hostName = hostName;
         this.port = port;
         socket = new Socket(hostName,port);
         br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         pw = new PrintWriter(socket.getOutputStream(),true);
         connected = true;
         Thread t = new Thread(this);
         t.start();
        }
    }

    public void run() {
	   
    	String msg = ""; //holds the msg received from server
         try {
            
        	 while(connected){
            	//
            }
            
         }
         
         finally { connected = false; }
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
    
    public int getPort(){
            return port;
        }

    public void setPort(int port){
            this.port = port;
        }

    public String getHostName(){
            return hostName;
        }

    public void setHostName(String hostName){
            this.hostName = hostName;
        }

	
    /*public static void main(String[] argv)throws IOException {
        SocketNetworking c = new So();
        c.connect("localhost",5555);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = "";
        while(!msg.equalsIgnoreCase("quit"))
        {
           msg = br.readLine();
           c.sendMessage(msg);
        }
        c.disconnect();
    }*/
}
