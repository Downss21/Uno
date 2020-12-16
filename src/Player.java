
public class Player {
	private Hand hand;
	private final String name;
	private int points;

	public Player(String name, Deck deck) {
		this.name = name;
		this.points = 0;
		this.hand = new Hand(deck);
	}

	public void addPoints(int reward) {
		this.points += reward;
	}

	public Card getCard(int c) {
		return this.hand.getCard(c);
	}

	/**
	 * @return the hand
	 */
	public Hand getHand() {
		return this.hand;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return this.points;
	}

	public int getWorth() {
		final int length = this.hand.getLength();
		int worth = 0;
		for (int i = 0; i < length; i++)
		{
			final String number = this.hand.getCard(i).getNumber();
			worth += (int) Helper.POINTS.get(number);
		}
		return worth;
	}

	public boolean isEmpty() {
		return this.hand.isEmpty();
	}

	public void receiveCard(Card c) {
		this.hand.addCard(c);
		System.out.println(this.name + " received a " + c.toString());
	}

	public Card removeCard(int c) {
		return this.hand.removeCard(c);
	}

	public void resetHand(Deck deck) {
		this.hand = new Hand(deck);
	}

	@Override
	public String toString() {
		return this.hand.toString();
	}
}
