package au.edu.unimelb.cis.dragons.core.genetics;

import java.util.HashMap;
import java.util.Map;

public enum Allele {
	ShortLegs("short_legs"),
	LongLegs("long_legs"),
	ClawedFeet("clawed_feet"),
	WebbedFeet("webbed_feet"),
	ScaledCoat("scaled_coat"),
	FurryCoat("furry_coat"),
	SmallWings("small_wings"),
	LargeWings("large_wings"),
	ShortTail("short_tail"),
	LongTail("long_tail"),
	MuscularPhysique("muscular_physique"),
	LeanPhysique("lean_physique"),
	AdditiveLungs("additive_lungs"),
	PlainLungs("plain_lungs"),
	AdditiveHeart("additive_heart"),
	PlainHeart("plain_heart");
	
	private static Map<String, Allele> nameToAlleleMap = new HashMap<String, Allele>();
	
	static {
		for (Allele allele : Allele.values()) {
			nameToAlleleMap.put(allele._name, allele);
		}
		nameToAlleleMap.put("", null);
	}
	
	private String _name;
	
	Allele(String name) {
		_name = name;
	}
	
	@Override
	public String toString() {
		return _name;
	}
	
	/**
	 * Get the Allele for a given allele name, which is returned from 
	 * the GameState.
	 * @param alleleName The name of the allele to retrieve.
	 * @return The allele.
	 */
	/* package */ static Allele dragonStateForStateName(String alleleName) {
		return nameToAlleleMap.get(alleleName);
	}
}