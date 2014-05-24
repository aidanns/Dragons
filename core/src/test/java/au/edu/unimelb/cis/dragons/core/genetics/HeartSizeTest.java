package au.edu.unimelb.cis.dragons.core.genetics;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Gene.HeartSize}.
 *  
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class HeartSizeTest {

    private Gene _heartSize = null;

    @Before
    public void setUp() throws Exception {
        _heartSize = Gene.HeartSize;
    }

    @After
    public void tearDown() throws Exception {
        _heartSize = null;
    }
    
    @Test
    public void noAdditive() {
        assertEquals(_heartSize.getPhenotypeForGenotype(Allele.PlainHeart, Allele.PlainHeart), Phenotype.SmallHeart);
    }

    @Test
    public void oneAdditive() {
        assertEquals(_heartSize.getPhenotypeForGenotype(Allele.AdditiveHeart, Allele.PlainHeart), Phenotype.MediumHeart);
        assertEquals(_heartSize.getPhenotypeForGenotype(Allele.PlainHeart, Allele.AdditiveHeart), Phenotype.MediumHeart);
    }

    @Test
    public void twoAdditive() {
        assertEquals(_heartSize.getPhenotypeForGenotype(Allele.AdditiveHeart, Allele.AdditiveHeart), Phenotype.LargeHeart);
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsErrorOnFirstAllele() {
        _heartSize.getPhenotypeForGenotype(Allele.AdditiveHeart, Allele.ScaledCoat);
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsExceptionOnSecondAllele() {
        _heartSize.getPhenotypeForGenotype(Allele.ScaledCoat, Allele.AdditiveLungs);
    }
}
