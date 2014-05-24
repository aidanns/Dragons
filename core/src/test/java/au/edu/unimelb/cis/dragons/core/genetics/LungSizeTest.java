package au.edu.unimelb.cis.dragons.core.genetics;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LungSizeTest {

    private Gene _lungSize = null;

    @Before
    public void setUp() throws Exception {
        _lungSize = Gene.LungSize;
    }

    @After
    public void tearDown() throws Exception {
        _lungSize = null;
    }
    
    @Test
    public void noAdditive() {
        assertEquals(_lungSize.getPhenotypeForGenotype(Allele.PlainLungs, Allele.PlainLungs), Phenotype.SmallLungs);
    }

    @Test
    public void oneAdditive() {
        assertEquals(_lungSize.getPhenotypeForGenotype(Allele.AdditiveLungs, Allele.PlainLungs), Phenotype.MediumLungs);
        assertEquals(_lungSize.getPhenotypeForGenotype(Allele.PlainLungs, Allele.AdditiveLungs), Phenotype.MediumLungs);
    }

    @Test
    public void twoAdditive() {
        assertEquals(_lungSize.getPhenotypeForGenotype(Allele.AdditiveLungs, Allele.AdditiveLungs), Phenotype.LargeLungs);
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsErrorOnFirstAllele() {
        _lungSize.getPhenotypeForGenotype(Allele.AdditiveLungs, Allele.ScaledCoat);
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsExceptionOnSecondAllele() {
        _lungSize.getPhenotypeForGenotype(Allele.ScaledCoat, Allele.AdditiveLungs);
    }
}
