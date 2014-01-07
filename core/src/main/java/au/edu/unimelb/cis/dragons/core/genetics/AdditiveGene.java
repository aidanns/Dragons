package au.edu.unimelb.cis.dragons.core.genetics;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class AdditiveGene extends Gene {
	
	/** The allele that causes the additive effect of this gene. */
	private Allele _additiveAllele;
	/** Phenotype if we have a sum total of 0 additive alleles. */
	private Phenotype _smallPhenotype;
	/** Phenotype if we have a sum total of 1 additive alleles. */
	private Phenotype _mediumPhenotype;
	/** Phenotype if we have a sum total of 2 additive alleles. */
	private Phenotype _largePhenotype;
	/** All valid alleles for this gene. */
	private Set<Allele> _validAlleles; // Initialised in the constructor.
	
	/**
	 * Get the phenotype that corresponds to two provided alleles for this gene.
	 * @param first The first allele.
	 * @param second The second allele.
	 * @return The Phenotype for a dragon with these two alleles.
	 * @throws InvalidAlleleException If either of the alleles are not valid
	 *     for this gene.
	 */
	public Phenotype getPhenotypeForGenotype(Allele first, Allele second) throws InvalidAlleleException {
		if (!_validAlleles.contains(first) || !_validAlleles.contains(second)) {
			throw new InvalidAlleleException();
		}
		
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
	
	public AdditiveGene(Allele additiveAllele, Phenotype smallPhenotype, Phenotype mediumPhenotype, Phenotype largePhenotype, Set<Allele> validAlleles) {
		_additiveAllele = additiveAllele;
		_smallPhenotype = smallPhenotype;
		_mediumPhenotype = mediumPhenotype;
		_largePhenotype = largePhenotype;
		
		_validAlleles = ImmutableSet.copyOf(validAlleles);
	}
}