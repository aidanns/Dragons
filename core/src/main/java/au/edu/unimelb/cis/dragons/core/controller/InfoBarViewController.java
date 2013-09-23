package au.edu.unimelb.cis.dragons.core.controller;

import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.FlowLayout;

/**
 * ViewController for the info bar displayed at the top of the screen.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class InfoBarViewController extends ViewController {

	@Override
	public String title() {
		return "Info Bar";
	}
	
	@Override
	protected Group createInterface() {
		SizableGroup group = new SizableGroup(new FlowLayout());
		group.setConstraint(AxisLayout.stretched());
		group.preferredSize.update(100,100);
		group.add(new Label("This is the top info bar"));
		return group;
	}
	
}
