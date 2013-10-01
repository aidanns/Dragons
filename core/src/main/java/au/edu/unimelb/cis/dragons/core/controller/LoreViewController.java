package au.edu.unimelb.cis.dragons.core.controller;

import java.util.HashMap;
import java.util.Map;

import tripleplay.ui.Label;

/**
 * ViewController for the Lore screen.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class LoreViewController extends LeftMenuViewController {

	private static Map<Label, ViewController> LabelsToViewControllers = new HashMap<Label, ViewController>();
	
	static {
		LabelsToViewControllers.put(new Label("Black"), StubViewController.makeBlackViewController());
		LabelsToViewControllers.put(new Label("Blue"), StubViewController.makeBlueViewController());
		LabelsToViewControllers.put(new Label("Red"), StubViewController.makeRedViewController());
	}
	
	public LoreViewController() {
		super(LabelsToViewControllers);
	}
	
	
	@Override
	public String title() {
		return "Lore";
	}
}
