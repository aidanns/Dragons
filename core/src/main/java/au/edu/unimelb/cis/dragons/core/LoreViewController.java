package au.edu.unimelb.cis.dragons.core;

import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.FlowLayout;

/**
 * ViewController for the Lore screen.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class LoreViewController extends ViewController {

	@Override
	public String title() {
		return "Lore";
	}

	@Override
	protected Group createInterface() {
		SizableGroup group = new SizableGroup(new FlowLayout());
		group.setConstraint(AxisLayout.stretched());
		group.preferredSize.update(100, 100);
		group.add(new Label("This is the dragon lore screen"));
		return group;
	}
	
}
