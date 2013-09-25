package au.edu.unimelb.cis.dragons.core;

import react.UnitSlot;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.FlowLayout;
import au.edu.unimelb.cis.dragons.core.GameState.Key;
import au.edu.unimelb.cis.dragons.core.controller.ViewController;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Farm.PenState;

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
				_farm.stateForPen(0, 0).update(PenState.Empty);
			}
		});
		group.add(openPensButton);
		
		// Button to populate pen at (0, 1)
		Button populatePenButton = new Button("Populate Pen");
		populatePenButton.onClick(new UnitSlot() {
			@Override
			public void onEmit() {
				_farm.stateForPen(0, 1).update(PenState.Full);
			}
		});
		group.add(populatePenButton);
		
		// Button to set useres gold to 100
		Button goldButton = new Button("Give Gold");
		goldButton.onClick(new UnitSlot() {
			@Override
			public void onEmit() {
				_gameState.valueForKey(Key.CurrentGold).update(Integer.toString(100));
			}
		});
		group.add(goldButton);
		
		return group;
	}
	
	

}
