package au.edu.unimelb.cis.dragons.core.genetics;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Gene.TailLength}.
 *  
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class TailLengthTest {
    
    private Gene _tailLength = null;

    @Before
    public void setUp() throws Exception {
        _tailLength = Gene.TailLength;
    }

    @After
    public void tearDown() throws Exception {
        _tailLength = null;
    }
    
    @Test
    public void homozygousShortTail() {
        assertEquals(Phenotype.ShortTail, _tailLength.getPhenotypeForGenotype(Allele.ShortTail, Allele.ShortTail));
    }
    
    @Test
    public void heterozygote() {
        assertEquals(Phenotype.ShortTail, _tailLength.getPhenotypeForGenotype(Allele.ShortTail, Allele.LongTail));
        assertEquals(Phenotype.ShortTail, _tailLength.getPhenotypeForGenotype(Allele.LongTail, Allele.ShortTail));
    }
    
    @Test
    public void homozygousLongTail() {
        assertEquals(Phenotype.LongTail, _tailLength.getPhenotypeForGenotype(Allele.LongTail, Allele.LongTail));
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsErrorOnFirstAllele() {
        _tailLength.getPhenotypeForGenotype(Allele.AdditiveHeart, Allele.ShortTail);
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsExceptionOnSecondAllele() {
        _tailLength.getPhenotypeForGenotype(Allele.ShortTail, Allele.AdditiveLungs);
    }
}
