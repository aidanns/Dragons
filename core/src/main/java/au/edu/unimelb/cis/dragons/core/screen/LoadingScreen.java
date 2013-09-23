package au.edu.unimelb.cis.dragons.core.screen;

import au.edu.unimelb.cis.dragons.core.controller.ViewController;
import tripleplay.game.ScreenStack;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.layout.AxisLayout;

/**
 * Screen that displays a loading message.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class LoadingScreen extends DragonGameScreen {

	public LoadingScreen(ScreenStack stack) {
		super(stack, new ViewController() {
			
			/*
			 * (non-Javadoc)
			 * @see au.edu.unimelb.cis.dragons.core.ViewController#title()
			 */
			@Override
			public String title() {
				return "Loading";
			}
			
			/*
			 * (non-Javadoc)
			 * @see au.edu.unimelb.cis.dragons.core.ViewController#createInterface()
			 */
			@Override
			protected Group createInterface() {
				Group view = new Group(AxisLayout.vertical());
				view.add(new Label("Loading..."));
				return view;
			}
		});
	}
}
