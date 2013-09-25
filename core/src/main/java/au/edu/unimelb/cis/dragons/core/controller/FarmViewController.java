package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.ExpandingRowsTableLayout;
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
	 */
	private class PenViewController extends ViewController {
		
		@Override
		public String title() {
			return "Pen";
		}
		
		@Override
		protected Group createInterface() {
			SizableGroup group = new SizableGroup(new FlowLayout());
			group.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFF0000FF))));
			group.setConstraint(AxisLayout.stretched());
			
			Label l = new Label("Pen");
			group.add(l);
			return group;
		}
	}
	
	private int NUM_COLUMNS = 4;
	private int NUM_ROWS = 2;
	
	private int ROW_GAP = 5;
	private int COL_GAP = 5;

	@Override
	public String title() {
		return "Farm";
	}
	
	@Override
	protected  Group createInterface() {

		SizableGroup _pens = new SizableGroup(new ExpandingRowsTableLayout(TableLayout.COL.stretch().free(1).copy(NUM_COLUMNS)).gaps(ROW_GAP, COL_GAP).fillHeight());
		_pens.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFF000000))));
		
		for (int i = 0; i < NUM_COLUMNS * NUM_ROWS; i++) {
			ViewController child = new PenViewController();
			addSubViewController(child);
			child.view().setConstraint(AxisLayout.stretched());
			_pens.add(child.view());
		}
		return _pens;
	}

}
