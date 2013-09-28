package au.edu.unimelb.cis.dragons.core.controller;

import tripleplay.ui.Group;
import tripleplay.ui.MigGroup;
import tripleplay.ui.MigLayout;

/**
 * A ViewController that handles the display of one view as a top bar, with
 * another in the main content area.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class TopBarViewController extends ViewController {

	// The ViewController for the top bar.
	private ViewController _topBarViewController;
	
	// The ViewController for the main content area.
	private ViewController _contentAreaViewController;
	
	public TopBarViewController(ViewController topBarViewController,
		ViewController contentAreaViewController) {
		_topBarViewController = topBarViewController;
		_contentAreaViewController = contentAreaViewController;
		
		addSubViewController(_topBarViewController);
		addSubViewController(_contentAreaViewController);
	}

	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.ViewController#title()
	 */
	@Override
	public String title() {
		return _contentAreaViewController.title();
	}

	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.ViewController#createInterface()
	 */
	@Override
	protected Group createInterface() {
		MigGroup parent = new MigGroup(new MigLayout("fill, insets 0", "0[]0", "0[10%!]0[90%!]0"));
		parent.add(_topBarViewController.view(), "cell 0 0, grow"); // Top bar.
		parent.add(_contentAreaViewController.view(), "cell 0 1, grow"); // Main content.
		return parent;
	}
}
