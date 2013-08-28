package au.edu.unimelb.cis.dragons.core;

import java.util.HashSet;
import java.util.Set;

/**
 * A ViewController that has the ability to display other ViewControllers.
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class ContainerViewController extends ViewController {

	// Those ViewControllers that may have their view's displayed by this
	// ViewControllers, based on some logic.
	private Set<ViewController> _childViewControllers =
		new HashSet<ViewController>();
	
	/**
	 * Add a child ViewController to this ViewController.
	 * @param child The ViewController to add as a child.
	 */
	protected final void addChildViewController(ViewController child) {
		_childViewControllers.add(child);
	}
	
	/**
	 * Remove a ViewController child from this ViewController.
	 * @param child The ViewController to remove.
	 */
	protected final void removeChildViewController(ViewController child) {
		_childViewControllers.remove(child);
	}

	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.ViewController#wasAdded()
	 */
	@Override
	public void wasAdded() {
		super.wasAdded();
		for (final ViewController child : _childViewControllers) {
			child.wasAdded();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see au.edu.unimelb.cis.dragons.core.ViewController#wasRemoved()
	 */
	@Override
	public void wasRemoved() {
		super.wasRemoved();
		for (final ViewController child : _childViewControllers) {
			child.wasRemoved();
		}
	}

}
