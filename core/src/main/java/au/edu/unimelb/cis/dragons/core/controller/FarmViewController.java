package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.ExpandingRowsTableLayout;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Wallet;
import au.edu.unimelb.cis.dragons.core.view.PenView;
import react.Function;
import react.SignalView;
import react.Slot;
import react.UnitSlot;
import react.ValueView.Listener;
import tripleplay.ui.Background;
import tripleplay.ui.Group;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.TableLayout;

/**
 * ViewController for the farm screen.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class FarmViewController extends ContainerViewController {
	
	private static class PenPosition {

		private final int _column;
		private final int _row;
		
		public PenPosition(int column, int row) {
			_column = column;
			_row = row;
		}
		
		public int getColumn() {
			return _column;
		}
		
		public int getRow() {
			return _row;
		}
	}

	/**
	 * Represents an individual pen within the farm view.
	 * Logic is managed by the FarmViewController, this only manages updating the view on demand.
	 */
	private class FarmPenViewController extends ViewController {

		// Farm that is backing this pen view.
		private Farm _farm;

		// Id of this pen within the farm.
		private int _penIdX;
		private int _penIdY;

		private PenView _view;

		/**
		 * Create a new PenViewController
		 * @param farm The farm that backs this PenViewController
		 * @param penIdX Column location of this pen within the farm.
		 * @param penIdY Row location of this pen within the farm.
		 */
		public FarmPenViewController(Farm farm, int penIdX, int penIdY) {
			_farm = farm;
			_penIdX = penIdX;
			_penIdY = penIdY;
			_view = new PenView();
		}
		 
		@Override
		protected Group createInterface() {
			// Wire interface.

			_farm.stateForPen(_penIdX, _penIdY).connectNotify(_view.penState().slot());

			// Show the dragon name label as needed.
			// Make sure the dragon state listener is always listening to the correct dragon.
			_farm.dragonForPen(_penIdX, _penIdY).connectNotify(new Listener<Dragon>() {
				@Override
				public void onChange(Dragon value, Dragon oldValue) {
					if (oldValue != null) {
						oldValue.state().disconnect(_view.dragonState().slot());
					}
					if (value != null) {
						value.state().connectNotify(_view.dragonState().slot());
						value.name().connectNotify(_view.dragonName().slot());
					}
				}
			});

			return _view.view();
		}

		@Override
		public String title() {
			return "Pen";
		}

		public SignalView<Group> clicked() {
			return _view.clicked();
		}
	}

	// Size of the grid representing the farm.
	private int NUM_COLUMNS = Farm.NUM_COLUMNS;
	private int NUM_ROWS = Farm.NUM_ROWS;

	private int ROW_GAP = 0;
	private int COL_GAP = 0;

	// The farm that is backing this view.
	private Farm _farm;

	// The wallet that is backing the view.
	private Wallet _wallet;

	// The GameState that's used to create new dragons in the buy view.
	private GameState _gameState;
	
	// The listener that is called when a dragon is selected by the user.
	// If null, a default of opening the dragon detail view is used.
	private Listener<PenPosition> _dragonSelectionListener;
	
	/**
	 * Create a new FarmViewController.
	 * 
	 * This constructor allows the specification of a custom action to use when a dragon is 
	 * selected.
	 * 
	 * @param farm The farm that backs this view.
	 * @param wallet The wallet that backs this view.
	 * @param gameState
	 * @param dragonSelectionListener The listener that is called when a dragon is selected.
	 */
	public FarmViewController(Farm farm, Wallet wallet, GameState gameState, Slot<PenPosition> dragonSelectionListener) {
		_dragonSelectionListener = dragonSelectionListener;
		_farm = farm;
		_wallet = wallet;
		_gameState = gameState;
	}
	
	/**
	 * Create a new FarmVewController.
	 * 
	 * @param farm
	 * @param wallet
	 * @param gameState
	 */
	public FarmViewController(Farm farm, Wallet wallet, GameState gameState) {
		this(farm, wallet, gameState, null);
	}

	@Override
	public String title() {
		return "Farm";
	}

	@Override
	protected  Group createInterface() {
		SizableGroup view = new SizableGroup(AxisLayout.vertical());

		SizableGroup pensView = new SizableGroup(new ExpandingRowsTableLayout(TableLayout.COL.stretch().free(1).copy(NUM_COLUMNS)).gaps(ROW_GAP, COL_GAP).fillHeight());
		pensView.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFF000000))));

		// Create a controller for every pen and add it to this view as a sub view controller.
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLUMNS; j++) {

				final int currentRow = i;
				final int currentColumn = j;

				final FarmPenViewController child = new FarmPenViewController(_farm, i, j);
				addSubViewController(child);
				child.view().setConstraint(AxisLayout.stretched());

				// Add a listener for click events on the pen to allow purchasing.
				child.clicked().connect(new UnitSlot() {
					@Override
					public void onEmit() {
						switch(_farm.stateForPen(currentRow, currentColumn).get()) {
						case Locked:
							parentScreen().presentViewController(new ClosableModalViewController(new BuyPenViewController(_farm, currentColumn, currentRow, _wallet)));
							break;
						case Full:
							if (_dragonSelectionListener == null) {
								parentScreen().presentViewController(new ClosableModalViewController(new DragonDetailViewController(_gameState, _farm, _wallet, currentColumn, currentRow)));
							} else {
								_dragonSelectionListener.onChange(new PenPosition(currentColumn, currentRow), null);
							}
							break;
						case Empty:
							parentScreen().presentViewController(new ClosableModalViewController(new BuyDragonViewController(_gameState, _farm, _wallet, currentColumn, currentRow)));
							break;
						}
					}
				});

				pensView.add(child.view());
			}
		}
		view.add(pensView);

		Function<Integer, String> integerToStringMapper = new Function<Integer, String>() {
			@Override
			public String apply(Integer input) {
				return input.toString();
			}
		};

		TopBarEntryViewController score = new TopBarEntryViewController("Total Farm Score");
		_farm.score().map(integerToStringMapper).connectNotify(score.valueLabel().text.slot());
		view.add(score.view());

		return view;
	}
}
