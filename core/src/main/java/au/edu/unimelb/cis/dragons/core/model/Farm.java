package au.edu.unimelb.cis.dragons.core.model;

import java.util.HashMap;
import java.util.Map;

import react.Function;
import react.ValueView;
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
		// back the correct PenState object after retrieving it's name from the
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
	public ValueView<PenState> stateForPen(Integer row, Integer column) {
		return _gameState.valueForKey(GameState.Key.penStateKeyAtIndex(row, column)).map(new Function<String, PenState>() {
			@Override
			public PenState apply(String input) {
				return PenState.penStateForStateName(input);
			}
		});
	}
	
	/**
	 * Set a given pen to be close, removing any dragon that was previous in it.
	 * @param row The row of the pen.
	 * @param column The column of the pen.
	 */
	public void lockPen(Integer row, Integer column) {
		_gameState.valueForKey(GameState.Key.penStateKeyAtIndex(row, column)).update(PenState.Locked.toString());
		_gameState.valueForKey(GameState.Key.penDragonIdKeyAtIndex(row, column)).update("");
	}
	
	/**
	 * Set a given pen to be open, removing any dragon that was previously in it.
	 * @param row The row of the pen.
	 * @param column The column of the pen.
	 */
	public void openAndClearPen(Integer row, Integer column) {
		_gameState.valueForKey(GameState.Key.penStateKeyAtIndex(row, column)).update(PenState.Empty.toString());
		_gameState.valueForKey(GameState.Key.penDragonIdKeyAtIndex(row, column)).update("");
	}
	
	/**
	 * Get the Dragon for the pen at a certain index.
	 * @param row The row index.
	 * @param column The column index.
	 * @return The Dragon.
	 */
	public ValueView<Dragon> dragonForPen(Integer row, Integer column) {
		return _gameState.valueForKey(GameState.Key.penDragonIdKeyAtIndex(row, column)).map(new Function<String, Dragon>() {
			@Override
			public Dragon apply(String input) {
				if (input.equals("")) { return null; }
				else { return new Dragon(_gameState, Integer.valueOf(input)); }
			}
		});
	}
	
	/**
	 * Set the Dragon that inhabits a certain pen, opening it if needed.
	 * @param dragon The dragon that should be in the pen.
	 * @param row The row of the pen.
	 * @param column The column of the pen.
	 */
	public void setDragonForPen(Dragon dragon, Integer row, Integer column) {
		_gameState.valueForKey(GameState.Key.penStateKeyAtIndex(row, column)).update(PenState.Full.toString());
		_gameState.valueForKey(GameState.Key.penDragonIdKeyAtIndex(row, column)).update(dragon.id().toString());
	}
}
