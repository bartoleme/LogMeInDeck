package deck.service;

import deck.IDeck;
import deck.IDeckInfo;
import deck.cards.ICard;

/**
 * Represents a controller for {@link IDeck} that uses a map of
 * {@link IDeckInfo} to kepp track of the {@link IDeck} it manages. It provide a
 * facade for {@link IDeck} functionalities.
 * 
 * @author sguay
 *
 */
public interface IDeckController {

	/**
	 * Creates a new {@link IDeck}/{@link IDeckInfo} and returns the generated UUID.
	 * 
	 * @return the {@link IDeck} UUID
	 */
	String createDeck();

	/**
	 * Deals one {@link ICard} from the {@link IDeck} matching this UUID or null if
	 * either the {@link IDeck} has no more {@link ICard} or if UUID is invalid.
	 * 
	 * @param pDeckUUID the {@link IDeck} UUID
	 * @return one {@link ICard} or null
	 */
	ICard dealOneCard(String pDeckUUID);

	/**
	 * Shuffle the {@link IDeck} matching this UUID or nothing if UUID is invalid.
	 * 
	 * @param pDeckUUID the {@link IDeck} UUID
	 */
	void shuffleDeck(String pDeckUUID);

}
