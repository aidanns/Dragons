package au.edu.unimelb.cis.dragons.core.controller;

import java.util.ArrayList;
import java.util.List;

import au.edu.unimelb.cis.dragons.core.GameState;
import tripleplay.ui.Group;
import tripleplay.ui.Shim;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.layout.AxisLayout;

/**
 * ViewController for the info bar displayed at the top of the screen.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class InfoBarViewController extends ContainerViewController {
	
	private List<ViewController> _entries = new ArrayList<ViewController>();
	
	private List<GameState.Key> _keysToAdd = new ArrayList<GameState.Key>();
	
	{
		_keysToAdd.add(GameState.Key.Username);
		_keysToAdd.add(GameState.Key.CurrentGold);
	}
	
	
	public InfoBarViewController(GameState gameState) {
		for (GameState.Key key : _keysToAdd) {
			ViewController child = new TopBarEntryViewController(gameState, key);
			_entries.add(child);
			addChildViewController(child);
		}
	}

	@Override
	public String title() {
		return "Info Bar";
	}
	
	@Override
	protected Group createInterface() {
		SizableGroup group = new SizableGroup(new AxisLayout.Horizontal());
		group.setStyles(Styles.make(Style.HALIGN.left));
		group.add(new Shim(5, 5));
		for (ViewController v : _entries) {
			group.add(v.view());
		}
		
		return group;
	}
	
}
