package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.GlobalConfig;
import playn.core.Font;
import playn.core.PlayN;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
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
		_titleLabel = new Label(title + ":");
		_valueLabel = new Label();
	}

	@Override
	public String title() {
		return _titleLabel.text.get();
	}

	@Override
	protected Group createInterface() {
		SizableGroup group = new SizableGroup(AxisLayout.horizontal());
		group.setConstraint(AxisLayout.fixed());
		_titleLabel.addStyles(Style.FONT.is(PlayN.graphics().createFont(
				GlobalConfig.FontName, Font.Style.BOLD, GlobalConfig.FontSize)));
		group.add(_titleLabel);
		_valueLabel.addStyles(Style.FONT.is(PlayN.graphics().createFont(
				GlobalConfig.FontName, Font.Style.PLAIN, GlobalConfig.FontSize)));
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
