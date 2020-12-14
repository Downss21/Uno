import java.util.ArrayList;

public class Hand {
	ArrayList<Card> hand = new ArrayList<Card>();
	
	public Hand(Deck deck)
	{
		for (int i = 0; i < 7; i++) {
			Card c = deck.draw();
			addCard(c);
		}
	}
	public boolean canPlay(Deck deck) {
		int length = hand.size();
		for (int i = 0; i < length; i++) {
			if (hand.get(i).canPlay(deck)) return true;
		}
		return false;
	}
	public void addCard(Card c) {
		hand.add(c);
	}
	public Card removeCard(int i) {
		return hand.remove(i);
	}
	public String[] toStringArray() {
		int length = hand.size();
		String[] string = new String[length];
		for (int i = 0; i < length; i++) {
			Card c = hand.get(i);
			string[i] = c.toString();
		}
		return string;
	}
	
	@Override
	public String toString() {
		String s = hand.get(0).toString();
		int length = hand.size();
		for (int i = 1; i < length; i++) {
			s += ", "+hand.get(i);
		}
		return s;
	}
	public Card getCard(int c) {
		return hand.get(c);
	}
}
