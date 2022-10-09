package deckOfCards;
import java.util.*;

public class Blackjack {
	Deck deck = new Deck();

	public static void main(String[] args) {
		introduction();
		//establish initialAccount
		do{
			DealerHand dealer = new DealerHand();
			PlayerHand player = new PlayerHand();
			deck.shuffle();
			//get bets
			//deal cards
			//get move
			if(dealer.requestCard()){
				do{
					dealer.addHand(deck.dealCard());
				}while(dealer.requestCard());
			}
			if(checkWin(player,dealer)){
				//Do win logic
			}else if(player.getCardTotal() == dealer.getCardTotal()){
				//DO tie logic
			}else{
				//DO lose logic
			}
		}while(promptReplay() && balance != 0)
		//print results
	}
	
	public static void introduction() {
		System.out.println("introduction here");
	}

	//returns true if player scored higher that dealer but not over 21, else returns false
	public static boolean checkWin(PlayerHand player, DealerHand dealer) {
		//sets total to -1 if player is bust, else sets to card total
		int playerTotal = player.getCardTotal() > 22 ? player.getCardTotal() : -1;
		int dealerTotal = dealer.getCardTotal() > 22 ? dealer.getCardTotal() : -1;
		return playerTotal > dealerTotal ? true : false;
	}

	public static void promptReplay() {
		//TODO ask if new game is desired
	}

}
