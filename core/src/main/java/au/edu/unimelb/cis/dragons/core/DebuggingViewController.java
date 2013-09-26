package au.edu.unimelb.cis.dragons.core;

import react.UnitSlot;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.FlowLayout;
import au.edu.unimelb.cis.dragons.core.GameState.Key;
import au.edu.unimelb.cis.dragons.core.controller.ViewController;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Dragon.DragonState;
import au.edu.unimelb.cis.dragons.core.model.Farm;

/**
 * View for doing debugging manipulations to the game state.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class DebuggingViewController extends ViewController {

	private GameState _gameState;
	private Farm _farm;
	
	public DebuggingViewController(GameState gameState) {
		_gameState = gameState;
		_farm = new Farm(_gameState);
	}
	
	@Override
	public String title() {
		return "Debugging";
	}

	@Override
	protected Group createInterface() {
		Group group = new Group(new FlowLayout());
		group.setConstraint(AxisLayout.stretched());
		
		// Button to open pen at (0, 0)
		Button openPensButton = new Button("Open Pen");
		openPensButton.onClick(new UnitSlot() {
			@Override
			public void onEmit() {
				_farm.openAndClearPen(0, 0);
			}
		});
		group.add(openPensButton);
		
		// Button to set user's gold to 100
		Button goldButton = new Button("Give Gold");
		goldButton.onClick(new UnitSlot() {
			@Override
			public void onEmit() {
				_gameState.valueForKey(Key.CurrentGold).update(Integer.toString(100));
			}
		});
		group.add(goldButton);
		
		// Button to add a dragon to pen two and make it full.
		group.add(new Button("Add Dragon").onClick(new UnitSlot() {
			@Override
			public void onEmit() {
				Dragon flameyTheDragon = new Dragon(_gameState, 0);
				flameyTheDragon.name().update("Flamey");
				flameyTheDragon.state().update(DragonState.Available);
				_farm.setDragonForPen(flameyTheDragon, 0, 2);
			}
		}));
		
		return group;
	}
	
	

}
