package it.polimi.ingsw.ps29.view.GUI.utilities;

public class InteractionMessagesFunctions {
	
	public static int translateSpace (int index) {
		switch(index) {
		case 0:
		case 1:
		case 2:
		case 3:
			return 3;
		case 4:
		case 5:
		case 6:
		case 7:
			return 5;
		case 8:
		case 9:
		case 10:
		case 11:
			return 4;
		case 12:
		case 13:
		case 14:
		case 15:
			return 6;
		case 16:
			return 1;
		case 17:
			return 2;
		case 18:
			return 7;
		case 19:
			return 8;
		case 20:
			return 9;
		case 21: 
			return 10;
		case 22:
			return 11;
		default:
				return 12;
		}
	}
	
	public static int translateFloor (int index) {
		if(index>-1 && index<16)
			return index%4 +1;
		else return -1;
	}
	
	public static int translateFamiliar (String familiar) {
		switch(familiar.toLowerCase()) {
		case "black":
			return 1;
		case "white":
			return 2;
		case "orange":
			return 3;
		case "neutral":
			return 4;
		default:
			return -1;
		}
	}
	

}
