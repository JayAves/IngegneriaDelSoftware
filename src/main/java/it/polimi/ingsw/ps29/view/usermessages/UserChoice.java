package it.polimi.ingsw.ps29.view.usermessages;

public class UserChoice implements UserMessage {

	private String name;
	private int[] choices;
	//int [0] spazio
	//int [1] piano (ev. vale 0)
	//int [2] num servitori
	//int [3] familiare
	
	public UserChoice (String name, int[] choices) {
		this.setName(name);
		this.setChoices(choices);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChoices(int i) {
		return choices[i];
	}

	public void setChoices(int[] choices) {
		this.choices = choices;
	}
	
	
}
