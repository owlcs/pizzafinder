package uk.ac.man.cs.mig.coode.pizzafinder.ui;

import org.semanticweb.owlapi.model.OWLClass;
import uk.ac.man.cs.mig.coode.pizzafinder.model.PizzaOntology;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Oct 7, 2004<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class PizzaApplication extends JFrame {

	private PizzaOntology ontology;

	private ResultsPanel resultsPanel;

	private JPanel cardPanel;

	private JPanel mainPanel;

    private Action aboutAction = new AbstractAction("About...") {
		public void actionPerformed(ActionEvent e) {
			AboutDialog dlg = new AboutDialog(PizzaApplication.this);
			dlg.setVisible(true);
		}
	};

	public PizzaApplication() {
		setupMenuBar();
		setupFrame();
		setupMainPanel();
		final LoaderPanel loaderPanel = new LoaderPanel();
		loaderPanel.startLoadingAnimation(mainPanel, BorderLayout.CENTER);
		Runnable r = new Runnable() {
			public void run() {
				// Create and load the Pizza Ontology
				ontology = new PizzaOntology();
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setupCardPanel();
						loaderPanel.stopLoadingAnimation();
					}
				});
			}
		};
		Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                loaderPanel.displayError(throwable.getMessage());
            }
        });
		t.start();
	}

	protected void setupMainPanel() {
		mainPanel = new JPanel(new BorderLayout(7, 7));
		mainPanel.add(new BannerPanel(), BorderLayout.NORTH);
        getContentPane().add(mainPanel);
	}

	protected void setupCardPanel() {
		cardPanel = new JPanel();
		cardPanel.setLayout(new CardLayout());
        cardPanel.add(new ToppingsChooserPanel(ontology, this), "ToppingsChooserPanel");
        cardPanel.add(resultsPanel = new ResultsPanel(ontology, this), "ResultsPanel");
		mainPanel.add(cardPanel);
	}

	protected void setupMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("Pizza Finder");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem(aboutAction);
		menu.add(menuItem);
	}

	protected void setupFrame() {
		setSize(800, 600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public void showToppingsPanel() {
		((CardLayout)cardPanel.getLayout()).first(cardPanel);
	}

	public void showResultsPanel(Collection<OWLClass> pizzas) {
		resultsPanel.setPizzas(pizzas);
		((CardLayout)cardPanel.getLayout()).last(cardPanel);
	}

	public static void main(String [] args) {
		System.out.println("Starting app...");
		PizzaApplication panel = new PizzaApplication();
		System.out.println("...created pizza app.");
		panel.setVisible(true);

	}
}

