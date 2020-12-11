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
		return true;
	}
	public void addCard(Card c) {
		hand.add(c);
	}
	public Card removeCard(int i) {
		return hand.remove(i);
	}
	@Override
	public String toString() {
		String s = hand.get(0).toString()+"\n";
		int length = hand.size();
		for (int i = 1; i < length; i++) {
			Card c = hand.get(i);
			s += c.toString()+"\n";
		}
		return s;
	}
}
