package au.edu.unimelb.cis.dragons.core.controller;

import react.UnitSlot;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.layout.AxisLayout;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Wallet;

public class RaceSelectionViewController extends ViewController {

	private GameState _gameState;
	private Dragon _dragon;
	private Wallet _wallet;

	public RaceSelectionViewController(GameState gameState, Dragon dragon, Wallet wallet) {
		_gameState = gameState;
		_dragon = dragon;
		_wallet = wallet;
	}

	@Override
	public String title() {
		return "Race a dragon";
	}

	@Override
	protected Group createInterface() {
		Group group = new Group(AxisLayout.vertical());

		group.add(new Label("Select which island you want to race on."));

		Button marshyIsland = new Button("Marshy Island");
		marshyIsland.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().presentViewController(new ClosableModalViewController(new MarshyIslandRaceViewController(_gameState, _wallet, _dragon)));
			}
		});
		group.add(marshyIsland);

		Button arcticIsland = new Button("Arctic Island");
		arcticIsland.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().presentViewController(new ClosableModalViewController(new ArcticIslandRaceViewController(_gameState, _wallet, _dragon)));
			}
		});
		group.add(arcticIsland);

		Button desertIsland = new Button("Desert Island");
		desertIsland.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().presentViewController(new ClosableModalViewController(new DesertIslandRaceViewController(_gameState, _wallet, _dragon)));
			}
		});
		group.add(desertIsland);

		Button grassyIsland = new Button("Grassy Island");
		grassyIsland.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().presentViewController(new ClosableModalViewController(new GrassyIslandRaceViewController(_gameState, _wallet, _dragon)));
			}
		});
		group.add(grassyIsland);

		return group;
	}

}
