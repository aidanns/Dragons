package au.edu.unimelb.cis.dragons.core.genetics.genes;

import com.google.common.collect.ImmutableSet;

import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.DominantRecessiveGene;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;

public class FeetType extends DominantRecessiveGene {
	public FeetType() {
		super(Allele.ClawedFeet, Phenotype.ClawedFeet, Phenotype.WebbedFeet, ImmutableSet.of(Allele.ClawedFeet, Allele.WebbedFeet));
	}
}