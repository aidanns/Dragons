package au.edu.unimelb.cis.dragons.core.controller;

import static au.edu.unimelb.cis.dragons.core.GlobalConfig.makeBoldLabel;
import static au.edu.unimelb.cis.dragons.core.GlobalConfig.makePlainLabel;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.layout.AxisLayout;

/**
 * A ViewController that handles the presentation of information in the top
 * bar of the game. Shows a human readable description of the data and it's
 * value.
 * 
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class TopBarEntryViewController extends ViewController {

	private Label _titleLabel;
	private Label _valueLabel;

	/**
	 * Create a new entry based on the value stored under the @param valueKey
	 * parameter in the game state.
	 * 
	 * @param title
	 *            The title for this entry.
	 */
	public TopBarEntryViewController(String title) {
		_titleLabel = makeBoldLabel(title + ":");
		_valueLabel = makePlainLabel();
	}

	@Override
	public String title() {
		return _titleLabel.text.get();
	}

	@Override
	protected Group createInterface() {
		SizableGroup group = new SizableGroup(AxisLayout.horizontal());
		group.setConstraint(AxisLayout.fixed());
		group.add(_titleLabel);
		group.add(_valueLabel);
		return group;
	}

	/**
	 * Get the label for the vlaue in this view.
	 * 
	 * @return The label.
	 */
	public Label valueLabel() {
		return _valueLabel;
	}

}
