package au.edu.unimelb.cis.dragons.core.model;

import react.UnitSlot;
import react.Value;
import react.ValueView;
import au.edu.unimelb.cis.dragons.core.Pair;
import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.Gene;

/**
 * Represents the state of all Genes in the breeding minigame.
 * 
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class BreedingMinigameState {

	private static class BreedingMinigameGeneState {

		// First allele from both dragons.
		private Pair<Allele, Allele> _allelePairOne;
		// First allele from dragon one with second allele from dragon two.
		private Pair<Allele, Allele> _allelePairTwo;
		// Second allele from dragon one with the first allele from dragon one.
		private Pair<Allele, Allele> _allelePairThree;
		// Second allele from both dragons.
		private Pair<Allele, Allele> _allelePairFour;

		// First dragon in the cross.
		private final Dragon _firstDragon;
		// Second dragon in the cross.
		private final Dragon _secondDragon;
		// The gene that we're crossing.
		private final Gene _gene;
		
		private final Value<Boolean> _selectedPairsAreCorrect = Value.create(false);

		public BreedingMinigameGeneState(Dragon firstDragon, Dragon secondDragon, Gene gene) {
			_firstDragon = firstDragon;
			_secondDragon = secondDragon;
			_gene = gene;
		}
		
		public ValueView<Boolean> selectedPairsAreCorrect() {
			return _selectedPairsAreCorrect;
		}

	    /**
	     * Set the pairs that were selected by the user.
	     * @param first
	     * @param second
	     * @param third
	     * @param fourth
	     */
		public void setSelectedPairs(Pair<Allele, Allele> first,
				Pair<Allele, Allele> second, Pair<Allele, Allele> third,
				Pair<Allele, Allele> fourth) {
			_allelePairOne = first;
			_allelePairTwo = second;
			_allelePairThree = third;
			_allelePairFour = fourth;
			_selectedPairsAreCorrect.update(checkThatSelectedPairsAreCorrect());
		}

		/**
		 * @return True if the correct alleles are selected.
		 */
		private boolean checkThatSelectedPairsAreCorrect() {

			// Shut up the compiler.
			Allele firstOne = null;
			Allele firstTwo = null;
			Allele secondOne = null;
			Allele secondTwo = null;

			switch (_gene) {
			case CoatType:
				firstOne = _firstDragon.coatTypeAlleleOne().get();
				firstTwo = _firstDragon.coatTypeAlleleTwo().get();
				secondOne = _secondDragon.coatTypeAlleleOne().get();
				secondTwo = _secondDragon.coatTypeAlleleTwo().get();
				break;
			case FeetType:
				firstOne = _firstDragon.feetTypeAlleleOne().get();
				firstTwo = _firstDragon.feetTypeAlleleTwo().get();
				secondOne = _secondDragon.feetTypeAlleleOne().get();
				secondTwo = _secondDragon.feetTypeAlleleTwo().get();
				break;
			case HeartSize:
				firstOne = _firstDragon.heartSizeAlleleOne().get();
				firstTwo = _firstDragon.heartSizeAlleleTwo().get();
				secondOne = _secondDragon.heartSizeAlleleOne().get();
				secondTwo = _secondDragon.heartSizeAlleleTwo().get();
				break;
			case LegLength:
				firstOne = _firstDragon.legLengthAlleleOne().get();
				firstTwo = _firstDragon.legLengthAlleleTwo().get();
				secondOne = _secondDragon.legLengthAlleleOne().get();
				secondTwo = _secondDragon.legLengthAlleleTwo().get();
				break;
			case LungSize:
				firstOne = _firstDragon.lungSizeAlleleOne().get();
				firstTwo = _firstDragon.lungSizeAlleleTwo().get();
				secondOne = _secondDragon.lungSizeAlleleOne().get();
				secondTwo = _secondDragon.lungSizeAlleleTwo().get();
				break;
			case Physique:
				firstOne = _firstDragon.physiqueAlleleOne().get();
				firstTwo = _firstDragon.physiqueAlleleTwo().get();
				secondOne = _secondDragon.physiqueAlleleOne().get();
				secondTwo = _secondDragon.physiqueAlleleTwo().get();
				break;
			case TailLength:
				firstOne = _firstDragon.tailLengthAlleleOne().get();
				firstTwo = _firstDragon.tailLengthAlleleTwo().get();
				secondOne = _secondDragon.tailLengthAlleleOne().get();
				secondTwo = _secondDragon.tailLengthAlleleTwo().get();
				break;
			case WingSize:
				firstOne = _firstDragon.wingSizeAlleleOne().get();
				firstTwo = _firstDragon.wingSizeAlleleTwo().get();
				secondOne = _secondDragon.wingSizeAlleleOne().get();
				secondTwo = _secondDragon.wingSizeAlleleTwo().get();
				break;
			default:
				new RuntimeException("Got a unknown gene.");
			}

			return _allelePairOne.getFirst() == firstOne
					&& _allelePairOne.getSecond() == secondOne
					&& _allelePairTwo.getFirst() == firstOne
					&& _allelePairTwo.getSecond() == secondTwo
					&& _allelePairThree.getFirst() == firstTwo
					&& _allelePairThree.getSecond() == secondOne
					&& _allelePairFour.getFirst() == firstTwo
					&& _allelePairFour.getSecond() == secondTwo;
		}
	}

	private final Dragon _firstDragon;
	private final Dragon _secondDragon;

	private final BreedingMinigameGeneState _coatTypeState;
	private final BreedingMinigameGeneState _feetTypeState;
	private final BreedingMinigameGeneState _heartSizeState;
	private final BreedingMinigameGeneState _legLengthState;
	private final BreedingMinigameGeneState _lungSizeState;
	private final BreedingMinigameGeneState _physiqueState;
	private final BreedingMinigameGeneState _tailLengthState;
	private final BreedingMinigameGeneState _wingSizeState;
	
	// A view for the state of the overall game.
	private Value<Boolean> _allStatePairsAreCorrect = null;

	public BreedingMinigameState(final Dragon firstDragon,
			final Dragon secondDragon) {
		_firstDragon = firstDragon;
		_secondDragon = secondDragon;

		_coatTypeState = new BreedingMinigameGeneState(_firstDragon, _secondDragon, Gene.CoatType);
		_feetTypeState = new BreedingMinigameGeneState(_firstDragon, _secondDragon, Gene.FeetType);
		_heartSizeState = new BreedingMinigameGeneState(_firstDragon, _secondDragon, Gene.HeartSize);
		_legLengthState = new BreedingMinigameGeneState(_firstDragon, _secondDragon, Gene.LegLength);
		_lungSizeState = new BreedingMinigameGeneState(_firstDragon, _secondDragon, Gene.LungSize);
		_physiqueState = new BreedingMinigameGeneState(_firstDragon, _secondDragon, Gene.Physique);
		_tailLengthState = new BreedingMinigameGeneState(_firstDragon, _secondDragon, Gene.TailLength);
		_wingSizeState = new BreedingMinigameGeneState(_firstDragon, _secondDragon, Gene.WingSize);
		
		_allStatePairsAreCorrect = Value.create(Boolean.FALSE);
		_lungSizeState.selectedPairsAreCorrect().connectNotify(new UnitSlot() {
			@Override
			public void onEmit() {
				_allStatePairsAreCorrect.update(
						_coatTypeState.selectedPairsAreCorrect().get() &&
						_feetTypeState.selectedPairsAreCorrect().get() &&
						_heartSizeState.selectedPairsAreCorrect().get() &&
						_legLengthState.selectedPairsAreCorrect().get() &&
						_lungSizeState.selectedPairsAreCorrect().get() &&
						_physiqueState.selectedPairsAreCorrect().get() &&
						_tailLengthState.selectedPairsAreCorrect().get() &&
						_wingSizeState.selectedPairsAreCorrect().get());
			}
		});
	}

	public void setCoatTypeSelectedPairs(Pair<Allele, Allele> firstPair,
			Pair<Allele, Allele> secondPair, Pair<Allele, Allele> thirdPair,
			Pair<Allele, Allele> fourthPair) {
		_coatTypeState.setSelectedPairs(firstPair, secondPair, thirdPair, fourthPair);
	}

	public void setFeetTypeSelectedPairs(Pair<Allele, Allele> firstPair,
			Pair<Allele, Allele> secondPair, Pair<Allele, Allele> thirdPair,
			Pair<Allele, Allele> fourthPair) {
		_feetTypeState.setSelectedPairs(firstPair, secondPair, thirdPair, fourthPair);
	}

	public void setHeartSizeSelectedPairs(Pair<Allele, Allele> firstPair,
			Pair<Allele, Allele> secondPair, Pair<Allele, Allele> thirdPair,
			Pair<Allele, Allele> fourthPair) {
		_heartSizeState.setSelectedPairs(firstPair, secondPair, thirdPair, fourthPair);
	}

	public void setLegLengthSelectedPairs(Pair<Allele, Allele> firstPair,
			Pair<Allele, Allele> secondPair, Pair<Allele, Allele> thirdPair,
			Pair<Allele, Allele> fourthPair) {
		_legLengthState.setSelectedPairs(firstPair, secondPair, thirdPair, fourthPair);
	}

	public void setLungSizeSelectedPairs(Pair<Allele, Allele> firstPair,
			Pair<Allele, Allele> secondPair, Pair<Allele, Allele> thirdPair,
			Pair<Allele, Allele> fourthPair) {
		_lungSizeState.setSelectedPairs(firstPair, secondPair, thirdPair, fourthPair);
	}

	public void setPhysiqueSelectedPairs(Pair<Allele, Allele> firstPair,
			Pair<Allele, Allele> secondPair, Pair<Allele, Allele> thirdPair,
			Pair<Allele, Allele> fourthPair) {
		_physiqueState.setSelectedPairs(firstPair, secondPair, thirdPair, fourthPair);
	}

	public void setTailLengthSelectedPairs(Pair<Allele, Allele> firstPair,
			Pair<Allele, Allele> secondPair, Pair<Allele, Allele> thirdPair,
			Pair<Allele, Allele> fourthPair) {
		_tailLengthState.setSelectedPairs(firstPair, secondPair, thirdPair, fourthPair);
	}

	public void setWingSizeSelectedPairs(Pair<Allele, Allele> firstPair,
			Pair<Allele, Allele> secondPair, Pair<Allele, Allele> thirdPair,
			Pair<Allele, Allele> fourthPair) {
		_wingSizeState.setSelectedPairs(firstPair, secondPair, thirdPair, fourthPair);
	}

	public ValueView<Boolean> coatTypeStatePairsAreCorrect() {
		return _coatTypeState.selectedPairsAreCorrect();
	}

	public ValueView<Boolean> feetTypeStatePairsAreCorrect() {
		return _feetTypeState.selectedPairsAreCorrect();
	}

	public ValueView<Boolean> heartSizeStatePairsAreCorrect() {
		return _heartSizeState.selectedPairsAreCorrect();
	}

	public ValueView<Boolean> legLengthStatePairsAreCorrect() {
		return _legLengthState.selectedPairsAreCorrect();
	}

	public ValueView<Boolean> lungSizeStatePairsAreCorrect() {
		return _lungSizeState.selectedPairsAreCorrect();
	}

	public ValueView<Boolean> physiqueStatePairsAreCorrect() {
		return _physiqueState.selectedPairsAreCorrect();
	}

	public ValueView<Boolean> tailLengthStatePairsAreCorrect() {
		return _tailLengthState.selectedPairsAreCorrect();
	}

	public ValueView<Boolean> wingSizeStatePairsAreCorrect() {
		return _wingSizeState.selectedPairsAreCorrect();
	}
	
	public ValueView<Boolean> allStatePairsAreCorrect() {
		return _allStatePairsAreCorrect;
	}
}
