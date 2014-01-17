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
		state.addValueForKey(Value.create("0"), GameState.Key.CurrentGold);
		state.addValueForKey(Value.create(""), GameState.Key.Username);

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

		// Dragons
		for (int i = 0; i < 8; i++) {
			state.addValueForKey(Value.create(""), GameState.Key.dragonStateKeyForId(i));
			state.addValueForKey(Value.create(""), GameState.Key.dragonNameKeyForId(i));
			state.addValueForKey(Value.create(""), GameState.Key.dragonScoreKeyForId(i));

			state.addValueForKey(Value.create(""), GameState.Key.dragonLegLengthAlleleOneKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonLegLengthAlleleTwoKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonFeetTypeAlleleOneKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonFeetTypeAlleleTwoKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonCoatTypeAlleleOneKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonCoatTypeAlleleTwoKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonWingSizeAlleleOneKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonWingSizeAlleleTwoKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonTailLengthAlleleOneKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonTailLengthAlleleTwoKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonPhysiqueAlleleOneKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonPhysiqueAlleleTwoKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonLungSizeAlleleOneKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonLungSizeAlleleTwoKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonHeartSizeAlleleOneKeyForId(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonHeartSizeAlleleTwoKeyForId(i));

        	state.addValueForKey(Value.create(""), GameState.Key.dragonSpeedAttributeTrainingRemainingRacesKey(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonEnduranceAttributeTrainingRemainingRacesKey(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonBalanceAttributeTrainingRemainingRacesKey(i));
        	state.addValueForKey(Value.create(""), GameState.Key.dragonWeightAttributeTrainingRemainingRacesKey(i));
		}

		state.markPopulated();
		return;
	}

}
