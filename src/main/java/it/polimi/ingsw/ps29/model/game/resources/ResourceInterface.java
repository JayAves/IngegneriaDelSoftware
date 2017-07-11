package it.polimi.ingsw.ps29.model.game.resources;

/**generic resource interface used to implement decorator pattern
 * 
 * @author Giovanni Mele
 * @author Pietro Melzi
 *
 */
public interface ResourceInterface {

	void modifyAmount(int amount);

	int getAmount();
	
	public String getType();
	
	public void negativeAmount(); //used when resources are to be paid instead of gotten
	
	public ResourceInterface clone();
		
}
