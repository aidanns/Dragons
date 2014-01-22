package au.edu.unimelb.cis.dragons.core;

import react.UnitSlot;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.FlowLayout;
import au.edu.unimelb.cis.dragons.core.controller.ViewController;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Wallet;

/**
 * View for doing debugging manipulations to the game state.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class DebuggingViewController extends ViewController {

	private GameState _gameState;
	private Farm _farm;
	private Wallet _wallet;
	
	public DebuggingViewController(GameState gameState) {
		_gameState = gameState;
		_farm = new Farm(gameState);
		_wallet = new Wallet(gameState);
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
		Button goldButton = new Button("Add 100 Gold");
		goldButton.onClick(new UnitSlot() {
			@Override
			public void onEmit() {
				_wallet.addGold(100);
			}
		});
		group.add(goldButton);
		
		// Button to remove 100 gold from the user.
		group.add(new Button("Remove 100 Gold").onClick(new UnitSlot() {
			@Override
			public void onEmit() {
				_wallet.subtractGold(100);
			}
		}));
		
		final Dragon flameyTheDragon = Dragon.create(_gameState, Integer.valueOf(0), "Flamey");
		
		// Button to add a dragon to pen two and make it full.
		group.add(new Button("Add Flamey to the Farm").onClick(new UnitSlot() {
			@Override
			public void onEmit() {
				_farm.setDragonForPen(flameyTheDragon, 0, 2);
			}
		}));
		
		// Button to send flamey to race.
		group.add(new Button("Send Flamey to Race").onClick(new UnitSlot() {
			@Override
			public void onEmit() {
				flameyTheDragon.sendToRace();;
			}
		}));
		
		// Button to send flamey to breed.
		group.add(new Button("Send Flamey to Breed").onClick(new UnitSlot() {
			@Override
			public void onEmit() {
				flameyTheDragon.sendToBreed();
			}
		}));
		
		// Button to make flamey available.
		group.add(new Button("Make Flamey Available").onClick(new UnitSlot() {
			@Override
			public void onEmit() {
				flameyTheDragon.makeAvailable();;
			}
		}));
		
		// Button to give flamey extra score.
		group.add(new Button("Give flamey 100 points").onClick(new UnitSlot() {
			@Override
			public void onEmit() {
				flameyTheDragon.incrementScore(100);
			}
		}));
		
		return group;
	}
}
