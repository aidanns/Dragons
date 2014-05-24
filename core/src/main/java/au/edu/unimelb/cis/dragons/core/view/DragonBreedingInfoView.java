package au.edu.unimelb.cis.dragons.core.view;

import static au.edu.unimelb.cis.dragons.core.GlobalConfig.*;

import au.edu.unimelb.cis.dragons.core.model.Dragon;
import tripleplay.ui.Group;
import tripleplay.ui.layout.AxisLayout;

/**
 * View that displays the details of the genotype of both dragons in a cross.
 * 
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class DragonBreedingInfoView extends View {

	private Dragon _dragon;

	public DragonBreedingInfoView(Dragon dragon) {
		_dragon = dragon;
	}

	@Override
	protected Group createInterface() {
		// Wrapper for the whole view.
		Group group = new Group(AxisLayout.vertical());
		// Display the name of the dragon at the top.
		group.add(makeBoldLabel(_dragon.name().get()));

		// Display two columns of alleles under the dragon's name.
		Group alleleInfoWrapper = new Group(AxisLayout.horizontal());

		// Left column.
		Group alleleInfoWrapperLeft = new Group(AxisLayout.vertical());
		alleleInfoWrapperLeft.add(makeBoldLabel("Leg Length"));
		alleleInfoWrapperLeft.add(makePlainLabel(_dragon.legLengthAlleleOne().get().toString()));
		alleleInfoWrapperLeft.add(makePlainLabel(_dragon.legLengthAlleleTwo().get().toString()));
		alleleInfoWrapperLeft.add(makeBoldLabel("Feet Type"));
		alleleInfoWrapperLeft.add(makePlainLabel(_dragon.feetTypeAlleleOne().get().toString()));
		alleleInfoWrapperLeft.add(makePlainLabel(_dragon.feetTypeAlleleTwo().get().toString()));
		alleleInfoWrapperLeft.add(makeBoldLabel("Coat Type"));
		alleleInfoWrapperLeft.add(makePlainLabel(_dragon.coatTypeAlleleOne().get().toString()));
		alleleInfoWrapperLeft.add(makePlainLabel(_dragon.coatTypeAlleleTwo().get().toString()));
		alleleInfoWrapperLeft.add(makeBoldLabel("Wing Size"));
		alleleInfoWrapperLeft.add(makePlainLabel(_dragon.wingSizeAlleleOne().get().toString()));
		alleleInfoWrapperLeft.add(makePlainLabel(_dragon.wingSizeAlleleTwo().get().toString()));

		// Right column.
		Group alleleInfoWrapperRight = new Group(AxisLayout.vertical());
		alleleInfoWrapperRight.add(makeBoldLabel("Tail Length"));
		alleleInfoWrapperRight.add(makePlainLabel(_dragon.tailLengthAlleleOne().get().toString()));
		alleleInfoWrapperRight.add(makePlainLabel(_dragon.tailLengthAlleleTwo().get().toString()));
		alleleInfoWrapperRight.add(makeBoldLabel("Phyisque"));
		alleleInfoWrapperRight.add(makePlainLabel(_dragon.physiqueAlleleOne().get().toString()));
		alleleInfoWrapperRight.add(makePlainLabel(_dragon.physiqueAlleleTwo().get().toString()));
		alleleInfoWrapperRight.add(makeBoldLabel("Lung Size"));
		alleleInfoWrapperRight.add(makePlainLabel(_dragon.lungSizeAlleleOne().get().toString()));
		alleleInfoWrapperRight.add(makePlainLabel(_dragon.lungSizeAlleleTwo().get().toString()));
		alleleInfoWrapperRight.add(makeBoldLabel("Heart Size"));
		alleleInfoWrapperRight.add(makePlainLabel(_dragon.heartSizeAlleleOne().get().toString()));
		alleleInfoWrapperRight.add(makePlainLabel(_dragon.heartSizeAlleleTwo().get().toString()));

		alleleInfoWrapper.add(alleleInfoWrapperLeft);
		alleleInfoWrapper.add(alleleInfoWrapperRight);
		group.add(alleleInfoWrapper);

		return group;
	}

}
