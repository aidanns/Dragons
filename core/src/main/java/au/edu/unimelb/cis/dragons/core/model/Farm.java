package au.edu.unimelb.cis.dragons.core.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import react.Function;
import react.UnitSlot;
import react.ValueView;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.MultipleSourceValueViewBuilder;
import au.edu.unimelb.cis.dragons.core.RebuildableMultipleSourceValueViewBuilder;
import au.edu.unimelb.cis.dragons.core.model.Dragon.DragonState;

/**
 * Representation of the farm. Wraps the GameState to give simplified access to
 * state related to the farm.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class Farm {

	/**
	 * Represents the different possible states for a pen within the farm.
	 */
	public enum PenState {

		/** The pen has not yet been unlocked. */
		Locked("locked"),

		/** The pen is unlocked, but no dragon is in it. */
		Empty("empty"),

		/** The pen is unlocked and has a dragon in it. */
		Full("full");

		// Map from the state name back to the state, to make it easy to pass
		// back the correct PenState object after retrieving it's name from the
		// GameState.
		private static Map<String, PenState> stateNameToPenState = new HashMap<String, PenState>();

		// Create the Map from the state names back to the state objects.
		static {
			for (PenState penState : PenState.values()) {
				stateNameToPenState.put(penState._stateName, penState);
			}
		}

		/**
		 * The name for this state, which will be stored as a value in the
		 * GameState.
		 */
		private String _stateName;

		PenState(String stateName) {
			_stateName = stateName;
		}

		public String toString() {
			return _stateName;
		}

		/**
		 * Get the PenState for a given state name, which is returned from the
		 * GameState.
		 * @param stateName The name of the state, retrieved from the GameState.
		 * @return The PenState enumeration object that represents this state.
		 */
		/* package */ static PenState penStateForStateName(String stateName) {
			return stateNameToPenState.get(stateName);
		}
	}

	// The underlying game state.
	private GameState _gameState;

	/**
	 * Create a new FarmModel, wrapping the supplied GameState.
	 * @param gameState The GameState that this FarmModel will give access to.
	 */
	public Farm(GameState gameState) {
		_gameState = gameState;
	}

	/**
	 * Get the PenState for the pen at a certain index.
	 * @param row The row index.
	 * @param column The column index.
	 * @return The PenState.
	 */
	public ValueView<PenState> stateForPen(Integer row, Integer column) {
		return _gameState.valueForKey(GameState.Key.penStateKeyAtIndex(row, column)).map(new Function<String, PenState>() {
			@Override
			public PenState apply(String input) {
				return PenState.penStateForStateName(input);
			}
		});
	}

	/**
	 * Set a given pen to be close, removing any dragon that was previous in it.
	 * @param row The row of the pen.
	 * @param column The column of the pen.
	 */
	public void lockPen(Integer row, Integer column) {
		_gameState.valueForKey(GameState.Key.penStateKeyAtIndex(row, column)).update(PenState.Locked.toString());
		_gameState.valueForKey(GameState.Key.penDragonIdKeyAtIndex(row, column)).update("");
	}

	/**
	 * Set a given pen to be open, removing any dragon that was previously in it.
	 * @param row The row of the pen.
	 * @param column The column of the pen.
	 */
	public void openAndClearPen(Integer row, Integer column) {
		_gameState.valueForKey(GameState.Key.penStateKeyAtIndex(row, column)).update(PenState.Empty.toString());
		_gameState.valueForKey(GameState.Key.penDragonIdKeyAtIndex(row, column)).update("");
	}

	/**
	 * Get the Dragon for the pen at a certain index.
	 * @param row The row index.
	 * @param column The column index.
	 * @return The Dragon.
	 */
	public ValueView<Dragon> dragonForPen(Integer row, Integer column) {
		return _gameState.valueForKey(GameState.Key.penDragonIdKeyAtIndex(row, column)).map(new Function<String, Dragon>() {
			@Override
			public Dragon apply(String input) {
				if (input.equals("")) { return null; }
				else { return new Dragon(_gameState, Integer.valueOf(input)); }
			}
		});
	}

	/**
	 * Set the Dragon that inhabits a certain pen, opening it if needed.
	 * @param dragon The dragon that should be in the pen.
	 * @param row The row of the pen.
	 * @param column The column of the pen.
	 */
	public void setDragonForPen(Dragon dragon, Integer row, Integer column) {
		_gameState.valueForKey(GameState.Key.penStateKeyAtIndex(row, column)).update(PenState.Full.toString());
		_gameState.valueForKey(GameState.Key.penDragonIdKeyAtIndex(row, column)).update(dragon.id().toString());
	}

	/**
	 * @return Number of dragons currently in the farm.
	 */
	public ValueView<Integer> numberOfDragons() {
		 MultipleSourceValueViewBuilder<PenState, Integer> viewBuilder = new MultipleSourceValueViewBuilder<PenState, Integer>(new Function<List<ValueView<PenState>>, Integer>() {
			@Override
			public Integer apply(List<ValueView<PenState>> input) {
				Integer i = 0;
				for (ValueView<PenState> state : input) {
					if (state.get() == PenState.Full) {
						++i;
					}
				}
				return i;
			}
		}, penStates());
		return viewBuilder.valueView();
	}

	private MultipleSourceValueViewBuilder<DragonState, Integer> _dragonsAvailableBuilder = null;
	private MultipleSourceValueViewBuilder<DragonState, Integer> _dragonsRacingBuilder = null;
	private MultipleSourceValueViewBuilder<DragonState, Integer> _dragonsBreedingBuilder = null;
	private MultipleSourceValueViewBuilder<DragonState, Integer> _dragonsTrainingBuilder = null;

	/**
	 * @return Number of dragons currently available in the farm.
	 */
	public ValueView<Integer> numberOfDragonsAvailable() {
		if (_dragonsAvailableBuilder == null) {
			createDragonStateViews();
		}
		return _dragonsAvailableBuilder.valueView();
	}
	
	/**
	 * @return Number of dragons currently racing in the farm.
	 */
	public ValueView<Integer> numberOfDragonsRacing() {
		if (_dragonsRacingBuilder == null) {
			createDragonStateViews();
		}
		return _dragonsRacingBuilder.valueView();
	}
	
	/**
	 * @return Number of dragons currently breeding in the farm.
	 */
	public ValueView<Integer> numberOfDragonsBreeding() {
		if (_dragonsBreedingBuilder == null) {
			createDragonStateViews();
		}
		return _dragonsBreedingBuilder.valueView();
	}
	
	/**
	 * @return Number of dragons currently training in the farm.
	 */
	public ValueView<Integer> numberOfDragonsTraining() {
		if (_dragonsTrainingBuilder == null) {
			createDragonStateViews();
		}
		return _dragonsTrainingBuilder.valueView();
	}

	private void createDragonStateViews() {
		// Calculate dragons available by summing all dragons that have the state available.
		_dragonsAvailableBuilder = new MultipleSourceValueViewBuilder<DragonState, Integer>(new Function<List<ValueView<DragonState>>, Integer>() {
			@Override
			public Integer apply(List<ValueView<DragonState>> input) {
				Integer i = 0;
				for (ValueView<DragonState> dragonState : input) {
					if (dragonState.get() == DragonState.Available) {
						++i;
					}
				}
				return i;
			}
		});

		// Calculate dragons racing by summing all dragons that have the state racing.
		_dragonsRacingBuilder = new MultipleSourceValueViewBuilder<DragonState, Integer>(new Function<List<ValueView<DragonState>>, Integer>() {
			@Override
			public Integer apply(List<ValueView<DragonState>> input) {
				Integer i = 0;
				for (ValueView<DragonState> dragonState : input) {
					if (dragonState.get() == DragonState.Racing) {
						++i;
					}
				}
				return i;
			}
		});

		// Calculate dragons breeding by summing all dragons that have the state racing.
		_dragonsBreedingBuilder = new MultipleSourceValueViewBuilder<DragonState, Integer>(new Function<List<ValueView<DragonState>>, Integer>() {
			@Override
			public Integer apply(List<ValueView<DragonState>> input) {
				Integer i = 0;
				for (ValueView<DragonState> dragonState : input) {
					if (dragonState.get() == DragonState.Breeding) {
						++i;
					}
				}
				return i;
			}
		});

		_dragonsTrainingBuilder = new MultipleSourceValueViewBuilder<DragonState, Integer>(new Function<List<ValueView<DragonState>>, Integer>() {
			@Override
			public Integer apply(List<ValueView<DragonState>> input) {
				Integer i = 0;
				for (ValueView<DragonState> dragonState : input) {
					if (dragonState.get() == DragonState.Training) {
						++i;
					}
				}
				return i;
			}
		});

		// Need two levels of mapping because the number of available dragons needs
		// to be updated when either a dragon's state changes, or a dragon changes.
		// When the dragon changes, we also need to update with the extra dragon state
		// that we now have access to.
		final UnitSlot rebuildDragonStateBuilderSources = new UnitSlot() {
			@Override
			public void onEmit() {
				_dragonsAvailableBuilder.clearSources();
				_dragonsRacingBuilder.clearSources();
				_dragonsBreedingBuilder.clearSources();
				_dragonsTrainingBuilder.clearSources();
				for (ValueView<Dragon> dragon : activeDragons()) {
					_dragonsAvailableBuilder.addSource(dragon.get().state());
					_dragonsRacingBuilder.addSource(dragon.get().state());
					_dragonsBreedingBuilder.addSource(dragon.get().state());
					_dragonsTrainingBuilder.addSource(dragon.get().state());
				}
			}
		};
		for (ValueView<Dragon> dragon : dragons()) {
			dragon.connect(rebuildDragonStateBuilderSources);
		}
		
		rebuildDragonStateBuilderSources.onEmit();
	}
	
	/**
	 * @return A List of all dragons in the farm. Some may contain null.
	 */
	private List<ValueView<Dragon>> dragons() {
		List<ValueView<Dragon>> dragons = Lists.newArrayList();
		for (int col = 0; col < 4; ++col) {
			for (int row = 0; row < 2; ++row) {
				dragons.add(dragonForPen(row, col));
			}
		}
		return dragons;
	}
	
	private List<ValueView<Dragon>> activeDragons() {
		return Lists.newArrayList(Iterables.filter(dragons(), new Predicate<ValueView<Dragon>>() {
			@Override
			public boolean apply(ValueView<Dragon> input) {
				return input.get() != null;
			}
		}));
	}
	
	/**
	 * @return A list of all pen states for the farm.
	 */
	private List<ValueView<PenState>> penStates() {
		List<ValueView<PenState>> penStates = Lists.newArrayList();
		for (int col = 0; col < 4; ++col) {
			for (int row = 0; row < 2; ++row) {
				penStates.add(stateForPen(row, col));
			}
		}
		return penStates;
	}
	
	/**
	 * @return Sum of the score of all dragons in the farm.
	 */
	public ValueView<Integer> score() {
		return new RebuildableMultipleSourceValueViewBuilder<Integer, Integer>(
				new Function<List<ValueView<Integer>>, Integer>() {
					@Override
					public Integer apply(List<ValueView<Integer>> input) {
						Integer sum = 0;
						for (ValueView<Integer> i : input) {
							sum += i.get();
						}
						return sum;
					}
				}, new Function<Void, List<ValueView<Integer>>>() {
					@Override
					public List<ValueView<Integer>> apply(Void _) {
						List<ValueView<Integer>> dragonScores = Lists.newArrayList();
						for (int col = 0; col < 4; ++col) {
							for (int row = 0; row < 2; ++row) {
								Dragon dragon = dragonForPen(row, col).get();
								if (dragon != null) {
									dragonScores.add(dragon.score());
								}
							}
						}
						return dragonScores;
					}
				}, dragons()).valueView();
	}
	
}
