package au.edu.unimelb.cis.dragons.core.view;

import static playn.core.PlayN.assets;
import react.Value;
import react.ValueView.Listener;
import tripleplay.ui.Background;
import tripleplay.ui.Group;
import tripleplay.ui.Icons;
import tripleplay.ui.Label;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.layout.AbsoluteLayout;
import au.edu.unimelb.cis.dragons.core.model.Dragon.DragonState;
import au.edu.unimelb.cis.dragons.core.model.Farm.PenState;

public class PenView extends View {

	// Absolute positioning data for the pen view.
	private int PEN_X_PADDING = 15;
	private int PEN_Y_PADDING = 15;
	private int PEN_WIDTH = 100;
	private int PEN_HEIGHT = 100;

	// Label displaying the name of the dragon in the pen.
	private Label _dragonNameLabel = new Label();

	// Labels that hold images used to display the state of the pen and it's current contents.
	private Label _penBorder;
	private Label _penLockedText;
	private Label _penEmptyText;
	private Label _penDragonText;
	private Label _penDragonStateAvailableText;
	private Label _penDragonStateBreedingText;
	private Label _penDragonStateRacingText;
	private Label _penDragonStateTrainingText;

	private Value<DragonState> _dragonState = Value.create(null);
	private Value<PenState> _penState = Value.create(PenState.Empty);

	public Value<String> dragonName() {
		return _dragonNameLabel.text;
	}

	public Value<DragonState> dragonState() {
		return _dragonState;
	}

	public Value<PenState> penState() {
		return _penState;
	}

	@Override
	protected Group createInterface() {
		// Load up all the images for the pen.
		_penBorder = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen.png")));
		_penLockedText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_locked.png")));
		_penEmptyText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_empty.png")));
		_penDragonText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_dragon.png")));
		_penDragonStateAvailableText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_dragon_status_available.png")));
		_penDragonStateBreedingText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_dragon_status_breeding.png")));
		_penDragonStateRacingText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_dragon_status_racing.png")));
		_penDragonStateTrainingText = new Label(Icons.image(assets().getImage("images/farm_placeholder_pen_dragon_status_training.png")));

		Group group = new Group(new AbsoluteLayout());
		group.setStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0xFFFFFFFF))));

		// Add all the labels in their final position and initially hide them.
		group.add(AbsoluteLayout.at(_penBorder, PEN_X_PADDING, PEN_Y_PADDING, PEN_WIDTH + PEN_X_PADDING, PEN_HEIGHT + PEN_Y_PADDING));
		group.add(AbsoluteLayout.at(_penLockedText, PEN_X_PADDING, PEN_Y_PADDING, PEN_WIDTH + PEN_X_PADDING, PEN_HEIGHT + PEN_Y_PADDING));
		group.add(AbsoluteLayout.at(_penEmptyText, PEN_X_PADDING, PEN_Y_PADDING, PEN_WIDTH + PEN_X_PADDING, PEN_HEIGHT + PEN_Y_PADDING));
		group.add(AbsoluteLayout.at(_penDragonText, PEN_X_PADDING, PEN_Y_PADDING, PEN_WIDTH + PEN_X_PADDING, PEN_HEIGHT + PEN_Y_PADDING));
		group.add(AbsoluteLayout.at(_penDragonStateAvailableText, PEN_X_PADDING, PEN_Y_PADDING, PEN_WIDTH + PEN_X_PADDING, PEN_HEIGHT + PEN_Y_PADDING));
		group.add(AbsoluteLayout.at(_penDragonStateBreedingText, PEN_X_PADDING, PEN_Y_PADDING, PEN_WIDTH + PEN_X_PADDING, PEN_HEIGHT + PEN_Y_PADDING));
		group.add(AbsoluteLayout.at(_penDragonStateRacingText, PEN_X_PADDING, PEN_Y_PADDING, PEN_WIDTH + PEN_X_PADDING, PEN_HEIGHT + PEN_Y_PADDING));
		group.add(AbsoluteLayout.at(_penDragonStateTrainingText, PEN_X_PADDING, PEN_Y_PADDING, PEN_WIDTH + PEN_X_PADDING, PEN_HEIGHT + PEN_Y_PADDING));
		group.add(AbsoluteLayout.at(_dragonNameLabel, PEN_X_PADDING, PEN_Y_PADDING + 5, PEN_WIDTH, PEN_Y_PADDING + 40));

		resetPenStateText();
		resetDragonStateText();

		// Hook up listeners from the state of the view to the view itself.
		_dragonState.connectNotify(new Listener<DragonState>() {
			@Override
			public void onChange(DragonState value, DragonState oldValue) {
				resetDragonStateText();
				if (value != null) {
					switch (value) {
					case Available:
						_penDragonStateAvailableText.setVisible(true);
						break;
					case Breeding:
						_penDragonStateBreedingText.setVisible(true);
						break;
					case Racing:
						_penDragonStateRacingText.setVisible(true);
						break;
					case Training:
						_penDragonStateTrainingText.setVisible(true);
						break;
					default:
						break;
					}
				}
			}
		});

		_penState.connectNotify(new Listener<PenState>() {
			@Override
			public void onChange(PenState value, PenState oldValue) {
				resetPenStateText();
				if (value != null) {
					switch(value) {
					case Empty:
						resetDragonStateText();
						dragonName().update("");
						_penEmptyText.setVisible(true);
						break;
					case Full:
						_penDragonText.setVisible(true);
						_dragonState.updateForce(_dragonState.get());
						break;
					case Locked:
						resetDragonStateText();
						dragonName().update("");
						_penLockedText.setVisible(true);
						break;
					default:
						break;
					}
				}
			}
		});

		return group;
	}

	/** Make all pen state labels invisible. */
	private void resetPenStateText() {
		_penLockedText.setVisible(false);
		_penEmptyText.setVisible(false);
		_penDragonText.setVisible(false);
	}

	/** Make all dragon state labels invisible. */
	private void resetDragonStateText() {
		_penDragonStateAvailableText.setVisible(false);
		_penDragonStateBreedingText.setVisible(false);
		_penDragonStateRacingText.setVisible(false);
		_penDragonStateTrainingText.setVisible(false);
	}
}
