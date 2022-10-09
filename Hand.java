package blackjack;

import java.util.ArrayList;

public class Hand {
	private String name;
	private boolean ace;
	private int value;
	private ArrayList<Card> hand;
	
	public Hand() {
		name = "";
		ace = false;
		value = 0;
		hand = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		value += card.cardValue();
		if(card.cardValue() == 11) {
			ace = true;
		}
		hand.add(card);
	}
	
	public Card getCard(int index) {
		return hand.get(index);
	}
	
	public String toString() {
		String s = name + "'s hand: ";
		for(int i = 0; i < hand.size() - 1; i++) {
			s += hand.get(i).toString() + ", ";
		}
		s += hand.get(hand.size()-1).toString();
		return s;
	}
	
	
	public ArrayList<Card> returnCards(){
		return hand;
	}
	
	public void resetHand() {
		ace = false;
		value = 0;
		hand.clear();
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
}
