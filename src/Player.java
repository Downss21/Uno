
public class Player {
	private String name;
	private int points;
	private Hand hand;
	
	public Player(String name, Deck deck)
	{
		this.name = name;
		this.points = 0;
		this.hand = new Hand(deck);
	}
	public void receiveCard(Card c) {
		hand.addCard(c);
		System.out.println(name+" received a "+c.toString());
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
	public void removeCard(int c) {
		hand.removeCard(c);
	}
	public Card getCard(int c) {
		return hand.getCard(c);
	}
	
	public String toString() {
		return hand.toString();
	}
}
