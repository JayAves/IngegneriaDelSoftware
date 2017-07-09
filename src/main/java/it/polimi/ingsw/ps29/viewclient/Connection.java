package it.polimi.ingsw.ps29.viewclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Observable;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;


/**
 * Opens a connection with server 
 * @author Pietro Grotti
 * @author Pietro Melzi
 *
 */

public abstract class Connection extends Observable implements Runnable{
	
	public abstract void sendMessage( InteractionMessage msg);
	
	
	
	
	/**
	 *	Creates a unique token for any player, giving clients the chance to reconnect to running matches they logged out.
	 *	Before creating a new token, checks if in file there is one consisting with playerName written in the parameter loginMessage. If so, that token is set in loginMessage. Otherwise the new one is set.
	 * @param loginMessage	where playerName is taken from and token is put
	 * @throws IOException for any trouble reading/writing Login.json file
	 * @see LoginMessage
	 */
	
	public void setLoginToken(PlayerInfoMessage loginMessage) throws IOException{
		
		BufferedReader login = new BufferedReader(new FileReader("src/data/login.json"));
	    GsonBuilder gcode = new GsonBuilder();
	    String[] jcode = gcode.create().fromJson(login, String[].class );
	    String newToken= "";
	    
	    if(jcode!=null) { 	//Login.json is not empty//
		    
	    	String token=checkForName(jcode,loginMessage.getName());
	    	
		    
		    if (token.equals("notFound")) {
		    	
		    	newToken= createNewToken(loginMessage);
		 		loginMessage.setToken(newToken);
		 		login.close(); //close File Reader
		 		writeNewArrayInFile(jcode,newToken, jcode.length+1);
		    }	
		    
		    else {

		    	loginMessage.setToken(token);
		    	
		    }
	   }
	    else { 		//Login.json is empty//
	    	
	    	newToken=createNewToken(loginMessage);
	 		loginMessage.setToken(newToken);
	 		login.close(); //close File Reader
	 		writeNewArrayInFile(jcode,newToken,1);
	 		System.out.println("FirstToken");
	    }
	}
		
	private String checkForName(String[] tokens, String name) {
		
		for (int i=0; i<tokens.length; i++) {
			
			if (tokens[i].contains(name))
				return tokens[i];
			}
		
		return "notFound";
	}
	
	private String createNewToken(PlayerInfoMessage loginMessage) {
		Random random1 = new Random();
 	    Random random2 = new Random();
 	    int head = random1.nextInt(1000000);
 	    int tail= random2.nextInt(1000000);
 	    return head+ loginMessage.getName()+tail;
	}
	
	private void writeNewArrayInFile(String[] jcode, String code,int size) throws IOException {
		String[] newjcode= new String[size];
		newjcode[size-1]=code;
		if(size!=1) {
			for (int j=0; j<jcode.length; j++)
	 			newjcode[j]=jcode[j];
		}
		Gson gson=new Gson();
		File myfile = new File("src/data/login.json");
 		FileOutputStream fout= new FileOutputStream(myfile);
 		OutputStreamWriter myOutWriter= new OutputStreamWriter(fout);
 		myOutWriter.append(gson.toJson(newjcode));
 		myOutWriter.close();
 		fout.close();
	}

}
	
	

