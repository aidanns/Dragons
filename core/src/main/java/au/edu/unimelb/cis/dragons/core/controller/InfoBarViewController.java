package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.model.Farm;
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

	// The farm to display details for.
	private Farm _farm;

	/**
	 * Create a new InfoBarViewController.
	 * @param user User to display details for.
	 * @param wallet Wallet to display details for.
	 */
	public InfoBarViewController(User user, Wallet wallet, Farm farm) {
		_user = user;
		_wallet = wallet;
		_farm = farm;
	}

	@Override
	public String title() {
		return "Info Bar";
	}

	@Override
	protected Group createInterface() {
		
		Function<Integer, String> integerToStringMapper = new Function<Integer, String>() {
			@Override
			public String apply(Integer input) {
				return input.toString();
			}
		};
		
		// Group to hold the top bar.
		SizableGroup group = new SizableGroup(new AxisLayout.Horizontal());
		group.setStyles(Styles.make(Style.HALIGN.left));
		group.add(new Shim(5, 5));

		TopBarEntryViewController userNameView = new TopBarEntryViewController("User");
		_user.name().connectNotify(userNameView.valueLabel().text.slot());
		group.add(userNameView.view());

		TopBarEntryViewController goldView = new TopBarEntryViewController("$");
		_wallet.gold().map(integerToStringMapper).connectNotify(goldView.valueLabel().text.slot());
		group.add(goldView.view());

		TopBarEntryViewController dragonsOwnedView = new TopBarEntryViewController("Own");
		_farm.numberOfDragons().map(integerToStringMapper).connectNotify(dragonsOwnedView.valueLabel().text.slot());
		group.add(dragonsOwnedView.view());

		TopBarEntryViewController dragonsAvailableView = new TopBarEntryViewController("Avail");
		_farm.numberOfDragonsAvailable().map(integerToStringMapper).connectNotify(dragonsAvailableView.valueLabel().text.slot());
		group.add(dragonsAvailableView.view());
		
		TopBarEntryViewController dragonsRacingView = new TopBarEntryViewController("Race");
		_farm.numberOfDragonsRacing().map(integerToStringMapper).connectNotify(dragonsRacingView.valueLabel().text.slot());
		group.add(dragonsRacingView.view());
		
		TopBarEntryViewController dragonsBreedingView = new TopBarEntryViewController("Breed");
		_farm.numberOfDragonsBreeding().map(integerToStringMapper).connectNotify(dragonsBreedingView.valueLabel().text.slot());
		group.add(dragonsBreedingView.view());
		
		TopBarEntryViewController score = new TopBarEntryViewController("Score");
		_farm.score().map(integerToStringMapper).connectNotify(score.valueLabel().text.slot());
		group.add(score.view());

		return group;
	}

}
