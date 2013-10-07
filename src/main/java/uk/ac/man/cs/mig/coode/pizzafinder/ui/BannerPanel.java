package uk.ac.man.cs.mig.coode.pizzafinder.ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Oct 8, 2004<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class BannerPanel extends JPanel {

	private Image image;
	private Dimension prefSize;

	public BannerPanel() {
		URL url = getClass().getResource("/PizzaFinderBanner.png");
		ImageIcon icon = new ImageIcon(url);
		image = icon.getImage();
		prefSize = new Dimension(icon.getIconWidth(), icon.getIconHeight());
		setBorder(BorderFactory.createEmptyBorder(7, 7, 0, 0));
	}


	public Dimension getPreferredSize() {
		return prefSize;
	}


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}

