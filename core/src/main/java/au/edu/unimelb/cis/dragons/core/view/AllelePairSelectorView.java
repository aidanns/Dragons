package au.edu.unimelb.cis.dragons.core.view;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import au.edu.unimelb.cis.dragons.core.Pair;
import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import react.UnitSlot;
import react.Value;
import react.ValueView;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.MigGroup;
import tripleplay.ui.MigLayout;
import tripleplay.ui.layout.AxisLayout;

/**
 * View to allow the user to select which allele pairs are in a particular box within a punnet
 * square.
 * 
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class AllelePairSelectorView extends View {

	private Collection<Allele> _alleles;
	
	// Currently selected alleles in this view.
	private Value<Pair<Allele, Allele>> _selectedAlleles = 
			Value.create(new Pair<Allele, Allele>(null, null));

	/**
	 * @param validAlleles A list of all valid alleles for this gene locus.
	 */
	public AllelePairSelectorView(Set<Allele> validAlleles) {
		_alleles = new HashSet<Allele>(validAlleles);
	}

	@Override
	protected Group createInterface() {
		// Two column layout. Each column allows selecting from all possible alleles.
		MigGroup group = new MigGroup(new MigLayout("fill, insets 0, gap 0",
				"[][]",
				"[]"));

		Group leftColumn = new Group(AxisLayout.vertical());
		for (final Allele allele : _alleles) {
			final Button selectAlleleButton = new Button(allele.toString());
			selectAlleleButton.clicked().connect(new UnitSlot() {
				@Override
				public void onEmit() {
					_selectedAlleles.update(new Pair<Allele, Allele>(allele, _selectedAlleles.get().getSecond()));
				}
			});
			leftColumn.add(selectAlleleButton);
		}
		group.add(leftColumn, "cell 0 0");

		Group rightColumn = new Group(AxisLayout.vertical());
		for (final Allele allele : _alleles) {
			final Button selectAlleleButton = new Button(allele.toString());
			selectAlleleButton.clicked().connect(new UnitSlot() {
				@Override
				public void onEmit() {
					_selectedAlleles.update(new Pair<Allele, Allele>(_selectedAlleles.get().getFirst(), allele));
				}
			});
			rightColumn.add(selectAlleleButton);
		}
		group.add(rightColumn, "cell 1 0");

		return group;
	}

	/**
	 * @return The currently selected {@link Allele}'s.
	 */
	public ValueView<Pair<Allele, Allele>> selectedAlleles() {
		return _selectedAlleles;
	}
}
