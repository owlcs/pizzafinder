package uk.ac.man.cs.mig.coode.pizzafinder.ui;


import org.semanticweb.owlapi.model.OWLClass;
import uk.ac.man.cs.mig.coode.pizzafinder.model.PizzaOntology;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Oct 6, 2004<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class ResultsPanel extends JPanel {

	private PizzaOntology ontology;

	private PizzaApplication application;

	private Box box;


	public ResultsPanel(PizzaOntology ontology, PizzaApplication application) {
		this.ontology = ontology;
		this.application = application;
		createUI();
	}

	private void createUI() {
		setLayout(new BorderLayout(7, 7));
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		Action backAction = new AbstractAction("Back") {
			public void actionPerformed(ActionEvent e) {
				application.showToppingsPanel();
			}
		};
		buttonPanel.add(new JButton(backAction));
		add(buttonPanel, BorderLayout.SOUTH);
		box = new Box(BoxLayout.Y_AXIS);
		box.setBackground(Color.WHITE);
		add(new JScrollPane(box));
	}

	public void setPizzas(Collection<OWLClass> pizzas) {
		setPizzaPanels(createPizzaPanels(pizzas));
	}

    /**
     * Creates pizza panels according to returned result back from reasoner(Collection, e.g Pepproni pizza, SeaFoof Pizza)
     * it creates one pizza panel for each result (Named pizza in Ontology) and display the pizza toppings)
     * @param pizzas
     * @return
     */
	private PizzaPanel [] createPizzaPanels(Collection<OWLClass> pizzas) {
		PizzaPanel [] panels = new PizzaPanel[pizzas.size()];
		int counter = 0;
		for(Iterator it = pizzas.iterator(); it.hasNext(); counter++) {
			OWLClass cls = (OWLClass) it.next();
			panels[counter] = new PizzaPanel(ontology, cls);
		}
		return panels;
	}

	private void setPizzaPanels(PizzaPanel [] panels) {
		box.removeAll();
		for(int i = 0; i < panels.length; i++) {
			box.add(panels[i]);
			box.add(Box.createVerticalStrut(10));
		}
		revalidate();
	}
}

