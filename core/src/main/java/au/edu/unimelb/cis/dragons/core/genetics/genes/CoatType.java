package au.edu.unimelb.cis.dragons.core.genetics.genes;

import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.genetics.DominantRecessiveGene;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;

public class CoatType extends DominantRecessiveGene {
	public CoatType() {
		super(Allele.ScaledCoat, Phenotype.ScaledCoat, Phenotype.FurryCoat);
	}
}