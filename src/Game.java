import java.util.Scanner;

public class Game {
	private Player[] playerList;
	private Deck deck;
	private Scanner reader = new Scanner(System.in);
	private boolean playing = false;
	private int players;
	
	public Game()
	{
		deck = new Deck();
		//building the player list
		System.out.println("How many players are there?");
		this.players = reader.nextInt();
		playerList = new Player[players];
		for (int i = 0; i < players; i++) {
			System.out.print("Player "+(i+1)+" what is your name? ");
			String name = reader.nextLine();
			Player p = new Player(name, deck);
			playerList[i] = p;
		}
	}
	public void play() {
		int i = 0;
		while (playing) {
			Player currentPlayer = playerList[i];
			Card currentCard = deck.peek();
			System.out.println("The current player is "+currentPlayer.getName());
			System.out.println("The current card is "+currentCard.toString());
			System.out.println("Your hand is "+currentPlayer.toString());
			if (currentPlayer.getHand().canPlay(deck))
			{
				int choice = menu("First action in turn", new String[]{"Draw a Card","Play a Card"});
				switch(choice)
				{
				case 0:volDraw(currentPlayer); break;
				case 1:
				}
			}
			
			if (i < players) i++;
			else i = 0;
		}
	}
	private void volDraw(Player p) {
		Card c = deck.draw();
		if (c.canPlay(deck)) {
			int choice = menu("Second action in turn", new String[] {"Put "+c.toString()+" in your hand","Play "+c.toString()});
			switch(choice)
			{
			case 0: p.receiveCard(c); break;
			case 1: deck.discard(c); break;
			}
		}
	}
	private void forceDraw(int number, Player p) {
		for (int i = 0; i < number; i++) {
			p.receiveCard(deck.draw());
		}
	}
	private int menu(String title, String[] options) {
		int length = options.length;
		System.out.println(title);
		for (int i = 0; i < length; i++) {
			System.out.println(i+". "+options[i]);
		}
		System.out.print("Enter the index of the option you want: ");
		return reader.nextInt();
	}
}
