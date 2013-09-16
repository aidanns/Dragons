package au.edu.unimelb.cis.dragons.core;

import playn.core.Graphics;

/**
 * A custom graphics objects that allows a callback to be registered for
 * screen resize events.
 * 
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public interface CustomGraphics extends Graphics {
	
	public interface ResizeHandler {
		
		/**
		 * Called when the game is re-sized using this platform.
		 * @param newWidth The new game width.
		 * @param newHeight The new game height.
		 */
		public abstract void handleResize(int newWidth, int newHeight);
		
	}

	/**
	 * Add a new resize handler.
	 * @param handler The handler to add.
	 */
	public void registerResizeHandler(ResizeHandler handler);
	
	/**
	 * Remove a resize handler.
	 * @param handler The handler to remove.
	 */
	public void removeResizeHandler(ResizeHandler handler);
	
}
