package au.edu.unimelb.cis.dragons.core.controller;

import java.util.ArrayList;
import java.util.List;

import au.edu.unimelb.cis.dragons.core.MultipleSourceValueViewBuilder;
import au.edu.unimelb.cis.dragons.core.model.Farm;
import au.edu.unimelb.cis.dragons.core.model.Dragon.DragonState;
import au.edu.unimelb.cis.dragons.core.model.Farm.PenState;
import au.edu.unimelb.cis.dragons.core.model.User;
import au.edu.unimelb.cis.dragons.core.model.Wallet;
import react.Function;
import react.UnitSlot;
import react.ValueView;
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
		// Group to hold the top bar.
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

		// Calculate dragons owned by summing all pens that are full.
		List<ValueView<PenState>> pens = new ArrayList<ValueView<PenState>>(8);
		for (int col = 0; col < 4; ++col) {
			for (int row = 0; row < 2; ++row) {
				pens.add(_farm.stateForPen(row, col));
			}
		}
		TopBarEntryViewController dragonsOwnedView = new TopBarEntryViewController("Own");
		new MultipleSourceValueViewBuilder<PenState, String>(new Function<List<ValueView<PenState>>, String>() {
			@Override
			public String apply(List<ValueView<PenState>> input) {
				Integer i = 0;
				for (ValueView<PenState> state : input) {
					if (state.get() == PenState.Full) {
						++i;
					}
				}
				return i.toString();
			}
		}, pens).valueView().connectNotify(dragonsOwnedView.valueLabel().text.slot());
		group.add(dragonsOwnedView.view());

		// Calculate dragons available by summing all dragons that have the state available.
		TopBarEntryViewController dragonsAvailableView = new TopBarEntryViewController("Avail");
		final MultipleSourceValueViewBuilder<DragonState, String> dragonStateBuilder = new MultipleSourceValueViewBuilder<DragonState, String>(new Function<List<ValueView<DragonState>>, String>() {
			@Override
			public String apply(List<ValueView<DragonState>> input) {
				Integer i = 0;
				for (ValueView<DragonState> dragonState : input) {
					if (dragonState.get() == DragonState.Available) {
						++i;
					}
				}
				return i.toString();
			}
		});
		dragonStateBuilder.valueView().connectNotify(dragonsAvailableView.valueLabel().text.slot());
		// Need two levels of mapping because the number of available dragons needs
		// to be updated when either a dragon's state changes, or a dragon changes.
		// When the dragon changes, we also need to update with the extra dragon state
		// that we now have access to.
		final UnitSlot rebuildDragonStateBuilderSources = new UnitSlot() {
			@Override
			public void onEmit() {
				dragonStateBuilder.clearSources();
				for (int row = 0; row < 2; ++row) {
					for (int col = 0; col < 4; ++col) {
						if (_farm.dragonForPen(row, col).get() != null) {
							dragonStateBuilder.addSource(_farm.dragonForPen(row, col).get().state());
						}
					}
				}
			}
		};
		for (int row = 0; row < 2; ++row) {
			for (int col = 0; col < 4; ++col) {
				_farm.dragonForPen(row, col).connect(rebuildDragonStateBuilderSources);
			}
		}
		// Push the first update.
		rebuildDragonStateBuilderSources.onEmit();

		//.valueView().connectNotify(dragonsAvailableView.valueLabel().text.slot());
		group.add(dragonsAvailableView.view());

		return group;
	}

}
