package au.edu.unimelb.cis.dragons.core.controller;

import react.UnitSlot;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.layout.AxisLayout;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Farm.PenState;
import au.edu.unimelb.cis.dragons.core.model.Wallet;
import au.edu.unimelb.cis.dragons.core.view.PenView;

/**
 * View Controller to allow the player to sell a dragon to the dragon guilde.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class SellDragonViewController extends ViewController {

	private Farm _farm;
	private Wallet _wallet;
	private int _penColumn;
	private int _penRow;
	
	public SellDragonViewController(Farm farm, Wallet wallet, Integer penColumn, Integer penRow) {
		_farm = farm;
		_wallet = wallet;
		_penColumn = penColumn;
		_penRow = penRow;
	}
	
	@Override
	public String title() {
		return "Sell a dragon";
	}
	
	@Override
	protected Group createInterface() {
		Dragon dragon = _farm.dragonForPen(_penRow, _penColumn).get();
		
		Group group = new Group(AxisLayout.vertical());
		
		Label label = new Label("Warning: Once sold, " + dragon.name().get() + " will be permanently removed from the farm.");
		group.add(label);
		
		PenView penView = new PenView();
		penView.penState().update(PenState.Full);
		dragon.state().connectNotify(penView.dragonState().slot());
		dragon.name().connectNotify(penView.dragonName().slot());
		group.add(penView.view());
		
		Button sellButton = new Button("Sell " + dragon.name().get());
		final Integer goldToSellDragonFor = 100;
		sellButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				_wallet.addGold(goldToSellDragonFor);
				_farm.openAndClearPen(_penRow, _penColumn);
				parentScreen().dismissViewController(SellDragonViewController.this);
			}
		});
		group.add(sellButton);
		
		return group;
	}
	
}
