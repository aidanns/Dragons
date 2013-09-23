package au.edu.unimelb.cis.dragons.core;

import tripleplay.ui.Container;
import tripleplay.ui.layout.TableLayout;

/**
 * A TableLayout that expands it's rows to fill the available space.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class ExpandingRowsTableLayout extends TableLayout {

	public ExpandingRowsTableLayout(Column[] copy) {
		super(copy);
	}

	@Override
	protected Metrics computeMetrics(Container<?> elems, float hintX, float hintY) {
		Metrics metrics = super.computeMetrics(elems, hintX, hintY);
		if (metrics.totalHeight(_rowgap) < hintY) {
			float extraSpace = hintY - metrics.totalHeight(_rowgap);
			for (int i = 0; i < metrics.rows(); i++) {
				metrics.rowHeights[i] += extraSpace / metrics.rows();
			}
		}
		return metrics;
	}
}
