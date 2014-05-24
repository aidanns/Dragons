package au.edu.unimelb.cis.dragons.core.genetics;

import java.util.HashSet;
import java.util.Set;

/**
 * Representation of each gene in the game.
 * 
 * @author Aidan Nagorck-Smith (aidanns@gmail.com)
 */
public enum Gene {
	LegLength(Allele.ShortLegs, Allele.LongLegs, Phenotype.ShortLegs, Phenotype.LongLegs),
	FeetType(Allele.ClawedFeet, Allele.WebbedFeet, Phenotype.ClawedFeet, Phenotype.WebbedFeet),
	CoatType(Allele.ScaledCoat, Allele.FurryCoat, Phenotype.ScaledCoat, Phenotype.FurryCoat),
	WingSize(Allele.SmallWings, Allele.LargeWings, Phenotype.SmallWings, Phenotype.LargeWings),
	TailLength(Allele.ShortTail, Allele.LongTail, Phenotype.ShortTail, Phenotype.LongTail),
	Physique(Allele.MuscularPhysique, Allele.LeanPhysique, Phenotype.MuscularPhysique, Phenotype.LeanPhysique),
	LungSize(Allele.AdditiveLungs, Allele.PlainLungs, Phenotype.LargeLungs, Phenotype.MediumLungs, Phenotype.SmallLungs),
	HeartSize(Allele.AdditiveHeart, Allele.PlainHeart, Phenotype.LargeHeart, Phenotype.MediumHeart, Phenotype.SmallHeart);
	
	enum GeneType {
		DominantRecessive,
		Additive
	}
	
	// Terrible design but we're better off having Gene as an enum that 
	// having a class heirarchy here.
	private GeneType _type;
	
	private Allele _dominantAllele;
	private Phenotype _dominantPhenotype;
	private Phenotype _recessivePhenotype;
	
	/** The allele that causes the additive effect of this gene. */
	private Allele _additiveAllele;
	/** Phenotype if we have a sum total of 2 additive alleles. */
	private Phenotype _largePhenotype;
	/** Phenotype if we have a sum total of 1 additive alleles. */
	private Phenotype _mediumPhenotype;
	/** Phenotype if we have a sum total of 0 additive alleles. */
	private Phenotype _smallPhenotype;
	/** All valid alleles for this gene. */
	private Set<Allele> _validAlleles = new HashSet<Allele>();
	
	Gene(Allele dominantAllele, Allele recessiveAllele, Phenotype dominantPhenotype, Phenotype recessivePhenotype) {
		_type = GeneType.DominantRecessive;
		_dominantAllele = dominantAllele;
		_dominantPhenotype = dominantPhenotype;
		_recessivePhenotype = recessivePhenotype;
		_validAlleles.add(dominantAllele);
		_validAlleles.add(recessiveAllele);
	}

	Gene(Allele additiveAllele, Allele otherAllele, Phenotype largePhenotype, Phenotype mediumPhenotype, Phenotype smallPhenotype) {
		_type = GeneType.Additive;
		_additiveAllele = additiveAllele;
		_largePhenotype = largePhenotype;
		_mediumPhenotype = mediumPhenotype;
		_smallPhenotype = smallPhenotype;
		_validAlleles.add(additiveAllele);
		_validAlleles.add(otherAllele);
	}
	
	/**
	 * @return The set of all valid alleles for this gene.
	 */
	public Set<Allele> getValidAlleles() {
		return new HashSet<Allele>(_validAlleles);
	}
	
	/**
	 * Get the phenotype that corresponds to two provided alleles for this gene.
	 * @param first The first allele.
	 * @param second The second allele.
	 * @return The Phenotype for a dragon with these two alleles.
	 * @throws InvalidAlleleException If either of the alleles are not valid
	 *     for this gene.
	 */
	public Phenotype getPhenotypeForGenotype(Allele first, Allele second) {
		switch(_type) {
		case DominantRecessive:
			if (!_validAlleles.contains(first) || !_validAlleles.contains(second)) {
				throw new InvalidAlleleException();
			}
			
			if (first == _dominantAllele || second == _dominantAllele) {
				return _dominantPhenotype;
			} else {
				return _recessivePhenotype;
			}
		case Additive:
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
		throw new RuntimeException("Got a type of gene that shouldn't exist");
	}
}
