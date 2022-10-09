//Sarah Hoekema, Sean Chambers
//CS 145
//October 11, 2022
//Lab 4 - Deck of Cards

package blackjack;

//Card.java represents a single card for the game Blackjack
public class Card {
	//fields
	private final String face;
	private final String suit;

	//constructor
	public Card(String cardFace, String cardSuit){
		this.face = cardFace; //initializes face of card
		this.suit = cardSuit; //initializes suit of card
	}

	//return String representation of Card
	public String toString(){
		return face + " of " + suit;
	}
	//gets face of Card
	public String getFace() {
		return face;
	}
	//gets suit of Card
	public String getSuit() {
		return suit;
	}
	//calculates and returns the point value of the Card
	public int cardValue() {
		switch(face) {
			case "Ace": return 11;
			case "Deuce": return 2;
			case "Three": return 3;
			case "Four": return 4;
			case "Five": return 5;
			case "Six": return 6;
			case "Seven": return 7;
			case "Eight": return 8;
			case "Nine": return 9;
			default: return 10;
		}
	}
}
