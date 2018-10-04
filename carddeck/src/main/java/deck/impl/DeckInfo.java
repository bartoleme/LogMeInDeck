package deck.impl;

import java.util.UUID;

import org.apache.commons.lang.Validate;

import deck.IDeck;
import deck.IDeckInfo;

/**
 * Default implementation for {@link IDeckInfo}.
 * 
 * @author sguay
 *
 */
public final class DeckInfo implements IDeckInfo {

	static final String ERROR_DECK_SHOULD_NOT_BE_NULL = "Deck should not be null.";

	private long mCreationDate;

	private long mLastUpdateDate;

	private String mUUID;

	private IDeck mDeck;

	/**
	 * Constructor for {link DeckInfo}.
	 * 
	 * @param pDeck the {@link IDeckInfo} instance
	 */
	public DeckInfo(IDeck pDeck) {
		Validate.notNull(pDeck, ERROR_DECK_SHOULD_NOT_BE_NULL);
		mDeck = pDeck;
		mUUID = UUID.randomUUID().toString();
		mCreationDate = System.currentTimeMillis();
		mLastUpdateDate = mCreationDate;
	}

	@Override
	public long getCreationDate() {
		return mCreationDate;
	}

	@Override
	public IDeck getDeck() {
		mLastUpdateDate = System.currentTimeMillis();
		return mDeck;
	}

	@Override
	public long getLastUpdateDate() {
		return mLastUpdateDate;
	}

	@Override
	public String getUUID() {
		return mUUID;
	}

	@Override
	public boolean isUnUsed(long pTime) {
		return System.currentTimeMillis() - mLastUpdateDate >= pTime;
	}

}
