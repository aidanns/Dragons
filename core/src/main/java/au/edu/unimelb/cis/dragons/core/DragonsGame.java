package au.edu.unimelb.cis.dragons.core;

import static playn.core.PlayN.*;

import java.util.ArrayList;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.util.Clock;
import tripleplay.game.ScreenStack;
import tripleplay.ui.Element;
import tripleplay.ui.Group;

/**
 * Root of the game, loads up the screen stack and delegates all work to it.
 * 
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class DragonsGame extends Game.Default {

	// 50ms between each update.
	public static final int UPDATE_RATE = 50;

	// Screen that manages a stack of other screens being presented.
	private final ScreenStack _screens = new ScreenStack() {
		@Override protected final void handleError (RuntimeException error) {
			PlayN.log().warn("Screen failure", error);
		}
		@Override protected Transition defaultPushTransition() {
			return slide();
		}
		@Override protected Transition defaultPopTransition() {
			return slide();
		}
	};
	
	private final Clock.Source _clock = new Clock.Source(UPDATE_RATE);
	
	public DragonsGame() {
		super(UPDATE_RATE);
	}

	@Override
	public void init() {
		_screens.push(new DragonGameScreen(_screens, new TabController(new ArrayList<ViewController>())));
	}

	@Override
	public void update(int delta) {
		_clock.update(delta);
		_screens.update(delta);
	}

	@Override
	public void paint(float alpha) {
		_clock.paint(alpha);
		_screens.paint(_clock);
	}
}
