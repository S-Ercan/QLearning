package main.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ConfigurationDialog extends JDialog {

	private static final long serialVersionUID = -8482977682324666207L;

	public ConfigurationDialog() {
		JSpinner xSizeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		JSpinner ySizeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

		JSpinner pRewardSpinner = new JSpinner(new SpinnerNumberModel(0.2, 0, 1, 0.1));
		JSpinner pPunishmentSpinner = new JSpinner(new SpinnerNumberModel(0.1, 0, 1, 0.1));

		JButton startButton = new JButton("Start");

		JPanel panel = new JPanel();
		panel.add(xSizeSpinner);
		panel.add(ySizeSpinner);
		panel.add(pRewardSpinner);
		panel.add(pPunishmentSpinner);
		panel.add(startButton);
		add(panel);

		setSize(new Dimension(300, 150));
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setVisible(true);
	}

}
