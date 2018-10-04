package deck.cards.impl;

import java.util.Objects;

import org.apache.commons.lang.Validate;

import deck.cards.Face;
import deck.cards.ICard;
import deck.cards.Suit;

/**
 * Default implementation for {@link ICard}.
 * 
 * @author sguay
 *
 */
public final class Card implements ICard {

	static final String ERROR_SUIT_SHOULD_NOT_BE_NULL = "Suit should not be null.";

	static final String ERROR_FACE_SHOULD_NOT_BE_NULL = "Suit should not be null.";

	private Suit mSuit;

	private Face mFace;

	/**
	 * Constructor for {@link Card}.
	 * 
	 * @param pSuit the suit for this card. Cannot be null.
	 * @param pFace the face for this card. Cannot be null.
	 */
	public Card(Suit pSuit, Face pFace) {
		Validate.notNull(pSuit, ERROR_SUIT_SHOULD_NOT_BE_NULL);
		Validate.notNull(pFace, ERROR_FACE_SHOULD_NOT_BE_NULL);
		mSuit = pSuit;
		mFace = pFace;
	}

	/**
	 * Compare 2 cards to see which one is the strongest.
	 */
	@Override
	public int compareTo(ICard pOther) {
		int result = mFace.compareTo(pOther.getFace());
		if (result == 0) {
			result = mSuit.compareTo(pOther.getSuit());
		}
		return result;
	}

	@Override
	public final boolean equals(Object pObj) {
		if (this == pObj) {
			return true;
		}

		if (pObj == null || getClass() != pObj.getClass()) {
			return false;
		}
		Card other = (Card) pObj;
		return Objects.equals(mFace, other.mFace) && Objects.equals(mSuit, other.mSuit);
	}

	@Override
	public Face getFace() {
		return mFace;
	}

	@Override
	public Suit getSuit() {
		return mSuit;
	}

	@Override
	public final int hashCode() {
		return Objects.hash(mFace, mSuit);
	}

}
