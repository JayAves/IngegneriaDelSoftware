package it.polimi.ingsw.ps29.view_client;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class View extends Observable implements Runnable, Observer {

	@Override
	public void run() {
		System.out.println("1) raccolto\n 2)Produzione \n 3)Torre\nScelta: ");
		Scanner s = new Scanner(System.in);
		String text = s.next();
		setChanged();
		notifyObservers(text);
		
		
		s.close();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
