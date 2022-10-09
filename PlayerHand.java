package blackjack;

public class PlayerHand extends Hand{
	private int wager;
	
	public PlayerHand() {
		super();
		wager = 0;	
	}
	
	public void resetHand() {
		super.resetHand();
		wager = 0;
	}
	
	public int getWager() {
		return wager;
	}
	
	public void setWager(int wager) {
		this.wager = wager;
	}
}
