package blackjack;

public class DealerHand extends Hand{
	
	public DealerHand() {
		super();
		super.setName("Dealer");
	}
	
	public boolean requestCard() {
		if(super.getValue() <= 16) {
			return true;
		} else {
			return false;
		}
	}
}
