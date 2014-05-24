package au.edu.unimelb.cis.dragons.core.controller;

import static au.edu.unimelb.cis.dragons.core.GlobalConfig.*;
import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.Gene;
import au.edu.unimelb.cis.dragons.core.model.BreedingMinigameState;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.view.AllelePairSelectorView;
import react.Function;
import react.UnitSlot;
import tripleplay.ui.Group;
import tripleplay.ui.Label;
import tripleplay.ui.MigGroup;
import tripleplay.ui.MigLayout;

/**
 * Allows the user to complete a punnet sqaure for a particulary gene locus.
 *
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class GenotypeSelectorViewController extends ViewController {

	// The two dragon's that are being crossed.
    private Dragon _dragon;
    private Dragon _otherDragon;
    // The gene locus that we're crossing on.
    private Gene _gene;
    // The state of the minigame for all genes.
    private BreedingMinigameState _miniGameState;

    public GenotypeSelectorViewController(Dragon dragon, Dragon otherDragon,
            Gene gene, BreedingMinigameState miniGameState) {
        _dragon = dragon;
        _otherDragon = otherDragon;
        _gene = gene;
        _miniGameState = miniGameState;
    }

    @Override
    protected Group createInterface() {
    	// Title up top, cross below.
    	MigGroup group = new MigGroup(new MigLayout("fill, insets 0, gap 0",
    			"[100%, fill]", "[][grow]"));
        group.add(makeBoldLabel("Cross for " + _gene.toString()), "cell 0 0");

		// Wrapper for the cross.
		MigGroup crossGroup = new MigGroup(new MigLayout("fill, insets 0, gap 0",
				"[][][][]",
				"[][][][]"));
		group.add(crossGroup, "cell 0 1");

		// Display the name of the dragons on the side and top.
		crossGroup.add(makeBoldLabel(_otherDragon.name().get()), "cell 2 0, span 2 1");
		crossGroup.add(makeBoldLabel(_dragon.name().get()), "cell 0 2, span 1 2");

		// Labels for the allels being supplied by each dragon go at the top of each columns and
		// at the left of each row.
		Label dragonOneAlleleOneLabel = makePlainLabel();
		crossGroup.add(dragonOneAlleleOneLabel, "cell 1 2");
		Label dragonOneAlleleTwoLabel = makePlainLabel();
        crossGroup.add(dragonOneAlleleTwoLabel, "cell 1 3");
		Label dragonTwoAlleleOneLabel = makePlainLabel();
        crossGroup.add(dragonTwoAlleleOneLabel, "cell 2 1");
		Label dragonTwoAlleleTwoLabel = makePlainLabel();
        crossGroup.add(dragonTwoAlleleTwoLabel, "cell 3 1");

		Function<Allele, String> alleleToStringMapper = new Function<Allele, String>() {
			@Override
			public String apply(Allele input) {
				return input.toString();
			}
		};

		UnitSlot allelesUpdatedHandler = null;

		final AllelePairSelectorView topLeftSelector = new AllelePairSelectorView(_gene.getValidAlleles());
        crossGroup.add(topLeftSelector.view(), "cell 2 2");
        final AllelePairSelectorView topRightSelector = new AllelePairSelectorView(_gene.getValidAlleles());
        crossGroup.add(topRightSelector.view(), "cell 3 2");
        final AllelePairSelectorView bottomleftSelector = new AllelePairSelectorView(_gene.getValidAlleles());
        crossGroup.add(bottomleftSelector.view(), "cell 2 3");
        final AllelePairSelectorView bottomRightSelector = new AllelePairSelectorView(_gene.getValidAlleles());
        crossGroup.add(bottomRightSelector.view(), "cell 3 3");

		// Fill in the titles.
        switch (_gene) {
        case CoatType:
            allelesUpdatedHandler = new UnitSlot() {
                @Override
                public void onEmit() {
                    _miniGameState.setCoatTypeSelectedPairs(topLeftSelector.selectedAlleles().get(),
                    		topRightSelector.selectedAlleles().get(),
                    		bottomleftSelector.selectedAlleles().get(),
                    		bottomRightSelector.selectedAlleles().get());
                }
            };
            _dragon.coatTypeAlleleOne().map(alleleToStringMapper).connectNotify(dragonOneAlleleOneLabel.text.slot());
            _dragon.coatTypeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonOneAlleleTwoLabel.text.slot());
            _otherDragon.coatTypeAlleleOne().map(alleleToStringMapper).connectNotify(dragonTwoAlleleOneLabel.text.slot());
            _otherDragon.coatTypeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonTwoAlleleTwoLabel.text.slot());
            break;
        case FeetType:
            allelesUpdatedHandler = new UnitSlot() {
                @Override
                public void onEmit() {
                    _miniGameState.setFeetTypeSelectedPairs(topLeftSelector.selectedAlleles().get(),
                            topRightSelector.selectedAlleles().get(),
                            bottomleftSelector.selectedAlleles().get(),
                            bottomRightSelector.selectedAlleles().get());
                }
            };
            _dragon.feetTypeAlleleOne().map(alleleToStringMapper).connectNotify(dragonOneAlleleOneLabel.text.slot());
            _dragon.feetTypeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonOneAlleleTwoLabel.text.slot());
            _otherDragon.feetTypeAlleleOne().map(alleleToStringMapper).connectNotify(dragonTwoAlleleOneLabel.text.slot());
            _otherDragon.feetTypeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonTwoAlleleTwoLabel.text.slot());
            break;
        case HeartSize:
            allelesUpdatedHandler = new UnitSlot() {
                @Override
                public void onEmit() {
                    _miniGameState.setHeartSizeSelectedPairs(topLeftSelector.selectedAlleles().get(),
                            topRightSelector.selectedAlleles().get(),
                            bottomleftSelector.selectedAlleles().get(),
                            bottomRightSelector.selectedAlleles().get());
                }
            };
            _dragon.heartSizeAlleleOne().map(alleleToStringMapper).connectNotify(dragonOneAlleleOneLabel.text.slot());
            _dragon.heartSizeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonOneAlleleTwoLabel.text.slot());
            _otherDragon.heartSizeAlleleOne().map(alleleToStringMapper).connectNotify(dragonTwoAlleleOneLabel.text.slot());
            _otherDragon.heartSizeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonTwoAlleleTwoLabel.text.slot());
            break;
        case LegLength:
            allelesUpdatedHandler = new UnitSlot() {
                @Override
                public void onEmit() {
                    _miniGameState.setLegLengthSelectedPairs(topLeftSelector.selectedAlleles().get(),
                            topRightSelector.selectedAlleles().get(),
                            bottomleftSelector.selectedAlleles().get(),
                            bottomRightSelector.selectedAlleles().get());
                }
            };
            _dragon.legLengthAlleleOne().map(alleleToStringMapper).connectNotify(dragonOneAlleleOneLabel.text.slot());
            _dragon.legLengthAlleleTwo().map(alleleToStringMapper).connectNotify(dragonOneAlleleTwoLabel.text.slot());
            _otherDragon.legLengthAlleleOne().map(alleleToStringMapper).connectNotify(dragonTwoAlleleOneLabel.text.slot());
            _otherDragon.legLengthAlleleTwo().map(alleleToStringMapper).connectNotify(dragonTwoAlleleTwoLabel.text.slot());
            break;
        case LungSize:
            allelesUpdatedHandler = new UnitSlot() {
                @Override
                public void onEmit() {
                    _miniGameState.setLungSizeSelectedPairs(topLeftSelector.selectedAlleles().get(),
                            topRightSelector.selectedAlleles().get(),
                            bottomleftSelector.selectedAlleles().get(),
                            bottomRightSelector.selectedAlleles().get());
                }
            };
            _dragon.lungSizeAlleleOne().map(alleleToStringMapper).connectNotify(dragonOneAlleleOneLabel.text.slot());
            _dragon.lungSizeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonOneAlleleTwoLabel.text.slot());
            _otherDragon.lungSizeAlleleOne().map(alleleToStringMapper).connectNotify(dragonTwoAlleleOneLabel.text.slot());
            _otherDragon.lungSizeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonTwoAlleleTwoLabel.text.slot());
            break;
        case Physique:
            allelesUpdatedHandler = new UnitSlot() {
                @Override
                public void onEmit() {
                    _miniGameState.setPhysiqueSelectedPairs(topLeftSelector.selectedAlleles().get(),
                            topRightSelector.selectedAlleles().get(),
                            bottomleftSelector.selectedAlleles().get(),
                            bottomRightSelector.selectedAlleles().get());
                }
            };
            _dragon.physiqueAlleleOne().map(alleleToStringMapper).connectNotify(dragonOneAlleleOneLabel.text.slot());
            _dragon.physiqueAlleleTwo().map(alleleToStringMapper).connectNotify(dragonOneAlleleTwoLabel.text.slot());
            _otherDragon.physiqueAlleleOne().map(alleleToStringMapper).connectNotify(dragonTwoAlleleOneLabel.text.slot());
            _otherDragon.physiqueAlleleTwo().map(alleleToStringMapper).connectNotify(dragonTwoAlleleTwoLabel.text.slot());
            break;
        case TailLength:
            allelesUpdatedHandler = new UnitSlot() {
                @Override
                public void onEmit() {
                    _miniGameState.setTailLengthSelectedPairs(topLeftSelector.selectedAlleles().get(),
                            topRightSelector.selectedAlleles().get(),
                            bottomleftSelector.selectedAlleles().get(),
                            bottomRightSelector.selectedAlleles().get());
                }
            };
            _dragon.tailLengthAlleleOne().map(alleleToStringMapper).connectNotify(dragonOneAlleleOneLabel.text.slot());
            _dragon.tailLengthAlleleTwo().map(alleleToStringMapper).connectNotify(dragonOneAlleleTwoLabel.text.slot());
            _otherDragon.tailLengthAlleleOne().map(alleleToStringMapper).connectNotify(dragonTwoAlleleOneLabel.text.slot());
            _otherDragon.tailLengthAlleleTwo().map(alleleToStringMapper).connectNotify(dragonTwoAlleleTwoLabel.text.slot());
            break;
        case WingSize:
            allelesUpdatedHandler = new UnitSlot() {
                @Override
                public void onEmit() {
                    _miniGameState.setWingSizeSelectedPairs(topLeftSelector.selectedAlleles().get(),
                            topRightSelector.selectedAlleles().get(),
                            bottomleftSelector.selectedAlleles().get(),
                            bottomRightSelector.selectedAlleles().get());
                }
            };
            _dragon.wingSizeAlleleOne().map(alleleToStringMapper).connectNotify(dragonOneAlleleOneLabel.text.slot());
            _dragon.wingSizeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonOneAlleleTwoLabel.text.slot());
            _otherDragon.wingSizeAlleleOne().map(alleleToStringMapper).connectNotify(dragonTwoAlleleOneLabel.text.slot());
            _otherDragon.wingSizeAlleleTwo().map(alleleToStringMapper).connectNotify(dragonTwoAlleleTwoLabel.text.slot());
            break;
        default:
        	throw new RuntimeException("Got a gene that shouldn't exist.");
        }

        topLeftSelector.selectedAlleles().connectNotify(allelesUpdatedHandler);
        topRightSelector.selectedAlleles().connectNotify(allelesUpdatedHandler);
        bottomleftSelector.selectedAlleles().connectNotify(allelesUpdatedHandler);
        bottomRightSelector.selectedAlleles().connectNotify(allelesUpdatedHandler);

        return group;
    }
}
