package au.edu.unimelb.cis.dragons.core;

import tripleplay.ui.Group;
import tripleplay.ui.layout.FlowLayout;

public class ViewController {
	
	private DragonGameScreen _parentScreen;
	private Group _view;

	/** Return the view that this controller is managing. */
	public final Group view() {
		if (_view == null) {
			_view = createInterface();
		}
		return _view;
	}

	/** Called when the parent screen is added to the stack for the first time.
	 */
	public void wasAdded() {}

	/** Called when the parent screen becomes the top screen and is made
	 * visible
	 */
	public void wasShown() {}

	/** Called when the parent screen is no longer the top screen. */
	public void wasHidden() {}

	/** Called when the parent screen is removed from the stack. */
	public void wasRemoved() {
		_view.destroyAll();
		_view = null;
	}
	
	public final void setParentScreen(DragonGameScreen parentScreen) {
		_parentScreen = parentScreen;
	}
	
	/** Override this method to return the interface for your view. */
	protected Group createInterface() {
		return new Group(new FlowLayout());
	}

}
