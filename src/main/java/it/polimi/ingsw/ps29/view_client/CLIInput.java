package it.polimi.ingsw.ps29.view_client;

import java.util.Observable;
import java.util.Scanner;

public class CLIInput extends Input {

	@Override
	public void run() {
		Scanner s = new Scanner(System.in);
		System.out.println("Inserisci nome: ");
		String nome = s.next();
		
		System.out.println("1) raccolto\n2)Produzione\n3)Torre\nScelta: ");
		String text = s.next();
		
		setChanged();
		notifyObservers(nome+";"+text);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


}
