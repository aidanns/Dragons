package au.edu.unimelb.cis.dragons.core;

import java.util.HashMap;
import java.util.Map;

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
	public enum Key {
		
		Username("username"),
		CurrentGold("current_gold"),
		
		// The current state of the pens.
		PenZeroZeroState("pen_0_0_state"),
		PenZeroOneState("pen_0_1_state"),
		PenZeroTwoState("pen_0_2_state"),
		PenZeroThreeState("pen_0_3_state"),
		PenOneZeroState("pen_1_0_state"),
		PenOneOneState("pen_1_1_state"),
		PenOneTwoState("pen_1_2_state"),
		PenOneThreeState("pen_1_3_state"),
		
		// The current dragon in each pen.
		PenZeroZeroDragon("pen_0_0_dragon"),
		PenZeroOneDragon("pen_0_1_dragon"),
		PenZeroTwoDragon("pen_0_2_dragon"),
		PenZeroThreeDragon("pen_0_3_dragon"),
		PenOneZeroDragon("pen_1_0_dragon"),
		PenOneOneDragon("pen_1_1_dragon"),
		PenOneTwoDragon("pen_1_2_dragon"),
		PenOneThreeDragon("pen_1_3_dragon"),
		
		// The current state of the dragons.
		DragonZeroState("dragon_0_state"),
		DragonOneState("dragon_1_state"),
		DragonTwoState("dragon_2_state"),
		DragonThreeState("dragon_3_state"),
		DragonFourState("dragon_4_state"),
		DragonFiveState("dragon_5_state"),
		DragonSixState("dragon_6_state"),
		DragonSevenState("dragon_7_state"),

		// The current score of the dragons.
		DragonZeroScore("dragon_0_score"),
		DragonOneScore("dragon_1_score"),
		DragonTwoScore("dragon_2_score"),
		DragonThreeScore("dragon_3_score"),
		DragonFourScore("dragon_4_score"),
		DragonFiveScore("dragon_5_score"),
		DragonSixScore("dragon_6_score"),
		DragonSevenScore("dragon_7_score"),
		
		// Dragon Phenotypes.
		
		// Leg Length
		DragonZeroLegLengthAlleleOne("dragon_0_leg_length_allele_one"),
		DragonOneLegLengthAlleleOne("dragon_1_leg_length_allele_one"),
		DragonTwoLegLengthAlleleOne("dragon_2_leg_length_allele_one"),
		DragonThreeLegLengthAlleleOne("dragon_3_leg_length_allele_one"),
		DragonFourLegLengthAlleleOne("dragon_4_leg_length_allele_one"),
		DragonFiveLegLengthAlleleOne("dragon_5_leg_length_allele_one"),
		DragonSixLegLengthAlleleOne("dragon_6_leg_length_allele_one"),
		DragonSevenLegLengthAlleleOne("dragon_7_leg_length_allele_one"),
		
		DragonZeroLegLengthAlleleTwo("dragon_0_leg_length_allele_two"),
		DragonOneLegLengthAlleleTwo("dragon_1_leg_length_allele_two"),
		DragonTwoLegLengthAlleleTwo("dragon_2_leg_length_allele_two"),
		DragonThreeLegLengthAlleleTwo("dragon_3_leg_length_allele_two"),
		DragonFourLegLengthAlleleTwo("dragon_4_leg_length_allele_two"),
		DragonFiveLegLengthAlleleTwo("dragon_5_leg_length_allele_two"),
		DragonSixLegLengthAlleleTwo("dragon_6_leg_length_allele_two"),
		DragonSevenLegLengthAlleleTwo("dragon_7_leg_length_allele_two"),

		// Feet Type
		DragonZeroFeetTypeAlleleOne("dragon_0_feet_type_allele_one"),
		DragonOneFeetTypeAlleleOne("dragon_1_feet_type_allele_one"),
		DragonTwoFeetTypeAlleleOne("dragon_2_feet_type_allele_one"),
		DragonThreeFeetTypeAlleleOne("dragon_3_feet_type_allele_one"),
		DragonFourFeetTypeAlleleOne("dragon_4_feet_type_allele_one"),
		DragonFiveFeetTypeAlleleOne("dragon_5_feet_type_allele_one"),
		DragonSixFeetTypeAlleleOne("dragon_6_feet_type_allele_one"),
		DragonSevenFeetTypeAlleleOne("dragon_7_feet_type_allele_one"),
		
		DragonZeroFeetTypeAlleleTwo("dragon_0_feet_type_allele_two"),
		DragonOneFeetTypeAlleleTwo("dragon_1_feet_type_allele_two"),
		DragonTwoFeetTypeAlleleTwo("dragon_2_feet_type_allele_two"),
		DragonThreeFeetTypeAlleleTwo("dragon_3_feet_type_allele_two"),
		DragonFourFeetTypeAlleleTwo("dragon_4_feet_type_allele_two"),
		DragonFiveFeetTypeAlleleTwo("dragon_5_feet_type_allele_two"),
		DragonSixFeetTypeAlleleTwo("dragon_6_feet_type_allele_two"),
		DragonSevenFeetTypeAlleleTwo("dragon_7_feet_type_allele_two"),
		
		// Coat Type
		DragonZeroCoatTypeAlleleOne("dragon_0_coat_type_allele_one"),
		DragonOneCoatTypeAlleleOne("dragon_1_coat_type_allele_one"),
		DragonTwoCoatTypeAlleleOne("dragon_2_coat_type_allele_one"),
		DragonThreeCoatTypeAlleleOne("dragon_3_coat_type_allele_one"),
		DragonFourCoatTypeAlleleOne("dragon_4_coat_type_allele_one"),
		DragonFiveCoatTypeAlleleOne("dragon_5_coat_type_allele_one"),
		DragonSixCoatTypeAlleleOne("dragon_6_coat_type_allele_one"),
		DragonSevenCoatTypeAlleleOne("dragon_7_coat_type_allele_one"),
		
		DragonZeroCoatTypeAlleleTwo("dragon_0_coat_type_allele_two"),
		DragonOneCoatTypeAlleleTwo("dragon_1_coat_type_allele_two"),
		DragonTwoCoatTypeAlleleTwo("dragon_2_coat_type_allele_two"),
		DragonThreeCoatTypeAlleleTwo("dragon_3_coat_type_allele_two"),
		DragonFourCoatTypeAlleleTwo("dragon_4_coat_type_allele_two"),
		DragonFiveCoatTypeAlleleTwo("dragon_5_coat_type_allele_two"),
		DragonSixCoatTypeAlleleTwo("dragon_6_coat_type_allele_two"),
		DragonSevenCoatTypeAlleleTwo("dragon_7_coat_type_allele_two"),

		// Wing Size
		DragonZeroWingSizeAlleleOne("dragon_0_wing_size_allele_one"),
		DragonOneWingSizeAlleleOne("dragon_1_wing_size_allele_one"),
		DragonTwoWingSizeAlleleOne("dragon_2_wing_size_allele_one"),
		DragonThreeWingSizeAlleleOne("dragon_3_wing_size_allele_one"),
		DragonFourWingSizeAlleleOne("dragon_4_wing_size_allele_one"),
		DragonFiveWingSizeAlleleOne("dragon_5_wing_size_allele_one"),
		DragonSixWingSizeAlleleOne("dragon_6_wing_size_allele_one"),
		DragonSevenWingSizeAlleleOne("dragon_7_wing_size_allele_one"),
		
		DragonZeroWingSizeAlleleTwo("dragon_0_wing_size_allele_two"),
		DragonOneWingSizeAlleleTwo("dragon_1_wing_size_allele_two"),
		DragonTwoWingSizeAlleleTwo("dragon_2_wing_size_allele_two"),
		DragonThreeWingSizeAlleleTwo("dragon_3_wing_size_allele_two"),
		DragonFourWingSizeAlleleTwo("dragon_4_wing_size_allele_two"),
		DragonFiveWingSizeAlleleTwo("dragon_5_wing_size_allele_two"),
		DragonSixWingSizeAlleleTwo("dragon_6_wing_size_allele_two"),
		DragonSevenWingSizeAlleleTwo("dragon_7_wing_size_allele_two"),

		// Tail Length
		DragonZeroTailLengthAlleleOne("dragon_0_tail_length_allele_one"),
		DragonOneTailLengthAlleleOne("dragon_1_tail_length_allele_one"),
		DragonTwoTailLengthAlleleOne("dragon_2_tail_length_allele_one"),
		DragonThreeTailLengthAlleleOne("dragon_3_tail_length_allele_one"),
		DragonFourTailLengthAlleleOne("dragon_4_tail_length_allele_one"),
		DragonFiveTailLengthAlleleOne("dragon_5_tail_length_allele_one"),
		DragonSixTailLengthAlleleOne("dragon_6_tail_length_allele_one"),
		DragonSevenTailLengthAlleleOne("dragon_7_tail_length_allele_one"),
		
		DragonZeroTailLengthAlleleTwo("dragon_0_tail_length_allele_two"),
		DragonOneTailLengthAlleleTwo("dragon_1_tail_length_allele_two"),
		DragonTwoTailLengthAlleleTwo("dragon_2_tail_length_allele_two"),
		DragonThreeTailLengthAlleleTwo("dragon_3_tail_length_allele_two"),
		DragonFourTailLengthAlleleTwo("dragon_4_tail_length_allele_two"),
		DragonFiveTailLengthAlleleTwo("dragon_5_tail_length_allele_two"),
		DragonSixTailLengthAlleleTwo("dragon_6_tail_length_allele_two"),
		DragonSevenTailLengthAlleleTwo("dragon_7_tail_length_allele_two"),

		// Physique
		DragonZeroPhysiqueAlleleOne("dragon_0_physique_allele_one"),
		DragonOnePhysiqueAlleleOne("dragon_1_physique_allele_one"),
		DragonTwoPhysiqueAlleleOne("dragon_2_physique_allele_one"),
		DragonThreePhysiqueAlleleOne("dragon_3_physique_allele_one"),
		DragonFourPhysiqueAlleleOne("dragon_4_physique_allele_one"),
		DragonFivePhysiqueAlleleOne("dragon_5_physique_allele_one"),
		DragonSixPhysiqueAlleleOne("dragon_6_physique_allele_one"),
		DragonSevenPhysiqueAlleleOne("dragon_7_physique_allele_one"),
		
		DragonZeroPhysiqueAlleleTwo("dragon_0_physique_allele_two"),
		DragonOnePhysiqueAlleleTwo("dragon_1_physique_allele_two"),
		DragonTwoPhysiqueAlleleTwo("dragon_2_physique_allele_two"),
		DragonThreePhysiqueAlleleTwo("dragon_3_physique_allele_two"),
		DragonFourPhysiqueAlleleTwo("dragon_4_physique_allele_two"),
		DragonFivePhysiqueAlleleTwo("dragon_5_physique_allele_two"),
		DragonSixPhysiqueAlleleTwo("dragon_6_physique_allele_two"),
		DragonSevenPhysiqueAlleleTwo("dragon_7_physique_allele_two"),

		// Lung Size
		DragonZeroLungSizeAlleleOne("dragon_0_lung_size_allele_one"),
		DragonOneLungSizeAlleleOne("dragon_1_lung_size_allele_one"),
		DragonTwoLungSizeAlleleOne("dragon_2_lung_size_allele_one"),
		DragonThreeLungSizeAlleleOne("dragon_3_lung_size_allele_one"),
		DragonFourLungSizeAlleleOne("dragon_4_lung_size_allele_one"),
		DragonFiveLungSizeAlleleOne("dragon_5_lung_size_allele_one"),
		DragonSixLungSizeAlleleOne("dragon_6_lung_size_allele_one"),
		DragonSevenLungSizeAlleleOne("dragon_7_lung_size_allele_one"),
		
		DragonZeroLungSizeAlleleTwo("dragon_0_lung_size_allele_two"),
		DragonOneLungSizeAlleleTwo("dragon_1_lung_size_allele_two"),
		DragonTwoLungSizeAlleleTwo("dragon_2_lung_size_allele_two"),
		DragonThreeLungSizeAlleleTwo("dragon_3_lung_size_allele_two"),
		DragonFourLungSizeAlleleTwo("dragon_4_lung_size_allele_two"),
		DragonFiveLungSizeAlleleTwo("dragon_5_lung_size_allele_two"),
		DragonSixLungSizeAlleleTwo("dragon_6_lung_size_allele_two"),
		DragonSevenLungSizeAlleleTwo("dragon_7_lung_size_allele_two"),

		// Heart Size
		DragonZeroHeartSizeAlleleOne("dragon_0_heart_size_allele_one"),
		DragonOneHeartSizeAlleleOne("dragon_1_heart_size_allele_one"),
		DragonTwoHeartSizeAlleleOne("dragon_2_heart_size_allele_one"),
		DragonThreeHeartSizeAlleleOne("dragon_3_heart_size_allele_one"),
		DragonFourHeartSizeAlleleOne("dragon_4_heart_size_allele_one"),
		DragonFiveHeartSizeAlleleOne("dragon_5_heart_size_allele_one"),
		DragonSixHeartSizeAlleleOne("dragon_6_heart_size_allele_one"),
		DragonSevenHeartSizeAlleleOne("dragon_7_heart_size_allele_one"),
		
		DragonZeroHeartSizeAlleleTwo("dragon_0_heart_size_allele_two"),
		DragonOneHeartSizeAlleleTwo("dragon_1_heart_size_allele_two"),
		DragonTwoHeartSizeAlleleTwo("dragon_2_heart_size_allele_two"),
		DragonThreeHeartSizeAlleleTwo("dragon_3_heart_size_allele_two"),
		DragonFourHeartSizeAlleleTwo("dragon_4_heart_size_allele_two"),
		DragonFiveHeartSizeAlleleTwo("dragon_5_heart_size_allele_two"),
		DragonSixHeartSizeAlleleTwo("dragon_6_heart_size_allele_two"),
		DragonSevenHeartSizeAlleleTwo("dragon_7_heart_size_allele_two");
		
		private String _dataStoreKey;
		private String _shortDescription;
		private String _longDescription;
		
		private static Map<String, String> dataStoreKeyToShortDescription = new HashMap<String, String>();
		private static Map<String, String> dataStoreKeyToLongDescription = new HashMap<String, String>();
		
		// Set the descriptions for every Key.
		static {
			dataStoreKeyToShortDescription.put("username", "Username");
			dataStoreKeyToLongDescription.put("username", "The Google username for the currently logged in player.");		
			
			dataStoreKeyToShortDescription.put("current_gold", "Current Gold");
			dataStoreKeyToLongDescription.put("current_gold", "The ammount of gold that the current player has.");
		
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 2; j++) {
					dataStoreKeyToShortDescription.put("pen_" + i + "_" + j + "_state", "Pen (" + i + "," + j +") State");
					dataStoreKeyToLongDescription.put("pen_" + i + "_" + j + "_state", "The current state of pen (" + i + "," + j +")");
					
					dataStoreKeyToShortDescription.put("pen_" + i + "_" + j + "_dragon", "Pen (" + i + "," + j +") Dragon");
					dataStoreKeyToLongDescription.put("pen_" + i + "_" + j + "_dragon", "The current dragon in pen (" + i + "," + j +")");
				}
			}
			
			for (int i = 0; i < 8; i++) {
				dataStoreKeyToShortDescription.put("dragon_" + i + "_state", "Dragon "+ i + "'s state");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_state", "The current state of dragon " + i);
				dataStoreKeyToShortDescription.put("dragon_" + i + "_score", "Dragon "+ i + "'s score");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_score", "The current score of dragon " + i);
				
				// Phenotypes.
				dataStoreKeyToShortDescription.put("dragon_" + i + "_leg_length_allele_one", "Dragon " + i + "'s first leg length allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_leg_length_allele_one", "The first allele contributing towards the leg length phenotype for dragon " + i);
				dataStoreKeyToShortDescription.put("dragon_" + i + "_leg_length_allele_two", "Dragon " + i + "'s second leg length allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_leg_length_allele_two", "The second allele contributing towards the leg length phenotype for dragon " + i);
				
				dataStoreKeyToShortDescription.put("dragon_" + i + "_feet_type_allele_one", "Dragon " + i + "'s first feet type allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_feet_type_allele_one", "The first allele contributing towards the feet type phenotype for dragon " + i);
				dataStoreKeyToShortDescription.put("dragon_" + i + "_feet_type_allele_two", "Dragon " + i + "'s first feet type allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_feet_type_allele_two", "The first allele contributing towards the feet type phenotype for dragon " + i);
				
				dataStoreKeyToShortDescription.put("dragon_" + i + "_coat_type_allele_one", "Dragon " + i + "'s first coat_type allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_coat_type_allele_one", "The first allele contributing towards the coat_type phenotype for dragon " + i);
				dataStoreKeyToShortDescription.put("dragon_" + i + "_coat_type_allele_two", "Dragon " + i + "'s first coat_type allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_coat_type_allele_two", "The first allele contributing towards the coat_type phenotype for dragon " + i);
				
				dataStoreKeyToShortDescription.put("dragon_" + i + "_wing_size_allele_one", "Dragon " + i + "'s first wing size allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_wing_size_allele_one", "The first allele contributing towards the wing size phenotype for dragon " + i);
				dataStoreKeyToShortDescription.put("dragon_" + i + "_wing_size_allele_two", "Dragon " + i + "'s first wing size allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_wing_size_allele_two", "The first allele contributing towards the wing size phenotype for dragon " + i);
				
				dataStoreKeyToShortDescription.put("dragon_" + i + "_tail_length_allele_one", "Dragon " + i + "'s first tail length allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_tail_length_allele_one", "The first allele contributing towards the tail length phenotype for dragon " + i);
				dataStoreKeyToShortDescription.put("dragon_" + i + "_tail_length_allele_two", "Dragon " + i + "'s first tail length allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_tail_length_allele_two", "The first allele contributing towards the tail length phenotype for dragon " + i);
				
				dataStoreKeyToShortDescription.put("dragon_" + i + "_physique_allele_one", "Dragon " + i + "'s first physique allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_physique_allele_one", "The first allele contributing towards the physique phenotype for dragon " + i);
				dataStoreKeyToShortDescription.put("dragon_" + i + "_physique_allele_two", "Dragon " + i + "'s first physique allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_physique_allele_two", "The first allele contributing towards the physique phenotype for dragon " + i);
				
				dataStoreKeyToShortDescription.put("dragon_" + i + "_lung_size_allele_one", "Dragon " + i + "'s first lung size allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_lung_size_allele_one", "The first allele contributing towards the lung size phenotype for dragon " + i);
				dataStoreKeyToShortDescription.put("dragon_" + i + "_lung_size_allele_two", "Dragon " + i + "'s first lung size allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_lung_size_allele_two", "The first allele contributing towards the lung size phenotype for dragon " + i);
				
				dataStoreKeyToShortDescription.put("dragon_" + i + "_heart_size_allele_one", "Dragon " + i + "'s first heart size allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_heart_size_allele_one", "The first allele contributing towards the heart size phenotype for dragon " + i);
				dataStoreKeyToShortDescription.put("dragon_" + i + "_heart_size_allele_two", "Dragon " + i + "'s first heart size allele");
				dataStoreKeyToLongDescription.put("dragon_" + i + "_heart_size_allele_two", "The first allele contributing towards the heart size phenotype for dragon " + i);
			}
			
			for (Key key : Key.values()) {
				key._shortDescription = dataStoreKeyToShortDescription.get(key._dataStoreKey);
				key._longDescription = dataStoreKeyToLongDescription.get(key._longDescription);
			}
		}
		
		// Set up a map from the dataStore key back to the key enumeration object.
		private static Map<String, Key> dataStoreKeyToKey = new HashMap<String, Key>();
		
		static {
			for (Key key : Key.values()) {
				dataStoreKeyToKey.put(key._dataStoreKey, key);
			}
		}
		
		Key(String dataStoreKey) {
			_dataStoreKey = dataStoreKey;
		}
		
		/* package */ String shortDescription() {
			return _shortDescription;
		}
		
		/* package */ String longDescription() {
			return _longDescription;
		}
		
		/**
		 * Get the Key for the penState at a given index.
		 * @param row The row index.
		 * @param column The column index.
		 * @return The key that can be used to access the pen state for that pen.
		 */
		public static Key penStateKeyAtIndex(Integer row, Integer column) {
			return dataStoreKeyToKey.get("pen_" + row + "_" + column + "_state");
		}

		/**
		 * Get the Key for the state for a dragon with a certain id.
		 * @param id The id of the dragon.
		 * @return The key for the state of that dragon.
		 */
		public static Key dragonStateKeyForId(Integer id) {
			return dataStoreKeyToKey.get("dragon_" + id + "_state");
		}

		/**
		 * Get the Key for the name for a dragon with a certain id.
		 * @param id The id of the dragon.
		 * @return The key for that dragon's name.
		 */
		public static Key dragonNameKeyForId(Integer id) {
			return dataStoreKeyToKey.get("dragon_" + id + "_name");
		}

		/**
		 * Get the Key for the dragon stored in a certain pen in the farm.
		 * @param row The row of the pen.
		 * @param column The column of the pen.
		 * @return The key for the dragon in that pen.
		 */
		public static Key penDragonIdKeyAtIndex(Integer row, Integer column) {
			return dataStoreKeyToKey.get("pen_" + row + "_" + column + "_dragon");
		}

		/**
		 * Get the Key for the score of a certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the score of that dragon.
		 */
		public static Key dragonScoreKeyForId(Integer id) {
			return dataStoreKeyToKey.get("dragon_" + id + "_score");
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
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "", 1);
		}

		/**
		 * Get the Key for the first allele for the heart size phenotype for a 
		 * certain dragon.
		 * @param id The id of the dragon.
		 * @return The key for the allele.
		 */
		public static Key dragonHeartSizeAlleleTwoKeyForId(Integer id) {
			return dragonAlleleKeyForIdAndGeneAndNumber(id, "", 2);
		}

		/**
		 * Get the key for a certain allele from a certain dragon.
		 * @param id The id of the dragon.
		 * @param gene The key of the gene to retrieve from.
		 * @param number The number of the allele to retrieve (1 or 2).
		 * @return The key for the allele.
		 */
		private static Key dragonAlleleKeyForIdAndGeneAndNumber(Integer id, String gene, Integer number) {
			return dataStoreKeyToKey.get("dragon_" + id + "_" + gene + "_allele_" + number.toString());
		}
	}
	
	/** Whether the game state has been populated. */
	private boolean _hasBeenPopulated = false;
	
	/** All the variables that make up the game state. */
	private HashMap<Key, Value<String>> _values = new HashMap<Key, Value<String>>();
	
	public Value<String> valueForKey(Key key) {
		return _values.get(key);
	}
	
	/**
	 * Get the short description for this key.
	 * @param key The key to retrieve info for.
	 * @return The short description, suitable for displaying in the GUI.
	 */
	public String shortDescriptionForKey(Key key) {
		return key.shortDescription();
	}
	
	/**
	 * Get the long description for this key.
	 * @param key The key to retrieve info for.
	 * @return The long description, suitable for understand the key.
	 */
	public String longDescriptionForKey(Key key) {
		return key.longDescription();
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
}
