package au.edu.unimelb.cis.dragons.core.genetics;


public class DominantRecessiveGene extends Gene {
	
	private Allele _dominantAllele;
	
	private Phenotype _dominantPhenotype;
	private Phenotype _recessivePhenotype;
	
	public Phenotype getPhenotypeForGenotype(Allele first, Allele second) {
		if (first == _dominantAllele || second == _dominantAllele) {
			return _dominantPhenotype;
		} else {
			return _recessivePhenotype;
		}
	}
	
	public DominantRecessiveGene(Allele dominantAllele, Phenotype dominantPhenotype, Phenotype recessivePhenotype) {
		_dominantAllele = dominantAllele;
		_dominantPhenotype = dominantPhenotype;
		_recessivePhenotype = recessivePhenotype;
	}
}