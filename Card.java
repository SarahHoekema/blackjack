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
}