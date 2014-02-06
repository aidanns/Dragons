package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.Alert;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Race;
import au.edu.unimelb.cis.dragons.core.model.Wallet;
import react.UnitSlot;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.layout.AxisLayout;

public class RaceViewController extends ViewController {
	
	private GameState _gameState;
	private Dragon _dragon;
	private Wallet _wallet;
	private Phenotype _phenotype;
	private Integer _bonusSize;
	private String _attribute;

	public RaceViewController(GameState gameState, Dragon dragon, Wallet wallet, Phenotype phenotype, Integer bonusSize, String attribute) {
		_dragon = dragon;
		_wallet = wallet;
		_phenotype = phenotype;
		_bonusSize = bonusSize;
		_attribute = attribute;
		_gameState = gameState;
	}
	
	@Override
	public String title() {
		return "Race a dragon";
	}
	
	@Override
	public Group createInterface() {
		Group group = new Group(AxisLayout.vertical());
		
		group.add(new Label("Dragons with " + _phenotype.toString() + 
				" receive a +" + _bonusSize.toString() + " to " + _attribute + 
				" during races on this island."));
		
		Button raceButton = new Button("Race");
		
		final Integer prizeMoney = 100;
		
		raceButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				Race r = new Race(_gameState, _dragon, 3000.0, _phenotype, _bonusSize);
				Integer place = r.simulateAndGetPlace();
				if (place == 1) {
					new Alert("Your dragon won!").displayOnScreen(parentScreen());
					_wallet.addGold(prizeMoney);
				} else {
					new Alert("Your dragon was placed " + place.toString() + ". Better luck next time.").displayOnScreen(parentScreen());
				}
			}
		});
		group.add(raceButton);
		
		return group;
	}

}
