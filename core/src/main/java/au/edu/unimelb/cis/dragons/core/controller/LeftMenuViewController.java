package au.edu.unimelb.cis.dragons.core.controller;

import java.util.HashMap;
import java.util.Map;

import react.UnitSlot;
import tripleplay.ui.ClickableGroup;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.MigGroup;
import tripleplay.ui.MigLayout;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.FlowLayout;

/**
 * A ViewController that presents a left menu with buttons, which determine
 * the content that is presented in the right pane.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class LeftMenuViewController extends ViewController {
	
	// Map from labels to display in the left pane to ViewControllers to display in the right.
	private Map<Label, ViewController> _labelToViewControllerMapping = new HashMap<Label, ViewController>();
	
	// The current view being displayed on the right.
	private ViewController _currentRightViewController;
	
	/**
	 * Create a new LeftMenuViewController.
	 * @param labelToViewControllerMapping Mapping between labels to display in 
	 *     the left menu and the ViewController to display in the right pane 
	 *     when that label is clicked.
	 */
	public LeftMenuViewController(Map<Label, ViewController> labelToViewControllerMapping) {
		_labelToViewControllerMapping.putAll(labelToViewControllerMapping);
	}
	
	@Override
	protected Group createInterface() {
		final MigGroup group = new MigGroup(new MigLayout(
				"fill, insets 5, gap 5", "[30%, fill][70%, fill]", "[100%, fill]"));
		
		final Group leftPane = new Group(AxisLayout.vertical());
		final Group rightPane = new Group(AxisLayout.horizontal().offStretch());
		
		for (final Label label : _labelToViewControllerMapping.keySet()) {
			Group labelGroup = new Group(new FlowLayout());
			labelGroup.add(label);
			new ClickableGroup(labelGroup).clicked().connect(new UnitSlot() {
				@Override
				public void onEmit() {
					if (_currentRightViewController != null) {
						rightPane.remove(_currentRightViewController.view());
					}
					_currentRightViewController = _labelToViewControllerMapping.get(label);
					rightPane.add(_currentRightViewController.view().setConstraint(AxisLayout.stretched()));
				}
			});
			leftPane.add(labelGroup);
		}
		
		group.add(leftPane, "cell 0 0");
		group.add(rightPane, "cell 1 0");
		
		return group;
	}
}
