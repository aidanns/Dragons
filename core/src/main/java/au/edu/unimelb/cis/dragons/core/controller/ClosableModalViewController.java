package au.edu.unimelb.cis.dragons.core.controller;

import react.UnitSlot;
import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.MigGroup;
import tripleplay.ui.MigLayout;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.layout.AxisLayout;

public class ClosableModalViewController extends ViewController {

	// The view controller presented modally.
	private ViewController _innerViewController;
	
	/**
	 * Create a new ClosableModalViewController.
	 * @param innerViewController The view controller to display in this modal.
	 */
	public ClosableModalViewController(ViewController innerViewController) {
		_innerViewController = innerViewController;
		addSubViewController(_innerViewController);
	}
	
	@Override
	public String title() {
		return _innerViewController.title();
	}

	@Override
	protected Group createInterface() {
		MigGroup group = new MigGroup(new MigLayout("fill, insets 0", "[100%, fill]", "[90%, fill][10%, fill]"));
		group.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFFFFFFFF))));
		// Add the child view to be displayed in the modal.
		group.add(_innerViewController.view(), "cell 0 0");
		
		// Add the bottom bar which can be used to close the modal.
		Group bottomPane = new Group(AxisLayout.horizontal());
		Button closeModalButton = new Button("Close");
		closeModalButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().dismissViewController(ClosableModalViewController.this);
			}
		});
		bottomPane.add(closeModalButton);
		group.add(bottomPane, "cell 0 1");
		
		return group;
	}
}
