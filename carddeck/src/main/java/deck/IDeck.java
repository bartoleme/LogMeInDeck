package deck;

import deck.cards.ICard;

/**
 * Represents a deck which is a stack of 52 {@link ICard}.
 * 
 * @author sguay
 *
 */
public interface IDeck {

	/**
	 * @return one card from the deck from the top.
	 */
	ICard dealOneCard();

	/**
	 * Suffle the deck so that the 52 cards receive a new order.
	 */
	void shuffle();
}
