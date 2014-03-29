package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.DragonGenerator;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.view.DragonBreedingInfoView;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.layout.AxisLayout;

public class BreedingViewController extends ViewController {

	private GameState _gameState;
	private Dragon _dragon;
	private Dragon _otherDragon;

	public BreedingViewController(GameState gameState, Dragon dragon) {
		_gameState = gameState;
		_dragon = dragon;

		DragonGenerator generator = new DragonGenerator(_gameState);
		_otherDragon = generator.createRandomDragon(2);
	}

	@Override
	public String title() {
		return "Breed a dragonling.";
	}

	@Override
	public Group createInterface() {
		Group group = new Group(AxisLayout.horizontal());

		group.add(new DragonBreedingInfoView(_dragon).view());

		Group middlePane = new Group(AxisLayout.vertical());

		Button legLength = new Button("Leg Length");
		middlePane.add(legLength);
		Button feetType = new Button("Feet Type");
		middlePane.add(feetType);
		Button coatType = new Button("Coat Type");
		middlePane.add(coatType);
		Button wingSize = new Button("Wing Size");
		middlePane.add(wingSize);
		Button tailLength = new Button("Tail Length");
		middlePane.add(tailLength);
		Button physique = new Button("Physique");
		middlePane.add(physique);
		Button lungSize = new Button("Lung Size");
		middlePane.add(lungSize);
		Button heartSize = new Button("Heart Size");
		middlePane.add(heartSize);

		group.add(middlePane);

		group.add(new DragonBreedingInfoView(_otherDragon).view());

		return group;
	}

}
