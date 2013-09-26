package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.model.User;
import au.edu.unimelb.cis.dragons.core.model.Wallet;
import react.Function;
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
	
	// The user to display details for.
	private User _user;
	
	// The wallet to display details for.
	private Wallet _wallet;

	/**
	 * Create a new InfoBarViewController.
	 * @param user User to display details for.
	 * @param wallet Wallet to display details for.
	 */
	public InfoBarViewController(User user, Wallet wallet) {
		_user = user;
		_wallet = wallet;
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
		
		TopBarEntryViewController userNameView = new TopBarEntryViewController("Username");
		_user.name().connectNotify(userNameView.valueLabel().text.slot());
		group.add(userNameView.view());
		
		TopBarEntryViewController goldView = new TopBarEntryViewController("Gold");
		_wallet.gold().map(new Function<Integer, String>() {
			@Override
			public String apply(Integer input) {
				return input.toString();
			}
		}).connectNotify(goldView.valueLabel().text.slot());
		group.add(goldView.view());
		
		return group;
	}
	
}
