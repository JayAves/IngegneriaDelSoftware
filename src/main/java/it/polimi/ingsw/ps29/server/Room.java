package it.polimi.ingsw.ps29.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.controller.Controller;
import it.polimi.ingsw.ps29.model.game.Match;


public class Room extends Thread{
	
	
	//ArrayList<BufferedReader> readers;
	//ArrayList<BufferedWriter> writers;
	Match model;
	Controller controller;
	ArrayList<ClientThread> sockets;
	
	public Room (ArrayList<ClientThread> playersInQueue) throws FileNotFoundException{
		
		ArrayList<String> names = new ArrayList<String>();
		
		for (ClientThread th: playersInQueue){
			
			names.add(th.getClientName());
		}
		
		
		
		model= new Match(names);
		
		controller = new Controller (model);
    	
		for (ClientThread th: playersInQueue){
			
			th.addObserver(controller);
			controller.addView(th, th.getClientName());
			th.setInGame();
		
		}
	
		model.addObserver(controller);
    	
    	model.gameEngine();
		
		
		
		
		
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


