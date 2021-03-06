package au.edu.unimelb.cis.dragons.core.genetics;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Gene.LegLength}.
 *  
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class LegLengthTest {
    
    private Gene _legLength = null;

    @Before
    public void setUp() throws Exception {
        _legLength = Gene.LegLength;
    }

    @After
    public void tearDown() throws Exception {
        _legLength = null;
    }
    
    @Test
    public void homozygousShortLegs() {
        assertEquals(Phenotype.ShortLegs, _legLength.getPhenotypeForGenotype(Allele.ShortLegs, Allele.ShortLegs));
    }
    
    @Test
    public void heterozygote() {
        assertEquals(Phenotype.ShortLegs, _legLength.getPhenotypeForGenotype(Allele.ShortLegs, Allele.LongLegs));
        assertEquals(Phenotype.ShortLegs, _legLength.getPhenotypeForGenotype(Allele.LongLegs, Allele.ShortLegs));
    }
    
    @Test
    public void homozygousLongLegs() {
        assertEquals(Phenotype.LongLegs, _legLength.getPhenotypeForGenotype(Allele.LongLegs, Allele.LongLegs));
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsErrorOnFirstAllele() {
        _legLength.getPhenotypeForGenotype(Allele.AdditiveHeart, Allele.ShortLegs);
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsExceptionOnSecondAllele() {
        _legLength.getPhenotypeForGenotype(Allele.ShortLegs, Allele.AdditiveLungs);
    }
}
