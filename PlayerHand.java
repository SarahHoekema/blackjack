package deckOfCards;
import java.util.*;

public class PlayerHand {
	
	private String name;
	private int wager;
	private ArrayList<Card>;
	
	public PlayerHand(){
		wager = 0;
		name = "";
		ArrayList<Card> hand = new ArrayList<Card>();
	}
	
	public void addHand(){
		
	}
	
	public String getCards(int index) {
		return hand.get(index).toString();
	}
	
	public void getWager() {
		
	}
}
