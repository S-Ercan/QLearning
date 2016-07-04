package main.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import main.maze.EnvironmentManager;

public class ConfigurationDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = -8482977682324666207L;

	private JSpinner xSizeSpinner;
	private JSpinner ySizeSpinner;
	private JSpinner pRewardSpinner;
	private JSpinner pPunishmentSpinner;
	private JButton startButton;

	public ConfigurationDialog() {
		JPanel spinnerPanel = createInputPanel();

		startButton = new JButton("Start");
		startButton.addActionListener(this);

		JPanel panel = new JPanel();
		panel.add(spinnerPanel);
		panel.add(startButton);
		add(panel);

		setTitle("Configure simulation");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();

		CoordinatePair windowLocation = GameWindow.getWindowLocation(getWidth(), getHeight());
		setLocation(windowLocation.getX(), windowLocation.getY());

		setVisible(true);
	}

	public JPanel createInputPanel() {
		JLabel xSizeLabel = new JLabel("Maze width");
		xSizeSpinner = new JSpinner(new SpinnerNumberModel(5, 2, 10, 1));
		JLabel ySizeLabel = new JLabel("Maze height");
		ySizeSpinner = new JSpinner(new SpinnerNumberModel(5, 2, 10, 1));

		JLabel pRewardLabel = new JLabel("pReward");
		pRewardSpinner = new JSpinner(new SpinnerNumberModel(0.2, 0, 1, 0.1));
		JLabel pPunishmentLabel = new JLabel("pPunishment");
		pPunishmentSpinner = new JSpinner(new SpinnerNumberModel(0.1, 0, 1, 0.1));

		JPanel spinnerPanel = new JPanel();
		spinnerPanel.setLayout(new GridLayout(2, 4));
		spinnerPanel.add(xSizeLabel);
		spinnerPanel.add(xSizeSpinner);
		spinnerPanel.add(ySizeLabel);
		spinnerPanel.add(ySizeSpinner);
		spinnerPanel.add(pRewardLabel);
		spinnerPanel.add(pRewardSpinner);
		spinnerPanel.add(pPunishmentLabel);
		spinnerPanel.add(pPunishmentSpinner);

		return spinnerPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			dispose();
			EnvironmentManager.start((int) xSizeSpinner.getValue(), (int) ySizeSpinner.getValue(),
					(double) pRewardSpinner.getValue(), (double) pPunishmentSpinner.getValue());
		}
	}

}
