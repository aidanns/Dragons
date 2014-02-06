package au.edu.unimelb.cis.dragons.core.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import au.edu.unimelb.cis.dragons.core.DragonGenerator;
import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;

/**
 * Simulation of a Race between a dragon and some randomly generated 
 * competitors.
 * @author Aidan Nagorcka-Smith
 */
public class Race {

	private GameState _gameState;
	private Dragon _dragon;
	private Double _raceLength;
	private Phenotype _phenotypeBonus;
	private Integer _speedBonusFromPhenotype;
	
	public Race(GameState gameState, Dragon dragon, Double length, Phenotype phenotype, Integer speedBonus) {
		_gameState = gameState;
		_dragon = dragon;
		_raceLength = length;
		_phenotypeBonus = phenotype;
		_speedBonusFromPhenotype = speedBonus;
	}
	
	public Integer simulateAndGetPlace() {

		// Generator for the random competitors.
		DragonGenerator generator = new DragonGenerator(_gameState);
		int numberOfDragons = 6;
		
		List<Dragon> dragons = new ArrayList<Dragon>(numberOfDragons);
		List<Double> distances = new ArrayList<Double>(numberOfDragons);
		dragons.add(_dragon);
		distances.add(0.0);
		for (int i = 1; i < numberOfDragons; ++i) {
			dragons.add(generator.createRandomDragon(2));
			distances.add(0.0);
		}
		
		Random r = new Random();
		r.setSeed(new Date().getTime());
		
		boolean hasWinner = false;
		
		// Until at least one dragon passes the distance of the race.
		while (!hasWinner) {
			// Move each of the dragons.
			for (int i = 0; i < numberOfDragons; ++i) {
				Dragon d = dragons.get(i);

				// Calculate the bonus speed because of where we are.
				double speedBonus = 0;
				switch(_phenotypeBonus) {
				case ClawedFeet:
				case WebbedFeet:
					if (d.feetType().get() == _phenotypeBonus) {
						speedBonus += _speedBonusFromPhenotype;
					}
					break;
				case FurryCoat:
				case ScaledCoat:
					if (d.coatType().get() == _phenotypeBonus) {
						speedBonus += _speedBonusFromPhenotype;
					}
					break;
				case LargeHeart:
				case MediumHeart:
				case SmallHeart:
					if (d.heartSize().get() == _phenotypeBonus) {
						speedBonus += _speedBonusFromPhenotype;
					}
					break;
				case LargeLungs:
				case MediumLungs:
				case SmallLungs:
					if (d.lungSize().get() == _phenotypeBonus) {
						speedBonus += _speedBonusFromPhenotype;
					}
					break;
				case LargeWings:
				case SmallWings:
					if (d.lungSize().get() == _phenotypeBonus) {
						speedBonus += _speedBonusFromPhenotype;
					}
					break;
				case LeanPhysique:
				case MuscularPhysique:
					if (d.physique().get() == _phenotypeBonus) {
						speedBonus += _speedBonusFromPhenotype;
					}
					break;
				case LongLegs:
				case ShortLegs:
					if (d.legLength().get() == _phenotypeBonus) {
						speedBonus += _speedBonusFromPhenotype;
					}
					break;
				case LongTail:
				case ShortTail:
					if (d.tailLength().get() == _phenotypeBonus) {
						speedBonus += _speedBonusFromPhenotype;
					}
					break;
				default:
					break;
				}
				
				double fixedDistance = 40 + (10 * (d.speed().get() + speedBonus));
				double variableDistance = (10 + (10 * d.balance().get())) * r.nextDouble();
				double newDistance = distances.get(i) + fixedDistance + variableDistance;
				if (newDistance > _raceLength) {
					hasWinner = true;
				}
				distances.set(i, newDistance);
			}
		}
		
		// Allows the use of a priority queue to get the order of finish.
		class Result {
			
			private Dragon _dragon;
			private Double _distance;
			
			public Result(Dragon dragon, Double distance) {
				_dragon = dragon;
				_distance = distance;
			}
			
			public Dragon dragon() {
				return _dragon;
			}
			
			public Double distance() {
				return _distance;
			}
		}
		
		PriorityQueue<Result> placings = new PriorityQueue<Result>(6, new Comparator<Result>() {
			@Override
			public int compare(Result left, Result right) {
				// PriorityQueue is a min-queue, so flip the ordering here to have it return the max first.
				if (left.distance() < right.distance()) {
					return 1;
				} else if (left.distance() > right.distance()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		for (int i = 0; i < numberOfDragons; ++i) {
			placings.offer(new Result(dragons.get(i), distances.get(i)));
		}
		
		// Cycle through the results until we find the entered dragon and return
		// its place.
		for (int i = 0; i < numberOfDragons; ++i) {
			if (_dragon == placings.poll().dragon()) {
				return i + 1;
			}
		}
		
		// Will never get here, we always find the dragon that we entered in the
		// results.
		throw new RuntimeException();
	}

}
