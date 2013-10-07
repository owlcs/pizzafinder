package uk.ac.man.cs.mig.coode.pizzafinder.model;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Oct 6, 2004<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class ChoiceModelChangedEvent {

	final private ChoiceModel source;

	public ChoiceModelChangedEvent(ChoiceModel source) {
		this.source = source;
	}


	public ChoiceModel getSource() {
		return source;
	}
}

