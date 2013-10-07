package uk.ac.man.cs.mig.coode.pizzafinder.selection;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Oct 6, 2004<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class SelectionEvent {
	private Object source;


	public SelectionEvent(Object source) {
		this.source = source;
	}


	public Object getSource() {
		return source;
	}
}

