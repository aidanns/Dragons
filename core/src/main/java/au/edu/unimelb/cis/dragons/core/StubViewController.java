package au.edu.unimelb.cis.dragons.core;

import tripleplay.ui.Background;
import tripleplay.ui.Group;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.FlowLayout;

/**
 * Make stub ViewControllers that are useful for testing.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class StubViewController extends ViewController {
	
	public static ViewController makeBlueViewController() {
		return makeViewControllerWithBackgroundColor(0xFF0000FF,
			"Blue View");
	}
	
	public static ViewController makeRedViewController() {
		return makeViewControllerWithBackgroundColor(0xFFFF0000,
			"Red View");
	}
	
	public static ViewController makeGreenViewController() {
		return makeViewControllerWithBackgroundColor(0xFF00FF00,
			"Green View");
	}
	
	private static ViewController makeViewControllerWithBackgroundColor(
		final int color, final String title) {
		return new ViewController() {
			@Override
			protected Group createInterface() {
				SizableGroup group = new SizableGroup(new FlowLayout());
				group.setStyles(
						Style.BACKGROUND.is(Background.solid(color)));
				group.setConstraint(AxisLayout.stretched());
				group.preferredSize.update(100, 100);
				return group;
			}
			
			@Override
			public String title() {
				return title;
			}
		};
	}

}
