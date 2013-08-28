package au.edu.unimelb.cis.dragons.core;

/**
 * Container class for all game state that can be passed for persistence.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class GameState {
	
	// Username for the current user. Not persisted.
	private String _currentUserName;
	
	// Whether the current user name has been set.
	private boolean _currentUserNameHasBeenSet = false;
	
	/**
	 * @return The name of the current user.
	 */
	public String currentUserName() {
		return _currentUserName;
	}
	
	/**
	 * Set the current user name.
	 * @param userName The current user name.
	 */
	public void setCurrentUserName(final String userName) {
		_currentUserName = userName;
		_currentUserNameHasBeenSet = true;
	}
	
	/**
	 * @return Whether the current user name has been set.
	 */
	public boolean currentUserNameHasBeenSet() {
		return _currentUserNameHasBeenSet;
	}

}
