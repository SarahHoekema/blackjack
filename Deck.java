//DeckOfCards.java
//DeckOfCards class represents a deck of playing cards

package blackjack;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Stack;


public class Deck{
  //random number generator
  private static final SecureRandom randomNumbers = new SecureRandom();
  private static final int NUMBER_OF_CARDS = 52;

  private Stack<Card> deck = new Stack<Card>(); //Card references
 
  //constructor fills deck of Cards
  public Deck(){
	  String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
      "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
      String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

      //populate deck with Card objects
      for (int count = 0; count < NUMBER_OF_CARDS; count++){
        deck.push(new Card(faces[count%13], suits[count/13]));
      }
  }

  //shuffle deck of Cards with one-pass algorithm
  public void shuffle(){
	Card[] tempDeck = new Card[deck.size()];
	
	for(int i = 0; i < tempDeck.length; i++) {
		tempDeck[i] = deck.pop();
	}

    //for each Card, pick another random Card (0-51) and swap them
    for(int first = 0; first < tempDeck.length; first++){
      int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

      //swap current Card with randomly selected Card
      Card temp = tempDeck[first];
      tempDeck[first] = tempDeck[second];
      tempDeck[second] = temp;
    }
    
    for(int i = 0; i < tempDeck.length; i++) {
		deck.push(tempDeck[i]);
	}
  }

  //deal one card
  public Card dealCard(){
    //determine whether Cards remain to be dealt
    if(!deck.isEmpty()){
    	return deck.pop(); //return current Card in array
    } else {
      return null; //return null to indicate that all Cards were dealt
    }
  }
  
  public void addCards(ArrayList<Card> hand) {
	  for(int i = 0; i < hand.size(); i++) {
		  deck.push(hand.remove(i));
	  }
  }
}