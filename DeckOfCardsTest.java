//DeckOfCardsTest.java
//Card shuffling and dealing
package deckOfCards;

public class DeckOfCardsTest{
  //execute application
  public static void main(String[] args){
    Deck myDeckOfCards = new Deck();
    //myDeckOfCards.shuffle(); //place Cards to random order

    //print all 52 Cards in the order in which they are dealt
    for(int i = 1; i <= 52; i++){
      //deal and display a Card
      System.out.printf("%-19s", myDeckOfCards.dealCard());

      if(i%4 == 0){ //output a new line after every fourth card
        System.out.println();
      }
    }
  }
}