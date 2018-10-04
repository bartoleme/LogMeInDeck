package deck.impl;

import java.util.Random;

import deck.IDeck;
import deck.cards.Face;
import deck.cards.ICard;
import deck.cards.Suit;
import deck.cards.impl.Card;

/**
 * Default implementation of a {@link IDeck}.
 * 
 * @author sguay
 *
 */
public final class Deck implements IDeck {

	private static final int TOTAL_CARD_NUMBER = 52;

	private Card[] mCards = new Card[TOTAL_CARD_NUMBER];

	private int mCurrentDeckSize = TOTAL_CARD_NUMBER;

	/**
	 * Constructor for a {@link Deck}.
	 */
	public Deck() {
		int i = 0;
		for (Suit suit : Suit.values()) {
			for (Face face : Face.values()) {
				mCards[i] = new Card(suit, face);
				++i;
			}
		}
	}

	@Override
	public ICard dealOneCard() {
		Card nextCard = null;
		if (cardLeft()) {
			nextCard = mCards[0];
			resizeDeck();
		}
		return nextCard;
	}

	@Override
	public void shuffle() {
		if (mCards.length < 2) {
			return;
		}
		for (int i = 0; i < mCards.length; ++i) {
			permutate(i);
		}

	}

	private boolean cardLeft() {
		return mCurrentDeckSize > 0;
	}

	/**
	 * @return a new index within the range of the {@link Deck}.
	 */
	private int getNewIndex() {
		return new Random().nextInt(mCards.length);
	}

	/**
	 * Permutate {@link Card} at index pStartIndex with card from index given by
	 * {@link Deck#getNewIndex()}.
	 * 
	 * @param pIndex the index of the {@link Card} to permutate
	 */
	private void permutate(int pIndex) {
		int destIndex = getNewIndex();
		Card firstCard = mCards[pIndex];
		Card secondCard = mCards[destIndex];
		mCards[pIndex] = secondCard;
		mCards[destIndex] = firstCard;
	}

	/**
	 * Resize the {@link Deck} after card been given.
	 */
	private void resizeDeck() {
		int newSize = --mCurrentDeckSize;
		Card[] newCards = new Card[newSize];
		System.arraycopy(mCards, 1, newCards, 0, newSize);
		mCards = newCards;
	}

}
