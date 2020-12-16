import java.util.ArrayList;

public class Hand {
	ArrayList<Card> hand = new ArrayList<Card>();

	public Hand(Deck deck) {
		for (int i = 0; i < 7; i++)
		{
			final Card c = deck.draw();
			this.addCard(c);
		}
	}

	public void addCard(Card c) {
		this.hand.add(c);
	}

	public boolean canPlay(Card c) {
		final int length = this.hand.size();
		for (int i = 0; i < length; i++)
		{
			if (this.hand.get(i).canPlay(c)) return true;
		}
		return false;
	}

	public Card getCard(int c) {
		return this.hand.get(c);
	}

	public int getLength() {
		return this.hand.size();
	}

	public boolean[] getValidPositions(Card c) {
		final int size = this.hand.size();
		final boolean[] validPos = new boolean[size];
		for (int i = 0; i < size; i++)
		{
			validPos[i] = this.canPlay(c);
		}
		return validPos;
	}

	public boolean isEmpty() {
		return this.hand.isEmpty();
	}

	public Card removeCard(int i) {
		return this.hand.remove(i);
	}

	@Override
	public String toString() {
		String s = this.hand.get(0).toString();
		final int length = this.hand.size();
		for (int i = 1; i < length; i++)
		{
			s += ", " + this.hand.get(i);
		}
		return s;
	}

	public String[] toStringArray() {
		final int length = this.hand.size();
		final String[] string = new String[length];
		for (int i = 0; i < length; i++)
		{
			final Card c = this.hand.get(i);
			string[i] = c.toString();
		}
		return string;
	}
}
