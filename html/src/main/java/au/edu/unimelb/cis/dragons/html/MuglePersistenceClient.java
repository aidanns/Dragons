package au.edu.unimelb.cis.dragons.html;

import playn.core.PlayN;
import react.Value;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import au.edu.unimelb.cis.dragons.core.Badge;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.PersistenceClient;
import au.edu.unimelb.csse.mugle.client.api.Services;

public class MuglePersistenceClient implements PersistenceClient {

	// Unique game token assigned by MUGLE.
	private static final String GAME_TOKEN = "";
	
	// User facing error string for if we lose contact with the server.
	private static final String CONNECTION_ERROR_USER_MESSAGE =
		"Lost contact with the game server. Please reload the game webpage.";
	
	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.PersistenceClient#persist(au.edu.unimelb.cis.dragons.core.GameState)
	 */
	@Override
	public void persist(GameState state) {
		
	}

	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.PersistenceClient#populate(au.edu.unimelb.cis.dragons.core.GameState)
	 */
	@Override
	public void populate(GameState state) {
		
		// Stub the population for now, until we get the data store up and running.
		
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
			state.addValueForKey(Value.create(""), GameState.Key.dragonNameForId(i));
		}
		
		state.markPopulated();
		return;
	}

	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.PersistenceClient#achieveBadge(au.edu.unimelb.cis.dragons.core.Badge)
	 */
	@Override
	public void achieveBadge(final Badge badge) {
		Services.badges.setAchieved(GAME_TOKEN, badge.name(),
            new AsyncCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    badge.setAchieved();
                }
                @Override
                public void onFailure(Throwable caught) {
                	PlayN.log().error("Error while setting badge "
                            + badge.displayName()
                            + " as achieved on the server:"
                            + caught.getMessage());
                	// TODO aidanns: Feed this back to the game to show a proper error message.
                	Window.alert(CONNECTION_ERROR_USER_MESSAGE);
                }
            });
	}

	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.PersistenceClient#getUserName(au.edu.unimelb.cis.dragons.core.PersistenceClient.Callback)
	 */
	@Override
	public void getUserName(final Callback<String> callback) {
		Services.user.getUserNickName(new AsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }
            @Override
            public void onFailure(Throwable caught) {
                callback.onFailure(caught);
            }
        });
	}

}
