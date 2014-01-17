package au.edu.unimelb.cis.dragons.core.controller;

import static au.edu.unimelb.cis.dragons.core.GlobalConfig.*;
import static playn.core.PlayN.assets;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Dragon.DragonState;
import react.Function;
import react.UnitSlot;
import react.ValueView.Listener;
import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.ClickableGroup;
import tripleplay.ui.Group;
import tripleplay.ui.Icons;
import tripleplay.ui.Label;
import tripleplay.ui.MigGroup;
import tripleplay.ui.MigLayout;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;

/**
 * A DragonDetailView displays inforamtion about a particular draong to the
 * player.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class DragonDetailViewController extends ViewController {

	// The dragon that this screen is displaying.
	private Dragon _dragon;

	/**
	 * Create a new DragonDetailView.
	 * @param dragon The dragon that we're displaying information for.
	 */
	public DragonDetailViewController(Dragon dragon) {
		_dragon = dragon;
	}

	@Override
	public String title() {
		return "Dragon Detail";
	}

	@Override
	protected Group createInterface() {
		MigGroup group = new MigGroup(new MigLayout(
				"insets 0, gap 0, fill", "[50%, fill][50%, fill]", "[100%, fill]"));
		group.setStyles(Style.BACKGROUND.is(Background.solid(0xFFFFFFFF)));

		// Add a left pane that displays an image of the dragon.
		Group leftPane = new Group(AxisLayout.vertical());
		leftPane.add(new Label("Image of the dragon."));
		leftPane.add(new Label(Icons.image(assets().getImage("images/bug_in_lampshade_small.jpeg"))));
		group.add(leftPane, "cell 0 0");

		// Add a right pane which displays information about the dragon.
		MigGroup rightPane = new MigGroup(new MigLayout(
				"insets 5, gap 5, fill", "[][]", "[][][][][][][][grow][]"));

		// Add a label that displays the dragon's name
		rightPane.add(makeBoldLabel("Name:"), "cell 0 0");
		Label dragonNameLabel = makePlainLabel();
		_dragon.name().connectNotify(dragonNameLabel.text.slot());
		rightPane.add(dragonNameLabel, "cell 1 0");

		// Add a label that displays the dragon's status
		rightPane.add(makeBoldLabel("State:"), "cell 0 1");
		Label dragonStateLabel = makePlainLabel();
		_dragon.state().map(new Function<DragonState, String>() {
			@Override
			public String apply(DragonState input) {
				return input.toString();
			}
		}).connectNotify(dragonStateLabel.text.slot());
		rightPane.add(dragonStateLabel, "cell 1 1");

		// Add a label that displays the dragon's score.
		rightPane.add(makeBoldLabel("Score:"), "cell 0 2");
		Label dragonScoreLabel = makePlainLabel();
		_dragon.score().map(new Function<Integer, String>() {
			@Override
			public String apply(Integer input) {
				return input.toString();
			}
		}).connectNotify(dragonScoreLabel.text.slot());
		rightPane.add(dragonScoreLabel, "cell 1 2");

		Function<Integer, String> integerToStringMapper = new Function<Integer, String>() {
			@Override
			public String apply(Integer input) {
				return input.toString();
			}
		};

		// Add labels that display the dragon's attributes
		rightPane.add(makeBoldLabel("Speed:"), "cell 0 3");
		Label dragonSpeedLabel = makePlainLabel();
		_dragon.speed().map(integerToStringMapper).connectNotify(dragonSpeedLabel.text.slot());
		rightPane.add(dragonSpeedLabel, "cell 1 3");

		rightPane.add(makeBoldLabel("Endurance:"), "cell 0 4");
		Label dragonEnduranceLabel = makePlainLabel();
		_dragon.endurance().map(integerToStringMapper).connectNotify(dragonEnduranceLabel.text.slot());
		rightPane.add(dragonEnduranceLabel, "cell 1 4");

		rightPane.add(makeBoldLabel("Balance:"), "cell 0 5");
		Label dragonBalanceLabel = makePlainLabel();
		_dragon.balance().map(integerToStringMapper).connectNotify(dragonBalanceLabel.text.slot());
		rightPane.add(dragonBalanceLabel, "cell 1 5");

		rightPane.add(makeBoldLabel("Weight:"), "cell 0 6");
		Label dragonWeightLabel = makePlainLabel();
		_dragon.weight().map(integerToStringMapper).connectNotify(dragonWeightLabel.text.slot());
		rightPane.add(dragonWeightLabel, "cell 1 6");

		// Add a pane that shows buttons allowing the player to complete
		// actions using the dragon.
		Group actionPane = new Group(AxisLayout.vertical());

		final Button sendDragonToBreedButton = new Button("Send to Breed");
		sendDragonToBreedButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				_dragon.sendToBreed();
			}
		});
		actionPane.add(sendDragonToBreedButton);

		final Button sendDragonToRaceButton = new Button("Send to Race");
		sendDragonToRaceButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				_dragon.sendToRace();
			}
		});
		actionPane.add(sendDragonToRaceButton);

		final Button sendDragonToTrainButton = new Button("Send to Train");
		sendDragonToTrainButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				_dragon.sendToTrain();
			}
		});
		actionPane.add(sendDragonToTrainButton);

		final Button returnDragonToFarmButton = new Button("Return to Farm");
		returnDragonToFarmButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				_dragon.makeAvailable();
			}
		});
		actionPane.add(returnDragonToFarmButton);

		// Grey out the buttons that send the dragon to do what it's doing right
		// now
		_dragon.state().connectNotify(new Listener<DragonState>() {
			@Override
			public void onChange(DragonState value, DragonState oldValue) {
				sendDragonToBreedButton.setEnabled(true);
				sendDragonToRaceButton.setEnabled(true);
				sendDragonToTrainButton.setEnabled(true);
				returnDragonToFarmButton.setEnabled(true);

				switch(value) {
				case Available:
					returnDragonToFarmButton.setEnabled(false);
					break;
				case Training:
					sendDragonToTrainButton.setEnabled(false);
					break;
				case Racing:
					sendDragonToRaceButton.setEnabled(false);
					break;
				case Breeding:
					sendDragonToBreedButton.setEnabled(false);
					break;
				}
			}
		});

		rightPane.add(actionPane, "cell 0 8, span 2 1");

		group.add(rightPane, "cell 1 0");

		new ClickableGroup(group); // Make the root group absorb all click events.
		return group;
	}
}
