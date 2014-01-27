package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.DragonGenerator;
import au.edu.unimelb.cis.dragons.core.ExpandingRowsTableLayout;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Wallet;
import au.edu.unimelb.cis.dragons.core.view.PenView;
import react.UnitSlot;
import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.TableLayout;

/**
 * ViewController to allow the player to purchase a new dragon for an empty pen.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class BuyDragonViewController extends ViewController {
	
	// Size of the grid representing the available dragons.
	private int NUM_COLUMNS = 3;
	private int NUM_ROWS = 1;

	private int ROW_GAP = 0;
	private int COL_GAP = 0;
	
	// Farm that purchased dragons will be placed in to.
	private Farm _farm;
	
	// The wallet for the user that's buying dragons.
	private Wallet _wallet;
	
	// Id of the pen that we're buying for.
	private int _penIdX;
	private int _penIdY;
	
	// The generator for random dragons that can be sold.
	private DragonGenerator _dragonGenerator;

	public BuyDragonViewController(GameState gameState, Farm farm, Wallet wallet) {
		_dragonGenerator = new DragonGenerator(gameState);
		_farm = farm;
		_wallet = wallet;
	}
	
	@Override
	public String title() {
		return "Buy Dragons";
	}
	
	@Override
	protected Group createInterface() {
		Group group = new Group(AxisLayout.vertical());
		group.add(new Label("Player can purchase dragons here."));
		
		SizableGroup pensView = new SizableGroup(new ExpandingRowsTableLayout(TableLayout.COL.stretch().free(1).copy(NUM_COLUMNS)).gaps(ROW_GAP, COL_GAP).fillHeight());
		pensView.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFF000000))));
		
		// Create a controller for every pen and add it to this view as a sub view controller.
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLUMNS; j++) {
				Group penGroup = new Group(AxisLayout.vertical());
				penGroup.setStyles(Style.BACKGROUND.is(Background.solid(0xFFFFFFFF)));
				
				PenView penView = new PenView();
				
				Dragon dragon = _dragonGenerator.createRandomDragon(10);
				dragon.state().connectNotify(penView.dragonState().slot());
				dragon.name().connectNotify(penView.dragonName().slot());
				
				penGroup.add(penView.view());
				
				Button buyButton = new Button("Buy " + j);
				buyButton.clicked().connect(new UnitSlot() {
					@Override
					public void onEmit() {
						// Buy dragon logic.
					}
				});
				penGroup.add(buyButton);
				
				pensView.add(penView.view());
			}
		}
		group.add(pensView);
		
		return group;
	}
}
