package deck.impl;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import deck.cards.ICard;

/**
 * Unit test for class {@link Deck}.
 * 
 * @author sguay
 *
 */
public class DeckTest extends Assert {

	/**
	 * Test that {@link Deck#Deck()} always creates stack of cards with the same
	 * order .
	 */
	@Test
	public void testConstructor() {
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		ICard card1 = null;
		do {
			card1 = deck1.dealOneCard();
			ICard card2 = deck2.dealOneCard();
			if (card1 != null) {
				assertEquals(card1, card2);
			}
		} while (card1 != null);
	}

	/**
	 * Test method {@link Deck#dealOneCard()} to make sure it deal maximum 52 cards.
	 */
	@Test
	public void testDealOneCard52TimesMax() {
		Deck deck = new Deck();
		ICard card = null;
		int count = 0;
		do {
			card = deck.dealOneCard();
			if (card != null) {
				++count;
			}
		} while (card != null);
		assertTrue(count == 52);
	}

	/**
	 * Test method {@link Deck#dealOneCard()} to make sure it deal unique cards.
	 */
	@Test
	public void testDealOneCardUnique() {
		Deck deck = new Deck();
		ICard card = null;
		Set<ICard> dealtCards = new HashSet<ICard>(52);
		do {
			card = deck.dealOneCard();
			if (card != null) {
				assertTrue(dealtCards.add(card));
			}
		} while (card != null);
	}

	/**
	 * Test that {@link Deck#shuffle()} changes the order of the cards in a random
	 * way.
	 */
	@Test
	public void testSuffle() {
		Deck deck1 = new Deck();
		deck1.shuffle();
		Deck deck2 = new Deck();
		deck2.shuffle();
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
