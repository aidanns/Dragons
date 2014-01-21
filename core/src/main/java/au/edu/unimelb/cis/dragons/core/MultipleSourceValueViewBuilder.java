package au.edu.unimelb.cis.dragons.core;

import java.util.ArrayList;
import java.util.List;

import react.Function;
import react.UnitSlot;
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
	/** Slot that updates the view when anything changes */
	private UnitSlot _dependencyChangedSlot;

	/**
	 * @param func The function that maps between the input and output.
	 * @param sources Values that are used as input to the mapping function. Changes in these will cause re-calculation.
	 */
	public MultipleSourceValueViewBuilder(final Function<List<ValueView<T>>, U> func, List<ValueView<T>> sources) {
		_func = func;
		_sources = new ArrayList<ValueView<T>>(sources.size());

		// Create this first up so that there is a slot to bind to while we're
		// adding sources individually just below.
		_dependencyChangedSlot = new UnitSlot() {
			@Override
			public void onEmit() {
				_view.update(_func.apply(_sources));
			}
		};
		
		for (ValueView<T> valueView : sources) {
			// Pass false to make sure we don't try and do a map before we've 
			// got all the parameters from the constructor saved. The users function
			// might rely on a certain number of parameters being in the list.
			addSource(valueView, false);
		}

		_view = Value.create(_func.apply(_sources));
	}

	public MultipleSourceValueViewBuilder(final Function<List<ValueView<T>>, U> func) {
		this(func, new ArrayList<ValueView<T>>(0));
	}

	public MultipleSourceValueViewBuilder<T, U> addSource(ValueView<T> source) {
		return addSource(source, true);
	}

	/**
	 * Add a source to the view.
	 * @param source Source to add.
	 * @param notify Whether to notify the slot after adding.
	 * @return The view itself, for chaining.
	 */
	private MultipleSourceValueViewBuilder<T, U> addSource(ValueView<T> source, boolean notify) {
		_sources.add(source);
		source.connect(_dependencyChangedSlot);
		if (notify) {
			recalculate();
		}
		return this;
	}

	/**
	 * Add a source to the view and tell it perform an update.
	 * @param source The source to add.
	 * @return The view itself, for chaining.
	 */
	public MultipleSourceValueViewBuilder<T, U> removeSource(ValueView<T> source) {
		if (_sources.contains(source)) {
			_sources.remove(source);
			source.disconnect(_dependencyChangedSlot);
			recalculate();
		}
		return this;
	}

	/**
	 * Remove all sources from the view and recalculate its value.
	 * @return The view itself, for chaining.
	 */
	public MultipleSourceValueViewBuilder<T, U> clearSources() {
		return clearSources(true);
	}
	
	private MultipleSourceValueViewBuilder<T, U> clearSources(boolean notify) {
		for (ValueView<T> source : _sources) {
			source.disconnect(_dependencyChangedSlot);
		}
		_sources.clear();
		if (notify) {
			recalculate();
		}
		return this;
	}
	
	public MultipleSourceValueViewBuilder<T, U> replaceSources(List<ValueView<T>> newSources) {
		clearSources();
		for (ValueView<T> valueView : newSources) {
			addSource(valueView, false);
		}
		recalculate();
		return this;
	}
	
	private void recalculate() {
		_dependencyChangedSlot.onEmit();
	}

	/**
	 * Retrieve the ValueView that can be listened to by clients.
	 * @return The ValueView.
	 */
	public ValueView<U> valueView() {
		return _view;
	}

}