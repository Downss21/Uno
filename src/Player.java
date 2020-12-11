
public class Player {
	String name;
	int points;
	Hand hand;
	
	public Player(String name, Deck deck)
	{
		this.name = name;
		this.points = 0;
		this.hand = new Hand(deck);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @return the hand
	 */
	public Hand getHand() {
		return hand;
	}
	public void addPoints(int reward) {
		this.points +=reward;
	}
	public void resetHand(Deck deck) {
		this.hand = new Hand(deck);
	}
}
