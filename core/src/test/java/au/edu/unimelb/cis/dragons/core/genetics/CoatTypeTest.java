package au.edu.unimelb.cis.dragons.core.genetics;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.edu.unimelb.cis.dragons.core.genetics.genes.CoatType;

public class CoatTypeTest {
    
    private CoatType _coatType = null;

    @Before
    public void setUp() throws Exception {
        _coatType = new CoatType();
    }

    @After
    public void tearDown() throws Exception {
        _coatType = null;
    }
    
    @Test
    public void homozygousScales() {
        assertEquals(Phenotype.ScaledCoat, _coatType.getPhenotypeForGenotype(Allele.ScaledCoat, Allele.ScaledCoat));
    }
    
    @Test
    public void heterozygote() {
        assertEquals(Phenotype.ScaledCoat, _coatType.getPhenotypeForGenotype(Allele.ScaledCoat, Allele.FurryCoat));
        assertEquals(Phenotype.ScaledCoat, _coatType.getPhenotypeForGenotype(Allele.FurryCoat, Allele.ScaledCoat));
    }
    
    @Test
    public void homozygousFurry() {
        assertEquals(Phenotype.FurryCoat, _coatType.getPhenotypeForGenotype(Allele.FurryCoat, Allele.FurryCoat));
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsErrorOnFirstAllele() {
        _coatType.getPhenotypeForGenotype(Allele.AdditiveHeart, Allele.ScaledCoat);
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsExceptionOnSecondAllele() {
        _coatType.getPhenotypeForGenotype(Allele.ScaledCoat, Allele.AdditiveLungs);
    }
}
