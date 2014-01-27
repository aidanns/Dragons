package au.edu.unimelb.cis.dragons.core.controller;

import react.UnitSlot;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.layout.AxisLayout;
import au.edu.unimelb.cis.dragons.core.Alert;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Wallet;
import au.edu.unimelb.cis.dragons.core.model.Farm.PenState;
import au.edu.unimelb.cis.dragons.core.view.PenView;

/**
 * A ViewController that allows the player to have their dragon trained, to
 * improve specific attributes.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class DragonTrainingViewController extends ViewController {

	private Wallet _wallet;
	private Dragon _dragon;

	public DragonTrainingViewController(Wallet wallet, Dragon dragon) {
		_wallet = wallet;
		_dragon = dragon;
	}

	@Override
	public String title() {
		return "Train a dragon";
	}

	@Override
	protected Group createInterface() {
		Group group = new Group(AxisLayout.vertical());

		PenView penView = new PenView();
		penView.penState().update(PenState.Full);
		_dragon.name().connectNotify(penView.dragonName().slot());
		_dragon.state().connectNotify(penView.dragonState().slot());
		group.add(penView.view());

		final Integer goldToTrain = 100;
		final Integer numberOfRacesToTrainFor = 5;

		Group buttonGroup = new Group(AxisLayout.horizontal());

		Button trainSpeedButton = new Button("Train Speed");
		trainSpeedButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				if (_wallet.gold().get() >= goldToTrain) {
					_wallet.subtractGold(goldToTrain);
					_dragon.trainSpeedForRaces(numberOfRacesToTrainFor);
				} else {
					new Alert("Training a dragon costs " + goldToTrain + " gold. You do not have enough.").displayOnScreen(parentScreen());
				}
			}
		});
		buttonGroup.add(trainSpeedButton);

		Button trainEnduranceButton = new Button("Train Endurance");
		trainEnduranceButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				if (_wallet.gold().get() >= goldToTrain) {
					_wallet.subtractGold(goldToTrain);
					_dragon.trainEnduranceForRaces(numberOfRacesToTrainFor);
				} else {
					new Alert("Training a dragon costs " + goldToTrain + " gold. You do not have enough.").displayOnScreen(parentScreen());
				}
			}
		});
		buttonGroup.add(trainEnduranceButton);

		Button trainBalanceButton = new Button("Train Balance");
		trainBalanceButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				if (_wallet.gold().get() >= goldToTrain) {
					_wallet.subtractGold(goldToTrain);
					_dragon.trainBalanceForRaces(numberOfRacesToTrainFor);
				} else {
					new Alert("Training a dragon costs " + goldToTrain + " gold. You do not have enough.").displayOnScreen(parentScreen());
				}
			}
		});
		buttonGroup.add(trainBalanceButton);

		Button trainWeightButton = new Button("Train Weight");
		trainWeightButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				if (_wallet.gold().get() >= goldToTrain) {
					_wallet.subtractGold(goldToTrain);
					_dragon.trainWeightForRaces(numberOfRacesToTrainFor);
				} else {
					new Alert("Training a dragon costs " + goldToTrain + " gold. You do not have enough.").displayOnScreen(parentScreen());
				}
			}
		});
		buttonGroup.add(trainWeightButton);

		group.add(buttonGroup);

		return group;
	}

}
