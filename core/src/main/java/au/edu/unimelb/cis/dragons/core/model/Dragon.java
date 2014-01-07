package au.edu.unimelb.cis.dragons.core.model;

import java.util.HashMap;
import java.util.Map;

import react.Function;
import react.Slot;
import react.Value;
import react.ValueView;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;
import au.edu.unimelb.cis.dragons.core.genetics.genes.CoatType;
import au.edu.unimelb.cis.dragons.core.genetics.genes.FeetType;
import au.edu.unimelb.cis.dragons.core.genetics.genes.HeartSize;
import au.edu.unimelb.cis.dragons.core.genetics.genes.LegLength;
import au.edu.unimelb.cis.dragons.core.genetics.genes.LungSize;
import au.edu.unimelb.cis.dragons.core.genetics.genes.Physique;
import au.edu.unimelb.cis.dragons.core.genetics.genes.TailLength;
import au.edu.unimelb.cis.dragons.core.genetics.genes.WingSize;

/**
 * Represents an individual dragon within the game.
 * Wraps the GameState to provide mediated access to it from the controllers.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class Dragon {
	
	/**
	 * Represents the different possible states for a single dragon.
	 */
	public enum DragonState {
		/** The dragon is in the stable and available for use by the player. */
		Available("available"),
		
		/** The dragon has been submitted for a race and will return once the race has been completed. */
		Racing("racing"),
		
		/** The dragon has been sent to conduct training and will return once it has finished. */
		Training("training"),
		
		/** The dragon has been sent to breed and will return once it is finished. */
		Breeding("breeding");
		
		// Map from the state name back to the state, to make it easy to get
		// the state, given the string that is persisted to represent it.
		private static Map<String, DragonState> stateNameToStateMap = new HashMap<String, DragonState>();
		
		static {
			for (DragonState state : DragonState.values()) {
				stateNameToStateMap.put(state._stateName, state);
			}
			stateNameToStateMap.put("", null);
		}
		
		private String _stateName;
		
		DragonState(String stateName) {
			_stateName = stateName;
		}
		
		@Override
		public String toString() {
			return _stateName;
		}
		
		/**
		 * Get the DragonState for a given state name, which is returned from 
		 * the GameState.
		 * @param stateName The name of the state to retrieve.
		 * @return The state enum object representing this state.
		 */
		/* package */ static DragonState dragonStateForStateName(String stateName) {
			return stateNameToStateMap.get(stateName);
		}
	}
	
	/** The GameState that backs this dragon. */
	private GameState _gameState;
	
	/** The identifier for this dragon. */
	private Integer _id;
	
	/**
	 * Create a new dragon with a given name in the GameState with a state of available.
	 * WARNING: This will clobber any previous dragon with the same id in the GameState.
	 * @param gameState The GameState to create the dragon in.
	 * @param id The id of the dragon.
	 * @param name The name of the dragon to create.
	 * @return
	 */
	public static Dragon create(GameState gameState, Integer id, String name) {
		gameState.valueForKey(GameState.Key.dragonNameKeyForId(id)).update(name);
		gameState.valueForKey(GameState.Key.dragonScoreKeyForId(id)).update("0");
		Dragon dragon = new Dragon(gameState, id);
		dragon.makeAvailable();
		return dragon;
	}
	
	/**
	 * Create a new dragon, backed by a particular GameState.
	 * @param gameState The game state to store this dragon in.
	 * @param id The id to store this dragon at.
	 */
	public Dragon(GameState gameState, Integer id) {
		_gameState = gameState;
		_id = id;
	}
	
	/**
	 * Get the Id for this dragon.
	 * @return The dragons Id.
	 */
	public Integer id() {
		return _id;
	}
	
	/**
	 * Get the state of the dragon.
	 * @return The dragon's state.
	 */
	public ValueView<DragonState> state() {
		return _gameState.valueForKey(GameState.Key.dragonStateKeyForId(_id)).map(new Function<String, DragonState>() {
			@Override
			public DragonState apply(String input) {
				return DragonState.dragonStateForStateName(input);
			}
		});
	}
	
	/**
	 * Mark the dragon as available in the farm.
	 */
	public void makeAvailable() {
		_gameState.valueForKey(GameState.Key.dragonStateKeyForId(_id)).update(DragonState.Available.toString());
	}
	
	/**
	 * Mark the dragon as off racing.
	 */
	public void sendToRace() {
		_gameState.valueForKey(GameState.Key.dragonStateKeyForId(_id)).update(DragonState.Racing.toString());
	}
	
	/**
	 * Mark the dragon as off breeding.
	 */
	public void sendToBreed() {
		_gameState.valueForKey(GameState.Key.dragonStateKeyForId(_id)).update(DragonState.Breeding.toString());
	}
	
	/**
	 * Get the name of the dragon.
	 * @return The dragon's name.
	 */
	public ValueView<String> name() {
		return _gameState.valueForKey(GameState.Key.dragonNameKeyForId(_id));
	} 
	
	/**
	 * Get the score for this dragon.
	 * @return The dragon's score.
	 */
	public ValueView<Integer> score() {
		return _gameState.valueForKey(GameState.Key.dragonScoreKeyForId(_id)).map(new Function<String, Integer>() {
			@Override
			public Integer apply(String input) {
				return Integer.parseInt(input);
			}
		});
	}
	
	// For us in all the Allele retrieving functions.
	private Function<String, Allele> _stringToAlleleMapper = new Function<String, Allele>() {
		@Override
		public Allele apply(String input) {
			return Allele.valueOf(input);
		}
	};
	
	/**
	 * Get the first leg length allele for this dragon.
	 * @return The dragon's first leg length allele.
	 */
	public ValueView<Allele> legLengthAlleleOne() {
		return _gameState.valueForKey(GameState.Key.dragonLegLengthAlleleOneKeyForId(_id)).map(this._stringToAlleleMapper);
	}
	
	/**
	 * Get the second leg length allele for this dragon.
	 * @return The dragon's second leg length allele.
	 */
	public ValueView<Allele> legLengthAlleleTwo() {
		return _gameState.valueForKey(GameState.Key.dragonLegLengthAlleleTwoKeyForId(_id)).map(this._stringToAlleleMapper);
	}
	
	/**
	 * Get the first feet type allele for this dragon.
	 * @return The dragon's first feet type allele.
	 */
	public ValueView<Allele> feetTypeAlleleOne() {
		return _gameState.valueForKey(GameState.Key.dragonFeetTypeAlleleOneKeyForId(_id)).map(this._stringToAlleleMapper);
	}
	
	/**
	 * Get the second feet type allele for this dragon.
	 * @return The dragon's second feet type allele.
	 */
	public ValueView<Allele> feetTypeAlleleTwo() {
		return _gameState.valueForKey(GameState.Key.dragonFeetTypeAlleleTwoKeyForId(_id)).map(this._stringToAlleleMapper);
	}

	/**
	 * Get the first coat type allele for this dragon.
	 * @return The dragon's first coat type allele.
	 */
	public ValueView<Allele> coatTypeAlleleOne() {
		return _gameState.valueForKey(GameState.Key.dragonCoatTypeAlleleOneKeyForId(_id)).map(this._stringToAlleleMapper);
	}
	
	/**
	 * Get the second coat type allele for this dragon.
	 * @return The dragon's second coat type allele.
	 */
	public ValueView<Allele> coatTypeAlleleTwo() {
		return _gameState.valueForKey(GameState.Key.dragonCoatTypeAlleleTwoKeyForId(_id)).map(this._stringToAlleleMapper);
	}

	/**
	 * Get the first wing size allele for this dragon.
	 * @return The dragon's first wing size allele.
	 */
	public ValueView<Allele> wingSizeAlleleOne() {
		return _gameState.valueForKey(GameState.Key.dragonWingSizeAlleleOneKeyForId(_id)).map(this._stringToAlleleMapper);
	}
	
	/**
	 * Get the second wing size allele for this dragon.
	 * @return The dragon's second wing size allele.
	 */
	public ValueView<Allele> wingSizeAlleleTwo() {
		return _gameState.valueForKey(GameState.Key.dragonWingSizeAlleleTwoKeyForId(_id)).map(this._stringToAlleleMapper);
	}

	/**
	 * Get the first tail length allele for this dragon.
	 * @return The dragon's first tail length allele.
	 */
	public ValueView<Allele> tailLengthAlleleOne() {
		return _gameState.valueForKey(GameState.Key.dragonTailLengthAlleleOneKeyForId(_id)).map(this._stringToAlleleMapper);
	}
	
	/**
	 * Get the second tail length allele for this dragon.
	 * @return The dragon's second tail length allele.
	 */
	public ValueView<Allele> tailLengthAlleleTwo() {
		return _gameState.valueForKey(GameState.Key.dragonTailLengthAlleleTwoKeyForId(_id)).map(this._stringToAlleleMapper);
	}

	/**
	 * Get the first physique allele for this dragon.
	 * @return The dragon's first physique allele.
	 */
	public ValueView<Allele> physiqueAlleleOne() {
		return _gameState.valueForKey(GameState.Key.dragonPhysiqueAlleleOneKeyForId(_id)).map(this._stringToAlleleMapper);
	}
	
	/**
	 * Get the second physique allele for this dragon.
	 * @return The dragon's second physique allele.
	 */
	public ValueView<Allele> physiqueAlleleTwo() {
		return _gameState.valueForKey(GameState.Key.dragonPhysiqueAlleleTwoKeyForId(_id)).map(this._stringToAlleleMapper);
	}

	/**
	 * Get the first lung size allele for this dragon.
	 * @return The dragon's first lung size allele.
	 */
	public ValueView<Allele> lungSizeAlleleOne() {
		return _gameState.valueForKey(GameState.Key.dragonLungSizeAlleleOneKeyForId(_id)).map(this._stringToAlleleMapper);
	}
	
	/**
	 * Get the second lung size allele for this dragon.
	 * @return The dragon's second lung size allele.
	 */
	public ValueView<Allele> lungSizeAlleleTwo() {
		return _gameState.valueForKey(GameState.Key.dragonLungSizeAlleleTwoKeyForId(_id)).map(this._stringToAlleleMapper);
	}

	/**
	 * Get the first heart size allele for this dragon.
	 * @return The dragon's first heart size allele.
	 */
	public ValueView<Allele> heartSizeAlleleOne() {
		return _gameState.valueForKey(GameState.Key.dragonHeartSizeAlleleOneKeyForId(_id)).map(this._stringToAlleleMapper);
	}
	
	/**
	 * Get the second heart size allele for this dragon.
	 * @return The dragon's second heart size allele.
	 */
	public ValueView<Allele> heartSizeAlleleTwo() {
		return _gameState.valueForKey(GameState.Key.dragonHeartSizeAlleleTwoKeyForId(_id)).map(this._stringToAlleleMapper);
	}
	
	// Phenotype's are lazily loaded as needed.

	private Value<Phenotype> _legLengthPhenotype = null;
	
	/**
	 * @return The leg length phenotype for this dragon, computed from it's
	 * genotype.
	 */
	public ValueView<Phenotype> legLength() {
		if (_legLengthPhenotype == null) {
			final LegLength legLegthGene = new LegLength();
			final ValueView<Allele> alleleOne = legLengthAlleleOne();
			final ValueView<Allele> alleleTwo = legLengthAlleleTwo();
			
			_legLengthPhenotype = Value.create(legLegthGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
			
			Slot<Allele> alleleChangedSlot = new Slot<Allele>() {
				@Override
				public void onEmit(Allele allele) {
					_legLengthPhenotype.update(legLegthGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
				}
			};
			alleleOne.connect(alleleChangedSlot);
			alleleTwo.connect(alleleChangedSlot);
		}
		return _legLengthPhenotype;
	}
	
	private Value<Phenotype> _feetTypePhenotype = null;
	
	/**
	 * @return The feet type phenotype of this dragon, computed from it's
	 * genotype.
	 */
	public ValueView<Phenotype> feetType() {
		if (_feetTypePhenotype == null) {
			final FeetType feetTypeGene = new FeetType();
			final ValueView<Allele> alleleOne = feetTypeAlleleOne();
			final ValueView<Allele> alleleTwo = feetTypeAlleleTwo();
			
			_feetTypePhenotype = Value.create(feetTypeGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
			
			Slot<Allele> alleleChangedSlot = new Slot<Allele>() {
				@Override
				public void onEmit(Allele allele) {
					_feetTypePhenotype.update(feetTypeGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
				}
			};
			alleleOne.connect(alleleChangedSlot);
			alleleTwo.connect(alleleChangedSlot);
		}
		return _feetTypePhenotype;
	}
	
	private Value<Phenotype> _coatTypePhenotype = null;
	
	/**
	 * @return The coat type phenotype of this dragon, computed from it's
	 * genotype.
	 */
	public ValueView<Phenotype> coatType() {
		if (_coatTypePhenotype == null) {
			final CoatType coatTypeGene = new CoatType();
			final ValueView<Allele> alleleOne = coatTypeAlleleOne();
			final ValueView<Allele> alleleTwo = coatTypeAlleleTwo();
			
			_coatTypePhenotype = Value.create(coatTypeGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
			
			Slot<Allele> alleleChangedSlot = new Slot<Allele>() {
				@Override
				public void onEmit(Allele allele) {
					_coatTypePhenotype.update(coatTypeGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
				}
			};
			alleleOne.connect(alleleChangedSlot);
			alleleTwo.connect(alleleChangedSlot);
		}
		return _coatTypePhenotype;
	}
	
	private Value<Phenotype> _wingSizePhenotype = null;
	
	/**
	 * @return The wing size phenotype of this dragon, computed from it's
	 * genotype.
	 */
	public ValueView<Phenotype> wingSize() {
		if (_wingSizePhenotype == null) {
			final WingSize wingSizeGene = new WingSize();
			final ValueView<Allele> alleleOne = wingSizeAlleleOne();
			final ValueView<Allele> alleleTwo = wingSizeAlleleTwo();
			
			_wingSizePhenotype = Value.create(wingSizeGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
			
			Slot<Allele> alleleChangedSlot = new Slot<Allele>() {
				@Override
				public void onEmit(Allele allele) {
					_wingSizePhenotype.update(wingSizeGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
				}
			};
			alleleOne.connect(alleleChangedSlot);
			alleleTwo.connect(alleleChangedSlot);
		}
		return _wingSizePhenotype;
	}
	
	private Value<Phenotype> _tailLengthPhenotype = null;
	
	/**
	 * @return The tail length phenotype of this dragon, computed from it's
	 * genotype.
	 */
	public ValueView<Phenotype> tailLength() {
		if (_tailLengthPhenotype == null) {
			final TailLength tailLengthGene = new TailLength();
			final ValueView<Allele> alleleOne = tailLengthAlleleOne();
			final ValueView<Allele> alleleTwo = tailLengthAlleleTwo();
			
			_tailLengthPhenotype = Value.create(tailLengthGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
			
			Slot<Allele> alleleChangedSlot = new Slot<Allele>() {
				@Override
				public void onEmit(Allele allele) {
					_tailLengthPhenotype.update(tailLengthGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
				}
			};
			alleleOne.connect(alleleChangedSlot);
			alleleTwo.connect(alleleChangedSlot);
		}
		return _tailLengthPhenotype;
	}
	
	private Value<Phenotype> _physiquePhenotype = null;
	
	/**
	 * @return The physique phenotype of this dragon, computed from it's
	 * genotype.
	 */
	public ValueView<Phenotype> physique() {
		if (_physiquePhenotype == null) {
			final Physique physiqueGene = new Physique();
			final ValueView<Allele> alleleOne = physiqueAlleleOne();
			final ValueView<Allele> alleleTwo = physiqueAlleleTwo();
			
			_physiquePhenotype = Value.create(physiqueGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
			
			Slot<Allele> alleleChangedSlot = new Slot<Allele>() {
				@Override
				public void onEmit(Allele allele) {
					_physiquePhenotype.update(physiqueGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
				}
			};
			alleleOne.connect(alleleChangedSlot);
			alleleTwo.connect(alleleChangedSlot);
		}
		return _physiquePhenotype;
	}
	
	private Value<Phenotype> _lungSizePhenotype = null;
	
	/**
	 * @return The lung size phenotype of this dragon, computed from it's
	 * genotype.
	 */
	public ValueView<Phenotype> lungSize() {
		if (_lungSizePhenotype == null) {
			final LungSize lungSizeGene = new LungSize();
			final ValueView<Allele> alleleOne = lungSizeAlleleOne();
			final ValueView<Allele> alleleTwo = lungSizeAlleleTwo();
			
			_lungSizePhenotype = Value.create(lungSizeGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
			
			Slot<Allele> alleleChangedSlot = new Slot<Allele>() {
				@Override
				public void onEmit(Allele allele) {
					_lungSizePhenotype.update(lungSizeGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
				}
			};
			alleleOne.connect(alleleChangedSlot);
			alleleTwo.connect(alleleChangedSlot);
		}
		return _lungSizePhenotype;
	}
	
	private Value<Phenotype> _heartSizePhenotype = null;
	
	/**
	 * @return The heart size phenotype of this dragon, computed from it's
	 * genotype.
	 */
	public ValueView<Phenotype> heartSize() {
		if (_heartSizePhenotype == null) {
			final HeartSize heartSizeGene = new HeartSize();
			final ValueView<Allele> alleleOne = heartSizeAlleleOne();
			final ValueView<Allele> alleleTwo = heartSizeAlleleTwo();
			
			_heartSizePhenotype = Value.create(heartSizeGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
			
			Slot<Allele> alleleChangedSlot = new Slot<Allele>() {
				@Override
				public void onEmit(Allele allele) {
					_heartSizePhenotype.update(heartSizeGene.getPhenotypeForGenotype(alleleOne.get(), alleleTwo.get()));
				}
			};
			alleleOne.connect(alleleChangedSlot);
			alleleTwo.connect(alleleChangedSlot);
		}
		return _heartSizePhenotype;
	}
}
