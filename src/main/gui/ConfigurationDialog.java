package main.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import main.maze.EnvironmentManager;

public class ConfigurationDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = -8482977682324666207L;

	private JSpinner xSizeSpinner;
	private JSpinner ySizeSpinner;
	private JButton startButton;

	public ConfigurationDialog() {
		xSizeSpinner = new JSpinner(new SpinnerNumberModel(5, 2, 10, 1));
		ySizeSpinner = new JSpinner(new SpinnerNumberModel(5, 2, 10, 1));

		JSpinner pRewardSpinner = new JSpinner(new SpinnerNumberModel(0.2, 0, 1, 0.1));
		JSpinner pPunishmentSpinner = new JSpinner(new SpinnerNumberModel(0.1, 0, 1, 0.1));

		startButton = new JButton("Start");
		startButton.addActionListener(this);

		JPanel panel = new JPanel();
		panel.add(xSizeSpinner);
		panel.add(ySizeSpinner);
		panel.add(pRewardSpinner);
		panel.add(pPunishmentSpinner);
		panel.add(startButton);
		add(panel);

		setSize(new Dimension(300, 150));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			dispose();
			EnvironmentManager.start((int) xSizeSpinner.getValue(), (int) ySizeSpinner.getValue());
		}
	}

}
