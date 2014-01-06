package au.edu.unimelb.cis.dragons.core.genetics;



public class AdditiveGene extends Gene {
	
	private Allele _additiveAllele;
	
	private Phenotype _smallPhenotype;
	private Phenotype _mediumPhenotype;
	private Phenotype _largePhenotype;
	
	public Phenotype getPhenotypeForGenotype(Allele first, Allele second) {
		int level = 0;
		
		if (first == _additiveAllele) { level++; }
		if (second == _additiveAllele) { level++; }
		
		if (level == 0) {
			return _smallPhenotype;
		} else if (level == 1) {
			return _mediumPhenotype;
		} else {
			return _largePhenotype;
		}
	}
	
	public AdditiveGene(Allele additiveAllele, Phenotype smallPhenotype, Phenotype mediumPhenotype, Phenotype largePhenotype) {
		_additiveAllele = additiveAllele;
		_smallPhenotype = smallPhenotype;
		_mediumPhenotype = mediumPhenotype;
		_largePhenotype = largePhenotype;
	}
}