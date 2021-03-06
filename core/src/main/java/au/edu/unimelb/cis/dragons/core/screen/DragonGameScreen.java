package au.edu.unimelb.cis.dragons.core.screen;

import java.util.ArrayList;
import java.util.List;

import au.edu.unimelb.cis.dragons.core.controller.ViewController;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIAnimScreen;
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
public class DragonGameScreen extends UIAnimScreen {

	/** The root that this screen is presenting */
	private Root _root;
	
	/** The ViewController that this screen is presenting */
	private ViewController _viewController;
	
	/** The stack that this screen is on. */
	@SuppressWarnings("unused")
	private ScreenStack _stack;
	
	// The ViewController that is being presented on screen, if any. Null otherwise.
	private List<ViewController> _modalChildren = new ArrayList<ViewController>();
	
	// Number of pixels to inset the modally displayed view from the edge of the screen.
	private static int MODAL_INSET_DISTANCE = 0;
	
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
		_root = iface.createRoot(new AbsoluteLayout(),
			SimpleStyles.newSheet(), layer);
		_root.addStyles(Style.BACKGROUND.is(
			Background.solid(0xFFFFFFFF)), Style.VALIGN.top);
		_root.setSize(width(), height());
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
//		iface.destroyRoot(_root);
		_root = null;
		super.wasRemoved();
	}
	
	/**
	 * Modally present a ViewController on the screen. Will dismiss any previously presented controllers.
	 * @param modal The ViewController to present.
	 */
	public void presentViewController(ViewController modal) {
		_modalChildren.add(modal);
		modal.setParentScreen(this);
		_root.add(AbsoluteLayout.at(modal.view(), MODAL_INSET_DISTANCE, MODAL_INSET_DISTANCE, width() - 2 * MODAL_INSET_DISTANCE, height() - 2 * MODAL_INSET_DISTANCE));
		modal.wasShown();
	}
	
	/**
	 * Attempt to dismiss a given ViewController (or it's root ViewController
	 * if it has a parent).
	 */
	public void dismissViewController(ViewController modal) {
		if (modal != null) {
			// Find the root of the ViewController heirarchy for this potential modal.
			ViewController controllerToDismiss = modal;
			while (controllerToDismiss.parentViewController() != null) {
				controllerToDismiss = controllerToDismiss.parentViewController();
			}
			// If it's actually a modal child, then dismiss it.
			if (_modalChildren.contains(controllerToDismiss)) {
				_root.remove(controllerToDismiss.view());
				_root.pack();
				_modalChildren.remove(controllerToDismiss);
				controllerToDismiss.wasRemoved();
				controllerToDismiss.setParentScreen(null);
			}
		}
	}
}
