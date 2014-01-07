package au.edu.unimelb.cis.dragons.core.genetics;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.edu.unimelb.cis.dragons.core.genetics.genes.Physique;

public class PhysiqueTest {
    
    private Physique _physique = null;

    @Before
    public void setUp() throws Exception {
        _physique = new Physique();
    }

    @After
    public void tearDown() throws Exception {
        _physique = null;
    }
    
    @Test
    public void homozygousMuscular() {
        assertEquals(Phenotype.MuscularPhysique, _physique.getPhenotypeForGenotype(Allele.MuscularPhysique, Allele.MuscularPhysique));
    }
    
    @Test
    public void heterozygote() {
        assertEquals(Phenotype.MuscularPhysique, _physique.getPhenotypeForGenotype(Allele.MuscularPhysique, Allele.LeanPhysique));
        assertEquals(Phenotype.MuscularPhysique, _physique.getPhenotypeForGenotype(Allele.LeanPhysique, Allele.MuscularPhysique));
    }
    
    @Test
    public void homozygousLean() {
        assertEquals(Phenotype.LeanPhysique, _physique.getPhenotypeForGenotype(Allele.LeanPhysique, Allele.LeanPhysique));
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsErrorOnFirstAllele() {
        _physique.getPhenotypeForGenotype(Allele.AdditiveHeart, Allele.MuscularPhysique);
    }
    
    @Test(expected=InvalidAlleleException.class)
    public void throwsExceptionOnSecondAllele() {
        _physique.getPhenotypeForGenotype(Allele.MuscularPhysique, Allele.AdditiveLungs);
    }
}
