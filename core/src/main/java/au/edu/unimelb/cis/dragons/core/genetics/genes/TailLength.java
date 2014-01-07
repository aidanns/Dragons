package au.edu.unimelb.cis.dragons.core.genetics.genes;

import com.google.common.collect.ImmutableSet;

import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.DominantRecessiveGene;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;

public class TailLength extends DominantRecessiveGene {
	public TailLength() {
		super(Allele.ShortTail, Phenotype.ShortTail, Phenotype.LongTail, ImmutableSet.of(Allele.ShortTail, Allele.LongTail));
	}
}