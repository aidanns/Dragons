package au.edu.unimelb.cis.dragons.core.genetics;


public abstract class Gene {
	public abstract Phenotype getPhenotypeForGenotype(Allele first, Allele second);
}