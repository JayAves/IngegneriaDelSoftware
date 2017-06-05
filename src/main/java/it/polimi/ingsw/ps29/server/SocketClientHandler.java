package it.polimi.ingsw.ps29.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClientHandler extends Thread{
	
	Socket socket;
	public SocketClientHandler(Socket socket) { this.socket = socket; }
	public void run() {
		try {
			BufferedReader bufferedReader =
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bufferedWriter =
					new BufferedWriter(new OutputStreamWriter
							(socket.getOutputStream()));
			while (true) {
				/*String string = bufferedReader.readLine();
				bufferedWriter.write(string);
				bufferedWriter.newLine();
				bufferedWriter.flush(); */
			}
		} catch (IOException e) {
					e.printStackTrace();
			}
	}
}

