//Sarah Hoekema, Sean Chambers
//CS 145
//October 11, 2022
//Lab 4 - Deck of Cards

package blackjack;
import java.util.*;

public class Blackjack {

	//fields
	private static Deck deck;
	private static int balance;
	private static Scanner scan;

	public static void main(String[] args) {
		//initalize objects
		deck = new Deck();
		scan = new Scanner(System.in);
		DealerHand dealer = new DealerHand();
		PlayerHand player = new PlayerHand();
		
		//introduce game
		introduction();
		scan.nextLine();


		//accepts players name and formats it 
		clear();
		System.out.println("Welcome player!");
		System.out.print("Please enter your name: ");
		String name = scan.nextLine();
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		player.setName(name);

		//initalize account
		clear();
		final int INITIAL_ACCOUNT = establishAccount(scan);
		balance = INITIAL_ACCOUNT;

		do{

			//shuffle deck
			deck.shuffle();

			//establishes the bet
			clear();
			player.setWager(establishBet(scan));

			//deals two cards to dealer and player
			dealer.addCard(deck.dealCard());
			player.addCard(deck.dealCard());
			dealer.addCard(deck.dealCard());
			player.addCard(deck.dealCard());
			
			boolean stand = false;
			//loops for action until user stands or busts
			while(player.getValue()<22 && !stand){
				//switch performs actions based on player input
				switch(getMove(scan, player)){
					case "HIT":
						player.addCard(deck.dealCard());
						System.out.print(player);
						scan.nextLine();
						clear();
						break;
					case "STAND":
						stand = true;
						clear();
						break;
					default:
						System.out.print("Sorry input not recognized... ");
						scan.nextLine();
						clear();
						break;
				}
		    }

			//if player is not bust the dealer takes their turn
			if(player.getValue()<22){
				//deals to dealer per preset moves
				System.out.println(dealer);
				if(dealer.hitOrStand()){
					do{
						System.out.println("The dealer hits.");
						dealer.addCard(deck.dealCard());
						System.out.print(dealer);
						scan.nextLine();
						clear();
					}while(dealer.hitOrStand());
				}
			} else{
				System.out.print("Bust! ");
				scan.nextLine();
				clear();
			}

			//checks if player beat the dealer
			if(checkWin(player,dealer)){
				System.out.println("You win!");
				balance += player.getWager();
			}else if(player.getValue() == dealer.getValue()){
				System.out.println("It's a tie. Your bet is being returned");
			}else{
				System.out.println("Better luck next time...");
				balance -= player.getWager();
			}
			
			//add cards back to deck
			deck.addCards(player.getHand());
			deck.addCards(dealer.getHand());

			//resets objects for next loop
			player.resetHand();
			dealer.resetHand();
			scan.reset();

		}while(balance != 0 && promptReplay(scan));
		//print results
	}

	//requests user to establish inital account amount
	private static int establishAccount(Scanner accountAmount){
		try {
			clear();
			System.out.print("Please input your inital balance: ");
			int amount = Integer.valueOf(accountAmount.nextLine());
			return amount;
		} catch (Exception e) {
			clear();
			System.out.println("Whoops something went wrong... " + e);
			System.out.println("Setting inital balance to 100");
			return 100;
		}
	}

	//requests user to place their bet
	private static int establishBet(Scanner betAmount){
		try {
			clear();
			System.out.printf("You have %d left in your account.\nWhat is your bet? ", balance);
			String bet = betAmount.nextLine();
			return Integer.valueOf(bet);
		} catch (Exception e) {
			clear();
			System.out.println("Whoops something went wrong... " + e);
			System.out.println("Setting bet to 2");
			return 2;
		}
	}
	
	/**
	 * Prints introduction to console.
	 * Explains game and established basic rules. 
	 */
	private static void introduction() {
		clear();
		System.out.println("Welcome to blackjack!");
		System.out.println("This game is brought to you by Sarah Hoekema and Sean Chambers");
		System.out.println("The goal of the game is to beat the dealers score without going over 21.");
		System.out.println("Cards are equal to their face value.");
		System.out.print("All face cards are worth 10 points, Aces are either 11 or 1.");
	}

	//returns true if player scored higher that dealer but not over 21, else returns false
	private static boolean checkWin(PlayerHand player, DealerHand dealer) {
		//sets total to -1 if player is bust, else sets to card total
		int playerTotal = player.getValue() < 22 ? player.getValue() : -1;
		int dealerTotal = dealer.getValue() < 22 ? dealer.getValue() : -1;
		return playerTotal > dealerTotal ? true : false;
	}

	private static String getMove(Scanner nextMove, PlayerHand player){
		clear();
		System.out.println(player.toString());
		System.out.print("Would you like to hit or stand? ");
		String move = nextMove.nextLine();
		return move.toUpperCase();
	}

	private static boolean promptReplay(Scanner playAgainScanner) {
		clear();
		System.out.print("Would you like to play again? ");
		try {
			String response = playAgainScanner.nextLine();    
			//ensures response is the upper case of the first character
			char upper = Character.toUpperCase(response.charAt(0));	
			switch(upper) {								            
				case 'Y':
					return true;
				case 'N': 
					return false;
				default: 
					System.out.println("Sorry that response is not valid...");
			}
		} catch (Exception e) {
			clear();
			System.out.println("Sorry that response is not valid...");
		}
		return false;
	}

	//clears the console screen
	private static void clear(){
		for(int i = 0; i < 20; i++){
			System.out.println("");
		}
	}
}
