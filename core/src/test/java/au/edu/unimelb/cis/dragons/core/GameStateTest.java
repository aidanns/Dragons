package au.edu.unimelb.cis.dragons.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.edu.unimelb.cis.dragons.core.GameState.Key;
import react.Value;

public class GameStateTest {

	private GameState _gameState;
	
	@Before
	public void setUp() throws Exception {
		_gameState = new GameState();
	}

	@After
	public void tearDown() throws Exception {
		_gameState = null;
	}

	@Test
	public void storageWorks() {
		Value<String> myValue = Value.create("value");
		Key key = GameState.Key.currentGoldKey();
		
		_gameState.addValueForKey(myValue, key);
		
		assertEquals(myValue, _gameState.valueForKey(key));
	}

}
