package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.ExpandingRowsTableLayout;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Farm.PenState;
import react.Value;
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
		
		// Label being displayed in this view.
		private Label _label;
		
		@Override
		public String title() {
			return "Pen";
		}
		
		@Override
		protected Group createInterface() {
			SizableGroup group = new SizableGroup(new FlowLayout());
			group.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFF0000FF))));
			group.setConstraint(AxisLayout.stretched());
			
			_label= new Label();
			group.add(_label);
			return group;
		}
		
		/**
		 * Update the text in the label displayed by this pen.
		 * @param labelText The new label text.
		 */
		public void updateLabel(String labelText) {
			_label.text.update(labelText);
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
				Value<PenState> penState = _farm.stateForPen(i, j);
				child.updateLabel(penState.get().toString());
				penState.connect(new Listener<PenState>() {
					@Override
					public void onChange(PenState value, PenState oldValue) {
						child.updateLabel(value.toString());
					}
				});
				
				_pens.add(child.view());
			}
		}
		
		return _pens;
	}

}
