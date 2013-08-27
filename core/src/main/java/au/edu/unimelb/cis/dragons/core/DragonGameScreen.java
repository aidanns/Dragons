package au.edu.unimelb.cis.dragons.core;

import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.Background;
import tripleplay.ui.Root;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.Style;
import tripleplay.ui.Stylesheet;
import tripleplay.ui.layout.AxisLayout;

/**
 * Base class for all Screens. Presents a single ViewController.
 * 
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class DragonGameScreen extends UIScreen {

	/** The root that this screen is presenting */
	private Root _root;
	
	/** The ViewController that this screen is presenting */
	private ViewController _viewController;
	
	/** The stack that this screen is on. */
	private ScreenStack _stack;
	
	/**
	 * Create a new ScreenController.
	 * 
	 * @param controller The ViewController that will be presented.
	 */
	public DragonGameScreen(ScreenStack stack, ViewController controller) {
		_viewController = controller;
		_viewController.setParentScreen(this);
		_stack = stack;
	}
	
	@Override
	public void wasAdded() {
		super.wasAdded();
		// Create a root and add the view to it.
		_root = iface.createRoot(AxisLayout.vertical().gap(0).offStretch(), 
			SimpleStyles.newSheet(), layer);
		_root.addStyles(Style.BACKGROUND.is(
			Background.solid(0xFFFFFFFF)), Style.VALIGN.top);
		_root.setSize(width(), height());
		_root.add(_viewController.view().setConstraint(AxisLayout.stretched()));
		_viewController.wasAdded();
	}

	@Override
	public void wasShown() {
		super.wasShown();
		_viewController.wasShown();
	}

	@Override
	public void wasHidden() {
		super.wasHidden();
		_viewController.wasHidden();
	}

	@Override
	public void wasRemoved() {
		super.wasRemoved();
		_viewController.wasRemoved();
	}
	
}
