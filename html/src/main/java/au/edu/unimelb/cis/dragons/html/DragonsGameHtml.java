package au.edu.unimelb.cis.dragons.html;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;
import au.edu.unimelb.cis.dragons.core.DragonsGame;

public class DragonsGameHtml extends HtmlGame {
	
	// Handler that allows the simple execution of it's logic without passing
	// in a ResizeEvent.
	private interface CustomResizeHandler extends ResizeHandler {
		public void resizeGameCanvas();
	}

	// The CSS id of the playn root div.
	private static String PLAYN_ROOT_ID = "playn-root";

	@Override
	public void start() {
		HtmlPlatform.Config config = new HtmlPlatform.Config();
		final HtmlPlatform platform = HtmlPlatform.register(config);
		platform.assets().setPathPrefix("dragons/");
		CustomHtmlPlatform customPlatform = new CustomHtmlPlatform(platform);

		// The CSS style node for the playn root node.
		final Style rootStyle = DOM.getElementById(PLAYN_ROOT_ID).getStyle();
		
		// Set the div to be centered horizontally.
		rootStyle.setProperty("margin", "0 auto");
		
		// Wait until 250ms after the last resize event to do the resize on the
		// game.
		CustomResizeHandler resizeHandler = new CustomResizeHandler() {
			
			Timer resizeTimer = new Timer() {
				@Override
				public void run() {
					resizeGameCanvas();
				}
			};

			@Override
			public void onResize(ResizeEvent event) {
				resizeTimer.cancel();
				resizeTimer.schedule(250);
			}
			
			public void resizeGameCanvas() {
				// Put restrictions on game width and height so that it's 16:10 
				// and fits on the screen.
				int screenWidth = Window.getClientWidth();
				int screenHeight = Window.getClientHeight();

				int gameHeight = 
						Math.min(screenHeight, (int) (0.625 * screenWidth));
				int gameWidth = (int) (1.6 * gameHeight);
			
				// This updates the size of the canvas that actually holds the
				// game and the div that contains that canvas.
				platform.graphics().setSize(gameWidth, gameHeight);
				
				// Adjust the size of the top margin so that the game window is
				// centered.
				rootStyle.setMarginTop(
								Math.max((screenHeight - gameHeight) / 2,  0),
								Unit.PX);
			}
		};
		
		Window.addResizeHandler(resizeHandler);
		
		// Run the handler to set the initail size.
		resizeHandler.resizeGameCanvas();

		PlayN.run(
				new DragonsGame(new MuglePersistenceClient(), customPlatform));
	}
}
