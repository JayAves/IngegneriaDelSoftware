package it.polimi.ingsw.ps29.view.GUI.utilities;

import javax.swing.JTextArea;

public class ConsoleMessages {
	
	public static void writePlacement (int index, JTextArea console) {
		switch (index) {
		case 0:
			console.append("Placement in TERRITORY TOWER - FIRST FLOOR\n");
			break;
		case 1:
			console.append("Placement in TERRITORY TOWER - SECOND FLOOR\n");
			break;
		case 2:
			console.append("Placement in TERRITORY TOWER - THIRD FLOOR\n");
			break;
		case 3:
			console.append("Placement in TERRITORY TOWER - FOURTH FLOOR\n");
			break;
		case 4:
			console.append("Placement in CHARACTER TOWER - FIRST FLOOR\n");
			break;
		case 5:
			console.append("Placement in CHARACTER TOWER - SECOND FLOOR\n");
			break;
		case 6:
			console.append("Placement in CHARACTER TOWER - THIRD FLOOR\n");
			break;
		case 7:
			console.append("Placement in CHARACTER TOWER - FOURTH FLOOR\n");
			break;
		case 8:
			console.append("Placement in BUILDING TOWER - FIRST FLOOR\n");
			break;
		case 9:
			console.append("Placement in BUILDING TOWER - SECOND FLOOR\n");
			break;
		case 10:
			console.append("Placement in BUILDING TOWER - THIRD FLOOR\n");
			break;
		case 11:
			console.append("Placement in BUILDING TOWER - FOURTH FLOOR\n");
			break;
		case 12:
			console.append("Placement in VENTURE TOWER - FIRST FLOOR\n");
			break;
		case 13:
			console.append("Placement in VENTURE TOWER - SECOND FLOOR\n");
			break;
		case 14:
			console.append("Placement in VENTURE TOWER - THIRD FLOOR\n");
			break;
		case 15:
			console.append("Placement in VENTURE TOWER - FOURTH FLOOR\n");
			break;
		case 16:
			console.append("Placement in HARVEST SPACE\n");
			break;
		case 17:
			console.append("Placement in PRODUCTION SPACE\n");
			break;
		case 18:
			console.append("Placement in MARKET SPACE #1\n");
			break;
		case 19:
			console.append("Placement in MARKET SPACE #2\n");
			break;
		case 20:
			console.append("Placement in MARKET SPACE #3\n");
			break;
		case 21:
			console.append("Placement in MARKET SPACE #4\n");
			break;
		case 22:
			console.append("Placement in COUNCIL PALACE SPACE\n");
			break;
		}
		
		console.append("Choose familiar and number of servants and press Do Action!\n");
	}

}
