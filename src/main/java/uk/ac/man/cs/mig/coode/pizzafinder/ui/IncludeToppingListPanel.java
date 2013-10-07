package uk.ac.man.cs.mig.coode.pizzafinder.ui;

import org.semanticweb.owlapi.model.OWLClass;
import uk.ac.man.cs.mig.coode.pizzafinder.model.ChoiceModel;
import uk.ac.man.cs.mig.coode.pizzafinder.model.PizzaOntology;
import uk.ac.man.cs.mig.coode.pizzafinder.selection.Selectable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Collection;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Oct 6, 2004<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class IncludeToppingListPanel extends ToppingListPanel {

	private ChoiceModel choiceModel;

	private Selectable selectable;

	private Action addAction = new AbstractAction("Add") {
			public void actionPerformed(ActionEvent e) {
				Object selObj = selectable.getSelection();
				if(selObj != null && selObj instanceof OWLClass) {
					choiceModel.addIncluded((OWLClass) selObj);
				}
			}
		};

	private Action removeAction = new AbstractAction("Rem") {
			public void actionPerformed(ActionEvent e) {
				Object selObj = getSelection();
				if(selObj != null && selObj instanceof OWLClass) {
					choiceModel.removeIncluded((OWLClass) selObj);
				}
			}
		};

	public IncludeToppingListPanel(final PizzaOntology ontology,
                                   final Selectable selectable,
	                               final ChoiceModel choiceModel) {
		super(ontology, "Included toppings:", selectable, choiceModel);
		this.choiceModel = choiceModel;
		this.selectable = selectable;
		createUI();
	}


	protected Collection getListItems() {
		return choiceModel.getIncluded();
	}


	protected Action getAddAction() {
		return addAction;
	}


	protected Action getRemoveAction() {
		return removeAction;
	}
}

