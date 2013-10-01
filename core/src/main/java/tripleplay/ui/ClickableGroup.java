package tripleplay.ui;

import playn.core.Pointer;
import playn.core.Pointer.Event;
import react.Signal;
import react.SignalView;
import tripleplay.ui.Clickable;
import tripleplay.ui.Element.Flag;
import tripleplay.ui.Group;

/**
 * A Group that will respond to hit events no it's layer.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class ClickableGroup implements Clickable<Group> {

	// The group that we're making click-able.
	private Group _group;
	
	// The signal for clicking.
	private Signal<Group> _clicked = Signal.create();
	
	/**
	 * Create a new ClickableGroup.
	 * @param group The group that we're making click-able.
	 */
	public ClickableGroup(Group group) {
		_group = group;
		_group.set(Flag.HIT_ABSORB, true);
		_group.layer.addListener(new Pointer.Adapter() {
			@Override
			public void onPointerEnd(Event event) {
				_clicked.emit(_group);
			}
		});
	}
	
    @Override
    public SignalView<Group> clicked () {
        return _clicked;
    }

    @Override
    public void click () {
        _clicked.emit(_group);
    }
}
