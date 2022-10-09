//Sarah Hoekema, Sean Chambers
//CS 145
//October 11, 2022
//Lab 4 - Deck of Cards

package blackjack;

//PlayerHand.java represents the hand of a Blackjack player
public class PlayerHand extends Hand{
	//fields
	private int wager;
	
	//constructor
	public PlayerHand() {
		super();
		wager = 0;	
	}
	//resets the data within the PlayerHand and overrides
	//the superclass resetHand method
	public void resetHand() {
		super.resetHand();
		wager = 0;
	}
	//gets the player's wager for the hand
	public int getWager() {
		return wager;
	}
	//sets the wager for the player's hand
	public void setWager(int wager) {
		this.wager = wager;
	}
}
