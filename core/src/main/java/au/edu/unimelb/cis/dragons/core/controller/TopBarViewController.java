package au.edu.unimelb.cis.dragons.core.controller;

import tripleplay.ui.Background;
import tripleplay.ui.Group;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.BorderLayout;

/**
 * A ViewController that handles the display of one view as a top bar, with
 * another in the main content area.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class TopBarViewController extends ViewController {

	// The height in PX for the top bar.
	private static int TOP_BAR_HEIGHT = 30;
	
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
		Group parent = new Group(new BorderLayout(0),
			Style.BACKGROUND.is(Background.solid(0xFF00FF00)));
		parent.setConstraint(AxisLayout.stretched());
		
		// Main content window.
		Group contentView = new Group(AxisLayout.vertical().offStretch());
		contentView.add(_contentAreaViewController.view().setConstraint(
			AxisLayout.stretched()));
		parent.add(contentView.setConstraint(BorderLayout.CENTER));
		
		// Top Bar.
		SizableGroup topBarView = new SizableGroup(AxisLayout.horizontal(), 
			parentScreen().width(), TOP_BAR_HEIGHT);
		topBarView.add(_topBarViewController.view().setConstraint(
			AxisLayout.stretched()));
		parent.add(topBarView.setConstraint(BorderLayout.NORTH));

		
		return parent;
	}
	
}
