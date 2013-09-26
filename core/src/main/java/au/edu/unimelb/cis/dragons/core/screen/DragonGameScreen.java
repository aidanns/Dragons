package au.edu.unimelb.cis.dragons.core.screen;

import au.edu.unimelb.cis.dragons.core.controller.ViewController;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.Background;
import tripleplay.ui.Root;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AbsoluteLayout;

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
	@SuppressWarnings("unused")
	private ScreenStack _stack;
	
	// The ViewController that is being presented on screen, if any. Null otherwise.
	private ViewController _modalChild;
	
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
//		_root = iface.createRoot(AxisLayout.vertical().gap(0).offStretch(),
		_root = iface.createRoot(new AbsoluteLayout(),
			SimpleStyles.newSheet(), layer);
		_root.addStyles(Style.BACKGROUND.is(
			Background.solid(0xFFFFFFFF)), Style.VALIGN.top);
		_root.setSize(width(), height());
//		_root.add(_viewController.view().setConstraint(AxisLayout.stretched()));
		_root.add(AbsoluteLayout.at(_viewController.view(), 0, 0, width(), height()));
		_viewController.wasAdded();
	}

	@Override
	public void wasShown() {
		super.wasShown();
		_viewController.wasShown();
	}

	@Override
	public void wasHidden() {
		_viewController.wasHidden();
		super.wasHidden();
	}

	@Override
	public void wasRemoved() {
//		_viewController.wasRemoved();
		iface.destroyRoot(_root);
		_root = null;
		super.wasRemoved();
	}
	
	/**
	 * Modally present a ViewController on the screen. Will dismiss any previously presented controllers.
	 * @param modal The ViewController to present.
	 */
	public void presentViewController(ViewController modal) {
		if (_modalChild != null) {
			dismissViewController();
		}
		_modalChild = modal;
		_root.add(AbsoluteLayout.at(_modalChild.view(), 0, 0, width(), height()));
		_modalChild.wasShown();
	}
	
	/**
	 * Dismisses any ViewController's currently being displayed modally.
	 */
	public void dismissViewController() {
		if (_modalChild != null) {
			_root.remove(_modalChild.view());
			_root.pack();
			_modalChild.wasRemoved();
			_modalChild = null;
		}
	}
	
	/**
	 * Return the current modal child of this screen, or null.
	 * @return The ViewController being presented modally, or null if none is.
	 */
	public ViewController modalChild() {
		return _modalChild;
	}
}
