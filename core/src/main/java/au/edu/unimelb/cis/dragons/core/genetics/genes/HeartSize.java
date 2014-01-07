package au.edu.unimelb.cis.dragons.core.genetics.genes;

import com.google.common.collect.ImmutableSet;

import au.edu.unimelb.cis.dragons.core.genetics.AdditiveGene;
import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;


public class HeartSize extends AdditiveGene {
	public HeartSize() {
		super(Allele.AdditiveHeart, Phenotype.SmallHeart, Phenotype.MediumHeart, Phenotype.LargeHeart, ImmutableSet.of(Allele.AdditiveHeart, Allele.PlainHeart));
	}
}