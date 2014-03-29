package au.edu.unimelb.cis.dragons.core.view;

import au.edu.unimelb.cis.dragons.core.model.Dragon;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.layout.AxisLayout;

public class DragonBreedingInfoView extends View {

	private Dragon _dragon;

	public DragonBreedingInfoView(Dragon dragon) {
		_dragon = dragon;
	}

	@Override
	protected Group createInterface() {
		Group group = new Group(AxisLayout.vertical());
		
		group.add(new Label(_dragon.name().get()));

		group.add(new Label("Leg Length"));
		group.add(new Label(_dragon.legLengthAlleleOne().get().toString()));
		group.add(new Label(_dragon.legLengthAlleleTwo().get().toString()));
		group.add(new Label("Feet Type"));
		group.add(new Label(_dragon.feetTypeAlleleOne().get().toString()));
		group.add(new Label(_dragon.feetTypeAlleleTwo().get().toString()));
		group.add(new Label("Coat Type"));
		group.add(new Label(_dragon.coatTypeAlleleOne().get().toString()));
		group.add(new Label(_dragon.coatTypeAlleleTwo().get().toString()));
		group.add(new Label("Wing Size"));
		group.add(new Label(_dragon.wingSizeAlleleOne().get().toString()));
		group.add(new Label(_dragon.wingSizeAlleleTwo().get().toString()));
		group.add(new Label("Tail Length"));
		group.add(new Label(_dragon.tailLengthAlleleOne().get().toString()));
		group.add(new Label(_dragon.tailLengthAlleleTwo().get().toString()));
		group.add(new Label("Phyisque"));
		group.add(new Label(_dragon.physiqueAlleleOne().get().toString()));
		group.add(new Label(_dragon.physiqueAlleleTwo().get().toString()));
		group.add(new Label("Lung Size"));
		group.add(new Label(_dragon.lungSizeAlleleOne().get().toString()));
		group.add(new Label(_dragon.lungSizeAlleleTwo().get().toString()));
		group.add(new Label("Heart Size"));
		group.add(new Label(_dragon.heartSizeAlleleOne().get().toString()));
		group.add(new Label(_dragon.heartSizeAlleleTwo().get().toString()));

		return group;
	}

}
