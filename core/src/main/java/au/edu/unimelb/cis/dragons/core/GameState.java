package au.edu.unimelb.cis.dragons.core;

import java.util.HashMap;
import java.util.Map;

import react.Value;

/**
 * Container class for all game state that can be passed for persistence.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class GameState {
	
	/**
	 * A Key that can be used by clients of the GameState to refer to specific
	 * values that it stores.
	 */
	public enum Key {
		
		Username("username"),
		CurrentGold("current_gold"),
		
		// The current state of the pens.
		PenZeroZeroState("pen_0_0_state"),
		PenZeroOneState("pen_0_1_state"),
		PenZeroTwoState("pen_0_2_state"),
		PenZeroThreeState("pen_0_3_state"),
		PenOneZeroState("pen_1_0_state"),
		PenOneOneState("pen_1_1_state"),
		PenOneTwoState("pen_1_2_state"),
		PenOneThreeState("pen_1_3_state"),
		
		// The current dragon in each pen.
		PenZeroZeroDragon("pen_0_0_dragon"),
		PenZeroOneDragon("pen_0_1_dragon"),
		PenZeroTwoDragon("pen_0_2_dragon"),
		PenZeroThreeDragon("pen_0_3_dragon"),
		PenOneZeroDragon("pen_1_0_dragon"),
		PenOneOneDragon("pen_1_1_dragon"),
		PenOneTwoDragon("pen_1_2_dragon"),
		PenOneThreeDragon("pen_1_3_dragon"),
		
		// The current state of the dragons.
		DragonZeroState("dragon_0_state"),
		DragonOneState("dragon_1_state"),
		DragonTwoState("dragon_2_state"),
		DragonThreeState("dragon_3_state"),
		DragonFourState("dragon_4_state"),
		DragonFiveState("dragon_5_state"),
		DragonSixState("dragon_6_state"),
		DragonSevenState("dragon_7_state"),

		// The current score of the dragons.
		DragonZeroScore("dragon_0_score"),
		DragonOneScore("dragon_1_score"),
		DragonTwoScore("dragon_2_score"),
		DragonThreeScore("dragon_3_score"),
		DragonFourScore("dragon_4_score"),
		DragonFiveScore("dragon_5_score"),
		DragonSixScore("dragon_6_score"),
		DragonSevenScore("dragon_7_score");
		
		private String _dataStoreKey;
		private String _shortDescription;
		private String _longDescription;
		
		private static Map<String, String> dataStoreKeyToShortDescription = new HashMap<String, String>();
		private static Map<String, String> dataStoreKeyToLongDescription = new HashMap<String, String>();
		
		// Set the descriptions for every Key.
		static {
			dataStoreKeyToShortDescription.put("username", "Username");
			dataStoreKeyToLongDescription.put("username", "The Google username for the currently logged in player.");		
			
			dataStoreKeyToShortDescription.put("current_gold", "Current Gold");
			dataStoreKeyToLongDescription.put("current_gold", "The ammount of gold that the current player has.");
		
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 2; j++) {
					dataStoreKeyToShortDescription.put("pen_" + i + "_" + j + "_state", "Pen (" + i + "," + j +") State");
					dataStoreKeyToLongDescription.put("pen_" + i + "_" + j + "_state", "The current state of pen (" + i + "," + j +")");
					
					dataStoreKeyToShortDescription.put("pen_" + i + "_" + j + "_dragon", "Pen (" + i + "," + j +") Dragon");
					dataStoreKeyToLongDescription.put("pen_" + i + "_" + j + "_dragon", "The current dragon in pen (" + i + "," + j +")");
				}
			}
			
			for (int i = 0; i < 8; i++) {
				dataStoreKeyToShortDescription.put("dragon_" + i + "_state", "Dragon "+ i + "'s state");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_state", "The current state of dragon " + i);
				dataStoreKeyToShortDescription.put("dragon_" + i + "_score", "Dragon "+ i + "'s score");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_score", "The current score of dragon " + i);
			}
			
			for (Key key : Key.values()) {
				key._shortDescription = dataStoreKeyToShortDescription.get(key._dataStoreKey);
				key._longDescription = dataStoreKeyToLongDescription.get(key._longDescription);
			}
		}
		
		// Set up a map from the dataStore key back to the key enumeration object.
		private static Map<String, Key> dataStoreKeyToKey = new HashMap<String, Key>();
		
		static {
			for (Key key : Key.values()) {
				dataStoreKeyToKey.put(key._dataStoreKey, key);
			}
		}
		
		Key(String dataStoreKey) {
			_dataStoreKey = dataStoreKey;
		}
		
		/* package */ String shortDescription() {
			return _shortDescription;
		}
		
		/* package */ String longDescription() {
			return _longDescription;
		}
		
		/**
		 * Get the Key for the penState at a given index.
		 * @param row The row index.
		 * @param column The column index.
		 * @return The key that can be used to access the pen state for that pen.
		 */
		public static Key penStateKeyAtIndex(Integer row, Integer column) {
			return dataStoreKeyToKey.get("pen_" + row + "_" + column + "_state");
		}

		/**
		 * Get the Key for the state for a dragon with a certain id.
		 * @param id The id of the dragon.
		 * @return The state for that dragon.
		 */
		public static Key dragonStateKeyForId(Integer id) {
			return dataStoreKeyToKey.get("dragon_" + id + "_state");
		}

		/**
		 * Get the Key for the name for a dragon with a certain id.
		 * @param id The id of the dragon.
		 * @return The state for that dragon.
		 */
		public static Key dragonNameForId(Integer id) {
			return dataStoreKeyToKey.get("dragon_" + id + "_name");
		}

		public static Key penDragonIdKeyAtIndex(Integer row, Integer column) {
			return dataStoreKeyToKey.get("pen_" + row + "_" + column + "_dragon");
		}

		public static Key dragonScoreForId(Integer id) {
			return dataStoreKeyToKey.get("dragon_" + id + "_score");
		}
	}
	
	/** Whether the game state has been populated. */
	private boolean _hasBeenPopulated = false;
	
	/** All the variables that make up the game state. */
	private HashMap<Key, Value<String>> _values = new HashMap<Key, Value<String>>();
	
	public Value<String> valueForKey(Key key) {
		return _values.get(key);
	}
	
	/**
	 * Get the short description for this key.
	 * @param key The key to retrieve info for.
	 * @return The short description, suitable for displaying in the GUI.
	 */
	public String shortDescriptionForKey(Key key) {
		return key.shortDescription();
	}
	
	/**
	 * Get the long description for this key.
	 * @param key The key to retrieve info for.
	 * @return The long description, suitable for understand the key.
	 */
	public String longDescriptionForKey(Key key) {
		return key.longDescription();
	}
	
	/**
	 * Add a value to the game state for this key. Should only be called from 
	 * the persistence manager.
	 * @param value The value to store.
	 * @param key The key to store it at.
	 */
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
