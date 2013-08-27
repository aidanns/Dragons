package au.edu.unimelb.cis.dragons.core;

import java.util.ArrayList;
import java.util.List;

import tripleplay.ui.Background;
import tripleplay.ui.Group;
import tripleplay.ui.SizableGroup;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.ui.layout.BorderLayout;
import tripleplay.ui.layout.FlowLayout;

/**
 * A view controller that supports multiple tabs.
 * 
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class TabController extends ViewController {

	/** The ViewControllers that can be displayed by this view. */
	private List<ViewController> _viewControllers = 
		new ArrayList<ViewController>();
	
	public TabController(List<ViewController> viewControllers) {
		_viewControllers.addAll(viewControllers);
	}
	
	@Override
	protected Group createInterface() {
		Group parent = new Group(new BorderLayout(0),
			Style.BACKGROUND.is(Background.solid(0xFF000000)));
		
		parent.setConstraint(AxisLayout.stretched());
		
		SizableGroup centreGroup = new SizableGroup(new FlowLayout());
		centreGroup.setConstraint(BorderLayout.CENTER);
	    centreGroup.setStyles(Style.BACKGROUND.is(Background.solid(0xFFFFFFFF)));
		parent.add(centreGroup);
		
		SizableGroup bottomGroup = new SizableGroup(new FlowLayout());
		bottomGroup.setConstraint(BorderLayout.SOUTH);
		bottomGroup.setStyles(Style.BACKGROUND.is(Background.solid(0xFFCCCCCC)));
		bottomGroup.preferredSize.update(50, 50);
		parent.add(bottomGroup);
		
		return parent;
	}
	
	

}
