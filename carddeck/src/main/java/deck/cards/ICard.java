package deck.cards;

/**
 * Represents a card object that goes within a deck.
 * 
 * @author sguay
 *
 */
public interface ICard extends Comparable<ICard> {

	/**
	 * @return this card {@link Face}.
	 */
	Face getFace();

	/**
	 * @return this card {@link Suit}.
	 */
	Suit getSuit();
}
