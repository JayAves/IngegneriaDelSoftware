package it.polimi.ingsw.ps29.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Match;

public class Room extends Thread{
	
	ArrayList<Socket> gamers;
	ArrayList<BufferedReader> readers;
	ArrayList<BufferedWriter> writers;
	Match match;
	
	public Room (ArrayList<Socket> gamers){
		
		this.gamers = gamers;
		
		try {
			for (int i = 0; i < gamers.size();){
				readers.add(new BufferedReader(new InputStreamReader(gamers.get(i).getInputStream())));
				writers.add(new BufferedWriter(new OutputStreamWriter(gamers.get(i).getOutputStream())));
				
				
			/*BufferedReader bufferedReader =
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bufferedWriter =
					new BufferedWriter(new OutputStreamWriter
							(socket.getOutputStream()));*/
			while (true) {
				/*String string = bufferedReader.readLine();
				bufferedWriter.write(string);
				bufferedWriter.newLine();
				bufferedWriter.flush(); */
			}
			}} catch (IOException e) {
					e.printStackTrace();
			}
		
		
	}

}
