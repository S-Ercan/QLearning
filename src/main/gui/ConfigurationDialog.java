package main.gui;

import main.maze.EnvironmentManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JPanel createInputPanel() {
        JLabel xSizeLabel = new JLabel("Maze width");
        xSizeSpinner = new JSpinner(new SpinnerNumberModel(5, 2, 10, 1));
        JLabel ySizeLabel = new JLabel("Maze height");
        ySizeSpinner = new JSpinner(new SpinnerNumberModel(5, 2, 10, 1));

        JLabel pRewardLabel = new JLabel("p(tile has reward)");
        pRewardSpinner = new JSpinner(new SpinnerNumberModel(0.2, 0, 1, 0.1));
        JLabel pPunishmentLabel = new JLabel("p(tile has punishment)");
        pPunishmentSpinner = new JSpinner(new SpinnerNumberModel(0.1, 0, 1, 0.1));
        pRewardSpinner.addChangeListener(new JSpinnerChangeListener(pRewardSpinner, pPunishmentSpinner));
        pPunishmentSpinner.addChangeListener(new JSpinnerChangeListener(pPunishmentSpinner, pRewardSpinner));

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

    private class JSpinnerChangeListener implements ChangeListener {

        private JSpinner thisSpinner;
        private JSpinner otherSpinner;

        JSpinnerChangeListener(JSpinner thisSpinner, JSpinner otherSpinner) {
            this.thisSpinner = thisSpinner;
            this.otherSpinner = otherSpinner;
        }

        @Override
        public void stateChanged(ChangeEvent arg0) {
            double thisValue = (double) thisSpinner.getValue();
            double otherValue = (double) otherSpinner.getValue();
            if (thisValue + otherValue > 1) {
                otherSpinner.setValue(1 - thisValue);
            }
        }

    }

}
