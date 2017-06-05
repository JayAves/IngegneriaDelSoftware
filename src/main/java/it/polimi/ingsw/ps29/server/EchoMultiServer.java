package it.polimi.ingsw.ps29.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EchoMultiServer {

	public EchoMultiServer(int port) {
		
		try {
		
			ServerSocket serverSocket = new ServerSocket(port);
			Calendar now = Calendar.getInstance();
		    SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		    System.out.println("It is now : " + formatter.format(now.getTime()));
		
			while (true) {
				
				System.out.println("in attesa su " + port);
				Socket socket = serverSocket.accept();
				System.out.println("ricevuta connessione: "+ socket.getInetAddress() + ":" +socket.getPort());
				(new SocketClientHandler(socket)).start();
		
			}
			} catch (IOException e) {
				 System.out.println("Could not create server socket. Quitting.");
		         System.exit(-1);
				}
	}

	/*Le operazioni di lettura da stream non gestiscono le InterrupedException
		Quindi non serve richiamare interrupt() su un thread che legge da uno stream
		Si pu`o per`o chiudere lo stream dal quale il thread legge, oppure direttamente la Socket, richiamando il metodo close()
		In tal caso il thread ricever`a una java.io.IOException
		Per far questo, il thread principale deve tenersi memorizzati i
		riferimenti alle socket o agli stream
		Oppure il thread che si occupa del client deve implementare un metodo che chiude la socket, richiamabile dal thread principale
		 */

}
