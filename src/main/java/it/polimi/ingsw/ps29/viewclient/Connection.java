package it.polimi.ingsw.ps29.viewclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Observable;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;

public abstract class Connection extends Observable implements Runnable{
	
	public abstract void sendMessage( InteractionMessage msg);
	
	public void setLoginToken(PlayerInfoMessage loginMessage) throws IOException{
		
		BufferedReader login = new BufferedReader(new FileReader("src/main/java/Login.json"));
	    GsonBuilder gcode = new GsonBuilder();
	    String[] jcode = gcode.create().fromJson(login, String[].class );
	    
	    if(jcode!=null) {
		    
	    	String token=checkForName(jcode,loginMessage.getName());
		    
		    if (token.equals("notFound")) {
		    	
		    	Random random1 = new Random();
		 	    Random random2 = new Random();
		 	    int head = random1.nextInt(1000000);
		 	    int tail= random2.nextInt(1000000);
		 	    String code= head+ loginMessage.getName()+tail;
		 		loginMessage.setToken(code);
		 		
		 		//I'm creating new Array with old token plus the new one, to be written in the json file Login//
		 		String[] newjcode= new String[jcode.length+1]; 
		 		System.out.println(newjcode.length);
		 		for (int j=0; j<jcode.length; j++)
		 			newjcode[j]=jcode[j];
		 		
		 		newjcode[jcode.length]=code;
		 		login.close();
		 		File myfile = new File("src/main/java/Login.json");
		 		FileOutputStream fout= new FileOutputStream(myfile);
		 		OutputStreamWriter myOutWriter= new OutputStreamWriter(fout);
		 		Gson gson=new Gson();
		 		myOutWriter.append(gson.toJson(newjcode));
		 		myOutWriter.close();
		 		fout.close();
		    }	
		    
		    else {
		    	
		    	loginMessage.setToken(token);
		    	
		    }
	   }
	    else {
	    	Random random1 = new Random();
	 	    Random random2 = new Random();
	 	    int head = random1.nextInt(1000000);
	 	    int tail= random2.nextInt(1000000);
	 	    String code= head+ loginMessage.getName()+tail;
	 		loginMessage.setToken(code);
	 		String[] newjcode= new String[1];
	 		newjcode[0]=code;
	 		Gson gson=new Gson();
	 		login.close();
	 		File myfile = new File("src/main/java/Login.json");
	 		FileOutputStream fout= new FileOutputStream(myfile);
	 		OutputStreamWriter myOutWriter= new OutputStreamWriter(fout);
	 		myOutWriter.append(gson.toJson(newjcode));
	 		myOutWriter.close();
	 		fout.close();
	 		System.out.println("FirstToken");
	    }
	}
		
	public String checkForName(String[] tokens, String name) {
		
		for (int i=0; i<tokens.length; i++) {
			
			if (tokens[i].contains(name))
				return tokens[i];
			}
		
		return "notFound";
	}

}
	
	

