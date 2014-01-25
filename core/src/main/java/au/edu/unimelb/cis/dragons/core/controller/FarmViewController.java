package au.edu.unimelb.cis.dragons.core.controller;

import static playn.core.PlayN.assets;
import au.edu.unimelb.cis.dragons.core.ExpandingRowsTableLayout;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Dragon.DragonState;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Farm.PenState;
import au.edu.unimelb.cis.dragons.core.model.Wallet;
import au.edu.unimelb.cis.dragons.core.view.View;
import react.Function;
import react.SignalView;
import react.UnitSlot;
import react.Value;
import react.ValueView.Listener;
import tripleplay.ui.Background;
import tripleplay.ui.Group;
import tripleplay.ui.Icons;
import tripleplay.ui.Label;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.layout.AbsoluteLayout;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.TableLayout;

/**
 * ViewController for the farm screen.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class FarmViewController extends ContainerViewController {

	private class FarmPenView extends View {

		// Absolute positioning data for the pen view.
		private int PEN_X_OFFSET = 30;
		private int PEN_Y_OFFSET = 30;
		private int PEN_WIDTH = 100;
		private int PEN_HEIGHT = 100;

		// Label displaying the name of the dragon in the pen.
		private Label _dragonNameLabel = new Label();

		// Labels that hold images used to display the state of the pen and it's current contents.
		private Label _penBorder;
		private Label _penLockedText;
		private Label _penEmptyText;
		private Label _penDragonText;
		private Label _penDragonStateAvailableText;
		private Label _penDragonStateBreedingText;
		private Label _penDragonStateRacingText;
		private Label _penDragonStateTrainingText;

		private Value<DragonState> _dragonState = Value.create(DragonState.Available);;
		private Value<PenState> _penState = Value.create(PenState.Empty);

		public Value<String> dragonName() {
			return _dragonNameLabel.text;
		}

		public Value<DragonState> dragonState() {
			return _dragonState;
		}

		public Value<PenState> penState() {
			return _penState;
		}

		@Override
		protected Group createInterface() {
			// Load up all the images for the pen.
			_penBorder = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen.png")));
			_penLockedText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_locked.png")));
			_penEmptyText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_empty.png")));
			_penDragonText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_dragon.png")));
			_penDragonStateAvailableText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_dragon_status_available.png")));
			_penDragonStateBreedingText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_dragon_status_breeding.png")));
			_penDragonStateRacingText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_dragon_status_racing.png")));
			_penDragonStateTrainingText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_dragon_status_training.png")));

			Group group = new Group(new AbsoluteLayout());
			group.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFFFFFFFF))));

			// Add all the labels in their final position and initially hide them.
			group.add(AbsoluteLayout.at(_penBorder, PEN_X_OFFSET, PEN_Y_OFFSET, PEN_WIDTH, PEN_HEIGHT));
			group.add(AbsoluteLayout.at(_penLockedText, PEN_X_OFFSET, PEN_Y_OFFSET, PEN_WIDTH, PEN_HEIGHT));
			group.add(AbsoluteLayout.at(_penEmptyText, PEN_X_OFFSET, PEN_Y_OFFSET, PEN_WIDTH, PEN_HEIGHT));
			group.add(AbsoluteLayout.at(_penDragonText, PEN_X_OFFSET, PEN_Y_OFFSET, PEN_WIDTH, PEN_HEIGHT));
			group.add(AbsoluteLayout.at(_penDragonStateAvailableText, PEN_X_OFFSET, PEN_Y_OFFSET, PEN_WIDTH, PEN_HEIGHT));
			group.add(AbsoluteLayout.at(_penDragonStateBreedingText, PEN_X_OFFSET, PEN_Y_OFFSET, PEN_WIDTH, PEN_HEIGHT));
			group.add(AbsoluteLayout.at(_penDragonStateRacingText, PEN_X_OFFSET, PEN_Y_OFFSET, PEN_WIDTH, PEN_HEIGHT));
			group.add(AbsoluteLayout.at(_penDragonStateTrainingText, PEN_X_OFFSET, PEN_Y_OFFSET, PEN_WIDTH, PEN_HEIGHT));
			group.add(AbsoluteLayout.at(_dragonNameLabel, PEN_X_OFFSET, PEN_Y_OFFSET + 5, PEN_WIDTH, 40));

			resetPenStateText();
			resetDragonStateText();

			// Hook up listeners from the state of the view to the view itself.
			_dragonState.connectNotify(new Listener<DragonState>() {
				@Override
				public void onChange(DragonState value, DragonState oldValue) {
					switch (value) {
					case Available:
						resetDragonStateText();
						_penDragonStateAvailableText.setVisible(true);
						break;
					case Breeding:
						resetDragonStateText();
						_penDragonStateBreedingText.setVisible(true);
						break;
					case Racing:
						_penDragonStateRacingText.setVisible(true);
						resetDragonStateText();
						break;
					case Training:
						resetDragonStateText();
						_penDragonStateTrainingText.setVisible(true);
						break;
					default:
						break;
					}
				}
			});

			_penState.connectNotify(new Listener<PenState>() {
				@Override
				public void onChange(PenState value, PenState oldValue) {
					switch(value) {
					case Empty:
						resetPenStateText();
						_penEmptyText.setVisible(true);
						break;
					case Full:
						resetPenStateText();
						_penDragonText.setVisible(true);
						break;
					case Locked:
						resetPenStateText();
						_penLockedText.setVisible(true);
						break;
					default:
						break;
					}
				}
			});

			return group;
		}

		/** Make all pen state labels invisible. */
		private void resetPenStateText() {
			_penLockedText.setVisible(false);
			_penEmptyText.setVisible(false);
			_penDragonText.setVisible(false);
		}

		/** Make all dragon state labels invisible. */
		private void resetDragonStateText() {
			_penDragonStateAvailableText.setVisible(false);
			_penDragonStateBreedingText.setVisible(false);
			_penDragonStateRacingText.setVisible(false);
			_penDragonStateTrainingText.setVisible(false);
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

		private FarmPenView _view;

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
			_view = new FarmPenView();
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
	private int NUM_COLUMNS = 4;
	private int NUM_ROWS = 2;

	private int ROW_GAP = 0;
	private int COL_GAP = 0;

	// The farm that is backing this view.
	private Farm _farm;

	// The wallet that is backing the view.
	private Wallet _wallet;

	/**
	 * Create a new FarmViewController.
	 * @param farm The farm that backs this view.
	 * @param wallet The wallet that backs this view.
	 */
	public FarmViewController(Farm farm, Wallet wallet) {
		_farm = farm;
		_wallet = wallet;
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
							parentScreen().presentViewController(new ClosableModalViewController(new DragonDetailViewController(_farm.dragonForPen(currentRow, currentColumn).get())));
							break;
						case Empty:
							parentScreen().presentViewController(new ClosableModalViewController(new BuyDragonViewController()));
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
