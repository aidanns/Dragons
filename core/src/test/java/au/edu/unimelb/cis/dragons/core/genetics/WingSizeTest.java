package au.edu.unimelb.cis.dragons.core.genetics;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.edu.unimelb.cis.dragons.core.genetics.genes.WingSize;

public class WingSizeTest {
    
    private WingSize _wingSize = null;

    @Before
    public void setUp() throws Exception {
        _wingSize = new WingSize();
    }

    @After
    public void tearDown() throws Exception {
        _wingSize = null;
    }
    
    @Test
    public void homozygousSmallWings() {
        assertEquals(Phenotype.SmallWings, _wingSize.getPhenotypeForGenotype(Allele.SmallWings, Allele.SmallWings));
    }
    
    @Test
    public void heterozygote() {
        assertEquals(Phenotype.SmallWings, _wingSize.getPhenotypeForGenotype(Allele.SmallWings, Allele.LargeWings));
        assertEquals(Phenotype.SmallWings, _wingSize.getPhenotypeForGenotype(Allele.LargeWings, Allele.SmallWings));
    }
    
    @Test
    public void homozygousLargeWings() {
        assertEquals(Phenotype.LargeWings, _wingSize.getPhenotypeForGenotype(Allele.LargeWings, Allele.LargeWings));
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsErrorOnFirstAllele() {
        _wingSize.getPhenotypeForGenotype(Allele.AdditiveHeart, Allele.SmallWings);
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsExceptionOnSecondAllele() {
        _wingSize.getPhenotypeForGenotype(Allele.SmallWings, Allele.AdditiveLungs);
    }
}
