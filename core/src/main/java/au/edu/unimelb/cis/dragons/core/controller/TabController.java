package au.edu.unimelb.cis.dragons.core.controller;

import java.util.ArrayList;
import java.util.List;

import react.UnitSlot;
import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.BorderLayout;

/**
 * A view controller that supports multiple tabs.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class TabController extends ContainerViewController {
	
	// The height in PX for the tab bar at the bottom of this view.
	private static int TAB_BAR_HEIGHT = 100;

	// ViewController's that can have their views displayed in the by this
	// view.
	private List<ViewController> _viewControllers = 
		new ArrayList<ViewController>();
	
	// The ViewController that is currently being displayed.
	private ViewController _currentViewController;
	
	// The container view for whichever view is currently being dispalyed.
	private Group _contentView;
	
	/**
	 * @param viewControllers ViewControllers that will be displayed by the
	 * TabViewController.
	 */
	public TabController(List<ViewController> viewControllers) {
		_viewControllers.addAll(viewControllers);
		for (final ViewController controller : _viewControllers) {
			addChildViewController(controller);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.ViewController#createInterface()
	 */
	@Override
	protected Group createInterface() {
		
		Group parent = new Group(new BorderLayout(0));
		parent.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFF000000))));
		parent.setConstraint(AxisLayout.stretched());
		
		// Main content window.
		_contentView = new SizableGroup(AxisLayout.vertical().offStretch().stretchByDefault());
		_contentView.setConstraint(BorderLayout.CENTER);
		_contentView.setStyles(
	    	Style.BACKGROUND.is(Background.solid(0xFFFFFFFF)));
		parent.add(_contentView);
		
		// Bottom bar with tab switching buttons.
		SizableGroup bottomGroup = new SizableGroup(AxisLayout.horizontal());
		bottomGroup.setConstraint(BorderLayout.SOUTH);
		bottomGroup.setStyles(
				Style.BACKGROUND.is(Background.solid(0xFFCCCCCC)));
		bottomGroup.preferredSize.update(parentScreen().width(), 
			TAB_BAR_HEIGHT);
		parent.add(bottomGroup);
		
		// Add buttons to the bar for each of the available views.
		for (final ViewController childViewController : _viewControllers) {
			bottomGroup.add(new Button(childViewController.title()).onClick(
				new UnitSlot() {
					@Override
					public void onEmit() {
						setCurrentViewController(childViewController);
					}
				}));
		}
		
		// Put the first view controller in to the main content window.
		setCurrentViewController(_viewControllers.get(0));
		
		return parent;
	}

	/**
	 * Sets the view controller that is currently being displayed in the
	 * content view of the TabViewController.
	 * @param viewController The view controller to display.
	 */
	private void setCurrentViewController(ViewController viewController) {
		if (viewController != _currentViewController) {
			ViewController oldViewController = _currentViewController;
			removeSubViewController(_currentViewController);
			_currentViewController = viewController;
			addSubViewController(_currentViewController);
			_contentView.removeAll();
			_contentView.add(_currentViewController.view());
			if (currentlyOnScreen()) {
				oldViewController.wasHidden();
				_currentViewController.wasShown();
			}	
		}
	}

	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.ViewController#title()
	 */
	@Override
	public String title() {
		return _currentViewController.title();
	}
	
	

}
