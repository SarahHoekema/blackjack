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
