package au.edu.unimelb.cis.dragons.core;

import tripleplay.ui.Group;
import tripleplay.ui.Layout;

/**
 * A Group that will respond to hit events no it's layer.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class HittableGroup extends Group {

	public HittableGroup(Layout layout) {
		super(layout);
		set(Flag.HIT_ABSORB, true);
	}

}
