package it.polimi.ingsw.ps29.viewclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Observable;
import java.util.Random;

import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps29.view.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view.messages.PlayerInfoMessage;

public abstract class Connection extends Observable implements Runnable{
	
	
	
	public abstract void sendMessage( InteractionMessage msg);
	
	public void setLoginToken(PlayerInfoMessage loginMessage) throws IOException{
		
		BufferedReader login = new BufferedReader(new FileReader("src/main/java/Login.json"));
	    GsonBuilder gcode = new GsonBuilder();
	    String jcode = gcode.create().fromJson(login, String.class );
	    
	    
	    if (jcode!=null) {
	    	
	    	loginMessage.setToken(jcode);
	    }
		
	    else {
		
		Random random1 = new Random();
		Random random2 = new Random();
		int head = random1.nextInt(1000000);
		int tail= random2.nextInt(1000000);
		String code= head+ loginMessage.getName()+tail;
		loginMessage.setToken(code);
		File myfile= new File ("src/main/java/Login.json");
		FileOutputStream fOut = new FileOutputStream(myfile);
        OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
        myOutWriter.append(code);
        myOutWriter.close();
        fOut.close();
	    	}
	  }
		
		
	}
	
	

