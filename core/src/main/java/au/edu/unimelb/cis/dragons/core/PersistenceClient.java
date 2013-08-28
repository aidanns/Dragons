package au.edu.unimelb.cis.dragons.core;

/**
 * Handles persistence of game state.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public interface PersistenceClient {

	public interface Callback<T> {
		public void onSuccess(T result);
		public void onFailure(Throwable caught);
	}
	
	/**
	 * Persist the game state.
	 * @param state The game state to persist.
	 */
	public void persist(GameState state);
	
	/**
	 * Populate the game state from the persisted store.
	 * @param state The state object to populate.
	 */
	public void populate(final GameState state);
	
	/**
	 * Achieve a badge on for this user.
	 * @param badge The badge to achieve.
	 */
	public void achieveBadge(Badge badge);
	
	/**
	 * Retrieve the username from the store.
	 * @param callBack Callback that is called when the request for the
	 * username is complete.
	 */
	public void getUserName(Callback<String> callback);
	
}
