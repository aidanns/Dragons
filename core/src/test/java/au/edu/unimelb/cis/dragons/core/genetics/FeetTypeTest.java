package au.edu.unimelb.cis.dragons.core.genetics;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Gene.FeetType}.
 *  
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class FeetTypeTest {

    private Gene _feetType = null;

    @Before
    public void setUp() throws Exception {
        _feetType = Gene.FeetType;
    }

    @After
    public void tearDown() throws Exception {
        _feetType = null;
    }
    
    @Test
    public void homozygousClawedFeet() {
        assertEquals(Phenotype.ClawedFeet, _feetType.getPhenotypeForGenotype(Allele.ClawedFeet, Allele.ClawedFeet));
    }
    
    @Test
    public void heterozygote() {
        assertEquals(Phenotype.ClawedFeet, _feetType.getPhenotypeForGenotype(Allele.ClawedFeet, Allele.WebbedFeet));
        assertEquals(Phenotype.ClawedFeet, _feetType.getPhenotypeForGenotype(Allele.WebbedFeet, Allele.ClawedFeet));
    }
    
    @Test
    public void homozygousWebbedFeet() {
        assertEquals(Phenotype.WebbedFeet, _feetType.getPhenotypeForGenotype(Allele.WebbedFeet, Allele.WebbedFeet));
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsErrorOnFirstAllele() {
        _feetType.getPhenotypeForGenotype(Allele.AdditiveHeart, Allele.ClawedFeet);
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsExceptionOnSecondAllele() {
        _feetType.getPhenotypeForGenotype(Allele.ClawedFeet, Allele.AdditiveLungs);
    }
}
