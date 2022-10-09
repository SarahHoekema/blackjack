package blackjack;
import java.util.*;

public class PlayerHand {
	
	private String name;
	private int wager;
	private boolean ace;
	private int value;
	private ArrayList<Card> hand;
	
	public PlayerHand() {
		name = "";
		wager = 0;
		ace = false;
		value = 0;
		hand = new ArrayList<Card>();	
	}
	
	public void addCard(Card card) {
		hand.add(card);
		value += card.cardValue();
		if(card.cardValue() == 11) {
			ace = true;
		}
	}
	
	public void getCards() {
		System.out.print("Current hand: ");
		for(int i = 0; i < hand.size(); i++) {
			System.out.print(hand.get(i).toString());
		}
		System.out.println();
	}
	
	
	public ArrayList<Card> returnCards(){
		return hand;
	}
	
	public int getWager() {
		return wager;
	}
	
	public int getValue() {
		if(ace == true && value > 21) {
			value -= 10;
		}
		return value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setWager(int wager) {
		this.wager = wager;
	}
}
