package au.edu.unimelb.cis.dragons.core.model;

import java.util.HashMap;
import java.util.Map;

import react.Value;
import react.ValueView.Listener;
import au.edu.unimelb.cis.dragons.core.GameState;

/**
 * Representation of the farm. Wraps the GameState to give simplified access to
 * state related to the farm.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class Farm {
	
	/**
	 * Represents the different possible states for a pen within the farm.
	 */
	public enum PenState {

		/** The pen has not yet been unlocked. */
		Locked("locked"),
		
		/** The pen is unlocked, but no dragon is in it. */
		Empty("empty"),
		
		/** The pen is unlocked and has a dragon in it. */
		Full("full");
		
		// Map from the state name back to the state, to make it easy to pass
		// back the correct PenState object after retrievnig it's name from the
		// GameState.
		private static Map<String, PenState> stateNameToPenState = new HashMap<String, PenState>();
		
		// Create the Map from the state names back to the state objects.
		static {
			for (PenState penState : PenState.values()) {
				stateNameToPenState.put(penState._stateName, penState);
			}
		}
		
		/** 
		 * The name for this state, which will be stored as a value in the
		 * GameState.
		 */
		private String _stateName;
		
		PenState(String stateName) {
			_stateName = stateName;
		}
		
		public String toString() {
			return _stateName;
		}
		
		/**
		 * Get the PenState for a given state name, which is returned from the
		 * GameState.
		 * @param stateName The name of the state, retrieved from the GameState.
		 * @return The PenState enumeration object that represents this state.
		 */
		/* package */ static PenState penStateForStateName(String stateName) {
			return stateNameToPenState.get(stateName);
		}
	}
	
	// The underlying game state.
	private GameState _gameState;
	
	/**
	 * Create a new FarmModel, wrapping the supplied GameState.
	 * @param gameState The GameState that this FarmModel will give access to.
	 */
	public Farm(GameState gameState) {
		_gameState = gameState;
	}
	
	/**
	 * Get the PenState for the pen at a certain index.
	 * @param row The row index.
	 * @param column The column index.
	 * @return The PenState.
	 */
	public Value<PenState> stateForPen(Integer row, Integer column) {
		final Value<String> stringValue = _gameState.valueForKey(GameState.Key.penStateKeyAtIndex(row, column));
		final Value<PenState> penStateValue = Value.create(PenState.penStateForStateName(stringValue.get()));
		
		stringValue.connect(new Listener<String>() {
			@Override
			public void onChange(String value, String oldValue) {
				penStateValue.update(PenState.penStateForStateName(value));
			}
		});
		
		penStateValue.connect(new Listener<PenState>() {
			@Override
			public void onChange(PenState value, PenState oldValue) {
				stringValue.update(value._stateName);
			}
		});
		
		return penStateValue;
	}
}
