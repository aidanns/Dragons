package au.edu.unimelb.cis.dragons.core.controller;

import react.UnitSlot;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.layout.AxisLayout;
import au.edu.unimelb.cis.dragons.core.Alert;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Farm.PenState;
import au.edu.unimelb.cis.dragons.core.model.Wallet;

/**
 * Controller that will allow the user to purchase a locked pen in their farm.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class BuyPenViewController extends ViewController {
	
	// The farm that we're buying a pen in.
	private Farm _farm;
	
	// The row of the pen to buy.
	private int _row;
	
	// The column of the pen to buy.
	private int _column;
	
	// The users wallet that will be used to pay for the pen.
	private Wallet _wallet;
	
	/**
	 * Create a new BuyPenViewController.
	 * @param farm The farm that the pen to be purchased is in.
	 * @param column The column of the pen.
	 * @param row The row of the pen.
	 * @param wallet The wallet to be used to pay for the pen.
	 */
	public BuyPenViewController(Farm farm, Integer column, Integer row, Wallet wallet) {
		_farm = farm;
		_column = column;
		_row = row;
		_wallet = wallet;
	}

	@Override
	protected Group createInterface() {
		final Integer goldToUnlockNextPen = goldToUnlockNextPen();
		Group group = new Group(AxisLayout.vertical());
		Button buyPenButton = new Button("Purchase Pen: " + goldToUnlockNextPen.toString() + " Gold"); 
		buyPenButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				if (_wallet.gold().get() >= goldToUnlockNextPen) {
					_farm.openAndClearPen(_row, _column);
					_wallet.subtractGold(goldToUnlockNextPen);
					parentScreen().dismissViewController(BuyPenViewController.this);
				} else {
					new Alert("Unlocking the next pen costs " + goldToUnlockNextPen + " gold. You do not have enough.").displayOnScreen(parentScreen());;
				}
			}
		});	
		group.add(buyPenButton);
		return group;	
	}
	
	private int goldToUnlockNextPen() {
		int lockedPens = 0;
		for (int column = 0; column < 4; column++) {
			for (int row = 0; row < 2; row++) {
				if (_farm.stateForPen(row, column).get() == PenState.Locked) {
					lockedPens++;
				}
			}
		}
		switch (lockedPens) {
		case 1:
			return 1000;
		case 2:
			return 400;
		case 3:
			return 200;
		default:
			return 100;
		}
	}
}
