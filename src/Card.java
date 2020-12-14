public class Card {
	private String number;
	private String color;
	public final boolean ACTION;
	
	public Card(String number, String color) {
		this.number = number;
		if (number == "Wild" || number == "+4") this.color = "a";
		else this.color = color;
		this.ACTION = Helper.ACTION_CARDS.contains(number);
	}
	public boolean canPlay(Deck deck) {
		Card played = deck.peek();
		String playedColor = played.getColor();
		String playedNumber = played.getNumber();
		return (playedColor.equals(color) || playedNumber.equals(number) || Helper.WILD_CARDS.contains(number));
	}
		public String getColor() {
		return color;
	}
	public String getNumber() {
		return number;
	}
	public boolean isAction() {
		return ACTION;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return color+" "+number+"\u001b[0m";
	}
	
}
