import java.util.Scanner;

public class Game {
	Player[] playerList;
	Deck deck;
	Scanner reader = new Scanner(System.in);
	
	public Game()
	{
		deck = new Deck();
		//building the player list
		System.out.println("How many players are there?");
		int players = reader.nextInt();
		playerList = new Player[players];
		for (int i = 0; i < players; i++) {
			System.out.print("Player "+(i+1)+" what is your name? ");
			String name = reader.nextLine();
			Player p = new Player(name, deck);
			playerList[i] = p;
		}
	}

}
