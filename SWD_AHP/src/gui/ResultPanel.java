package gui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.Insets;
import java.util.ArrayList;

public class ResultPanel extends JPanel {
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	/**
	 * Create the panel.
	 */
	public ResultPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 2.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("Wynik przeprowadzonych obliczeñ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(label, gbc_label);
		
		JLabel lblNewLabel = new JLabel("W postaci rankingu produktów od najlepszego");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		JList<String> list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
	}
	
	public void loadRank(ArrayList<String> rank) {
		listModel.clear();
		for(int i = 0; i < rank.size(); i++) {
			listModel.addElement(i+1 + ". " + rank.get(i));
		}
	}
}
