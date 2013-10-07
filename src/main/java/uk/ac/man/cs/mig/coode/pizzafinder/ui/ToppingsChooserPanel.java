package uk.ac.man.cs.mig.coode.pizzafinder.ui;

import uk.ac.man.cs.mig.coode.pizzafinder.model.ChoiceModel;
import uk.ac.man.cs.mig.coode.pizzafinder.model.PizzaOntology;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;


/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Oct 5, 2004<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class ToppingsChooserPanel extends JPanel {

	private PizzaOntology ontology;

	private ChoiceModel choiceModel;

	private ToppingsPanel toppingsPanel;

	private ToppingListPanel includeListPanel;

	private ToppingListPanel excludeListPanel;

	private PizzaApplication application;

    public ToppingsChooserPanel(PizzaOntology ontology, PizzaApplication application) {
		this.ontology = ontology;
		this.application = application;
		choiceModel = new ChoiceModel(ontology);
		createUI();
	}

	protected void createUI() {
		setLayout(new BorderLayout());

        //add size Panel here

        JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(200);
		toppingsPanel = new ToppingsPanel(ontology);
		splitPane.setLeftComponent(toppingsPanel);
		Box box = new Box(BoxLayout.Y_AXIS);
		includeListPanel = new IncludeToppingListPanel(ontology, toppingsPanel, choiceModel);
		box.add(includeListPanel);

        box.add(Box.createVerticalStrut(12));
		excludeListPanel = new ExcludeToppingListPanel(ontology, toppingsPanel, choiceModel);
		box.add(excludeListPanel);
        box.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 12));
		splitPane.setRightComponent(box);
		add(splitPane);
		setupQueryPanel();
    }


    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }


    protected void setupQueryPanel() {
        JPanel queryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        queryPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        Action queryReasonerAction = new AbstractAction("Get Pizzas") {
            public void actionPerformed(ActionEvent e) {
                Collection c = ontology.getPizzas(choiceModel.getIncluded(), choiceModel.getExcluded());
                application.showResultsPanel(c);
            }
        };
        queryPanel.add(new JButton(queryReasonerAction));
        add(queryPanel, BorderLayout.SOUTH);
    }
}

