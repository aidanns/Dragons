package au.edu.unimelb.cis.dragons.core;

import java.util.HashMap;

import react.Value;

/**
 * Container class for all game state that can be passed for persistence.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class GameState {

	/**
	 * A Key that can be used by clients of the GameState to refer to specific
	 * values that it stores.
	 */
	public static class Key {
		
		private String _key;
		
		public Key(String key) {
			_key = key;
		}
		
		public String key() {
			return _key;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((_key == null) ? 0 : _key.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (_key == null) {
				if (other._key != null)
					return false;
			} else if (!_key.equals(other._key))
				return false;
			return true;
		}
		
		/**
		 * Get the Key for the penState at a given index.
		 * @param row The row index.
		 * @param column The column index.
		 * @return The key that can be used to access the pen state for that pen.
		 */
		public static Key penStateKeyAtIndex(Integer row, Integer column) {
			return new Key("pen_" + row + "_" + column + "_state");
		}

		/**
		 * Get the Key for the state for a dragon with a certain id.
		 * @param id The id of the dragon.
		 * @return The key for the state of that dragon.
		 */
		public static Key dragonStateKeyForId(Integer id) {
			return new Key("dragon_" + id + "_state");
		}

		/**
		 * Get the Key for the name for a dragon with a certain id.
		 * @param id The id of the dragon.
		 * @return The key for that dragon's name.
		 */
		public static Key dragonNameKeyForId(Integer id) {
			return new Key("dragon_" + id + "_name");
		}

		/**
		 * Get the Key for the dragon stored in a certain pen in the farm.
		 * @param row The row of the pen.
		 * @param column The column of the pen.
		 * @return The key for the dragon in that pen.
		 */
		public static Key penDragonIdKeyAtIndex(Integer row, Integer column) {
			return new Key("pen_" + row + "_" + column + "_dragon");
		}

		/**
		 * Get the Key for the score of a certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the score of that dragon.
		 */
		public static Key dragonScoreKeyForId(Integer id) {
			return new Key("dragon_" + id + "_score");
		}

		/**
		 * Get the Key for the first allele for the leg length phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonLegLengthAlleleOneKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "leg_length", 1);
		}

		/**
		 * Get the Key for the first allele for the leg length phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonLegLengthAlleleTwoKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "leg_length", 2);
		}

		/**
		 * Get the Key for the first allele for the feet type phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonFeetTypeAlleleOneKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "feet_type", 1);
		}

		/**
		 * Get the Key for the first allele for the feet type phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonFeetTypeAlleleTwoKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "feet_type", 2);
		}

		/**
		 * Get the Key for the first allele for the coat type phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonCoatTypeAlleleOneKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "coat_type", 1);
		}

		/**
		 * Get the Key for the first allele for the coat type phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonCoatTypeAlleleTwoKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "coat_type", 2);
		}

		/**
		 * Get the Key for the first allele for the wing size phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonWingSizeAlleleOneKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "wing_size", 1);
		}

		/**
		 * Get the Key for the first allele for the wing size phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonWingSizeAlleleTwoKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "wing_size", 2);
		}

		/**
		 * Get the Key for the first allele for the tail length phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonTailLengthAlleleOneKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "tail_length", 1);
		}

		/**
		 * Get the Key for the first allele for the tail length phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonTailLengthAlleleTwoKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "tail_length", 2);
		}

		/**
		 * Get the Key for the first allele for the physique phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonPhysiqueAlleleOneKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "physique", 1);
		}

		/**
		 * Get the Key for the first allele for the physique phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonPhysiqueAlleleTwoKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "physique", 2);
		}

		/**
		 * Get the Key for the first allele for the lung size phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonLungSizeAlleleOneKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "lung_size", 1);
		}

		/**
		 * Get the Key for the first allele for the lung size phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonLungSizeAlleleTwoKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "lung_size", 2);
		}

		/**
		 * Get the Key for the first allele for the heart size phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonHeartSizeAlleleOneKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "heart_size", 1);
		}

		/**
		 * Get the Key for the first allele for the heart size phenotype for a
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonHeartSizeAlleleTwoKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "heart_size", 2);
		}

		/**
		 * Get the key for a certain allele from a certain dragon.
		 * @param id The id of the dragon.
		 * @param gene The key of the gene to retrieve from.
		 * @param number The number of the allele to retrieve (1 or 2).
		 * @return The key for the allele.
		 */
		private static Key dragonAlleleKeyForIdAndGeneAndNumber(Integer id, String gene, Integer number) {
			return new Key("dragon_" + id.toString() + "_" + gene + "_allele_" + number.toString());
		}

		/**
		 * Get the key for number of races remaining before speed training
		 *     loses effectiveness for a certain dragon.
		 * @param id The id of the dragon.
		 * @return The Key.
		 */
		public static Key dragonSpeedAttributeTrainingRemainingRacesKey(Integer id) {
			return dragonTrainingRacesRemainingKeyForIdAndAttribute(id, "speed");
		}

		/**
		 * Get the key for number of races remaining before endurance training
		 *     loses effectiveness for a certain dragon.
		 * @param id The id of the dragon.
		 * @return The Key.
		 */
		public static Key dragonEnduranceAttributeTrainingRemainingRacesKey(Integer id) {
			return dragonTrainingRacesRemainingKeyForIdAndAttribute(id, "endurance");
		}

		/**
		 * Get the key for number of races remaining before balance training
		 *     loses effectiveness for a certain dragon.
		 * @param id The id of the dragon.
		 * @return The Key.
		 */
		public static Key dragonBalanceAttributeTrainingRemainingRacesKey(Integer id) {
			return dragonTrainingRacesRemainingKeyForIdAndAttribute(id, "balance");
		}

		/**
		 * Get the key for number of races remaining before weight training
		 *     loses effectiveness for a certain dragon.
		 * @param id The id of the dragon.
		 * @return The Key.
		 */
		public static Key dragonWeightAttributeTrainingRemainingRacesKey(Integer id) {
			return dragonTrainingRacesRemainingKeyForIdAndAttribute(id, "weight");
		}

		/**
		 * Get the key for the number of races remaining before training loses
		 *     effectiveness for a certain dragon and attribute.
		 * @param id The id of the dragon.
		 * @param attribute The name of the attribute.
		 * @return The Key.
		 */
		private static Key dragonTrainingRacesRemainingKeyForIdAndAttribute(Integer id, String attribute) {
			return new Key("dragon_" + id.toString() + "_" + attribute + "_training_remaining_races");
		}

		public static Key nextDragonIdKey() {
			return new Key("next_dragon_id");
		}
		
		public static Key usernameKey() {
			return new Key("username");
		}
		
		public static Key currentGoldKey() {
			return new Key("current_gold");
		}

	}

	/** Whether the game state has been populated. */
	private boolean _hasBeenPopulated = false;

	/** All the variables that make up the game state. */
	private HashMap<Key, Value<String>> _values = new HashMap<Key, Value<String>>();

	public Value<String> valueForKey(Key key) {
		if (!_values.containsKey(key)) {
			_values.put(key, Value.create(""));
		}
		return _values.get(key);
	}

	/**
	 * Add a value to the game state for this key. Should only be called from
	 * the persistence manager.
	 * @param value The value to store.
	 * @param key The key to store it at.
	 */
	public void addValueForKey(Value<String> value, Key key) {
		_values.put(key, value);
	}

	/** Marks the game state as populated and ready for use. */
	public void markPopulated() {
		_hasBeenPopulated = true;
	}

	/**
	 * Allows clients to check if the game state has been populated and is
	 * ready to receive calls to get values.
	 * @return Whether or not the state has been populated.
	 */
	public boolean hasBeenPopulated() {
		return _hasBeenPopulated;
	}

	/**
	 * Get the next valid id for a dragon.
	 * @return The id for the new dragon.
	 */
	public Integer nextDragonId() {
		Value<String> value = valueForKey(Key.nextDragonIdKey());
		Integer id = Integer.parseInt(value.get());
		value.update(Integer.toString(id + 1));
		return id;
	}
}
