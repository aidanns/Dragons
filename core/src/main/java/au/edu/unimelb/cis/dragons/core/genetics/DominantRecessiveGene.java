package au.edu.unimelb.cis.dragons.core.genetics;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class DominantRecessiveGene extends Gene {
	
	private Allele _dominantAllele;
	
	private Phenotype _dominantPhenotype;
	private Phenotype _recessivePhenotype;
	
	/** All valid alleles for this gene. */
	private Set<Allele> _validAlleles;
	
	public Phenotype getPhenotypeForGenotype(Allele first, Allele second) throws InvalidAlleleException {
		if (!_validAlleles.contains(first) || !_validAlleles.contains(second)) {
			throw new InvalidAlleleException();
		}
		
		if (first == _dominantAllele || second == _dominantAllele) {
			return _dominantPhenotype;
		} else {
			return _recessivePhenotype;
		}
	}
	
	public DominantRecessiveGene(Allele dominantAllele, Phenotype dominantPhenotype, Phenotype recessivePhenotype, Set<Allele> validAlleles) {
		_dominantAllele = dominantAllele;
		_dominantPhenotype = dominantPhenotype;
		_recessivePhenotype = recessivePhenotype;
		_validAlleles = ImmutableSet.copyOf(validAlleles);
	}
}