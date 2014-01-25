package au.edu.unimelb.cis.dragons.core.view;

import react.SignalView;
import tripleplay.ui.ClickableGroup;
import tripleplay.ui.Group;

public abstract class View {
	
	private Group _group;
	
	protected abstract Group createInterface();
	
	public Group view() {
		if (_group == null) {
			_group = createInterface();
		}
		return _group;
	}
	
	public SignalView<Group> clicked() {
		return new ClickableGroup(view()).clicked();
	}
}
