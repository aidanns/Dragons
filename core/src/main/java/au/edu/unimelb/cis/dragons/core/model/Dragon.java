package au.edu.unimelb.cis.dragons.core.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import react.Function;
import react.Value;
import react.ValueView;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.MultipleSourceValueViewBuilder;
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
     * Create a new dragon in the specified GameState.
     * @param gameState The GameState to create the dragon in.
     * @param name The name of the dragon to create.
     * @return
     */
    public static Dragon create(GameState gameState, String name) {
    	return create(gameState, name, Allele.ShortLegs, Allele.LongLegs,
    			Allele.ClawedFeet, Allele.WebbedFeet, Allele.FurryCoat, Allele.ScaledCoat,
    			Allele.SmallWings, Allele.LargeWings, Allele.LongTail, Allele.ShortTail,
    			Allele.MuscularPhysique, Allele.LeanPhysique, Allele.AdditiveLungs, Allele.PlainLungs,
    			Allele.AdditiveHeart, Allele.PlainHeart);
    }

    /**
     * Create a dragon with a specific genetic makeup in the specified GameState.
     * @param gameState
     * @param name
     * @param legLengthAlleleOne
     * @param legLengthAlleleTwo
     * @param feetTypeAlleleOne
     * @param feetTypeAlleleTwo
     * @param coatTypeAlleleOne
     * @param coatTypeAlleleTwo
     * @param wingSizeAlleleOne
     * @param wingSizeAlleleTwo
     * @param tailLengthAlleleOne
     * @param tailLengthAlleleTwo
     * @param physiqueAlleleOne
     * @param physiqueAlleleTwo
     * @param lungSizeAlleleOne
     * @param lungSizeAlleleTwo
     * @param heartSizeAlleleOne
     * @param heartSizeAlleleTwo
     * @return
     */
    public static Dragon create(GameState gameState, String name,
    		Allele legLengthAlleleOne, Allele legLengthAlleleTwo,
    		Allele feetTypeAlleleOne, Allele feetTypeAlleleTwo,
            Allele coatTypeAlleleOne, Allele coatTypeAlleleTwo,
            Allele wingSizeAlleleOne, Allele wingSizeAlleleTwo,
            Allele tailLengthAlleleOne, Allele tailLengthAlleleTwo,
            Allele physiqueAlleleOne, Allele physiqueAlleleTwo,
            Allele lungSizeAlleleOne, Allele lungSizeAlleleTwo,
            Allele heartSizeAlleleOne, Allele heartSizeAlleleTwo) {
    	int id = gameState.nextDragonId();
        Dragon dragon = new Dragon(gameState, id);
        dragon.makeAvailable();
        
        gameState.valueForKey(GameState.Key.dragonNameKeyForId(id)).update(name);
        gameState.valueForKey(GameState.Key.dragonScoreKeyForId(id)).update("0");


        gameState.valueForKey(GameState.Key.dragonLegLengthAlleleOneKeyForId(id)).update(legLengthAlleleOne.toString());
        gameState.valueForKey(GameState.Key.dragonLegLengthAlleleTwoKeyForId(id)).update(legLengthAlleleTwo.toString());
        gameState.valueForKey(GameState.Key.dragonFeetTypeAlleleOneKeyForId(id)).update(feetTypeAlleleOne.toString());
        gameState.valueForKey(GameState.Key.dragonFeetTypeAlleleTwoKeyForId(id)).update(feetTypeAlleleTwo.toString());
        gameState.valueForKey(GameState.Key.dragonCoatTypeAlleleOneKeyForId(id)).update(coatTypeAlleleOne.toString());
        gameState.valueForKey(GameState.Key.dragonCoatTypeAlleleTwoKeyForId(id)).update(coatTypeAlleleTwo.toString());
        gameState.valueForKey(GameState.Key.dragonWingSizeAlleleOneKeyForId(id)).update(wingSizeAlleleOne.toString());
        gameState.valueForKey(GameState.Key.dragonWingSizeAlleleTwoKeyForId(id)).update(wingSizeAlleleTwo.toString());
        gameState.valueForKey(GameState.Key.dragonTailLengthAlleleOneKeyForId(id)).update(tailLengthAlleleOne.toString());
        gameState.valueForKey(GameState.Key.dragonTailLengthAlleleTwoKeyForId(id)).update(tailLengthAlleleTwo.toString());
        gameState.valueForKey(GameState.Key.dragonPhysiqueAlleleOneKeyForId(id)).update(physiqueAlleleOne.toString());
        gameState.valueForKey(GameState.Key.dragonPhysiqueAlleleTwoKeyForId(id)).update(physiqueAlleleTwo.toString());
        gameState.valueForKey(GameState.Key.dragonLungSizeAlleleOneKeyForId(id)).update(lungSizeAlleleOne.toString());
        gameState.valueForKey(GameState.Key.dragonLungSizeAlleleTwoKeyForId(id)).update(lungSizeAlleleTwo.toString());
        gameState.valueForKey(GameState.Key.dragonHeartSizeAlleleOneKeyForId(id)).update(heartSizeAlleleOne.toString());
        gameState.valueForKey(GameState.Key.dragonHeartSizeAlleleTwoKeyForId(id)).update(heartSizeAlleleTwo.toString());

        gameState.valueForKey(GameState.Key.dragonSpeedAttributeTrainingRemainingRacesKey(id)).update("0");
        gameState.valueForKey(GameState.Key.dragonEnduranceAttributeTrainingRemainingRacesKey(id)).update("0");
        gameState.valueForKey(GameState.Key.dragonBalanceAttributeTrainingRemainingRacesKey(id)).update("0");
        gameState.valueForKey(GameState.Key.dragonWeightAttributeTrainingRemainingRacesKey(id)).update("0");

        return dragon;
    }
    
    /**
     * Retrieve a specific dragon already present in the GameState and create 
     * an instance of it.
     * @param gameState
     * @param id
     * @return
     */
    public static Dragon retrieveWithId(GameState gameState, Integer id) {
    	return new Dragon(gameState, id);
    }

    /**
     * Create a new instance of a dragon, backed by a particular GameState.
     * @param gameState The game state to store this dragon in.
     * @param id The id to store this dragon at.
     */
    private Dragon(GameState gameState, Integer id) {
        this(gameState, id, new LegLength(), new FeetType(), new CoatType(),
                new WingSize(), new TailLength(), new Physique(),
                new LungSize(), new HeartSize());
    }

    // These are used to convert between allele and phenotype for each gene.
    private final LegLength _legLength;
    private final FeetType _feetType;
    private final CoatType _coatType;
    private final WingSize _wingSize;
    private final TailLength _tailLength;
    private final Physique _physique;
    private final LungSize _lungSize;
    private final HeartSize _heartSize;

    // Designated constructor.
    /* package */ Dragon(GameState gameState, Integer id, LegLength legLength,
            FeetType feetType, CoatType coatType, WingSize wingSize,
            TailLength tailLength, Physique physique, LungSize lungSize,
            HeartSize heartSize) {
        _gameState = gameState;
        _id = id;
        _legLength = legLength;
        _feetType = feetType;
        _coatType = coatType;
        _wingSize = wingSize;
        _tailLength = tailLength;
        _physique = physique;
        _lungSize = lungSize;
        _heartSize = heartSize;
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
     * Mark the dragon as off training.
     */
	public void sendToTrain() {
		_gameState.valueForKey(GameState.Key.dragonStateKeyForId(_id)).update(DragonState.Training.toString());
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

    // Genotype.

    // For us in all the Allele retrieving functions.
    private Function<String, Allele> _stringToAlleleMapper = new Function<String, Allele>() {
        @Override
        public Allele apply(String input) {
            return Allele.alleleForAlleleName(input);
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

    // Phenotype.

    /**
     * @return The leg length phenotype for this dragon, computed from it's
     * genotype.
     */
    @SuppressWarnings("unchecked")
    public ValueView<Phenotype> legLength() {
        return new MultipleSourceValueViewBuilder<Allele, Phenotype>(
                new Function<List<ValueView<Allele>>, Phenotype>() {
                    @Override
                    public Phenotype apply(List<ValueView<Allele>> input) {
                        return _legLength.getPhenotypeForGenotype(input.get(0).get(), input.get(1).get());
                    }
                }, Lists.newArrayList(legLengthAlleleOne(), legLengthAlleleTwo())).valueView();
    }

    /**
     * @return The feet type phenotype of this dragon, computed from it's
     * genotype.
     */
    @SuppressWarnings("unchecked")
    public ValueView<Phenotype> feetType() {
        return new MultipleSourceValueViewBuilder<Allele, Phenotype>(
                new Function<List<ValueView<Allele>>, Phenotype>() {
                    @Override
                    public Phenotype apply(List<ValueView<Allele>> input) {
                        return _feetType.getPhenotypeForGenotype(input.get(0).get(), input.get(1).get());
                    }
                }, Lists.newArrayList(feetTypeAlleleOne(), feetTypeAlleleTwo())).valueView();
    }

    /**
     * @return The coat type phenotype of this dragon, computed from it's
     * genotype.
     */
    @SuppressWarnings("unchecked")
    public ValueView<Phenotype> coatType() {
        return new MultipleSourceValueViewBuilder<Allele, Phenotype>(
                new Function<List<ValueView<Allele>>, Phenotype>() {
                    @Override
                    public Phenotype apply(List<ValueView<Allele>> input) {
                        return _coatType.getPhenotypeForGenotype(input.get(0).get(), input.get(1).get());
                    }
                }, Lists.newArrayList(coatTypeAlleleOne(), coatTypeAlleleTwo())).valueView();
    }

    /**
     * @return The wing size phenotype of this dragon, computed from it's
     * genotype.
     */
    @SuppressWarnings("unchecked")
    public ValueView<Phenotype> wingSize() {
        return new MultipleSourceValueViewBuilder<Allele, Phenotype>(
                new Function<List<ValueView<Allele>>, Phenotype>() {
                    @Override
                    public Phenotype apply(List<ValueView<Allele>> input) {
                        return _wingSize.getPhenotypeForGenotype(input.get(0).get(), input.get(1).get());
                    }
                }, Lists.newArrayList(wingSizeAlleleOne(), wingSizeAlleleTwo())).valueView();
    }

    /**
     * @return The tail length phenotype of this dragon, computed from it's
     * genotype.
     */
    @SuppressWarnings("unchecked")
    public ValueView<Phenotype> tailLength() {
        return new MultipleSourceValueViewBuilder<Allele, Phenotype>(
                new Function<List<ValueView<Allele>>, Phenotype>() {
                    @Override
                    public Phenotype apply(List<ValueView<Allele>> input) {
                        return _tailLength.getPhenotypeForGenotype(input.get(0).get(), input.get(1).get());
                    }
                }, Lists.newArrayList(tailLengthAlleleOne(), tailLengthAlleleTwo())).valueView();
    }

    /**
     * @return The physique phenotype of this dragon, computed from it's
     * genotype.
     */
    @SuppressWarnings("unchecked")
    public ValueView<Phenotype> physique() {
        return new MultipleSourceValueViewBuilder<Allele, Phenotype>(
                new Function<List<ValueView<Allele>>, Phenotype>() {
                    @Override
                    public Phenotype apply(List<ValueView<Allele>> input) {
                        return _physique.getPhenotypeForGenotype(input.get(0).get(), input.get(1).get());
                    }
                }, Lists.newArrayList(physiqueAlleleOne(), physiqueAlleleTwo())).valueView();
    }

    /**
     * @return The lung size phenotype of this dragon, computed from it's
     * genotype.
     */
    @SuppressWarnings("unchecked")
    public ValueView<Phenotype> lungSize() {
        return new MultipleSourceValueViewBuilder<Allele, Phenotype>(
                new Function<List<ValueView<Allele>>, Phenotype>() {
                    @Override
                    public Phenotype apply(List<ValueView<Allele>> input) {
                        return _lungSize.getPhenotypeForGenotype(input.get(0).get(), input.get(1).get());
                    }
                }, Lists.newArrayList(lungSizeAlleleOne(), lungSizeAlleleTwo())).valueView();
    }

    /**
     * @return The heart size phenotype of this dragon, computed from it's
     * genotype.
     */
    @SuppressWarnings("unchecked")
    public ValueView<Phenotype> heartSize() {
        return new MultipleSourceValueViewBuilder<Allele, Phenotype>(
                new Function<List<ValueView<Allele>>, Phenotype>() {
                    @Override
                    public Phenotype apply(List<ValueView<Allele>> input) {
                        return _heartSize.getPhenotypeForGenotype(input.get(0).get(), input.get(1).get());
                    }
                }, Lists.newArrayList(heartSizeAlleleOne(), heartSizeAlleleTwo())).valueView();
    }

    // Attributes.

    /**
     * Map from a phenotype to an attribute bonus. Used when calculating the
     * attributes for each dragon.
     */
    private static class AttributeBonusMapper implements Function<Phenotype, Integer> {

    	/** Map from phenotypes that this mapper knows about to a bonus for each one. */
    	private final Map<Phenotype, Integer> _valueMap;

    	public AttributeBonusMapper() {
    		_valueMap = Maps.newHashMap();
    	}

    	/**
    	 * Add a phenotype to give a bonus for to the mapper.
    	 * @param targetPhenotype The phenotype to check for.
    	 * @param bonus The bonus to give for that phenotype.
    	 * @return The mapper, for chaining.
    	 */
    	public AttributeBonusMapper add(Phenotype targetPhenotype, Integer bonus) {
    		_valueMap.put(targetPhenotype, bonus);
    		return this;
    	}

		@Override
		public Integer apply(Phenotype input) {
			if (_valueMap.containsKey(input)) {
				return _valueMap.get(input);
			} else {
				return 0;
			}
		}

    }

    /**
     * Maps between the number of races remaining before training loses
     * effectiveness and the bonus for that attribute.
     *
     * Bonus of 2 for any training that is still active (>0 races remaining).
     */
    private Function<String, Integer> _trainingRacesRemainingToBonusMapper = new Function<String, Integer>() {
    	@Override
    	public Integer apply(String input) {
    		if (Integer.valueOf(input).intValue() > 0) {
    			return 2;
    		} else {
    			return 0;
    		}
    	}
    };

    /**
     * @return The base speed for a dragon.
     */
    private ValueView<Integer> speedBase() {
    	return Value.create(4);
    }

    /** @return Speed bonus from having long legs */
    private ValueView<Integer> speedBonusLegs() {
    	return legLength().map(new AttributeBonusMapper().add(Phenotype.LongLegs, 2));
    }

    /** @return Speed bonus from having a lean physique */
    private ValueView<Integer> speedBonusPhysique() {
    	return physique().map(new AttributeBonusMapper().add(Phenotype.LeanPhysique, 2));
    }

    /** @return Speed bonus from training */
    private ValueView<Integer> speedBonusTraining() {
    	return _gameState.valueForKey(GameState.Key.dragonSpeedAttributeTrainingRemainingRacesKey(_id)).map(_trainingRacesRemainingToBonusMapper);
    }

    /**
     * @return The dragon's computed speed attribute.
     */
    @SuppressWarnings("unchecked")
	public ValueView<Integer> speed() {
    	return new MultipleSourceValueViewBuilder<Integer, Integer>(
    			new Function<List<ValueView<Integer>>, Integer>() {
    				@Override
    				public Integer apply(List<ValueView<Integer>> input) {
    					int sum = 0;
    					for (ValueView<Integer> view : input) {
    						sum += view.get();
    					}
    					return sum;
    				}
    			}, Lists.newArrayList(speedBase(), speedBonusLegs(), speedBonusPhysique(), speedBonusTraining())).valueView();
    }

    /** @return Base endurance, before bonuses. */
    private ValueView<Integer> enduranceBase() {
    	return Value.create(4);
    }

    /** @return Endurance bonuses from heart size */
    private ValueView<Integer> enduranceBonusHeart() {
    	return heartSize().map(new AttributeBonusMapper().add(Phenotype.LargeHeart, 2).add(Phenotype.MediumHeart, 1));
    }

    /** @return Endurance bonuses from lung size */
    private ValueView<Integer> enduranceBonusLungs() {
    	return lungSize().map(new AttributeBonusMapper().add(Phenotype.LargeLungs, 2).add(Phenotype.MediumLungs, 1));
    }

    /** @return Endurance bonuses from training */
    private ValueView<Integer> enduranceBonusTraining() {
    	return _gameState.valueForKey(GameState.Key.dragonEnduranceAttributeTrainingRemainingRacesKey(_id)).map(_trainingRacesRemainingToBonusMapper);
    }

    /** @return The dragon's computed endurance attribute. */
    @SuppressWarnings("unchecked")
	public ValueView<Integer> endurance() {
    	return new MultipleSourceValueViewBuilder<Integer, Integer>(
    			new Function<List<ValueView<Integer>>, Integer>() {
    				@Override
    				public Integer apply(List<ValueView<Integer>> input) {
    					int sum = 0;
    					for (ValueView<Integer> view : input) {
    						sum += view.get();
    					}
    					return sum;
    				}
    			}, Lists.newArrayList(enduranceBase(), enduranceBonusHeart(), enduranceBonusLungs(), enduranceBonusTraining())).valueView();
    }

    /** The dragon's base balance. */
    private ValueView<Integer> balanceBase() {
    	return Value.create(4);
    }

    /** @return Balance bonus from large wings. */
    private ValueView<Integer> balanceBonusWings() {
    	return wingSize().map(new AttributeBonusMapper().add(Phenotype.LargeWings, 2));
    }

    /** @return Balance bonus from long tail */
    private ValueView<Integer> balanceBonusTail() {
    	return tailLength().map(new AttributeBonusMapper().add(Phenotype.LongTail, 2));
    }

    /** @return Balance bonus from training. */
    private ValueView<Integer> balanceBonusTraining() {
    	return _gameState.valueForKey(GameState.Key.dragonBalanceAttributeTrainingRemainingRacesKey(_id)).map(_trainingRacesRemainingToBonusMapper);
    }

    /** @return The dragon's computed balance. */
    @SuppressWarnings("unchecked")
	public ValueView<Integer> balance() {
    	return new MultipleSourceValueViewBuilder<Integer, Integer>(
    			new Function<List<ValueView<Integer>>, Integer>() {
    				@Override
    				public Integer apply(List<ValueView<Integer>> input) {
    					int sum = 0;
    					for (ValueView<Integer> view : input) {
    						sum += view.get();
    					}
    					return sum;
    				}
    			}, Lists.newArrayList(balanceBase(), balanceBonusWings(), balanceBonusTail(), balanceBonusTraining())).valueView();
    }

    /** @return Dragon's base weight. */
    private ValueView<Integer> weightBase() {
    	return Value.create(4);
    }

    /** @return Bonus weight from furry coat. */
    private ValueView<Integer> weightBonusCoat() {
    	return coatType().map(new AttributeBonusMapper().add(Phenotype.FurryCoat, 2));
    }

    /** @return Bonus weight from muscular physique. */
    private ValueView<Integer> weightBonusPhysique() {
    	return physique().map(new AttributeBonusMapper().add(Phenotype.MuscularPhysique, 2));
    }

    /** @return Bonus weight from training. */
    private ValueView<Integer> weightBonusTraining() {
    	return _gameState.valueForKey(GameState.Key.dragonWeightAttributeTrainingRemainingRacesKey(_id)).map(_trainingRacesRemainingToBonusMapper);
    }

    /** @return The dragon's computed weight. */
    @SuppressWarnings("unchecked")
	public ValueView<Integer> weight() {
    	return new MultipleSourceValueViewBuilder<Integer, Integer>(
    			new Function<List<ValueView<Integer>>, Integer>() {
    				@Override
    				public Integer apply(List<ValueView<Integer>> input) {
    					int sum = 0;
    					for (ValueView<Integer> view : input) {
    						sum += view.get();
    					}
    					return sum;
    				}
    			}, Lists.newArrayList(weightBase(), weightBonusCoat(), weightBonusPhysique(), weightBonusTraining())).valueView();
    }
    
    /**
     * Increment the score of this dragon.
     * @param value The value to increase by.
     * @return The dragon itself, for method chaining.
     */
    public Dragon incrementScore(Integer value) {
    	Value<String> score = _gameState.valueForKey(GameState.Key.dragonScoreKeyForId(_id));
    	score.update(Integer.toString(Integer.parseInt(score.get()) + value));
    	return this;
    }
}
