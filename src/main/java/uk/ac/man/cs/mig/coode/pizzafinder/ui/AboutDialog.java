package uk.ac.man.cs.mig.coode.pizzafinder.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Oct 9, 2004<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class AboutDialog extends JDialog {

	public AboutDialog(Frame frame) {
		super(frame, "Pizza Finder - About", true);
		createUI();
	}

	protected void createUI() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(new AboutPanel());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton button = new JButton(new AbstractAction("OK") {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPanel.add(button);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 12));
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		pack();
		Dimension screenSize = getToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
		setResizable(false);
	}
}

