import java.util.Scanner;

public class Game {
	private Player[] playerList;
	private Deck deck;
	private Scanner reader = new Scanner(System.in);
	private boolean playing = true;
	private int players;
	private int playDirection = 1;
	private int playerNumber = 0;
	
	public Game()
	{
		deck = new Deck();
		//building the player list
		System.out.print("Enter the number of players: ");
		this.players = reader.nextInt();
		playerList = new Player[players];
		for (int i = 0; i < players; i++) {
			System.out.print("Enter player "+(i+1)+"'s name: ");
			if (i == 0) reader.nextLine();
			String name = reader.nextLine();
			Player p = new Player(name, deck);
			playerList[i] = p;
		}
	}
	public void play() {
		while (playing) {
			Player currentPlayer = playerList[playerNumber];
			Card currentCard = deck.peek();
			System.out.println("The current player is "+currentPlayer.getName());
			System.out.println("The current card is a "+currentCard.toString());
			System.out.println("Your hand has "+currentPlayer.toString());
			if (currentPlayer.getHand().canPlay(deck))
			{
				int choice = menu("First action in turn", new String[]{"Draw a Card","Play a Card"});
				switch(choice)
				{
				case 0:volDraw(currentPlayer); break;
				case 1:playCard(currentPlayer); break;
				}
			}
			else volDraw(currentPlayer);
			advancePlayerlist();
		}
	}
	private void playCard(Player player) {
		int choice = menu("Playing Card on "+deck.peek().toString(), player.getHand().toStringArray());
		Card c = player.getCard(choice);
		if (c.isAction())
		{
			int choiceColor;
			switch (c.getNumber())
			{
			case "Skip":
				advancePlayerlist();
				break;
			case "Reverse":
				playDirection *= -1;
				break;
			case "+2":
				forceDraw(2, playerList[nextPlayer()]);
				break;
			case "Wild":
				choiceColor = menu("Wild Card Color Picker",Helper.COLORS);
				c.setColor(Helper.COLORS[choiceColor]);
				break;
			case "+4":
				choiceColor = menu("+4 Card Color Picker",Helper.COLORS);
				c.setColor(Helper.COLORS[choiceColor]);
				forceDraw(4,playerList[nextPlayer()]);
				break;
			}
		}
		player.removeCard(choice);
		deck.discard(c);
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
		else
		{
			p.receiveCard(c);;
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
	private void advancePlayerlist() {
		if (playDirection == 1)
		{
			if (playerNumber < players - 1) playerNumber += playDirection;
			else playerNumber = 0;
		}
		else
		{
			if (playerNumber > 0) playerNumber += playDirection;
			else playerNumber = players - 1;
		}
	}
	private int nextPlayer() {
		if (playDirection == 1)
		{
			if (playerNumber < players - 1) return playerNumber + playDirection;
			return 0;
		}
		else
		{
			if (playerNumber > 0) return playerNumber + playDirection;
			return players - 1;
		}
	}
}
