package au.edu.unimelb.cis.dragons.core.model;

import react.Function;
import react.Value;
import react.ValueView;
import au.edu.unimelb.cis.dragons.core.GameState;

/**
 * Represents a wallet within the game.
 * Wraps the GameState to provide mediated access to it from the controllers.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class Wallet {

	// The GameState that this wallet wraps.
	private GameState _gameState;
	
	/**
	 * Create a new Wallet, wrapping the GameState.
	 * @param gameState GameState to wrap.
	 */
	public Wallet(GameState gameState) {
		_gameState = gameState;
	}
	
	/**
	 * Get the current gold in the wallet.
	 * @return The current ammount of gold in the wallet.
	 */
	public ValueView<Integer> gold() {
		return _gameState.valueForKey(GameState.Key.currentGoldKey()).map(new Function<String, Integer>() {
			@Override
			public Integer apply(String input) {
				return Integer.parseInt(input);
			}
		});
	}
	
	/**
	 * Add an amount of gold to the wallet.
	 * @param amount The amount to add.
	 */
	public void addGold(Integer amount) {
		Value<String> goldValue = _gameState.valueForKey(GameState.Key.currentGoldKey());
		int currentGold = Integer.parseInt(goldValue.get());
		goldValue.update(new String("" + (currentGold + amount)));
	}
	
	/**
	 * Subtract an amount of gold from the wallet.
	 * @param amount The amount of gold to subtract.
	 */
	public void subtractGold(Integer amount) {
		addGold(amount * -1);
	}
	
}
