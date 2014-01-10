package au.edu.unimelb.cis.dragons.core;

import java.util.List;

import react.Function;
import react.Slot;
import react.Value;
import react.ValueView;

/**
 * Utility class that allows the wiring of multiple source ValueViews to a
 *     single output ValueView.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 *
 * @param <T> Any type.
 * @param <U> Any type where there is a mapping List<T> => U
 */
public class MultipleSourceValueViewBuilder<T, U> {
	
	/** The view that is presented to the final client. */
	private Value<U> _view;
	/** All the source views. */
	private List<ValueView<T>> _sources;
	/** The function that maps from List<T> => U */
	private Function<List<ValueView<T>>, U> _func;
	
	public MultipleSourceValueViewBuilder(List<ValueView<T>> sources, final Function<List<ValueView<T>>, U> func) {
		_sources = sources;
		_func = func;
	}
	
	/**
	 * Retrieve the ValueView that can be listened to by clients.
	 * @return The ValueView.
	 */
	public ValueView<U> valueView() {
		// Lazily load.
		if (_view == null) {
			_view = Value.create(_func.apply(_sources));
			// Slot listens for changes on any of the source views and
			// automatically recomputes the output view.
			Slot<T> changed = new Slot<T>() {
				@Override
				public void onEmit(T t) {
					_view.update(_func.apply(_sources));
				}
			};
			for (ValueView<T> view: _sources) {
				view.connect(changed);
			}
		}
		return _view;
	}
	
}