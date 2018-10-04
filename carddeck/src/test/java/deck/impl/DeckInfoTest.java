package deck.impl;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import deck.IDeck;

/**
 * Unit test for class {@link DeckInfo}.
 * 
 * @author sguay
 *
 */
public class DeckInfoTest extends Assert {

	private static final long ONE_SECOND = 1000L;

	private IDeck mMockDeck = Mockito.mock(IDeck.class);

	/**
	 * Test the constructor with a null {@link IDeck}
	 */
	@Test
	public void testConstructorWithNull() {
		try {
			new DeckInfo(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(DeckInfo.ERROR_DECK_SHOULD_NOT_BE_NULL, e.getMessage());
		}
	}

	/**
	 * Test {@link DeckInfo#getCreationDate()} to make sure 2 {@link DeckInfo}
	 * created at different time have 2 different dates.
	 * 
	 * @throws InterruptedException if Thread gets interrupted
	 */
	@Test
	public void testGetCreationDate() throws InterruptedException {
		DeckInfo deckInfo1 = new DeckInfo(mMockDeck);
		Thread.sleep(ONE_SECOND);
		DeckInfo deckInfo2 = new DeckInfo(mMockDeck);
		assertTrue(deckInfo1.getCreationDate() > 0);
		assertTrue(deckInfo1.getCreationDate() < deckInfo2.getCreationDate());
	}

	/**
	 * Test {@link DeckInfo#getDeck()} to make sure it returns the same
	 * {@link IDeck} than what was given to the constructor.
	 */
	@Test
	public void testGetDeck() {
		DeckInfo deckInfo = new DeckInfo(mMockDeck);
		assertEquals(mMockDeck, deckInfo.getDeck());
	}

	/**
	 * Test {@link DeckInfo#getUUID()} to make sure 2 differents {@link DeckInfo}
	 * have different UUIDs.
	 */
	@Test
	public void testGetUUID() {
		DeckInfo deckInfo1 = new DeckInfo(mMockDeck);
		DeckInfo deckInfo2 = new DeckInfo(mMockDeck);
		assertFalse(StringUtils.isEmpty(deckInfo1.getUUID()));
		assertNotSame(deckInfo1.getUUID(), deckInfo2.getUUID());
	}

	/**
	 * Test {@link DeckInfo#isUnUsed(long)}.
	 * 
	 * @throws InterruptedException if Thread gets interrupted
	 */
	@Test
	public void testIsUnUsed() throws InterruptedException {
		DeckInfo deckInfo = new DeckInfo(mMockDeck);
		assertFalse(deckInfo.isUnUsed(ONE_SECOND));
		Thread.sleep(ONE_SECOND);
		assertTrue(deckInfo.isUnUsed(ONE_SECOND));
	}

	/**
	 * Test {@link DeckInfo#getLastUpdateDate()} to make sure it gets initialized
	 * with creation date and getting updated when the {@link IDeck} gets used.
	 * 
	 * @throws InterruptedException if Thread gets interrupted
	 */
	@Test
	public void testLastUpdateDate() throws InterruptedException {
		DeckInfo deckInfo = new DeckInfo(mMockDeck);
		assertTrue(deckInfo.getCreationDate() == deckInfo.getLastUpdateDate());
		Thread.sleep(ONE_SECOND);
		deckInfo.getDeck();
		assertTrue(deckInfo.getCreationDate() < deckInfo.getLastUpdateDate());
	}
}
