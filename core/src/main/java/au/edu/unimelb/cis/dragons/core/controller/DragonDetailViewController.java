package au.edu.unimelb.cis.dragons.core.controller;

import playn.core.Font;
import playn.core.PlayN;
import au.edu.unimelb.cis.dragons.core.GlobalConfig;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Dragon.DragonState;
import react.Function;
import react.UnitSlot;
import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.MigGroup;
import tripleplay.ui.MigLayout;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;

/**
 * A DragonDetailView displays inforamtion about a particular draong to the 
 * player.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class DragonDetailViewController extends ViewController {

	// The dragon that this screen is displaying.
	private Dragon _dragon;

	/**
	 * Create a new DragonDetailView.
	 * @param dragon The dragon that we're displaying information for.
	 */
	public DragonDetailViewController(Dragon dragon) {
		_dragon = dragon;
	}
	
	@Override
	public String title() {
		return "Dragon Detail";
	}

	@Override
	protected Group createInterface() {
		MigGroup group = new MigGroup(new MigLayout(
				"insets 0, gap 0, fill", "[50%, fill][50%, fill]", "[90%, fill][10%, fill]"));
		group.setStyles(Style.BACKGROUND.is(Background.solid(0xFFFFFFFF)));
		
		// Add a left pane that displays an image of the dragon.
		Group leftPane = new Group(AxisLayout.vertical());
		leftPane.add(new Label("Image of the dragon."));
		group.add(leftPane, "cell 0 0");
		
		// Add a right pane which displays information about the dragon.
		MigGroup rightPane = new MigGroup(new MigLayout(
				"insets 5, gap 5, fill", "[][]", "[][][grow]"));
		
		// Add a label that displays the dragon's name
		rightPane.add(new Label("Name:").addStyles(Style.FONT.is(PlayN.graphics().createFont(
				GlobalConfig.FontName, Font.Style.BOLD, GlobalConfig.FontSize))), "cell 0 0");
		Label dragonNameLabel = new Label().addStyles(Style.FONT.is(
				PlayN.graphics().createFont(
						GlobalConfig.FontName, Font.Style.PLAIN, GlobalConfig.FontSize)));
		_dragon.name().connectNotify(dragonNameLabel.text.slot());
		rightPane.add(dragonNameLabel, "cell 1 0");
		
		// Add a label that displays the dragon's status
		rightPane.add(new Label("State:").addStyles(Style.FONT.is(PlayN.graphics().createFont(
				GlobalConfig.FontName, Font.Style.BOLD, GlobalConfig.FontSize))), "cell 0 1");
		Label dragonStateLabel = new Label().addStyles(Style.FONT.is(
				PlayN.graphics().createFont(
						GlobalConfig.FontName, Font.Style.PLAIN, GlobalConfig.FontSize)));;
		_dragon.state().map(new Function<DragonState, String>() {
			@Override
			public String apply(DragonState input) {
				return input.toString();
			}
		}).connectNotify(dragonStateLabel.text.slot());
		rightPane.add(dragonStateLabel, "cell 1 1");
		
		group.add(rightPane, "cell 1 0");
		
		// Add a bottom pane with a button to close the view.
		Group bottomPane = new Group(AxisLayout.horizontal());
		Button closeModelButton = new Button("Close");
		closeModelButton.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				parentScreen().dismissViewController();
			}
		});
		bottomPane.add(closeModelButton);
		group.add(bottomPane, "cell 0 1, span 2 1");
		
		return group;
	}
}
