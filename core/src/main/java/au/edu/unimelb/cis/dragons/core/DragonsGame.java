package au.edu.unimelb.cis.dragons.core;

import static playn.core.PlayN.*;

import java.util.ArrayList;
import java.util.List;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Platform;
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
	
	// PersistenceClient is used to store data on the server.
	private final PersistenceClient _persistenceClient;
	
	// Allows for platform specific interactions.
	private final Platform _platform;
	
	// GameState is used to store the current state of the game for this user.
	private final GameState _gameState = new GameState();

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
	
	public DragonsGame(PersistenceClient persistenceClient, Platform platform) {
		super(UPDATE_RATE);
		_persistenceClient = persistenceClient;
		_platform = platform;
	}

	@Override
	public void init() {
		
		List<ViewController> controllers = new ArrayList<ViewController>();
		controllers.add(StubViewController.makeBlueViewController());
		controllers.add(StubViewController.makeRedViewController());
		controllers.add(StubViewController.makeGreenViewController());
		
		_screens.push(new DragonGameScreen(_screens, new TopBarViewController(
				StubViewController.makeBlackViewController(),
				new TabController(controllers))));
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
