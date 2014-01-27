package au.edu.unimelb.cis.dragons.core;

import react.Value;

/**
 * Provides a static method to populate a gameState with dummy data.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class StubGameStatePopulator {

	/**
	 * Populate the game state with blank data.
	 * @param gameState The GameState to be populated.
	 */
	public static void populateGameState(GameState state) {
		state.addValueForKey(Value.create("0"), GameState.Key.currentGoldKey());
		state.addValueForKey(Value.create(""), GameState.Key.usernameKey());

		// Pen states
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				state.addValueForKey(Value.create(i == 1 || i == 2 ? "empty" : "locked"), GameState.Key.penStateKeyAtIndex(j, i));
			}
		}

		// Pen contents
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				state.addValueForKey(Value.create(""), GameState.Key.penDragonIdKeyAtIndex(j, i));
			}
		}

		// Dragon id generator
		state.addValueForKey(Value.create("0"), GameState.Key.nextDragonIdKey());

		state.markPopulated();
		return;
	}

}
