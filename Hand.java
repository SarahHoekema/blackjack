//Sarah Hoekema, Sean Chambers
//CS 145
//October 11, 2022
//Lab 4 - Deck of Cards

package blackjack;

import java.util.ArrayList;

public class Hand {
	//fields
	private String name;
	private boolean ace;
	private int value;
	private ArrayList<Card> hand;
	
	//constructor
	public Hand() {
		name = "";
		ace = false;
		value = 0;
		hand = new ArrayList<Card>();
	}
	//adds a single card to the hand ArrayList
	public void addCard(Card card) {
		value += card.cardValue();
		if(card.cardValue() == 11) {
			ace = true;
		}
		hand.add(card);
	}
	//returns the Card at the specified index
	public Card getCard(int index) {
		return hand.get(index);
	}
	//returns a string representation of the hand
	public String toString() {
		String s = name + "'s hand: ";
		for(int i = 0; i < hand.size() - 1; i++) {
			s += hand.get(i).toString() + ", ";
		}
		s += hand.get(hand.size()-1).toString();
		return s;
	}
	//gets the hand ArrayList
	public ArrayList<Card> getHand(){
		return hand;
	}
	//resets the data within the hand
	public void resetHand() {
		ace = false;
		value = 0;
		hand.clear();
	}
	//returns the current value of the hand
	public int getValue() {
		if(ace == true && value > 21) {
			value -= 10;
		}
		return value;
	}
	//gets the name of the hand
	public String getName() {
		return name;
	}
	//sets name of the hand
	public void setName(String name) {
		this.name = name;
	}
}
