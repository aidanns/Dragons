package au.edu.unimelb.cis.dragons.core;

import static playn.core.PlayN.*;

import java.util.ArrayList;
import java.util.List;

import au.edu.unimelb.cis.dragons.core.CustomGraphics.ResizeHandler;
import playn.core.AssetWatcher;
import playn.core.Game;
import playn.core.PlayN;
import playn.core.util.Clock;
import tripleplay.game.ScreenStack;
import tripleplay.util.Timer;

/**
 * Root of the game, loads up the screen stack and delegates all work to it.
 * 
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class DragonsGame extends Game.Default {
	
	// Username for the current user.
	@SuppressWarnings("unused")
	private String _currentUserName;
	
	// PersistenceClient is used to store data on the server.
	private final PersistenceClient _persistenceClient;
	
	// Allows for platform specific interactions.
	private final CustomPlatform _platform;
	
	// GameState is used to store the current state of the game for this user.
	private final GameState _gameState = new GameState();

	// 50ms between each update.
	private static final int UPDATE_RATE = 50;
	
	// Loading screen must be displayed for at least 4 seconds.
	private static final int MINIMUM_LOADING_SCREEN_TIME = 2000;

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
	
	// Main game clock.
	private final Clock.Source _clock = new Clock.Source(UPDATE_RATE);
	
	// Time that can be used to schedule actions.
	private final Timer _timer = new Timer();
	
	public DragonsGame(PersistenceClient persistenceClient, CustomPlatform platform) {
		super(UPDATE_RATE);
		_persistenceClient = persistenceClient;
		_platform = platform;
	}

	@Override
	public void init() {
		// Display the default loading screen.
		_screens.push(new LoadingScreen(_screens));
		populateGameState();
		loadResourcesThenDisplayGame();
		
		_platform.graphics().addResizeHandler(new ResizeHandler() {

			@Override
			public void handleResize(int newWidth, int newHeight) {
				// TODO: Does this cause a leak?
				_screens.top().wasAdded();
			}
			
		});
	}
	
	/*
	 * Populate the game state from the store.
	 */
	private void populateGameState() {
		_persistenceClient.populate(_gameState);
		_persistenceClient.getUserName(
			new PersistenceClient.Callback<String>() {
				@Override
				public void onSuccess(String result) {
					if (result != null) {
						_gameState.setCurrentUserName(result);
					}
				}
				@Override
				public void onFailure(Throwable caught) {
					// TODO aidanns: Show an error message to the user.
				}
			});
	}
	
	/*
	 * When the resources have loaded and MINIMUM_LOADING_SCREEN_TIME has past,
	 * the game is displayed.
	 */
	private void loadResourcesThenDisplayGame() {
		AssetWatcher assetWatcher = new AssetWatcher(new AssetWatcher.Listener() {
			
			@Override
			public void error(Throwable e) {
				log().error(e.getMessage());
				// TODO aidanns: Show an error message to the user.
			}
			
			@Override
			public void done() {
				// Display the main game UI.
				int timeToWait = MINIMUM_LOADING_SCREEN_TIME - (int) _clock.time();
				_timer.after(timeToWait >= 0 ? timeToWait : 0, new Runnable() {
					@Override
					public void run() {
						List<ViewController> controllers = new ArrayList<ViewController>();
						controllers.add(StubViewController.makeBlueViewController());
						controllers.add(StubViewController.makeRedViewController());
						controllers.add(StubViewController.makeGreenViewController());
						
						_screens.replace(new DragonGameScreen(_screens, new TopBarViewController(
								StubViewController.makeBlackViewController(),
								new TabController(controllers))));
					}
				});
			}
		});
		
		assetWatcher.start();
	}
	
	
	@Override
	public void update(int delta) {
		_clock.update(delta);
		_screens.update(delta);
		_timer.update();
	}

	@Override
	public void paint(float alpha) {
		_clock.paint(alpha);
		_screens.paint(_clock);
	}

}
