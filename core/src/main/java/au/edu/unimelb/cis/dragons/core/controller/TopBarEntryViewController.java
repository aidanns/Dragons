package au.edu.unimelb.cis.dragons.core.controller;

import playn.core.Font;
import playn.core.PlayN;
import au.edu.unimelb.cis.dragons.core.GameState;
import react.ValueView;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.FlowLayout;

/**
 * A ViewController that handles the presentation of information in the top
 * bar of the game. Shows a human readable description of the data and it's
 * value.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class TopBarEntryViewController extends ViewController {
	
	private static String FONT_NAME = "Times New Roman";
	private static int FONT_SIZE = 20;
	
	private GameState _gameState;
	private GameState.Key _valueKey;
	
	private Label _titleLabel;
	private Label _valueLabel;
	
	/**
	 * Create a new entry based on the value stored under the @param valueKey
	 * parameter in the game state.
	 * @param gameState The game state object to pull data from.
	 * @param valueKey The key of the value that will be displayed.
	 */
	public TopBarEntryViewController(GameState gameState, GameState.Key valueKey) {
		_gameState = gameState;
		_valueKey = valueKey;
	}

	@Override
	public String title() {
		return _gameState.shortDescriptionForKey(_valueKey);
	}

	@Override
	protected Group createInterface() {
		SizableGroup group = new SizableGroup(new FlowLayout());
		group.setConstraint(AxisLayout.fixed());
		_titleLabel = new Label(_gameState.shortDescriptionForKey(_valueKey) + ":");
		_titleLabel.addStyles(Style.FONT.is(PlayN.graphics().createFont(FONT_NAME, Font.Style.BOLD, FONT_SIZE)));
		group.add(_titleLabel);
		_valueLabel = new Label(_gameState.valueForKey(_valueKey).get().toString());
		_valueLabel.addStyles(Style.FONT.is(PlayN.graphics().createFont(FONT_NAME, Font.Style.PLAIN, FONT_SIZE)));
		_gameState.valueForKey(_valueKey).connect(new ValueView.Listener<Object>() {
			@Override
			public void onChange(Object value, Object oldValue) {
				_valueLabel.text.update(value.toString());
			}
		});
		group.add(_valueLabel);
		return group;
	}

}
