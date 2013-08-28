package au.edu.unimelb.cis.dragons.html;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;
import au.edu.unimelb.cis.dragons.core.DragonsGame;

public class DragonsGameHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform.Config config = new HtmlPlatform.Config();
    // use config to customize the HTML platform, if needed
    final HtmlPlatform platform = HtmlPlatform.register(config);
    platform.assets().setPathPrefix("dragons/");
    
    Window.addResizeHandler(new ResizeHandler() {		
    	Timer resizeTimer = new Timer() {
    		@Override
    		public void run() {
    			platform.graphics().setSize(Window.getClientWidth(),
    				Window.getClientHeight());
    		}
    	};
    	
		@Override
		public void onResize(ResizeEvent event) {
			resizeTimer.cancel();
			resizeTimer.schedule(250);
		}
	});
    
    PlayN.run(new DragonsGame(new MuglePersistenceClient(), platform));
  }
}
