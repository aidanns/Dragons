package au.edu.unimelb.cis.dragons.core.genetics.genes;

import com.google.common.collect.ImmutableSet;

import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.DominantRecessiveGene;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;

public class LegLength extends DominantRecessiveGene {
	public LegLength() {
		super(Allele.ShortLegs, Phenotype.ShortLegs, Phenotype.LongLegs, ImmutableSet.of(Allele.ShortLegs, Allele.LongLegs));
	}
}