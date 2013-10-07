package uk.ac.man.cs.mig.coode.pizzafinder.ui;

import uk.ac.man.cs.mig.coode.pizzafinder.model.Preferences;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Oct 9, 2004<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class AboutPanel extends JPanel {

	public static final String COPYRIGHT = "Copyright The University Of Manchester";
	public static final String ACK_1 = "Authors: Matthew Horridge, Afsaneh Maleki Dizaji";

    public static final String ACK_2 = "Original Idea: Mike Bada";
	public static final String WWW = "http://www.co-ode.org";


	public AboutPanel() {
		setupUI();
	}

	protected void setupUI() {
		setLayout(new BorderLayout(7, 7));
		setupInformationPanel();
	}

	protected void setupInformationPanel() {
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
		                                                GridBagConstraints.NONE,
		                                                new Insets(7, 7, 7, 7),
		                                                0, 0);
		JPanel box = new JPanel(new GridBagLayout());
		box.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		box.add(new ImagePanel(), gbc);
		gbc.gridy = 1;
		box.add(new JLabel("Ontology location: " + Preferences.getInstance().getOntologyDocumentIRI().toQuotedString()), gbc);
		gbc.gridy = 2;
		box.add(new JLabel(COPYRIGHT), gbc);
		gbc.gridy = 3;
		box.add(new JLabel(ACK_1), gbc);
		gbc.gridy = 4;
		box.add(new JLabel(ACK_2), gbc);
		gbc.gridy = 5;
		box.add(new JLabel(WWW), gbc);
		add(box, BorderLayout.SOUTH);
	}

	protected class ImagePanel extends JPanel {
		private ImageIcon image;

		public ImagePanel() {
			try {
				URL url = getClass().getResource("/PizzaFinderAboutBox.png");
				image = new ImageIcon(url);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		public Dimension getPreferredSize() {
			return new Dimension(image.getIconWidth(), image.getIconHeight());
		}


		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image.getImage(), 0, 0, this);
		}
	}
}

