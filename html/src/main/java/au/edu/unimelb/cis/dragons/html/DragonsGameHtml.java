package au.edu.unimelb.cis.dragons.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import au.edu.unimelb.cis.dragons.core.DragonsGame;

public class DragonsGameHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform.Config config = new HtmlPlatform.Config();
    // use config to customize the HTML platform, if needed
    HtmlPlatform platform = HtmlPlatform.register(config);
    platform.assets().setPathPrefix("dragons/");
    PlayN.run(new DragonsGame());
  }
}
