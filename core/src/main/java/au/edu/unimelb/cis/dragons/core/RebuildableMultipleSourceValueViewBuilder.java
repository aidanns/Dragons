package au.edu.unimelb.cis.dragons.core;

import java.util.List;

import react.Function;
import react.UnitSlot;
import react.ValueView;

public class RebuildableMultipleSourceValueViewBuilder<T, U> {

	private ValueView<U> _view;
	
	public RebuildableMultipleSourceValueViewBuilder(
			final Function<List<ValueView<T>>, U> mappingFunc, 
			final Function<?, List<ValueView<T>>> buildSourcesFunc, 
			final Iterable<? extends ValueView<?>> rebuildTriggers) {
		final MultipleSourceValueViewBuilder<T, U> builder = new MultipleSourceValueViewBuilder<T, U>(mappingFunc);
		final UnitSlot rebuilder = new UnitSlot() {
			@Override
			public void onEmit() {
				builder.clearSources();
				for (ValueView<T> view : buildSourcesFunc.apply(null)) {
					builder.addSource(view);
				}
			}
		};
		for (ValueView<? extends Object> trigger : rebuildTriggers) {
			trigger.connect(rebuilder);
		}
		rebuilder.onEmit();
		_view = builder.valueView();
	}
	
	public ValueView<U> valueView() {
		return _view;
	}
	
}
