//Card.java
//Card class represents a playing card
package deckOfCards;

public class Card {
  private final String face; //face of card ("Ace," "Deuce", ...)
  private final String suit; //suit of card ("Hearts", "Diamonds", etc)

  //two-argument constructor initializes card's face and suit
  public Card(String cardFace, String cardSuit){
    this.face = cardFace; //initializes face of card
    this.suit = cardSuit; //initializes suit of card
  }

  //return String representation of Card
  public String toString(){
    return face + " of " + suit;
  }
  
  public String getFace() {
	  return face;
  }
  
  public String getSuit() {
	  return suit;
  }
  
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
