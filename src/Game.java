import java.util.Random;
import java.util.Scanner;

public class Game {
	private Player currentPlayer;
	private Deck deck;
	private final Random generator = new Random(0);
	private int playDirection = 1;
	private final Player[] playerList;
	private int playerNumber = 0;
	private final int players;
	private boolean playing = true;
	private final Scanner reader = new Scanner(System.in);
	private boolean won = false;
	private int turns = 0;

	public Game() {
		this.deck = new Deck();
		this.playerList = new Player[4];
		this.playerList[0] = new Player("1", this.deck);
		this.playerList[1] = new Player("2", this.deck);
		this.playerList[2] = new Player("3", this.deck);
		this.playerList[3] = new Player("4", this.deck);
		this.players = 4;
	}
	/*
	public Game() {
		this.deck = new Deck();
		// building the player list
		System.out.print("Enter the number of players: ");
		this.players = this.reader.nextInt();
		this.playerList = new Player[this.players];
		for (int i = 0; i < this.players; i++)
		{
			System.out.print("Enter player " + (i + 1) + "'s name: ");
			if (i == 0)
			{
				this.reader.nextLine();
			}
			final String name = this.reader.nextLine();
			final Player p = new Player(name, this.deck);
			this.playerList[i] = p;
		}
	}
	*/

	private void advancePlayerlist() {
		if (this.playDirection == 1)
		{
			if (this.playerNumber < this.players - 1)
			{
				this.playerNumber += this.playDirection;
			}
			else
			{
				this.playerNumber = 0;
			}
		}
		else
		{
			if (this.playerNumber > 0)
			{
				this.playerNumber += this.playDirection;
			}
			else
			{
				this.playerNumber = this.players - 1;
			}
		}
	}

	private void forceDraw(int number, Player p) {
		for (int i = 0; i < number; i++)
		{
			p.receiveCard(this.deck.draw());
		}
	}

	private int getWinnings(Player currentPlayer) {
		int winnings = 0;
		for (int i = 0; i < this.players; i++)
		{
			winnings += this.playerList[i].getWorth();
		}
		return winnings;
	}

	private int menu(String title, String[] options) {
		final int length = options.length;
		System.out.println(title);
		for (int i = 0; i < length; i++)
		{
			System.out.println(i + ". " + options[i] + "\u001b[0m");
		}
		int choice;
		System.out.print("Enter the index of the option you want: ");
		choice = this.generator.nextInt(length);
		return choice;
	}

	private int menu(String title, String[] options, boolean[] validPos) {
		final int length = options.length;
		System.out.println(title);
		for (int i = 0; i < length; i++)
		{
			System.out.println(i + ". " + options[i]);
		}
		System.out.print("Enter the index of the option you want: ");
		int choice;
		do
		{
			choice = this.generator.nextInt(length);
		} while (!validPos[choice]);
		return choice;
	}

	private int nextPlayer() {
		if (this.playDirection == 1)
		{
			if (this.playerNumber < this.players - 1) return this.playerNumber + this.playDirection;
			return 0;
		}
		else
		{
			if (this.playerNumber > 0) return this.playerNumber + this.playDirection;
			return this.players - 1;
		}
	}

	public void play() {
		while (!this.won)
		{
			while (this.playing)
			{
				turns++;
				this.currentPlayer = this.playerList[this.playerNumber];
				final Card currentCard = this.deck.peek();
				System.out.println("It is now turn #"+turns);
				System.out.println("The current player is " + this.currentPlayer.getName());
				System.out.println("The current card is a " + currentCard.toString());
				System.out.println("Your hand has " + this.currentPlayer.toString());
				if (this.currentPlayer.getHand().canPlay(this.deck.peek()))
				{
					int choice = this.menu("First action in turn", new String[] { "Draw a Card", "Play a Card" });
					choice = 1;
					switch (choice)
					{
					case 0:
						this.volDraw(this.currentPlayer);
						break;
					case 1:
						this.playHand(this.currentPlayer);
						break;
					}
				}
				else
				{
					this.volDraw(this.currentPlayer);
				}
				this.advancePlayerlist();
			}
			final int winnings = this.getWinnings(this.currentPlayer);
			this.currentPlayer.addPoints(winnings);
			System.out.println(this.currentPlayer.getName() + " has won the hand!");
			System.out.println("They have won " + winnings + " points");
			System.out.println("They currently have " + this.currentPlayer.getPoints());
			if (this.currentPlayer.getPoints() >= 500)
			{
				System.out.println(this.currentPlayer.getName() + " has won the game!");
				this.won = true;
			}
			else
			{
				this.playing = true;
				this.playDirection = 1;
				this.playerNumber = 0;
				this.deck = new Deck();
				for (int i = 0; i < this.players; i++)
				{
					this.playerList[i].resetHand(this.deck);
				}
			}
		}
	}

	private void playHand(Player player) {
		final int choice = this.menu("Playing Card on " + this.deck.peek().toString(), player.getHand().toStringArray(),
				player.getHand().getValidPositions(this.deck.peek()));
		final Card c = player.removeCard(choice);
		playCard(c, player);
	}

	private void playCard(Card c, Player player) {
		if (c.isAction())
		{
			int choiceColor;
			switch (c.getNumber())
			{
			case "Skip":
				this.advancePlayerlist();
				break;
			case "Reverse":
				this.playDirection *= -1;
				break;
			case "+2":
				this.forceDraw(2, this.playerList[this.nextPlayer()]);
				break;
			case "Wild":
				choiceColor = this.menu("Wild Card Color Picker", Helper.COLORS);
				c.setColor(Helper.COLORS[choiceColor]);
				break;
			case "+4":
				choiceColor = this.menu("+4 Card Color Picker", Helper.COLORS);
				c.setColor(Helper.COLORS[choiceColor]);
				this.forceDraw(4, this.playerList[this.nextPlayer()]);
				break;
			}
		}
		this.deck.discard(c);
		if (player.isEmpty())
		{
			this.playing = false;
		}
	}

	private void volDraw(Player p) {
		final Card c = this.deck.draw();
		if (c.canPlay(this.deck.peek()))
		{
			int choice = this.menu("Second action in turn",
					new String[] { "Put " + c.toString() + " in your hand", "Play " + c.toString() });
			choice = 1;
			switch (choice)
			{
			case 0:
				p.receiveCard(c);
				break;
			case 1:
				playCard(c, p);
				break;
			}
		}
		else
		{
			p.receiveCard(c);
			;
		}
	}
}
