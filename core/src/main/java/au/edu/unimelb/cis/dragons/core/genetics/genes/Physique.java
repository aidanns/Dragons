package au.edu.unimelb.cis.dragons.core.genetics.genes;

import com.google.common.collect.ImmutableSet;

import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.DominantRecessiveGene;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;

public class Physique extends DominantRecessiveGene {
	public Physique() {
		super(Allele.MuscularPhysique, Phenotype.MuscularPhysique, Phenotype.LeanPhysique, ImmutableSet.of(Allele.MuscularPhysique, Allele.LeanPhysique));
	}
}