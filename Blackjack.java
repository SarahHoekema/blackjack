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
	private static ArrayList<PlayerHand> handList;
	private static Card dealersCard;

	public static void main(String[] args) {
		//initalize objects
		deck = new Deck();
		scan = new Scanner(System.in);
		handList = new ArrayList<PlayerHand>();
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

			//this is the card the user will be able to see
			dealersCard = dealer.getCard(0);
			
			//blackjack is true if the starting hand size is 2 and the value is equal to 21
			boolean blackjack = (player.getHand().size() == 2 && player.getValue() == 21) ? true : false;
			if(blackjack){
				System.out.print("Blackjack!");
				balance += (int) (player.getWager() * 1.5);
				deck.addCards(player.getHand());
				scan.nextLine();
				clear();
			}else{
				//checks if player wants to split, skips if this would make the player's hand value negative
				if(player.getCardFace(0)==player.getCardFace(1) && player.getWager() * 2 <= balance){
					System.out.println("The dealer's face up card is the " + dealersCard + ".");
					System.out.println(player);
					System.out.print("Would you like to split? (y/n) ");
					String response =  scan.nextLine();
					switch(response.toUpperCase().charAt(0)){
						case 'Y':
							PlayerHand tempHandOne = new PlayerHand();
							PlayerHand tempHandTwo = new PlayerHand();
							tempHandOne.setName(player.getName());
							tempHandTwo.setName(player.getName());
							tempHandOne.addCard(player.getCard(0));
							tempHandTwo.addCard(player.getCard(1));
							tempHandOne.setWager(player.getWager());
							tempHandTwo.setWager(player.getWager());
							handList.add(tempHandOne);
							handList.add(tempHandTwo);
							clear();
							break;
						case 'N':
							handList.add(player);
							clear();
							break;
						default:
							handList.add(player);
							clear();
							break;
					}
				}else{
					clear();
					System.out.println("The dealer's face up card is the " + dealersCard + ".");
					scan.nextLine();
					clear();
					handList.add(player);
				}

				boolean stand = false;
				//loops through arraylist in case player splits
				for(PlayerHand tempHand : handList){
					//loops for action until user stands or busts
					while(tempHand.getValue()<22 && !stand){
						//switch performs actions based on player input
						switch(getMove(scan, tempHand)){
							case "HIT":
								tempHand.addCard(deck.dealCard());
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
						if(dealer.getValue() > 21){
							System.out.print("Dealer busts! ");
							scan.nextLine();
							clear();
						}
					}
				} else{
					System.out.print("Bust! ");
					scan.nextLine();
					clear();
				}

				for(PlayerHand tempHand : handList){
					calculateWin(tempHand, dealer, scan);
					//add cards back to deck
					deck.addCards(tempHand.getHand());
					//resets objects for next loop
					tempHand.resetHand();
				}
			}
			//clear up this game
			deck.addCards(dealer.getHand());
			dealer.resetHand();
			handList.clear();
			scan.reset();

		}while(balance != 0 && promptReplay(scan));
		
		if(balance > INITIAL_ACCOUNT){
			clear();
			System.out.printf("Thanks for playing, you won $%d in total! ", balance - INITIAL_ACCOUNT);
			scan.nextLine();
			scan.close();
			clear();
		} else {
			clear();
			System.out.printf("Thanks for playing, you lost $%d in total!\nBetter luck next time! ", INITIAL_ACCOUNT - balance);
			scan.nextLine();
			scan.close();
			clear();
		}
	}

	//prints introduction to console that explains game and establishes basic rules
	private static void introduction() {
		clear();
		System.out.println("Welcome to blackjack!");
		System.out.println("This game is brought to you by Sarah Hoekema and Sean Chambers.");
		System.out.println("The goal of the game is to beat the dealer's score without going over 21.");
		System.out.println("Cards are equal to their face value.");
		System.out.print("All face cards are worth 10 points and Aces are either 11 or 1. ");
	}

	//requests user to establish initial account amount
	private static int establishAccount(Scanner accountAmount){
		try {
			clear();
			System.out.print("Please input your initial balance: ");
			int amount = Integer.valueOf(accountAmount.nextLine());
			return amount;
		} catch (Exception e) {
			clear();
			System.out.println("Whoops something went wrong... " + e);
			System.out.println("Setting initial balance to $100");
			return 100;
		}
	}

	//requests user to place their bet
	private static int establishBet(Scanner betAmount){
		while(true){
			try {
				clear();
				System.out.printf("You have $%d left in your account.\nWhat is your bet? ", balance);
				int bet = Integer.valueOf(betAmount.nextLine());
				//validates bet
				if(bet <= balance){
					return bet;
				}else{
					System.out.print("Bet can't exceed balance. ");
					betAmount.nextLine();
					clear();
					continue;
				}
			} catch (Exception e) {
				clear();
				System.out.println("Whoops that wasn't an int... ");
				System.out.println("Setting bet to $1");
				betAmount.nextLine();
				clear();
				return 1;
			}
		}
	}
	
	//asks if the player would like to hit or stand
	private static String getMove(Scanner nextMove, PlayerHand player){
		clear();
		System.out.println(player);
		System.out.print("Would you like to hit or stand? ");
		String move = nextMove.nextLine();
		return move.toUpperCase();
	}

	//returns true if player scored higher than dealer but not over 21, else returns false
	private static void calculateWin(PlayerHand player, DealerHand dealer, Scanner scan) {
		//sets total to -1 if player is bust, else sets to card total
		int playerTotal = player.getValue() < 22 ? player.getValue() : -1;
		int dealerTotal = dealer.getValue() < 22 ? dealer.getValue() : -1;
		//checks if player beat the dealer
		if(playerTotal > dealerTotal ? true : false){
			System.out.print("You win! ");
			//increases the balance by the players wager
			balance += player.getWager();
			scan.nextLine();
			clear();
		}else if(player.getValue() == dealer.getValue()){
			System.out.print("It's a tie. Your bet is being returned. ");
			scan.nextLine();
			clear();
		}else{
			System.out.print("Better luck next time... ");
			balance -= player.getWager();
			scan.nextLine();
			clear();
		}
	}

	//asks if the user would like to play again, provides basic error handing
	private static boolean promptReplay(Scanner playAgainScanner) {
		clear();
		System.out.print("Would you like to play again? (y/n) ");
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

	//clears the console screen, to be used after user acknowledges text 
	private static void clear(){
		for(int i = 0; i < 20; i++){
			System.out.println("");
		}
	}
}
