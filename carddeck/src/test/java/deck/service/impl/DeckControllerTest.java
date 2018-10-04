package deck.service.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import deck.IDeck;
import deck.IDeckInfo;
import deck.cards.ICard;
import deck.impl.Deck;
import deck.impl.DeckInfo;

/**
 * Unit test for {@link DeckController}.
 * 
 * @author sguay
 *
 */
public class DeckControllerTest extends Assert {

	private static final String INVALID_UUID = "invalid";

	/**
	 * Test {@link DeckController#createDeck()}.
	 */
	@Test
	public void testCreateDeck() {
		DeckController controller = new DeckController();
		String uuid = controller.createDeck();
		assertFalse(StringUtils.isEmpty(uuid));
		assertTrue(controller.getDeckInfos().size() == 1);
	}

	/**
	 * Test {@link DeckController#dealOneCard(String)}.
	 */
	@Test
	public void testDealOneCard() {
		DeckController controller = new DeckController();
		assertTrue(controller.dealOneCard(null) == null);
		String uuid = controller.createDeck();
		assertTrue(controller.dealOneCard(INVALID_UUID) == null);
		assertFalse(controller.dealOneCard(uuid) == null);
	}

	/**
	 * Test {@link DeckController#getDeckInfos()}. Make sure the map returned is
	 * unmodifiable.
	 */
	@Test
	public void testGetDeckInfos() {
		DeckController controller = new DeckController();
		try {
			controller.getDeckInfos().put(INVALID_UUID, new DeckInfo(new Deck()));
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
	}

	/**
	 * Test {@link DeckController#shuffleDeck(String)}.
	 */
	@Test
	public void testShuffleDeck() {
		DeckController controller = new DeckController();
		controller.shuffleDeck(INVALID_UUID);
		String uuid1 = controller.createDeck();
		String uuid2 = controller.createDeck();
		controller.shuffleDeck(uuid1);
		controller.shuffleDeck(uuid2);
		Map<String, IDeckInfo> deckInfos = controller.getDeckInfos();
		IDeck deck1 = deckInfos.get(uuid1).getDeck();
		IDeck deck2 = deckInfos.get(uuid2).getDeck();
		ICard card1 = null;
		int equalCount = 0;
		do {
			card1 = deck1.dealOneCard();
			ICard card2 = deck2.dealOneCard();
			if (card1 != null && card1.equals(card2)) {
				++equalCount;
			}
		} while (card1 != null);
		assertTrue(equalCount < 52);
	}
}
