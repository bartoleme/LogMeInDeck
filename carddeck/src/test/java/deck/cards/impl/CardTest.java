package deck.cards.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import deck.cards.Face;
import deck.cards.ICard;
import deck.cards.Suit;

/**
 * Unit test for class {@link Card}.
 * 
 * @author sguay
 *
 */
public class CardTest extends Assert {

	/**
	 * {@link Card#compareTo(ICard)} to see face strength order.
	 */
	@Test
	public void testCompareToForFace() {
		Iterator<Face> expectedOrderIt = getFaceExpectedOrder().iterator();
		Card currentCard = new Card(Suit.HEART, Face.TWO);
		for (Face face : Face.values()) {
			assertTrue(face == expectedOrderIt.next());
			Card nextCard = new Card(Suit.HEART, face);
			if (face == Face.TWO) {
				assertTrue(currentCard.compareTo(nextCard) == 0);
			} else {
				assertTrue(currentCard.compareTo(nextCard) < 0);
			}
			currentCard = nextCard;
		}
	}

	/**
	 * {@link Card#compareTo(ICard)} to see that suit follow this order : spade <
	 * club < diamond < heart.
	 */
	@Test
	public void testCompareToForSuit() {
		Iterator<Suit> expectedOrderIt = getSuitExpectedOrder().iterator();
		Card currentCard = new Card(Suit.SPADE, Face.TWO);
		for (Suit suit : Suit.values()) {
			assertTrue(suit == expectedOrderIt.next());
			Card nextCard = new Card(suit, Face.TWO);
			if (suit == Suit.SPADE) {
				assertTrue(currentCard.compareTo(nextCard) == 0);
			} else {
				assertTrue(currentCard.compareTo(nextCard) < 0);
			}
			currentCard = nextCard;
		}
	}

	/**
	 * Test the constructor with a null face
	 */
	@Test
	public void testConstructorWithNullFace() {
		try {
			new Card(null, Face.ACE);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Card.ERROR_SUIT_SHOULD_NOT_BE_NULL, e.getMessage());
		}
	}

	/**
	 * Test the constructor with a null suit
	 */
	@Test
	public void testConstructorWithNullSuit() {
		try {
			new Card(Suit.HEART, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Card.ERROR_FACE_SHOULD_NOT_BE_NULL, e.getMessage());
		}
	}

	/**
	 * Test {@link Card#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		Card card1 = new Card(Suit.DIAMOND, Face.KING);
		Card card2 = new Card(Suit.DIAMOND, Face.KING);
		assertTrue(card1.equals(card2));
		card2 = new Card(Suit.DIAMOND, Face.QUEEN);
		assertFalse(card1.equals(card2));
		card2 = new Card(Suit.SPADE, Face.KING);
		assertFalse(card1.equals(card2));
	}

	/**
	 * Test {@link Card#getFace()}.
	 */
	@Test
	public void testGetFace() {
		Card card = new Card(Suit.DIAMOND, Face.KING);
		assertEquals(Face.KING, card.getFace());
	}

	/**
	 * Test {@link Card#getSuit()}.
	 */
	@Test
	public void testGetSuit() {
		Card card = new Card(Suit.DIAMOND, Face.KING);
		assertEquals(Suit.DIAMOND, card.getSuit());
	}

	/**
	 * Test {@link Card#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		Card card1 = new Card(Suit.DIAMOND, Face.KING);
		Card card2 = new Card(Suit.DIAMOND, Face.KING);
		assertTrue(card1.hashCode() == card2.hashCode());
		card2 = new Card(Suit.DIAMOND, Face.QUEEN);
		assertTrue(card1.hashCode() != card2.hashCode());
		card2 = new Card(Suit.SPADE, Face.KING);
		assertTrue(card1.hashCode() != card2.hashCode());
	}

	/**
	 * @return all {@link Face} as they should be ordered in the enum.
	 */
	private List<Face> getFaceExpectedOrder() {
		List<Face> expectedOrder = new ArrayList<Face>(13);
		expectedOrder.add(Face.TWO);
		expectedOrder.add(Face.THREE);
		expectedOrder.add(Face.FOUR);
		expectedOrder.add(Face.FIVE);
		expectedOrder.add(Face.SIX);
		expectedOrder.add(Face.SEVEN);
		expectedOrder.add(Face.EIGHT);
		expectedOrder.add(Face.NINE);
		expectedOrder.add(Face.TEN);
		expectedOrder.add(Face.JACK);
		expectedOrder.add(Face.QUEEN);
		expectedOrder.add(Face.KING);
		expectedOrder.add(Face.ACE);
		return expectedOrder;
	}

	/**
	 * @return all {@link Suit} as they should be ordered in the enum.
	 */
	private List<Suit> getSuitExpectedOrder() {
		List<Suit> expectedOrder = new ArrayList<Suit>(4);
		expectedOrder.add(Suit.SPADE);
		expectedOrder.add(Suit.CLUB);
		expectedOrder.add(Suit.DIAMOND);
		expectedOrder.add(Suit.HEART);
		return expectedOrder;
	}
}
