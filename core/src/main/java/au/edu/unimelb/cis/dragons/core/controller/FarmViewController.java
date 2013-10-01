package au.edu.unimelb.cis.dragons.core.controller;

import playn.core.Pointer;
import playn.core.Pointer.Event;
import au.edu.unimelb.cis.dragons.core.Alert;
import au.edu.unimelb.cis.dragons.core.ExpandingRowsTableLayout;
import au.edu.unimelb.cis.dragons.core.HittableGroup;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Dragon.DragonState;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Farm.PenState;
import au.edu.unimelb.cis.dragons.core.model.Wallet;
import react.Function;
import react.ValueView;
import react.ValueView.Listener;
import tripleplay.ui.Background;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.FlowLayout;
import tripleplay.ui.layout.TableLayout;

/**
 * ViewController for the farm screen.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class FarmViewController extends ContainerViewController {
	
	/**
	 * Represents an individual pen within the farm view.
	 * Logic is managed by the FarmViewController, this only manages updating the view on demand.
	 */
	private class PenViewController extends ViewController {
		
		// Label displaying the pen state.
		private Label _penStateLabel = new Label();
		
		// Label displaying the name of the dragon in the pen.
		private Label _dragonNameLabel = new Label();
		
		// Label displaying the state of the dragon in the pen.
		private Label _dragonStateLabel = new Label();
		
		@Override
		public String title() {
			return "Pen";
		}
		
		@Override
		protected HittableGroup createInterface() {
			HittableGroup group = new HittableGroup(new FlowLayout());
			group.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFF0000FF))));
			group.setConstraint(AxisLayout.stretched());

			group.add(_penStateLabel);
			group.add(_dragonNameLabel);
			group.add(_dragonStateLabel);
			
			return group;
		}
		
		/**
		 * Get the label that displays the state of the pen.
		 * @return The label.
		 */
		public Label penStateLabel() {
			return _penStateLabel;
		}
		
		/**
		 * Get the label that displays the dragon's name.
		 * @return The label.
		 */
		public Label dragonNameLabel() {
			return _dragonNameLabel;
		}
		
		/**
		 * Get the label that displays the dragon's state.
		 * @return The label.
		 */
		public Label dragonStateLabel() {
			return _dragonStateLabel;
		}
		
		/**
		 * Set whether the dragon related labels should be visible.
		 * @param visible Whether the labels should be visible.
		 */
		public void setDragonLabelsVisible(boolean visible) {
			_dragonNameLabel.setVisible(visible);
			_dragonStateLabel.setVisible(visible);
		}
		
		/**
		 * Set whether the pen state label should be visible.
		 * @param visible Whether the label should be visible.
		 */
		public void setPenStateLabelVisible(boolean visible) {
			_penStateLabel.setVisible(visible);
		}
	}
	
	private int NUM_COLUMNS = 4;
	private int NUM_ROWS = 2;
	
	private int ROW_GAP = 5;
	private int COL_GAP = 5;
	
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
		SizableGroup _pens = new SizableGroup(new ExpandingRowsTableLayout(TableLayout.COL.stretch().free(1).copy(NUM_COLUMNS)).gaps(ROW_GAP, COL_GAP).fillHeight());
		_pens.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFF000000))));
		
		// Create a controller for every pen and add it to this view as a sub view controller.
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLUMNS; j++) {
				
				final int currentRow = i;
				final int currentColumn = j;
				
				final PenViewController child = new PenViewController();
				addSubViewController(child);
				child.view().setConstraint(AxisLayout.stretched());
				
				// The pen label should always read the current state of that pen.
				_farm.stateForPen(i, j).map(new Function<PenState, String>() {
					@Override
					public String apply(PenState input) {
						return input.toString();
					}
				}).connectNotify(child.penStateLabel().text.slot());
				
				final Listener<DragonState> dragonStateChangeListener = new Listener<DragonState>() {
					@Override
					public void onChange(DragonState value, DragonState oldValue) {
						child.dragonStateLabel().text.update(value.toString());
					}
				};

				// The dragons name should be updated if either the dragon, or it's name changes.
				ValueView<Dragon> dragon = _farm.dragonForPen(i, j); 
				dragon.connectNotify(new Listener<Dragon>() {
					@Override
					public void onChange(Dragon value, Dragon oldValue) {
						if (oldValue != null) {
							oldValue.name().disconnect(child.dragonNameLabel().text.slot());
							oldValue.state().disconnect(dragonStateChangeListener);
						}
						
						if (value != null) {
							value.name().connectNotify(child.dragonNameLabel().text.slot());
							value.state().connectNotify(dragonStateChangeListener);
						}
						
						// If the dragon is there, show it's labels and hide the pen label.
						child.setDragonLabelsVisible(value != null);
						child.setPenStateLabelVisible(value == null);
					}
				});
				
				// Add a listener for click events on the pen to allow purchasing.
				child.view().layer.addListener(new Pointer.Adapter() {
					@Override
					public void onPointerEnd(Event event) {
						switch(_farm.stateForPen(currentRow, currentColumn).get()) {
						case Locked:
							if (_wallet.gold().get() >= 50) {
								_farm.openAndClearPen(currentRow, currentColumn);
								_wallet.subtractGold(50);
							} else {
								new Alert("Unlocking a pen costs 50 gold. You do not have enough.").displayOnScreen(parentScreen());;
							}
							break;
						case Full:
							parentScreen().presentViewController(new ClosableModalViewController(new DragonDetailViewController(_farm.dragonForPen(currentRow, currentColumn).get())));
							break;
						case Empty:
							break;
						}
					}
				});
				
				_pens.add(child.view());
			}
		}
		
		return _pens;
	}

}
