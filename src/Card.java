public class Card {
	private String number;
	private String color;
	public final boolean ACTION;
	
	public Card(String number, String color) {
		this.number = number;
		this.color = color;
		this.ACTION = Helper.ACTION_CARDS.contains(number);
	}
	public String getNumber() {
		return number;
	}
	public boolean isAction() {
		return ACTION;
	}
	@Override
	public String toString() {
		return "Card [number=" + number + ", color=" + color + "]";
	}
	
}
