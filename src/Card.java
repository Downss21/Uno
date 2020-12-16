public class Card {
	public final boolean ACTION;
	private String color;
	private final String number;

	public Card(String number, String color) {
		this.number = number;
		if (number == "Wild" || number == "+4")
		{
			this.color = "a";
		}
		else
		{
			this.color = color;
		}
		this.ACTION = Helper.ACTION_CARDS.contains(number);
	}

	public boolean canPlay(Card played) {
		final String playedColor = played.getColor();
		final String playedNumber = played.getNumber();
		return (playedColor.equals(this.color) || playedNumber.equals(this.number)
				|| Helper.WILD_CARDS.contains(this.number));
	}

	public String getColor() {
		return this.color;
	}

	public String getNumber() {
		return this.number;
	}

	public boolean isAction() {
		return this.ACTION;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return this.color + " " + this.number + "\u001b[0m";
	}

}
