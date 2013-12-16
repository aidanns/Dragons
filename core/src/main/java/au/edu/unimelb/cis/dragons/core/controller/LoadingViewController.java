package au.edu.unimelb.cis.dragons.core.controller;

import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.MigGroup;
import tripleplay.ui.MigLayout;

/**
 * A ViewController that displays a loading screen while assets from the game
 * are being retrieved.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class LoadingViewController extends ViewController {

	public LoadingViewController() {
		
	}

	@Override
	public String title() {
		return "Loading";
	}

	@Override
	protected Group createInterface() {
		MigGroup parent = new MigGroup(new MigLayout("fill, insets 0", "0[]0", "0[]0"));
		parent.add(new Label("Loading..."));
		return parent;
	}
}
