package au.edu.unimelb.cis.dragons.core.controller;

import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.layout.AxisLayout;

/**
 * ViewController to allow the player to purchase a new dragon for an empty pen.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class BuyDragonViewController extends ViewController {

	@Override
	protected Group createInterface() {
		Group group = new Group(AxisLayout.horizontal());
		group.add(new Label("Player can purchase dragons here."));
		return group;
	}
}
