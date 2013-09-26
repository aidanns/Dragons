package au.edu.unimelb.cis.dragons.core.model;

import java.util.HashMap;
import java.util.Map;

import react.Value;
import react.ValueView.Listener;
import au.edu.unimelb.cis.dragons.core.GameState;

public class Dragon {
	
	/**
	 * Represents the different possible states for a single dragon.
	 */
	public enum DragonState {
		/** The dragon is in the stable and available for use by the player. */
		Available("available"),
		
		/** The dragon has been submitted for a race and will return once the race has been completed. */
		Racing("racing"),
		
		/** The dragon has been sent to conduct training and will return once it has finished. */
		Training("training"),
		
		/** The dragon has been sent to breed and will return once it is finished. */
		Breeding("breeding");
		
		// Map from the state name back to the state, to make it easy to get
		// the state, given the string that is persisted to represent it.
		private static Map<String, DragonState> stateNameToStateMap = new HashMap<String, DragonState>();
		
		static {
			for (DragonState state : DragonState.values()) {
				stateNameToStateMap.put(state._stateName, state);
			}
			stateNameToStateMap.put("", null);
		}
		
		private String _stateName;
		
		DragonState(String stateName) {
			_stateName = stateName;
		}
		
		@Override
		public String toString() {
			return _stateName;
		}
		
		/**
		 * Get the DragonState for a given state name, which is returned from 
		 * the GameState.
		 * @param stateName The name of the state to retrieve.
		 * @return The state enum object representing this state.
		 */
		/* package */ static DragonState dragonStateForStateName(String stateName) {
			return stateNameToStateMap.get(stateName);
		}
	}
	
	/** The GameState that backs this dragon. */
	private GameState _gameState;
	
	/** The identifier for this dragon. */
	private Integer _id;
	
	/**
	 * Create a new dragon, backed by a particular GameState.
	 * @param gameState The game state to store this dragon in.
	 * @param id The id to store this dragon at.
	 */
	public Dragon(GameState gameState, Integer id) {
		_gameState = gameState;
		_id = id;
	}
	
	/**
	 * Get the Id for this dragon.
	 * @return The dragons Id.
	 */
	public Integer id() {
		return _id;
	}
	
	/**
	 * Get the state of the dragon.
	 * @return The dragon's state.
	 */
	public Value<DragonState> state() {
		final Value<String> stringValue = _gameState.valueForKey(GameState.Key.dragonStateKeyForId(_id));
		final Value<DragonState> dragonStateValue = Value.create(DragonState.dragonStateForStateName(stringValue.get()));
		
		stringValue.connect(new Listener<String>() {
			@Override
			public void onChange(String value, String oldValue) {
				dragonStateValue.update(DragonState.dragonStateForStateName(value));
			}
		});
		
		dragonStateValue.connect(new Listener<DragonState>() {
			@Override
			public void onChange(DragonState value, DragonState oldValue) {
				stringValue.update(value.toString());
			}
		});
		
		return dragonStateValue;
	}
	
	/**
	 * Get the name of the dragon.
	 * @return The dragon's name.
	 */
	public Value<String> name() {
		return _gameState.valueForKey(GameState.Key.dragonNameForId(_id));
	}

}
