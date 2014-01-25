package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.ExpandingRowsTableLayout;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import react.UnitSlot;
import react.ValueView;
import tripleplay.ui.Background;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.TableLayout;

/**
 * ViewController to allow the player to purchase a new dragon for an empty pen.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class BuyDragonViewController extends ViewController {
	
	private class BuyDragonPenViewController extends ViewController {
		
		private ValueView<Dragon> _dragon;
		
		
		
	}
	
	// Size of the grid representing the available dragons.
	private int NUM_COLUMNS = 3;
	private int NUM_ROWS = 1;

	private int ROW_GAP = 0;
	private int COL_GAP = 0;
	
	// Farm that purchased dragons will be placed in to.
	private Farm _farm;
	
	// Id of the pen that we're buying for.
	private int _penIdX;
	private int _penIdY;

	@Override
	protected Group createInterface() {
		Group group = new Group(AxisLayout.vertical());
		group.add(new Label("Player can purchase dragons here."));
		
//		SizableGroup pensView = new SizableGroup(new ExpandingRowsTableLayout(TableLayout.COL.stretch().free(1).copy(NUM_COLUMNS)).gaps(ROW_GAP, COL_GAP).fillHeight());
//		pensView.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFF000000))));
//		
//		// Create a controller for every pen and add it to this view as a sub view controller.
//		for (int i = 0; i < NUM_ROWS; i++) {
//			for (int j = 0; j < NUM_COLUMNS; j++) {
//
//				final int currentRow = i;
//				final int currentColumn = j;
//
//				final PenViewController child = new PenViewController(_farm, i, j);
//				addSubViewController(child);
//				child.view().setConstraint(AxisLayout.stretched());
//
//				// Add a listener for click events on the pen to allow viewing of the dragon.
//				child.clicked().connect(new UnitSlot() {
//					@Override
//					public void onEmit() {
//						parentScreen().presentViewController(new ClosableModalViewController(new DragonDetailViewController(_farm.dragonForPen(currentRow, currentColumn).get())));
//					}
//				});
//				
//				pensView.add(child.view());
//			}
//		}
//		group.add(pensView);
		
		return group;
	}
}
