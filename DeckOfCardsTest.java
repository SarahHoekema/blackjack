//DeckOfCardsTest.java
//Card shuffling and dealing
package blackjack;
import java.util.ArrayList;

public class DeckOfCardsTest{
  //execute application
  public static void main(String[] args){
    Deck deck = new Deck();
    PlayerHand player = new PlayerHand();
    DealerHand dealer = new DealerHand();
    ArrayList<Hand> hands = new ArrayList<Hand>();
    //myDeckOfCards.shuffle(); //place Cards to random order
    deck.shuffle();
    //print all 52 Cards in the order in which they are dealt
//    for(int i = 1; i <= 52; i++){
//      //deal and display a Card
//      System.out.printf("%-19s", deck.dealCard());
//
//      if(i%4 == 0){ //output a new line after every fourth card
//        System.out.println();
//      }
//    }
    Card card = deck.dealCard();
    System.out.println(card);
    
    player.addCard(deck.dealCard());
    dealer.addCard(deck.dealCard());
    player.addCard(deck.dealCard());
    dealer.addCard(deck.dealCard());
    System.out.println(player);
    System.out.println(dealer);
    System.out.println(player.getValue());
    System.out.println(dealer.getValue());
  }
}