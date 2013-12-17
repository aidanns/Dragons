package au.edu.unimelb.cis.dragons.core;

import static playn.core.PlayN.*;

import java.util.ArrayList;
import java.util.List;

import au.edu.unimelb.cis.dragons.core.CustomGraphics.ResizeHandler;
import au.edu.unimelb.cis.dragons.core.controller.FarmViewController;
import au.edu.unimelb.cis.dragons.core.controller.InfoBarViewController;
import au.edu.unimelb.cis.dragons.core.controller.LeaderboardViewController;
import au.edu.unimelb.cis.dragons.core.controller.LoadingViewController;
import au.edu.unimelb.cis.dragons.core.controller.LoreViewController;
import au.edu.unimelb.cis.dragons.core.controller.TabController;
import au.edu.unimelb.cis.dragons.core.controller.TopBarViewController;
import au.edu.unimelb.cis.dragons.core.controller.ViewController;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.User;
import au.edu.unimelb.cis.dragons.core.model.Wallet;
import au.edu.unimelb.cis.dragons.core.screen.DragonGameScreen;
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
	private static final int MINIMUM_LOADING_SCREEN_TIME = 4000;

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
	private static final Timer timer = new Timer();
	
	/**
	 * Get a timer that can be used to schedule actions.
	 * @return The timer.
	 */
	public static Timer timer() {
		return timer;
	}
	
	public DragonsGame(PersistenceClient persistenceClient, CustomPlatform platform) {
		super(UPDATE_RATE);
		_persistenceClient = persistenceClient;
		_platform = platform;
	}

	@Override
	public void init() {
		// Display the default loading screen.
		LoadingViewController loadingViewController = new LoadingViewController();
		_screens.push(new DragonGameScreen(_screens, loadingViewController));
		populateGameState();
		loadResourcesThenDisplayGame(loadingViewController);
		
		_platform.graphics().addResizeHandler(new ResizeHandler() {

			@Override
			public void handleResize(int newWidth, int newHeight) {
				// Re-create the UI whenever the size changes.
				// TODO: aidanns: Does this cause a leak?
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
						_gameState.valueForKey(GameState.Key.Username).update(result);
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
	 * @param loadingViewController The view controller that is displaying the
	 *   loading status of the game to the user. Should be updated as the
	 *   status of the loading changes.
	 */
	private void loadResourcesThenDisplayGame(final LoadingViewController loadingViewController) {
		AssetWatcher assetWatcher = new AssetWatcher(new AssetWatcher.Listener() {
			
			@Override
			public void progress(int loaded, int errors, int total) {
				loadingViewController.setProgress(loaded, errors, total);
			}

			@Override
			public void error(Throwable e) {
				log().error(e.getMessage());
				// TODO aidanns: Show an error message to the user.
			}
			
			@Override
			public void done() {
				// Display the main game UI.
				int timeToWait = MINIMUM_LOADING_SCREEN_TIME - (int) _clock.time();
				final Runnable presentGameScreen = new Runnable() {
					@Override
					public void run() {
						// If the game state hasn't yet been populated, then
						// try again later.
						if (!_gameState.hasBeenPopulated()) {
							timer.after(250, this);
						} else {
							List<ViewController> controllers = new ArrayList<ViewController>();
							controllers.add(new LoreViewController());
							controllers.add(new FarmViewController(new Farm(_gameState), new Wallet(_gameState)));
							controllers.add(new LeaderboardViewController());
							controllers.add(new DebuggingViewController(_gameState));
							
							_screens.replace(new DragonGameScreen(_screens, new TopBarViewController(
									new InfoBarViewController(new User(_gameState), new Wallet(_gameState)),
									new TabController(controllers))));
						}
					}
				};
				timer.after(timeToWait >= 0 ? timeToWait : 0, presentGameScreen);
			}
		});
		
		// Add some images that we need to wait for before loading the main game.
		assetWatcher.add(assets().getImage("images/bg.png"));
		assetWatcher.add(assets().getImage("images/bug_in_lampshade.jpeg"));
		assetWatcher.add(assets().getImage("images/like_couch_better.jpeg"));
		
		// Start loading.
		assetWatcher.start();
	}
	
	
	@Override
	public void update(int delta) {
		_clock.update(delta);
		_screens.update(delta);
		timer.update();
	}

	@Override
	public void paint(float alpha) {
		_clock.paint(alpha);
		_screens.paint(_clock);
	}

}
