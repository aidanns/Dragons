package au.edu.unimelb.cis.dragons.core.controller;

import tripleplay.game.UIAnimScreen;
import tripleplay.ui.Background;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.MigGroup;
import tripleplay.ui.MigLayout;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.FlowLayout;

/**
 * A ViewController that displays a loading screen while assets from the game
 * are being retrieved.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class LoadingViewController extends ViewController {
	
	static private class ProgressBar extends SizableGroup {

		// ProgressBar is a group with a white background and an inner group
		// with a black background. The inner is animated to fill from left
		// to right, showing the progress bar fill up.
		
		// The bar the shows how much has loaded.
		private SizableGroup _loadingBar = new SizableGroup(new FlowLayout());
		
		public ProgressBar(float preferredWidth, float preferredHeight) {
			super(new FlowLayout(), preferredWidth, preferredHeight);
			setStyles(Style.BACKGROUND.is(Background.solid(0xFFFFFFFF)));
			addStyles(Style.HALIGN.is(Style.HAlign.LEFT));
			_loadingBar.setStyles(Style.BACKGROUND.is(Background.solid(0xFF000000)));
			_loadingBar.preferredSize.update(1, preferredHeight);
			this.add(_loadingBar);
		}
		
		public void setProgress(UIAnimScreen screen, float percent) {
			screen.anim.tweenScaleX(_loadingBar.layer).to(percent * size().width()).in(1000).easeInOut();
		}
	}

	private Label _loadingLabel = new Label("Loading...");
	private ProgressBar _progressBar = new ProgressBar(500, 50);
	
	@Override
	public String title() {
		return "Loading";
	}

	@Override
	protected Group createInterface() {
		MigGroup parent = new MigGroup(new MigLayout("fill, insets 0, gap 0", "[100%, fill]", "[30%, fill][20%, fill][20%, fill][30%, fill]"));
		parent.add(_loadingLabel, "cell 0 1");
		// Wrapper because the MigLayout will stretch whatever is in that cell.
		Group wrapper = new Group(AxisLayout.vertical());
		wrapper.add(_progressBar);
		parent.add(wrapper, "cell 0 2");
		return parent;
	}

	/**
	 * Sets the status of the loading bar in the view.
	 * @param loaded Number of items that have been loaded so far.
	 * @param errors Number of items that have failed loading so far.
	 * @param total Total number of items to be loaded.
	 */
	public void setProgress(int loaded, int errors, int total) {
		_progressBar.setProgress(this.parentScreen(), loaded / (total - errors));
	}
}
