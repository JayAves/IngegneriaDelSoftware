package it.polimi.ingsw.ps29.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import it.polimi.ingsw.ps29.controller.Controller;
import it.polimi.ingsw.ps29.model.game.Match;

public class Room extends Thread{
	
	
	//ArrayList<BufferedReader> readers;
	//ArrayList<BufferedWriter> writers;
	Match match;
	Controller controller;
	ArrayList<ClientThread> sockets;
	
	public Room (ArrayList<String> playersInQueue,ArrayList<ClientThread> threads){
		
		
		for (int i=0;i<threads.size();i++){
			if (playersInQueue.get(i).contains("Socket")){
				playersInQueue.remove(playersInQueue.get(i));
			}
		}
		
		//this.match= new Match(players)
		
		//devo spostare il metodo create players
		
		/*	//legge lista players e recuperare indirizzo del thread corrispondente se la stringa è per socket
		
		
		try {
			for (int i = 0; i < playersInQueue.size();){
				//readers.add(new BufferedReader(new InputStreamReader(gamers.get(i).getInputStream())));
				//writers.add(new BufferedWriter(new OutputStreamWriter(gamers.get(i).getOutputStream())));
				
				
			BufferedReader bufferedReader =
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bufferedWriter =
					new BufferedWriter(new OutputStreamWriter
							(socket.getOutputStream()));
			while (true) {
				String string = bufferedReader.readLine();
				bufferedWriter.write(string);
				bufferedWriter.newLine();
				bufferedWriter.flush(); 
			}
			}} catch (IOException e) {
					e.printStackTrace();
			}
		
		
	}
*/}

	
}

