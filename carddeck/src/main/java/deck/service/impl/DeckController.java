package deck.service.impl;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deck.IDeck;
import deck.IDeckInfo;
import deck.cards.ICard;
import deck.impl.Deck;
import deck.impl.DeckInfo;
import deck.service.IDeckController;

/**
 * Default implementation of a {@link IDeckController} that store generated
 * {@link IDeckInfo} into a map.
 * 
 * @author sguay
 *
 */
@RestController
public final class DeckController implements IDeckController {

	private static final long FIVE_MIN = 300000L;

	private Map<String, IDeckInfo> mDeckInfos = new ConcurrentHashMap<String, IDeckInfo>();

	@RequestMapping("/deck/create")
	@Override
	public String createDeck() {
		cleanMap();
		IDeckInfo deckInfo = new DeckInfo(new Deck());
		String uuid = deckInfo.getUUID();
		mDeckInfos.put(uuid, deckInfo);
		return uuid;
	}

	@RequestMapping("/deck/card")
	@Override
	public ICard dealOneCard(@RequestParam(value = "deckuuid") String pDeckUUID) {
		ICard card = null;
		IDeck deck = findDeck(pDeckUUID);
		if (deck != null) {
			card = deck.dealOneCard();
		}
		cleanMap();
		return card;
	}

	/**
	 * @return an unmodifiable map containing all {@link IDeckInfo}
	 */
	public Map<String, IDeckInfo> getDeckInfos() {
		return Collections.unmodifiableMap(mDeckInfos);
	}

	@RequestMapping("/deck/shuffle")
	@Override
	public void shuffleDeck(@RequestParam(value = "deckuuid") String pDeckUUID) {
		IDeck deck = findDeck(pDeckUUID);
		if (deck != null) {
			deck.shuffle();
		}
		cleanMap();
	}

	/**
	 * Cleans the {@link DeckController#mDeckInfos} map by removing unused decks.
	 */
	private void cleanMap() {
		for (String uuid : mDeckInfos.keySet()) {
			if (mDeckInfos.get(uuid).isUnUsed(FIVE_MIN)) {
				mDeckInfos.remove(uuid);
			}
		}
	}

	/**
	 * @param pDeckUUID the UUID
	 * @return the {@link IDeck} matching the UUID or null
	 */
	private IDeck findDeck(String pDeckUUID) {
		if (pDeckUUID == null) {
			return null;
		}
		try {
			IDeckInfo deckInfo = mDeckInfos.get(pDeckUUID);
			if (deckInfo != null) {
				return deckInfo.getDeck();
			}
		} catch (ConcurrentModificationException e) {
			return null;
		}
		return null;
	}
}
