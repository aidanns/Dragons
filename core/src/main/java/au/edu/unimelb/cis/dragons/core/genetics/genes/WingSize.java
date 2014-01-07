package au.edu.unimelb.cis.dragons.core.genetics.genes;

import com.google.common.collect.ImmutableSet;

import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.DominantRecessiveGene;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;

public class WingSize extends DominantRecessiveGene {
	public WingSize() {
		super(Allele.SmallWings, Phenotype.SmallWings, Phenotype.LargeWings, ImmutableSet.of(Allele.LargeWings, Allele.SmallWings));
	}
}