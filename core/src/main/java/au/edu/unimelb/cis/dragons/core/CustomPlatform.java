package au.edu.unimelb.cis.dragons.core;

import playn.core.Platform;

/**
 * A custom HtmlPlatform that supports the addition of a resize handler.
 * 
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public interface CustomPlatform extends Platform {
	
	/** 
	 * The custom graphics wrapper. 
	 */
	CustomGraphics graphics();

}
