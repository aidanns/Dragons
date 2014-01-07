package au.edu.unimelb.cis.dragons.core.genetics.genes;

import com.google.common.collect.ImmutableSet;

import au.edu.unimelb.cis.dragons.core.genetics.AdditiveGene;
import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;


public class LungSize extends AdditiveGene {
	public LungSize() {
		super(Allele.AdditiveLungs, Phenotype.SmallLungs, Phenotype.MediumLungs, Phenotype.LargeLungs, ImmutableSet.of(Allele.AdditiveLungs, Allele.PlainLungs));
	}
}