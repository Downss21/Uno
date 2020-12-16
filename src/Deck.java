import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Deck {
	private final int DECK_SIZE = 108;
	private Card[] deck = new Card[DECK_SIZE];
	private Random generator = new Random();
	private Stack<Card> drawPile = new Stack<Card>();
	private Stack<Card> discardPile = new Stack<Card>();
	
	public Deck() {
		int cardNumber = 0;
		//colors first
		for (int color = 0; color < 4; color++)
		{
			//then common cards
			for (int common = 0; common < 12; common++)
			{
				Card c = new Card(Helper.COMMON_CARDS[common], Helper.COLORS[color]);
				deck[cardNumber] = c;
				cardNumber++;
				deck[cardNumber] = c;
				cardNumber++;
			}
			//then add unique cards once
			for (int unique = 0; unique < 3; unique++)
			{
				Card c = new Card(Helper.UNIQUE_CARDS[unique], Helper.COLORS[color]);
				deck[cardNumber] = c;
				cardNumber++;
			}
		}
		//I'd never need an unshuffled deck so just auto shuffle at the end
		this.drawPile = shuffle(deck);
		this.discard(draw());
	}
	public Card draw() {
		Card c = drawPile.pop();
		if (drawPile.isEmpty()) refresh();
		return c;
	}
	public void discard(Card c) {
		discardPile.push(c);
	}
	public Card peek() {
		return discardPile.peek();
	}
	private Stack<Card> shuffle(Card[] deck) {
		int length = deck.length;
		for (int i = 0; i < length; i++) {
		int randomNumber = generator.nextInt(length);
		Card c = deck[i];
		deck[i] = deck[randomNumber];
		deck[randomNumber] = c;
		}
		Stack<Card> stack = new Stack<Card>();
		for (int i = 0; i < length; i++) {
			stack.add(deck[i]);
		}
		return stack;
	}
	private void refresh() {
		Card c = discardPile.pop();
		if (drawPile.isEmpty() && discardPile.isEmpty())
		{
			System.out.print("How did you manage to run out of cards. Do better next time");
			System.exit(0);
		}
		Card[] array = new Card[discardPile.size()];
		discardPile.copyInto(array);
		this.drawPile = shuffle(array);
		this.discardPile.clear();
		this.discardPile.push(c);
	}
	@Override
	public String toString() {
		return "Deck [drawPile=" + drawPile + "]";
	}
}
