package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.DragonGenerator;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.genetics.Gene;
import au.edu.unimelb.cis.dragons.core.model.BreedingMinigameState;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Farm.PenState;
import au.edu.unimelb.cis.dragons.core.view.DragonBreedingInfoView;
import react.Function;
import react.UnitSlot;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.layout.AxisLayout;

/**
 * Display the view for the breeding minigame. The player must correctly select the chances of 
 * inheriting specific alleles at each locus to be allowed to breed a baby dragon.
 *  
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class BreedingViewController extends ViewController {

	// Farm to insert newly bred dragons in to.
	private Farm _farm;
	
	// The two dragons that are being bred.
	private Dragon _dragon;
	private Dragon _otherDragon;
	// The current state of the minigame.
	private BreedingMinigameState _breedingMinigameState;
	private GameState _gameState;

	public BreedingViewController(GameState gameState, Dragon dragon) {
		_gameState = gameState;
		_farm = new Farm(_gameState);
		_dragon = dragon;
		DragonGenerator generator = new DragonGenerator(gameState);
		_otherDragon = generator.createRandomDragon(2);
		_breedingMinigameState =
				new BreedingMinigameState(dragon, _otherDragon);
	}

	@Override
	public String title() {
		return "Breed a dragonling.";
	}

	@Override
	public Group createInterface() {
		// Three column layout.
		Group group = new Group(AxisLayout.horizontal());

		// Left column holds the genotype view for the first dragon.
		group.add(new DragonBreedingInfoView(_dragon).view());

		// Middle column holds the buttons to open the cross mini-game for
		// each locus. Also hold the button that actually breeds a baby dragon once the games are
		// complete.
		Group middlePane = new Group(AxisLayout.vertical());

		Function<Boolean, Boolean> negateFunction = new Function<Boolean, Boolean>() {
			@Override
			public Boolean apply(Boolean input) {
				return !input;
			}
		};

		Button legLength = new Button("Leg Length");
		legLength.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().presentViewController(
						new ClosableModalViewController(
								new GenotypeSelectorViewController(
										_dragon, _otherDragon, Gene.LegLength,
										_breedingMinigameState)));
			}
		});
		legLength.bindEnabled(_breedingMinigameState.legLengthStatePairsAreCorrect().map(negateFunction));
		middlePane.add(legLength);
		
		Button feetType = new Button("Feet Type");
		feetType.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().presentViewController(
						new ClosableModalViewController(
								new GenotypeSelectorViewController(
										_dragon, _otherDragon, Gene.FeetType,
										_breedingMinigameState)));
			}
		});
		feetType.bindEnabled(_breedingMinigameState.feetTypeStatePairsAreCorrect().map(negateFunction));
		middlePane.add(feetType);		
		
		Button coatType = new Button("Coat Type");
		coatType.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().presentViewController(
						new ClosableModalViewController(
								new GenotypeSelectorViewController(
										_dragon, _otherDragon, Gene.CoatType,
										_breedingMinigameState)));
			}
		});
		coatType.bindEnabled(_breedingMinigameState.coatTypeStatePairsAreCorrect().map(negateFunction));
		middlePane.add(coatType);		
		
		Button wingSize = new Button("Wing Size");
		wingSize.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().presentViewController(
						new ClosableModalViewController(
								new GenotypeSelectorViewController(
										_dragon, _otherDragon, Gene.WingSize,
										_breedingMinigameState)));
			}
		});
		wingSize.bindEnabled(_breedingMinigameState.wingSizeStatePairsAreCorrect().map(negateFunction));
		middlePane.add(wingSize);		
		
		Button tailLength = new Button("Tail Length");
		tailLength.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().presentViewController(
						new ClosableModalViewController(
								new GenotypeSelectorViewController(
										_dragon, _otherDragon, Gene.TailLength,
										_breedingMinigameState)));
			}
		});
		tailLength.bindEnabled(_breedingMinigameState.tailLengthStatePairsAreCorrect().map(negateFunction));
		middlePane.add(tailLength);		
		
		Button physique = new Button("Physique");
		physique.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().presentViewController(
						new ClosableModalViewController(
								new GenotypeSelectorViewController(
										_dragon, _otherDragon, Gene.Physique,
										_breedingMinigameState)));
			}
		});
		physique.bindEnabled(_breedingMinigameState.physiqueStatePairsAreCorrect().map(negateFunction));
		middlePane.add(physique);		
		
		Button lungSize = new Button("Lung Size");
		lungSize.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().presentViewController(
						new ClosableModalViewController(
								new GenotypeSelectorViewController(
										_dragon, _otherDragon, Gene.LungSize,
										_breedingMinigameState)));
			}
		});
		lungSize.bindEnabled(_breedingMinigameState.lungSizeStatePairsAreCorrect().map(negateFunction));
		middlePane.add(lungSize);		
		
		Button heartSize = new Button("Heart Size");
		heartSize.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().presentViewController(
						new ClosableModalViewController(
								new GenotypeSelectorViewController(
										_dragon, _otherDragon, Gene.HeartSize,
										_breedingMinigameState)));
			}
		});
		heartSize.bindEnabled(_breedingMinigameState.heartSizeStatePairsAreCorrect().map(negateFunction));
		middlePane.add(heartSize);
		
		Button breedBabyDragonButton = new Button("Breed Baby Dragon");
		breedBabyDragonButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				// Breed a baby dragon and put it in the farm.
				Dragon babyDragon = Dragon.breedDragons(_gameState, "Bobby", _dragon, _otherDragon);
				for (int column = 0; column < Farm.NUM_COLUMNS; ++column) {
					for (int row = 0; row < Farm.NUM_ROWS; ++row) {
						if (_farm.stateForPen(row, column).get().equals(PenState.Empty)) {
							// Put the dragon in that pen.
							_farm.setDragonForPen(babyDragon, row, column);
							return;
						}
					}
				}
				
				// this case before we allow the breeding minigame to start.
			}
		});
		breedBabyDragonButton.bindEnabled(_breedingMinigameState.allStatePairsAreCorrect());
		middlePane.add(breedBabyDragonButton);
		
		group.add(middlePane);

		// Right column holds the genotype view for the second dragon.
		group.add(new DragonBreedingInfoView(_otherDragon).view());

		return group;
	}
}
