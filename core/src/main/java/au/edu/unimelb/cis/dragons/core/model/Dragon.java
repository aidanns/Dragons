package au.edu.unimelb.cis.dragons.core.model;

import java.util.HashMap;
import java.util.Map;

import react.Function;
import react.ValueView;
import au.edu.unimelb.cis.dragons.core.GameState;

/**
 * Represents an individual dragon within the game.
 * Wraps the GameState to provide mediated access to it from the controllers.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
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
	 * Create a new dragon with a given name in the GameState with a state of available.
	 * WARNING: This will clobber any previous dragon with the same id in the GameState.
	 * @param gameState The GameState to create the dragon in.
	 * @param id The id of the dragon.
	 * @param name The name of the dragon to create.
	 * @return
	 */
	public static Dragon create(GameState gameState, Integer id, String name) {
		gameState.valueForKey(GameState.Key.dragonNameForId(id)).update(name);
		Dragon dragon = new Dragon(gameState, id);
		dragon.makeAvailable();
		return dragon;
	}
	
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
	public ValueView<DragonState> state() {
		return _gameState.valueForKey(GameState.Key.dragonStateKeyForId(_id)).map(new Function<String, DragonState>() {
			@Override
			public DragonState apply(String input) {
				return DragonState.dragonStateForStateName(input);
			}
		});
	}
	
	/**
	 * Mark the dragon as available in the farm.
	 */
	public void makeAvailable() {
		_gameState.valueForKey(GameState.Key.dragonStateKeyForId(_id)).update(DragonState.Available.toString());
	}
	
	/**
	 * Mark the dragon as off racing.
	 */
	public void sendToRace() {
		_gameState.valueForKey(GameState.Key.dragonStateKeyForId(_id)).update(DragonState.Racing.toString());
	}
	
	/**
	 * Mark the dragon as off breeding.
	 */
	public void sendToBreed() {
		_gameState.valueForKey(GameState.Key.dragonStateKeyForId(_id)).update(DragonState.Breeding.toString());
	}
	
	/**
	 * Get the name of the dragon.
	 * @return The dragon's name.
	 */
	public ValueView<String> name() {
		return _gameState.valueForKey(GameState.Key.dragonNameForId(_id));
	}

}
