//Sarah Hoekema, Sean Chambers
//CS 145
//October 11, 2022
//Lab 4 - Deck of Cards

package blackjack;

import java.util.ArrayList;

//Hand.java represents a single hand of playing cards for the game Blackjack
public class Hand {
	//fields
	private String name;
	private int aceOfValue11;
	private int value;
	private ArrayList<Card> hand;
	
	//constructor
	public Hand() {
		name = "";
		aceOfValue11 = 0;
		value = 0;
		hand = new ArrayList<Card>();
	}
	//adds a single card to the hand ArrayList
	public void addCard(Card card) {
		value += card.cardValue();
		if(card.getFace() == "Ace") {
			aceOfValue11++;
		}
		hand.add(card);
	}
	//returns the Card at the specified index
	public Card getCard(int index) {
		return hand.get(index);
	}
	//returns the value of the Card at the specified index
	public int getCardValue(int index) {
		return hand.get(index).cardValue();
	}
	//returns the face of the Card at the specified index
	public String getCardFace(int index) {
		return hand.get(index).getFace();
	}
	//gets the hand ArrayList
	public ArrayList<Card> getHand(){
		return hand;
	}
	//resets the data within the hand
	public void resetHand() {
		aceOfValue11 = 0;
		value = 0;
		hand.clear();
	}
	//returns a string representation of the hand
	public String toString() {
		String s;
		if(name.charAt(name.length()-1) == 's') {
			s = name + "' hand: ";
		} else {
			s = name + "'s hand: ";
		}
		for(int i = 0; i < hand.size() - 1; i++) {
			s += hand.get(i).toString() + ", ";
		}
		s += hand.get(hand.size()-1).toString();
		return s;
	}
	//returns the current value of the hand
	public int getValue() {
		while(aceOfValue11 > 0 && value > 21) {
			value -= 10;
			aceOfValue11--;
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
