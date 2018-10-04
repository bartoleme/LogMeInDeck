package deck.cards;

/**
 * Represents a suit for cards.
 * @author sguay
 *
 */
public enum Suit{

	SPADE("SPADE"), CLUB("CLUB"), DIAMOND("DIAMOND"), HEART("HEART");
	
	String mName;
	
	/**
	 * Default constructor.
	 * @param pName the suit name
	 */
	private Suit(String pName) {
		mName = pName;
	}
	
	/**
	 * @param pName the name
	 * @return the {@link Suit} matching the given name or null.
	 */
	public Suit getInstance(String pName) {
		for (Suit suit : Suit.values()) {
			if (suit.mName.equals(pName.toUpperCase())) {
				return suit;
			}
		}
		return null;
	}
}
