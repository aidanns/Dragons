package au.edu.unimelb.cis.dragons.core.controller;

import static au.edu.unimelb.cis.dragons.core.GlobalConfig.*;
import static playn.core.PlayN.assets;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Dragon.DragonState;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Wallet;
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
	
	private GameState _gameState;
	private Farm _farm;
	private Wallet _wallet;
	private Integer _penColumn;
	private Integer _penRow;

	/**
	 * Create a new DragonDetailView that displays the information about a dragon.
	 * @param dragon The dragon that we're displaying information for.
	 */
	public DragonDetailViewController(Dragon dragon) {
		_dragon = dragon;
	}
	
	/**
	 * Create a new DragonDetailViewController that includes the action bar at
	 * the bottom of the screen.
	 * @param farm The farm backing the dragon.
	 * @param wallet The user's wallet.
	 * @param penColumn The column of the pen that the dragon is in.
	 * @param penRow The row of the pen that the dragon is in.
	 */
	public DragonDetailViewController(GameState gameState, Farm farm, Wallet wallet, Integer penColumn, Integer penRow) {
		_gameState = gameState;
		_farm = farm;
		_wallet = wallet;
		_penColumn = penColumn;
		_penRow = penRow;
		_dragon = _farm.dragonForPen(_penRow, _penColumn).get();
	}

	@Override
	public String title() {
		return "Dragon Detail";
	}

	@Override
	protected Group createInterface() {
		MigGroup group = new MigGroup(new MigLayout(
				"insets 0, gap 0, fill", "[40%, fill][60%, fill]", "[][]"));
		group.setStyles(Style.BACKGROUND.is(Background.solid(0xFFFFFFFF)));

		// Add a left pane that displays an image of the dragon.
		Group leftPane = new Group(AxisLayout.vertical());
		leftPane.add(new Label("Image of the dragon."));
		leftPane.add(new Label(Icons.image(assets().getImage("images/bug_in_lampshade_small.jpeg"))));
		group.add(leftPane, "cell 0 0");

		// Add a right pane which displays information about the dragon.
		MigGroup rightPane = new MigGroup(new MigLayout(
				"insets 5, gap 5, fill", "[50%][50%]", "[][][][][][][][][grow][]"));

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

//		// Add a label that displays the dragon's score.
//		rightPane.add(makeBoldLabel("Score:"), "cell 0 2");
//		Label dragonScoreLabel = makePlainLabel();
//		_dragon.score().map(new Function<Integer, String>() {
//			@Override
//			public String apply(Integer input) {
//				return input.toString();
//			}
//		}).connectNotify(dragonScoreLabel.text.slot());
//		rightPane.add(dragonScoreLabel, "cell 1 2");

		Function<Integer, String> integerToStringMapper = new Function<Integer, String>() {
			@Override
			public String apply(Integer input) {
				return input.toString();
			}
		};

		Function<Allele, String> alleleToStringMapper  = new Function<Allele, String>() {
			@Override
			public String apply(Allele input) {
				return input.toString();
			}
		};

		Function<Phenotype, String> phenotypeToStringMapper = new Function<Phenotype, String>() {
			@Override
			public String apply(Phenotype input) {
				return input.toString();
			}
		};

		// Add genotype
		MigGroup genesGroup = new MigGroup(new MigLayout(
				"insets 0, gap 0, fill", "[][][][]", "[][][][][][][][][grow]"));

		genesGroup.add(makeBoldLabel("Leg Length"), "cell 0 0");
		Label dragonLegLengthGenotypeLabelOne = makePlainLabel();
		_dragon.legLengthAlleleOne().map(alleleToStringMapper).connectNotify(dragonLegLengthGenotypeLabelOne.text.slot());
		genesGroup.add(dragonLegLengthGenotypeLabelOne, "cell 1 0");
		Label dragonLegLengthGenotypeLabelTwo = makePlainLabel();
		_dragon.legLengthAlleleTwo().map(alleleToStringMapper).connectNotify(dragonLegLengthGenotypeLabelTwo.text.slot());
		genesGroup.add(dragonLegLengthGenotypeLabelTwo, "cell 2 0");
		Label dragonLegLengthPhenotypeLabel = makePlainLabel();
		_dragon.legLength().map(phenotypeToStringMapper).connectNotify(dragonLegLengthPhenotypeLabel.text.slot());
		genesGroup.add(dragonLegLengthPhenotypeLabel, "cell 3 0");

		genesGroup.add(makeBoldLabel("Feet Type"), "cell 0 1");
		Label dragonFeetTypeGenotypeLabelOne = makePlainLabel();
		_dragon.feetTypeAlleleOne().map(alleleToStringMapper).connectNotify(dragonFeetTypeGenotypeLabelOne.text.slot());
		genesGroup.add(dragonFeetTypeGenotypeLabelOne, "cell 1 1");
		Label dragonFeetTypeGenotypeLabelTwo = makePlainLabel();
		_dragon.feetTypeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonFeetTypeGenotypeLabelTwo.text.slot());
		genesGroup.add(dragonFeetTypeGenotypeLabelTwo, "cell 2 1");
		Label dragonFeetTypePhenotypeLabel = makePlainLabel();
		_dragon.feetType().map(phenotypeToStringMapper).connectNotify(dragonFeetTypePhenotypeLabel.text.slot());
		genesGroup.add(dragonFeetTypePhenotypeLabel, "cell 3 1");

		genesGroup.add(makeBoldLabel("Coat Type"), "cell 0 2");
		Label dragonCoatTypeGenotypeLabelOne = makePlainLabel();
		_dragon.coatTypeAlleleOne().map(alleleToStringMapper).connectNotify(dragonCoatTypeGenotypeLabelOne.text.slot());
		genesGroup.add(dragonCoatTypeGenotypeLabelOne, "cell 1 2");
		Label dragonCoatTypeGenotypeLabelTwo = makePlainLabel();
		_dragon.coatTypeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonCoatTypeGenotypeLabelTwo.text.slot());
		genesGroup.add(dragonCoatTypeGenotypeLabelTwo, "cell 2 2");
		Label dragonCoatTypePhenotypeLabel = makePlainLabel();
		_dragon.coatType().map(phenotypeToStringMapper).connectNotify(dragonCoatTypePhenotypeLabel.text.slot());
		genesGroup.add(dragonCoatTypePhenotypeLabel, "cell 3 2");

		genesGroup.add(makeBoldLabel("Wing Size"), "cell 0 3");
		Label dragonWingSizeGenotypeLabelOne = makePlainLabel();
		_dragon.wingSizeAlleleOne().map(alleleToStringMapper).connectNotify(dragonWingSizeGenotypeLabelOne.text.slot());
		genesGroup.add(dragonWingSizeGenotypeLabelOne, "cell 1 3");
		Label dragonWingSizeGenotypeLabelTwo = makePlainLabel();
		_dragon.wingSizeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonWingSizeGenotypeLabelTwo.text.slot());
		genesGroup.add(dragonWingSizeGenotypeLabelTwo, "cell 2 3");
		Label dragonWingSizePhenotypeLabel = makePlainLabel();
		_dragon.wingSize().map(phenotypeToStringMapper).connectNotify(dragonWingSizePhenotypeLabel.text.slot());
		genesGroup.add(dragonWingSizePhenotypeLabel, "cell 3 3");

		genesGroup.add(makeBoldLabel("Tail Length"), "cell 0 4");
		Label dragonTailLengthGenotypeLabelOne = makePlainLabel();
		_dragon.tailLengthAlleleOne().map(alleleToStringMapper).connectNotify(dragonTailLengthGenotypeLabelOne.text.slot());
		genesGroup.add(dragonTailLengthGenotypeLabelOne, "cell 1 4");
		Label dragonTailLengthGenotypeLabelTwo = makePlainLabel();
		_dragon.tailLengthAlleleTwo().map(alleleToStringMapper).connectNotify(dragonTailLengthGenotypeLabelTwo.text.slot());
		genesGroup.add(dragonTailLengthGenotypeLabelTwo, "cell 2 4");
		Label dragonTailLengthPhenotypeLabel = makePlainLabel();
		_dragon.tailLength().map(phenotypeToStringMapper).connectNotify(dragonTailLengthPhenotypeLabel.text.slot());
		genesGroup.add(dragonTailLengthPhenotypeLabel, "cell 3 4");

		genesGroup.add(makeBoldLabel("Physique"), "cell 0 5");
		Label dragonPhysiqueGenotypeLabelOne = makePlainLabel();
		_dragon.physiqueAlleleOne().map(alleleToStringMapper).connectNotify(dragonPhysiqueGenotypeLabelOne.text.slot());
		genesGroup.add(dragonPhysiqueGenotypeLabelOne, "cell 1 5");
		Label dragonPhysiqueGenotypeLabelTwo = makePlainLabel();
		_dragon.physiqueAlleleTwo().map(alleleToStringMapper).connectNotify(dragonPhysiqueGenotypeLabelTwo.text.slot());
		genesGroup.add(dragonPhysiqueGenotypeLabelTwo, "cell 2 5");
		Label dragonPhysiquePhenotypeLabel = makePlainLabel();
		_dragon.physique().map(phenotypeToStringMapper).connectNotify(dragonPhysiquePhenotypeLabel.text.slot());
		genesGroup.add(dragonPhysiquePhenotypeLabel, "cell 3 5");

		genesGroup.add(makeBoldLabel("Lung Size"), "cell 0 6");
		Label dragonLungSizeGenotypeLabelOne = makePlainLabel();
		_dragon.lungSizeAlleleOne().map(alleleToStringMapper).connectNotify(dragonLungSizeGenotypeLabelOne.text.slot());
		genesGroup.add(dragonLungSizeGenotypeLabelOne, "cell 1 6");
		Label dragonLungSizeGenotypeLabelTwo = makePlainLabel();
		_dragon.lungSizeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonLungSizeGenotypeLabelTwo.text.slot());
		genesGroup.add(dragonLungSizeGenotypeLabelTwo, "cell 2 6");
		Label dragonLungSizePhenotypeLabel = makePlainLabel();
		_dragon.lungSize().map(phenotypeToStringMapper).connectNotify(dragonLungSizePhenotypeLabel.text.slot());
		genesGroup.add(dragonLungSizePhenotypeLabel, "cell 3 6");

		genesGroup.add(makeBoldLabel("Heart Size"), "cell 0 7");
		Label dragonHeartSizeGenotypeLabelOne = makePlainLabel();
		_dragon.heartSizeAlleleOne().map(alleleToStringMapper).connectNotify(dragonHeartSizeGenotypeLabelOne.text.slot());
		genesGroup.add(dragonHeartSizeGenotypeLabelOne, "cell 1 7");
		Label dragonHeartSizeGenotypeLabelTwo = makePlainLabel();
		_dragon.heartSizeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonHeartSizeGenotypeLabelTwo.text.slot());
		genesGroup.add(dragonHeartSizeGenotypeLabelTwo, "cell 2 7");
		Label dragonHeartSizePhenotypeLabel = makePlainLabel();
		_dragon.heartSize().map(phenotypeToStringMapper).connectNotify(dragonHeartSizePhenotypeLabel.text.slot());
		genesGroup.add(dragonHeartSizePhenotypeLabel, "cell 3 7");

		rightPane.add(genesGroup, "cell 0 3, span 2 1");

		// Add labels that display the dragon's attributes
		rightPane.add(makeBoldLabel("Speed:"), "cell 0 4");
		Label dragonSpeedLabel = makePlainLabel();
		_dragon.speed().map(integerToStringMapper).connectNotify(dragonSpeedLabel.text.slot());
		rightPane.add(dragonSpeedLabel, "cell 1 4");

		rightPane.add(makeBoldLabel("Endurance:"), "cell 0 5");
		Label dragonEnduranceLabel = makePlainLabel();
		_dragon.endurance().map(integerToStringMapper).connectNotify(dragonEnduranceLabel.text.slot());
		rightPane.add(dragonEnduranceLabel, "cell 1 5");

		rightPane.add(makeBoldLabel("Balance:"), "cell 0 6");
		Label dragonBalanceLabel = makePlainLabel();
		_dragon.balance().map(integerToStringMapper).connectNotify(dragonBalanceLabel.text.slot());
		rightPane.add(dragonBalanceLabel, "cell 1 6");

		rightPane.add(makeBoldLabel("Weight:"), "cell 0 7");
		Label dragonWeightLabel = makePlainLabel();
		_dragon.weight().map(integerToStringMapper).connectNotify(dragonWeightLabel.text.slot());
		rightPane.add(dragonWeightLabel, "cell 1 7");

		if (actionBarShouldBeDisplayed()) {
			// Add a pane that shows buttons allowing the player to complete
			// actions using the dragon.
			Group actionPane = new Group(AxisLayout.horizontal());

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
					parentScreen().presentViewController(new ClosableModalViewController(new RaceSelectionViewController(_gameState, _dragon, _wallet)));
				}
			});
			actionPane.add(sendDragonToRaceButton);

			final Button sendDragonToTrainButton = new Button("Send to Train");
			sendDragonToTrainButton.clicked().connect(new UnitSlot() {
				@Override
				public void onEmit() {
					parentScreen().presentViewController(new ClosableModalViewController(new DragonTrainingViewController(_wallet, _dragon)));
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
			
			final Button sellDragonButton = new Button("Sell dragon");
			sellDragonButton.clicked().connect(new UnitSlot() {
				@Override
				public void onEmit() {
					parentScreen().presentViewController(new ClosableModalViewController(new SellDragonViewController(_farm, _wallet, _penColumn, _penRow)));
				}
			});
			actionPane.add(sellDragonButton);
			
			// If we sell the dragon out of the farm, dismiss this view so we
			// go back to the farm after closing the sell view.
			_farm.dragonForPen(_penRow, _penColumn).connect(new Listener<Dragon>() {
				@Override
				public void onChange(Dragon value, Dragon oldValue) {
					if (value == null) {
						parentScreen().dismissViewController(DragonDetailViewController.this);
						_farm.dragonForPen(_penRow, _penColumn).disconnect(this);
					}
				}
			});
			
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

			group.add(actionPane, "cell 0 1, span 2 1");
		}

		group.add(rightPane, "cell 1 0");

		new ClickableGroup(group); // Make the root group absorb all click events.
		return group;
	}
	
	private boolean actionBarShouldBeDisplayed() {
		return (_farm != null && _wallet != null && _penColumn != null && _penRow != null);
	}
}
