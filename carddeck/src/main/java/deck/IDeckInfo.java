package deck;

/**
 * Represents additional informations for a {@link IDeck} instance such as
 * creation date, last update date and unique ID.
 * 
 * @author sguay
 *
 */
public interface IDeckInfo {

	/**
	 * @return the creation date for the {@link IDeck} in millisecond
	 */
	long getCreationDate();

	/**
	 * Gets the {@link IDeck} instance and update the last update date to current.
	 * 
	 * @return the {@link IDeck} instance
	 */
	IDeck getDeck();

	/**
	 * @return the last date (in millisecond) where the {@link IDeck} was used
	 */
	long getLastUpdateDate();

	/**
	 * @return the UUID for the {IDeck} instance
	 */
	String getUUID();

	/**
	 * @param pTime time in millisecond
	 * @return true if {@link IDeckInfo} has been unused for at least the given time
	 *         in milliseconds.
	 */
	boolean isUnUsed(long pTime);
}
