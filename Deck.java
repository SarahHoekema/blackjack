//Sarah Hoekema, Sean Chambers
//CS 145
//October 11, 2022
//Lab 4 - Deck of Cards

package blackjack;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Stack;

//Deck.java represents a deck of playing cards
public class Deck{
  //fields
  private static final SecureRandom randomNumbers = new SecureRandom();
  private static final int NUMBER_OF_CARDS = 52;
  private Stack<Card> deck = new Stack<Card>(); //Card references
 
  //constructor
  public Deck(){
	  String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
      "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
      String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

      //populate deck with Card objects
      for (int count = 0; count < NUMBER_OF_CARDS; count++){
        deck.push(new Card(faces[count%13], suits[count/13]));
      }
  }

  //shuffles Deck by converting the Stack to an array, switching two random cards until
  //all cards have switched indexes, and converting is back into a Stack
  public void shuffle(){
	Card[] tempDeck = new Card[deck.size()];
	
	//loads temporary array
	for(int i = 0; i < tempDeck.length; i++) {
		tempDeck[i] = deck.pop();
	}

    //for each Card, picks another random Card (0-51) and swap them
    for(int first = 0; first < tempDeck.length; first++){
      int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

      //swaps current Card with randomly selected Card
      Card temp = tempDeck[first];
      tempDeck[first] = tempDeck[second];
      tempDeck[second] = temp;
    }
    
    //converts array to Stack
    for(int i = 0; i < tempDeck.length; i++) {
		deck.push(tempDeck[i]);
	}
  }

  //deals one card
  public Card dealCard(){
    //determines whether Cards remain to be dealt
    if(!deck.isEmpty()){
    	return deck.pop();
    } else {
      return null; //return null to indicate that all Cards were dealt
    }
  }
  //adds cards from hand back into the deck
  public void addCards(ArrayList<Card> hand) {
	  for(int i = 0; i < hand.size(); i++) {
		  deck.push(hand.get(i));
	  }
  }
}