//Sarah Hoekema, Sean Chambers
//CS 145
//October 11, 2022
//Lab 4 - Deck of Cards

package blackjack;

//DealerHand.java represents the hand of a Blackjack dealer
public class DealerHand extends Hand{
	//constructor
	public DealerHand() {
		super();
		super.setName("Dealer");
	}
	//returns true if the hand value is 16 or less and the dealer must hit or
	//false if the hand value is 17 or more and the dealer must stand
	public boolean hitOrStand() {
		if(super.getValue() <= 16) {
			return true;
		} else {
			return false;
		}
	}
}
