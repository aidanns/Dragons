package au.edu.unimelb.cis.dragons.core;

import java.util.HashMap;

import react.Value;

/**
 * Container class for all game state that can be passed for persistence.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class GameState {
	
	public enum Key {
		
		Username("Username", "The Google username for the currently logged in player."),
		CurrentGold("Current Gold", "The ammount of gold that the current player has.");
		
		private String _shortDescription;
		private String _longDescription;
		
		Key(String shortDescription, String longDescription) {
			_shortDescription = shortDescription;
			_longDescription = longDescription;
		}
		
		/* package */ String shortDescription() {
			return _shortDescription;
		}
		
		/* package */ String longDescription() {
			return _longDescription;
		}
	}
	
	private HashMap<Key, Value<?>> _values = new HashMap<Key, Value<?>>();
	
	public Value<?> valueForKey(Key key) {
		return _values.get(key);
	}
	
	public String shortDescriptionForKey(Key key) {
		return key.shortDescription();
	}
	
	public String longDescriptionForKey(Key key) {
		return key.longDescription();
	}
	
	public void addValueForKey(Value<?> value, Key key) {
		_values.put(key, value);
	}
	
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
