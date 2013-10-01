package au.edu.unimelb.cis.dragons.core;

import tripleplay.ui.Background;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.FlowLayout;
import au.edu.unimelb.cis.dragons.core.controller.ViewController;
import au.edu.unimelb.cis.dragons.core.screen.DragonGameScreen;

/**
 * A message in a small view that can be displayed in the middle of the screen.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class Alert {

	// The message that we're going to display in the message.
	private String _message;
	
	/**
	 * Create a new Alert.
	 * @param message The message that will be displayed in the alert.
	 */
	public Alert(String message) {
		_message = message;
	}
	
	/**
	 * Display the alert on the given screen.
	 * @param screen The screen to display the alert on.
	 * @param numberOfMilliseconds The maximum of milliseconds to display the alert for.
	 */
	public void displayOnScreen(final DragonGameScreen screen, final int numberOfMilliseconds) {
		final ViewController alertViewController = new ViewController() {
			
			@Override
			protected Group createInterface() {
				Group parent = new Group(new AxisLayout.Vertical());
				Group group = new Group(new FlowLayout());
				group.setStyles(Style.BACKGROUND.is(Background.solid(0xFFFFFFFF)));
				group.add(new Label(_message));
				parent.add(group);
				return parent;
			}
		};
		screen.presentViewController(alertViewController);
		DragonsGame.timer().after(numberOfMilliseconds, new Runnable() {
			@Override
			public void run() {
				screen.dismissViewController(alertViewController);
			}
		});
	}

	/**
	 * Display the alert on a given screen for 1 second.
	 * @param screen The screen to display the alert on.
	 */
	public void displayOnScreen(final DragonGameScreen screen) {
		displayOnScreen(screen, 1000);
	}
	
}
