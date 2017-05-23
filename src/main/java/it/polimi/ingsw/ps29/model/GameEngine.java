package it.polimi.ingsw.ps29.model;
/*package it.polimi.ingsw.ps29.model.game;

public class GameEngine {
	
	public static final int MAX_ROUND = 6;
	
	private int round;
	private GameLogic model;
	private View view;
	
	public GameEngine () {
		
	}
	
	public GameEngine(GameLogic model) {
		round = 1;
		this.model = model;
	}
	
	private void gameManagment() {
		gameSetup();
		for(round = 1; round <= MAX_ROUND; round++) {
			roundManagment();
		}
	}
	
	private void roundManagment () {
		roundSetup();
		actionManagment();
		if(round%2==0) //turno pari
			vaticanReport();
		endOfTheRound();
	}

	private void gameSetup() {
		System.out.println("setup");
		
	}
	
	private void roundSetup() {
		System.out.println("round setup");
	}
	
	private void actionManagment() {
		for (int i=0; i < model.getGameBoard().getPlayers().size(); i++) {
			//playerAction(*stringa*);
		}
			
	}
	
	private void vaticanReport () {
		
	}
	
	private void endOfTheRound () {
		
	}
	
	public void playerAction (String player, String comando) {
		int choice = new Integer(comando);
		ManageAction manager;
		Action action = null;
		switch (choice) {
			case 1:
				action = new HarvestAction(player , model.getGameBoard());
				break;
			case 2:
				action = new ProductionAction();
				break;
			case 3:
				action = new TowerAction();
				break;
		}
		manager = new ManageAction (action);
		manager.doAction();
	}
	

}
*/