package au.edu.unimelb.cis.dragons.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import au.edu.unimelb.cis.dragons.core.genetics.Allele;
import au.edu.unimelb.cis.dragons.core.model.Dragon;

/**
 * A factory for random dragons.
 * @author Aidan Nagorcka-Smtih (aidanns@gmail.com)
 */
public class DragonGenerator {

	// The GameState to save the dragons to.
	private GameState _gameState;

	// List of all possible dragon names.
	private List<String> _names;

	{
		_names = new ArrayList<String>();
		_names.add("Bilal");
		_names.add("Andrew");
		_names.add("Puneet");
		_names.add("Stephen");
		_names.add("Wasabi");
		_names.add("Iain");
		_names.add("Alan");
	}
	
	private Random _random;
	
	{
		_random = new Random();
		_random.setSeed(new Date().getTime());
	}

	/**
	 * Create a new dragon generator, backed by a particular GameState.
	 * @param gameState The GameState backing the generator.
	 */
	public DragonGenerator(GameState gameState) {
		_gameState = gameState;
	}

	/**
	 * Create a random dragon.
	 * @param permutations Number of iterations to permute the genetic structure
	 * of the dragon.
	 * @return A new dragon.
	 */
	public Dragon createRandomDragon(Integer permutations) {

		List<Allele> alleles = new ArrayList<Allele>(16);
		alleles.add(Allele.ShortLegs);
		alleles.add(Allele.ShortLegs);
		alleles.add(Allele.ClawedFeet);
		alleles.add(Allele.ClawedFeet);
		alleles.add(Allele.FurryCoat);
		alleles.add(Allele.FurryCoat);
		alleles.add(Allele.SmallWings);
		alleles.add(Allele.SmallWings);
		alleles.add(Allele.ShortTail);
		alleles.add(Allele.ShortTail);
		alleles.add(Allele.MuscularPhysique);
		alleles.add(Allele.MuscularPhysique);
		alleles.add(Allele.PlainLungs);
		alleles.add(Allele.PlainLungs);
		alleles.add(Allele.PlainHeart);
		alleles.add(Allele.PlainHeart);

		for (int i = 0; i < permutations; ++i) {
			int k = _random.nextInt(16);
			alleles.set(k, swapAllele(alleles.get(k)));
		}

		String name = _names.get(_random.nextInt(_names.size()));

		return Dragon.create(_gameState, name, alleles.get(0), alleles.get(1),
			alleles.get(2), alleles.get(3), alleles.get(4), alleles.get(5),
			alleles.get(6), alleles.get(7), alleles.get(8), alleles.get(9),
			alleles.get(10), alleles.get(11), alleles.get(12), alleles.get(13),
			alleles.get(14), alleles.get(15));
	}

	/**
	 * @param allele The allele to reverse.
	 * @return The opposite Allele to what was passed in.
	 */
	private Allele swapAllele(Allele allele) {
		switch (allele) {
		case AdditiveHeart:
			return Allele.PlainHeart;
		case AdditiveLungs:
			return Allele.PlainLungs;
		case ClawedFeet:
			return Allele.WebbedFeet;
		case FurryCoat:
			return Allele.ScaledCoat;
		case LargeWings:
			return Allele.SmallWings;
		case LeanPhysique:
			return Allele.MuscularPhysique;
		case LongLegs:
			return Allele.ShortLegs;
		case LongTail:
			return Allele.ShortTail;
		case MuscularPhysique:
			return Allele.LeanPhysique;
		case PlainHeart:
			return Allele.AdditiveHeart;
		case PlainLungs:
			return Allele.AdditiveLungs;
		case ScaledCoat:
			return Allele.FurryCoat;
		case ShortLegs:
			return Allele.LongLegs;
		case ShortTail:
			return Allele.LongTail;
		case SmallWings:
			return Allele.LargeWings;
		case WebbedFeet:
			return Allele.ClawedFeet;
		default:
			throw new RuntimeException("Havn't accounted for all alleles.");
		}
	}
}
