package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.ExpandingRowsTableLayout;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Farm.PenState;
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
		
		@Override
		public String title() {
			return "Pen";
		}
		
		@Override
		protected Group createInterface() {
			SizableGroup group = new SizableGroup(new FlowLayout());
			group.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFF0000FF))));
			group.setConstraint(AxisLayout.stretched());

			group.add(new Label("Pen State:"));
			group.add(_penStateLabel);
			group.add(new Label("Dragon Name:"));
			group.add(_dragonNameLabel);
			
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
	}
	
	private int NUM_COLUMNS = 4;
	private int NUM_ROWS = 2;
	
	private int ROW_GAP = 5;
	private int COL_GAP = 5;
	
	// The model that is backing this view.
	private Farm _farm;
	
	public FarmViewController(Farm farm) {
		_farm = farm;
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

				// The dragons name should be updated if either the dragon, or it's name changes.
				ValueView<Dragon> dragon = _farm.dragonForPen(i, j); 
				dragon.connectNotify(new Listener<Dragon>() {
					@Override
					public void onChange(Dragon value, Dragon oldValue) {
						if (oldValue != null) {
							oldValue.name().disconnect(child.dragonNameLabel().text.slot());
						}
						if (value != null) {
							value.name().connectNotify(child.dragonNameLabel().text.slot());
						}
					}
				});
				
				_pens.add(child.view());
			}
		}
		
		return _pens;
	}

}
