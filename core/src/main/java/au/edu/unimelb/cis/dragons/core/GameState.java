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
	
	/** Whether the game state has been populated. */
	private boolean _hasBeenPopulated = false;
	
	/** All the variables that make up the game state. */
	private HashMap<Key, Value<String>> _values = new HashMap<Key, Value<String>>();
	
	public Value<String> valueForKey(Key key) {
		return _values.get(key);
	}
	
	public String shortDescriptionForKey(Key key) {
		return key.shortDescription();
	}
	
	public String longDescriptionForKey(Key key) {
		return key.longDescription();
	}
	
	public void addValueForKey(Value<String> value, Key key) {
		_values.put(key, value);
	}
	
	/** Marks the game state as populated and ready for use. */
	public void markPopulated() {
		_hasBeenPopulated = true;
	}
	
	/** 
	 * Allows clients to check if the game state has been populated and is 
	 * ready to receive calls to get values.
	 * @return Whether or not the state has been populated.
	 */
	public boolean hasBeenPopulated() {
		return _hasBeenPopulated;
	}
}
