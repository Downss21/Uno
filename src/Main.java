
public class Main {

	public static void main(String[] args) {
		Deck deck = new Deck();
		System.out.println(deck.toString());
		Hand hand = new Hand(deck);
		System.out.print(hand.toString());
	}

}
