package au.edu.unimelb.cis.dragons.core.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import react.Value;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.Gene;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;

/**
 * Tests for {@link Dragon}.
 *  
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class DragonTest {

    private static Integer _id = 0;

    @Mock
    private GameState _gameStateMock;

    @Mock
    private Gene _legLengthMock;

    @Mock
    private Gene _feetTypeMock;

    @Mock
    private Gene _coatTypeMock;

    @Mock
    private Gene _wingSizeMock;

    @Mock
    private Gene _tailLengthMock;

    @Mock
    private Gene _physiqueMock;

    @Mock
    private Gene _lungSizeMock;

    @Mock
    private Gene _heartSizeMock;

    @Mock
    private Dragon _dragon;

    @Before
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
        _dragon = new Dragon(_gameStateMock, _id, _legLengthMock, _feetTypeMock,
                _coatTypeMock, _wingSizeMock, _tailLengthMock, _physiqueMock,
                _lungSizeMock, _heartSizeMock);
    }

    @After
    public void tearDown() throws Exception {
        _dragon = null;
    }

    @Test
    public void returnsId() {
        assertEquals(_dragon.id(), _id);
    }

    @Test
    public void legLengthAlleleOne() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLegLengthAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ShortLegs.toString()));
        assertEquals(Allele.ShortLegs, _dragon.legLengthAlleleOne().get());
    }

    @Test
    public void legLengthAlleleTwo() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLegLengthAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongLegs.toString()));
        assertEquals(Allele.LongLegs, _dragon.legLengthAlleleTwo().get());
    }

    @Test
    public void feetTypeAlleleOne() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonFeetTypeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ClawedFeet.toString()));
        assertEquals(Allele.ClawedFeet, _dragon.feetTypeAlleleOne().get());
    }

    @Test
    public void feetTypeAlleleTwo() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonFeetTypeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.WebbedFeet.toString()));
        assertEquals(Allele.WebbedFeet, _dragon.feetTypeAlleleTwo().get());
    }

    @Test
    public void coatTypeAlleleOne() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonCoatTypeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ScaledCoat.toString()));
        assertEquals(Allele.ScaledCoat, _dragon.coatTypeAlleleOne().get());
    }

    @Test
    public void coatTypeAlleleTwo() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonCoatTypeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.FurryCoat.toString()));
        assertEquals(Allele.FurryCoat, _dragon.coatTypeAlleleTwo().get());
    }

    @Test
    public void wingSizeAlleleOne() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonWingSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.SmallWings.toString()));
        assertEquals(Allele.SmallWings, _dragon.wingSizeAlleleOne().get());
    }

    @Test
    public void wingSizeAlleleTwo() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonWingSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LargeWings.toString()));
        assertEquals(Allele.LargeWings, _dragon.wingSizeAlleleTwo().get());
    }

    @Test
    public void physiqueAlleleOne() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.MuscularPhysique.toString()));
        assertEquals(Allele.MuscularPhysique, _dragon.physiqueAlleleOne().get());
    }

    @Test
    public void physiqueAlleleTwo() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        assertEquals(Allele.LeanPhysique, _dragon.physiqueAlleleTwo().get());
    }

    @Test
    public void lungSizeAlleleOne() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.AdditiveLungs.toString()));
        assertEquals(Allele.AdditiveLungs, _dragon.lungSizeAlleleOne().get());
    }

    @Test
    public void lungSizeAlleleTwo() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainLungs.toString()));
        assertEquals(Allele.PlainLungs, _dragon.lungSizeAlleleTwo().get());
    }

    @Test
    public void heartSizeAlleleOne() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.AdditiveHeart.toString()));
        assertEquals(Allele.AdditiveHeart, _dragon.heartSizeAlleleOne().get());
    }

    @Test
    public void heartSizeAlleleTwo() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainHeart.toString()));
        assertEquals(Allele.PlainHeart, _dragon.heartSizeAlleleTwo().get());
    }

    @Test
    public void legLengthPhenotype() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLegLengthAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ShortLegs.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLegLengthAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongLegs.toString()));
        when(_legLengthMock.getPhenotypeForGenotype(
                Allele.ShortLegs, Allele.LongLegs)).thenReturn(Phenotype.ShortLegs);

        assertEquals(Phenotype.ShortLegs, _dragon.legLength().get());
    }

    @Test
    public void feetTypePhenotype() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonFeetTypeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ClawedFeet.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonFeetTypeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.WebbedFeet.toString()));
        when(_feetTypeMock.getPhenotypeForGenotype(
                Allele.ClawedFeet, Allele.WebbedFeet)).thenReturn(Phenotype.ClawedFeet);

        assertEquals(Phenotype.ClawedFeet, _dragon.feetType().get());
    }

    @Test
    public void coatTypePhenotype() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonCoatTypeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ScaledCoat.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonCoatTypeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.FurryCoat.toString()));
        when(_coatTypeMock.getPhenotypeForGenotype(
                Allele.ScaledCoat, Allele.FurryCoat)).thenReturn(Phenotype.ScaledCoat);

        assertEquals(Phenotype.ScaledCoat, _dragon.coatType().get());
    }

    @Test
    public void wingSizePhenotype() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonWingSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.SmallWings.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonWingSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LargeWings.toString()));
        when(_wingSizeMock.getPhenotypeForGenotype(
                Allele.SmallWings, Allele.LargeWings)).thenReturn(Phenotype.SmallWings);

        assertEquals(Phenotype.SmallWings, _dragon.wingSize().get());
    }

    @Test
    public void tailLengthPhenotype() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonTailLengthAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ShortTail.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonTailLengthAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongTail.toString()));
        when(_tailLengthMock.getPhenotypeForGenotype(
                Allele.ShortTail, Allele.LongTail)).thenReturn(Phenotype.ShortTail);

        assertEquals(Phenotype.ShortTail, _dragon.tailLength().get());
    }

    @Test
    public void physiquePhenotype() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.MuscularPhysique.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        when(_physiqueMock.getPhenotypeForGenotype(
                Allele.MuscularPhysique, Allele.LeanPhysique)).thenReturn(Phenotype.MuscularPhysique);

        assertEquals(Phenotype.MuscularPhysique, _dragon.physique().get());
    }

    @Test
    public void lungSizePhenotype() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.AdditiveLungs.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainLungs.toString()));
        when(_lungSizeMock.getPhenotypeForGenotype(
                Allele.AdditiveLungs, Allele.PlainLungs)).thenReturn(Phenotype.MediumLungs);

        assertEquals(Phenotype.MediumLungs, _dragon.lungSize().get());
    }

    @Test
    public void heartSizePhenotype() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.AdditiveHeart.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainHeart.toString()));
        when(_heartSizeMock.getPhenotypeForGenotype(
                Allele.AdditiveHeart, Allele.PlainHeart)).thenReturn(Phenotype.MediumHeart);

        assertEquals(Phenotype.MediumHeart, _dragon.heartSize().get());
    }

    @Test
    public void speedAttributeBase() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLegLengthAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ShortLegs.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLegLengthAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongLegs.toString()));
        when(_legLengthMock.getPhenotypeForGenotype(
                Allele.ShortLegs, Allele.LongLegs)).thenReturn(Phenotype.ShortLegs);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.MuscularPhysique.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        when(_physiqueMock.getPhenotypeForGenotype(
                Allele.MuscularPhysique, Allele.LeanPhysique)).thenReturn(Phenotype.MuscularPhysique);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonSpeedAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(4), _dragon.speed().get());
    }

    @Test
    public void speedAttributeTrainingBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLegLengthAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ShortLegs.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLegLengthAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongLegs.toString()));
        when(_legLengthMock.getPhenotypeForGenotype(
                Allele.ShortLegs, Allele.LongLegs)).thenReturn(Phenotype.ShortLegs);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.MuscularPhysique.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        when(_physiqueMock.getPhenotypeForGenotype(
                Allele.MuscularPhysique, Allele.LeanPhysique)).thenReturn(Phenotype.MuscularPhysique);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonSpeedAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("1"));

        assertEquals(Integer.valueOf(6), _dragon.speed().get());
    }

    @Test
    public void speedAttributeLongLegsBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLegLengthAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongLegs.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLegLengthAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongLegs.toString()));
        when(_legLengthMock.getPhenotypeForGenotype(
                Allele.LongLegs, Allele.LongLegs)).thenReturn(Phenotype.LongLegs);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.MuscularPhysique.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        when(_physiqueMock.getPhenotypeForGenotype(
                Allele.MuscularPhysique, Allele.LeanPhysique)).thenReturn(Phenotype.MuscularPhysique);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonSpeedAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(6), _dragon.speed().get());
    }

    @Test
    public void speedAttributeLeanPhysiqueBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLegLengthAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ShortLegs.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLegLengthAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongLegs.toString()));
        when(_legLengthMock.getPhenotypeForGenotype(
                Allele.ShortLegs, Allele.LongLegs)).thenReturn(Phenotype.ShortLegs);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        when(_physiqueMock.getPhenotypeForGenotype(
                Allele.LeanPhysique, Allele.LeanPhysique)).thenReturn(Phenotype.LeanPhysique);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonSpeedAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(6), _dragon.speed().get());
    }

    @Test
    public void enduranceAttributeBase() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainLungs.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainLungs.toString()));
        when(_lungSizeMock.getPhenotypeForGenotype(
                Allele.PlainLungs, Allele.PlainLungs)).thenReturn(Phenotype.SmallLungs);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainHeart.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainHeart.toString()));
        when(_heartSizeMock.getPhenotypeForGenotype(
                Allele.PlainHeart, Allele.PlainHeart)).thenReturn(Phenotype.SmallHeart);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonEnduranceAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(4), _dragon.endurance().get());
    }

    @Test
    public void enduranceAttributeTrainingBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainLungs.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainLungs.toString()));
        when(_lungSizeMock.getPhenotypeForGenotype(
                Allele.PlainLungs, Allele.PlainLungs)).thenReturn(Phenotype.SmallLungs);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainHeart.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainHeart.toString()));
        when(_heartSizeMock.getPhenotypeForGenotype(
                Allele.PlainHeart, Allele.PlainHeart)).thenReturn(Phenotype.SmallHeart);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonEnduranceAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("1"));

        assertEquals(Integer.valueOf(6), _dragon.endurance().get());
    }

    @Test
    public void enduranceAttributeMediumHeartBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainLungs.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainLungs.toString()));
        when(_lungSizeMock.getPhenotypeForGenotype(
                Allele.PlainLungs, Allele.PlainLungs)).thenReturn(Phenotype.SmallLungs);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.AdditiveHeart.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainHeart.toString()));
        when(_heartSizeMock.getPhenotypeForGenotype(
                Allele.AdditiveHeart, Allele.PlainHeart)).thenReturn(Phenotype.MediumHeart);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonEnduranceAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(5), _dragon.endurance().get());
    }

    @Test
    public void enduranceAttributeLargeHeartBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainLungs.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainLungs.toString()));
        when(_lungSizeMock.getPhenotypeForGenotype(
                Allele.PlainLungs, Allele.PlainLungs)).thenReturn(Phenotype.SmallLungs);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.AdditiveHeart.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.AdditiveHeart.toString()));
        when(_heartSizeMock.getPhenotypeForGenotype(
                Allele.AdditiveHeart, Allele.AdditiveHeart)).thenReturn(Phenotype.LargeHeart);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonEnduranceAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(6), _dragon.endurance().get());
    }

    @Test
    public void enduranceAttributeMediumLungsBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.AdditiveLungs.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainLungs.toString()));
        when(_lungSizeMock.getPhenotypeForGenotype(
                Allele.AdditiveLungs, Allele.PlainLungs)).thenReturn(Phenotype.MediumLungs);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainHeart.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainHeart.toString()));
        when(_heartSizeMock.getPhenotypeForGenotype(
                Allele.PlainHeart, Allele.PlainHeart)).thenReturn(Phenotype.SmallHeart);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonEnduranceAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(5), _dragon.endurance().get());
    }

    @Test
    public void enduranceAttributeLargeLungsBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.AdditiveLungs.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonLungSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.AdditiveLungs.toString()));
        when(_lungSizeMock.getPhenotypeForGenotype(
                Allele.AdditiveLungs, Allele.AdditiveLungs)).thenReturn(Phenotype.LargeLungs);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainHeart.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonHeartSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.PlainHeart.toString()));
        when(_heartSizeMock.getPhenotypeForGenotype(
                Allele.PlainHeart, Allele.PlainHeart)).thenReturn(Phenotype.SmallHeart);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonEnduranceAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(6), _dragon.endurance().get());
    }

    @Test
    public void balanceAttributeBase() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonWingSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.SmallWings.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonWingSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LargeWings.toString()));
        when(_wingSizeMock.getPhenotypeForGenotype(
                Allele.SmallWings, Allele.LargeWings)).thenReturn(Phenotype.SmallWings);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonTailLengthAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ShortTail.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonTailLengthAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongTail.toString()));
        when(_tailLengthMock.getPhenotypeForGenotype(
                Allele.ShortTail, Allele.LongTail)).thenReturn(Phenotype.ShortTail);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonBalanceAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(4), _dragon.balance().get());
    }

    @Test
    public void balanceTrainingBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonWingSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.SmallWings.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonWingSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LargeWings.toString()));
        when(_wingSizeMock.getPhenotypeForGenotype(
                Allele.SmallWings, Allele.LargeWings)).thenReturn(Phenotype.SmallWings);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonTailLengthAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ShortTail.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonTailLengthAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongTail.toString()));
        when(_tailLengthMock.getPhenotypeForGenotype(
                Allele.ShortTail, Allele.LongTail)).thenReturn(Phenotype.ShortTail);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonBalanceAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("1"));

        assertEquals(Integer.valueOf(6), _dragon.balance().get());
    }

    public void balanceAttributeLargeWingsBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonWingSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.LargeWings.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonWingSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LargeWings.toString()));
        when(_wingSizeMock.getPhenotypeForGenotype(
                Allele.LargeWings, Allele.LargeWings)).thenReturn(Phenotype.LargeWings);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonTailLengthAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ShortTail.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonTailLengthAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongTail.toString()));
        when(_tailLengthMock.getPhenotypeForGenotype(
                Allele.ShortTail, Allele.LongTail)).thenReturn(Phenotype.ShortTail);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonBalanceAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(6), _dragon.balance().get());
    }

    public void balanceAttributeLongTailBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonWingSizeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.SmallWings.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonWingSizeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LargeWings.toString()));
        when(_wingSizeMock.getPhenotypeForGenotype(
                Allele.SmallWings, Allele.LargeWings)).thenReturn(Phenotype.SmallWings);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonTailLengthAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongTail.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonTailLengthAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LongTail.toString()));
        when(_tailLengthMock.getPhenotypeForGenotype(
                Allele.LongTail, Allele.LongTail)).thenReturn(Phenotype.LongTail);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonBalanceAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(6), _dragon.balance().get());
    }

    @Test
    public void weightAttributeBase() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonCoatTypeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ScaledCoat.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonCoatTypeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.FurryCoat.toString()));
        when(_coatTypeMock.getPhenotypeForGenotype(
                Allele.ScaledCoat, Allele.FurryCoat)).thenReturn(Phenotype.ScaledCoat);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        when(_physiqueMock.getPhenotypeForGenotype(
                Allele.LeanPhysique, Allele.LeanPhysique)).thenReturn(Phenotype.LeanPhysique);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonWeightAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(4), _dragon.weight().get());
    }

    @Test
    public void weightAttributeTrainingBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonCoatTypeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ScaledCoat.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonCoatTypeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.FurryCoat.toString()));
        when(_coatTypeMock.getPhenotypeForGenotype(
                Allele.ScaledCoat, Allele.FurryCoat)).thenReturn(Phenotype.ScaledCoat);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        when(_physiqueMock.getPhenotypeForGenotype(
                Allele.LeanPhysique, Allele.LeanPhysique)).thenReturn(Phenotype.LeanPhysique);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonWeightAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("1"));

        assertEquals(Integer.valueOf(6), _dragon.weight().get());
    }

    @Test
    public void weightAttributeFurryCoatBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonCoatTypeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.FurryCoat.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonCoatTypeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.FurryCoat.toString()));
        when(_coatTypeMock.getPhenotypeForGenotype(
                Allele.FurryCoat, Allele.FurryCoat)).thenReturn(Phenotype.FurryCoat);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.LeanPhysique.toString()));
        when(_physiqueMock.getPhenotypeForGenotype(
                Allele.LeanPhysique, Allele.LeanPhysique)).thenReturn(Phenotype.LeanPhysique);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonWeightAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(6), _dragon.weight().get());
    }

    @Test
    public void weightAttributeMuscularPhysiqueBonus() {
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonCoatTypeAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.ScaledCoat.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonCoatTypeAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.FurryCoat.toString()));
        when(_coatTypeMock.getPhenotypeForGenotype(
                Allele.ScaledCoat, Allele.FurryCoat)).thenReturn(Phenotype.ScaledCoat);
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleOneKeyForId(_id))).thenReturn(
                        Value.create(Allele.MuscularPhysique.toString()));
        when(_gameStateMock.valueForKey(
                GameState.Key.dragonPhysiqueAlleleTwoKeyForId(_id))).thenReturn(
                        Value.create(Allele.MuscularPhysique.toString()));
        when(_physiqueMock.getPhenotypeForGenotype(
                Allele.MuscularPhysique, Allele.MuscularPhysique)).thenReturn(Phenotype.MuscularPhysique);
        when(_gameStateMock.valueForKey(
        		GameState.Key.dragonWeightAttributeTrainingRemainingRacesKey(_id))).thenReturn(
        				Value.create("0"));

        assertEquals(Integer.valueOf(6), _dragon.weight().get());
    }
}
