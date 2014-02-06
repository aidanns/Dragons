package au.edu.unimelb.cis.dragons.core.controller;

import au.edu.unimelb.cis.dragons.core.GameState;
import au.edu.unimelb.cis.dragons.core.genetics.Phenotype;
import au.edu.unimelb.cis.dragons.core.model.Dragon;
import au.edu.unimelb.cis.dragons.core.model.Wallet;

public class ArcticIslandRaceViewController extends RaceViewController {

	public ArcticIslandRaceViewController(GameState gameState, Wallet wallet, Dragon dragon) {
		super(gameState, dragon, wallet, Phenotype.ClawedFeet, 2, "speed");
	}

}
