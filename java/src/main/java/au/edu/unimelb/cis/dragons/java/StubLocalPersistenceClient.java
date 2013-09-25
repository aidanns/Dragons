package au.edu.unimelb.cis.dragons.java;

import java.util.ArrayList;
import java.util.List;

import react.Value;
import au.edu.unimelb.cis.dragons.core.Badge;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.PersistenceClient;

/**
 * A stub persistence client that mimicks the MUGLE API.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class StubLocalPersistenceClient implements PersistenceClient {

	// List of all badges that have been recorded as achieved.
	private List<String> _achievedBadges = new ArrayList<String>();
	
	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.PersistenceClient#persist(au.edu.unimelb.cis.dragons.core.GameState)
	 */
	@Override
	public void persist(GameState state) {
		return;
	}

	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.PersistenceClient#populate(au.edu.unimelb.cis.dragons.core.GameState)
	 */
	@Override
	public void populate(GameState state) {
		state.addValueForKey(Value.create("0"), GameState.Key.CurrentGold);
		state.addValueForKey(Value.create(""), GameState.Key.Username);
		
		// Pen states
		state.addValueForKey(Value.create("locked"), GameState.Key.penStateKeyAtIndex(0, 0));
		state.addValueForKey(Value.create("empty"), GameState.Key.penStateKeyAtIndex(0, 1));
		state.addValueForKey(Value.create("empty"), GameState.Key.penStateKeyAtIndex(0, 2));
		state.addValueForKey(Value.create("locked"), GameState.Key.penStateKeyAtIndex(0, 3));
		state.addValueForKey(Value.create("locked"), GameState.Key.penStateKeyAtIndex(1, 0));
		state.addValueForKey(Value.create("empty"), GameState.Key.penStateKeyAtIndex(1, 1));
		state.addValueForKey(Value.create("empty"), GameState.Key.penStateKeyAtIndex(1, 2));
		state.addValueForKey(Value.create("locked"), GameState.Key.penStateKeyAtIndex(1, 3));
		
		// Dragons
		for (int i = 0; i < 8; i++) {
			state.addValueForKey(Value.create("null"), GameState.Key.dragonStateKeyForId(i));
		}
		
		state.markPopulated();
		return;
	}

	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.PersistenceClient#achieveBadge(au.edu.unimelb.cis.dragons.core.Badge)
	 */
	@Override
	public void achieveBadge(Badge badge) {
		_achievedBadges.add(badge.name());
		badge.setAchieved();
		return;
	}

	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.PersistenceClient#getUserName(au.edu.unimelb.cis.dragons.core.PersistenceClient.Callback)
	 */
	@Override
	public void getUserName(Callback<String> callback) {
		callback.onSuccess("Local Test User");
		return;
	}

}
