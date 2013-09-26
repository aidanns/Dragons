package au.edu.unimelb.cis.dragons.core.model;

import react.ValueView;
import au.edu.unimelb.cis.dragons.core.GameState;

/**
 * Represents an individual user within the game.
 * Wraps the GameState to provide mediated access to it from the controllers.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class User {
	
	// The game state that this User is wrapping.
	private GameState _gameState;
	
	/**
	 * Create a new User, wrapping a given GameState.
	 * @param gameState The GameState to wrap.
	 */
	public User(GameState gameState) {
		_gameState = gameState;
	}
	
	/**
	 * Get the name of this user.
	 * @return The name of this user.
	 */
	public ValueView<String> name() {
		return _gameState.valueForKey(GameState.Key.Username);
	}

}
