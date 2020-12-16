import java.util.Random;
import java.util.Stack;

public class Deck {
	private final Card[] deck = new Card[this.DECK_SIZE];
	private final int DECK_SIZE = 108;
	private final Stack<Card> discardPile = new Stack<Card>();
	private Stack<Card> drawPile = new Stack<Card>();
	private final Random generator = new Random(6);

	public Deck() {
		int cardNumber = 0;
		// colors first
		for (int color = 0; color < 4; color++)
		{
			// then common cards
			for (int common = 0; common < 12; common++)
			{
				final Card c = new Card(Helper.COMMON_CARDS[common], Helper.COLORS[color]);
				this.deck[cardNumber] = c;
				cardNumber++;
				this.deck[cardNumber] = c;
				cardNumber++;
			}
			// then add unique cards once
			for (int unique = 0; unique < 3; unique++)
			{
				final Card c = new Card(Helper.UNIQUE_CARDS[unique], Helper.COLORS[color]);
				this.deck[cardNumber] = c;
				cardNumber++;
			}
		}
		// I'd never need an unshuffled deck so just auto shuffle at the end
		this.drawPile = this.shuffle(this.deck);
		this.discard(this.draw());
	}

	public void discard(Card c) {
		this.discardPile.push(c);
	}

	public Card draw() {
		final Card c = this.drawPile.pop();
		if (this.drawPile.isEmpty())
		{
			this.refresh();
		}
		return c;
	}

	public Card peek() {
		return this.discardPile.peek();
	}

	private void refresh() {
		final Card c = this.discardPile.pop();
		if (this.drawPile.isEmpty() && this.discardPile.isEmpty())
		{
			System.out.print("How did you manage to run out of cards. Do better next time");
			System.exit(0);
		}
		final Card[] array = new Card[this.discardPile.size()];
		this.discardPile.copyInto(array);
		this.drawPile = this.shuffle(array);
		this.discardPile.clear();
		this.discardPile.push(c);
	}

	private Stack<Card> shuffle(Card[] deck) {
		final int length = deck.length;
		for (int i = 0; i < length; i++)
		{
			final int randomNumber = this.generator.nextInt(length);
			final Card c = deck[i];
			deck[i] = deck[randomNumber];
			deck[randomNumber] = c;
		}
		final Stack<Card> stack = new Stack<Card>();
		for (int i = 0; i < length; i++)
		{
			stack.add(deck[i]);
		}
		return stack;
	}

	@Override
	public String toString() {
		return "Deck [drawPile=" + this.drawPile + "]";
	}
}
