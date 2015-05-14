package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CriteriaPanel extends JPanel {

	private JTextField textFieldCriterionName;
	private DefaultListModel<String> listModelCriteria = new DefaultListModel<String>();
	private JList<String> listCriteria;
	
	/**
	 * Create the panel.
	 */
	public CriteriaPanel() {
		setLayout(null);
		
		JLabel labelDescription = new JLabel("description");
		labelDescription.setBounds(10, 11, 46, 14);
		add(labelDescription);
		
		textFieldCriterionName = new JTextField();
		textFieldCriterionName.setBounds(10, 36, 86, 20);
		add(textFieldCriterionName);
		textFieldCriterionName.setColumns(10);
		
		JButton buttonAdd = new JButton("Dodaj");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModelCriteria.addElement(textFieldCriterionName.getText());
				listCriteria.setModel(listModelCriteria);
			}
		});
		buttonAdd.setBounds(10, 67, 89, 23);
		add(buttonAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(176, 11, 209, 166);
		add(scrollPane);
		
		listCriteria = new JList<String>(listModelCriteria);
		scrollPane.setViewportView(listCriteria);
		
		JButton buttonDelete = new JButton("Usuñ");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    listModelCriteria.remove(listCriteria.getSelectedIndex());
			}
		});
		buttonDelete.setBounds(10, 101, 89, 23);
		add(buttonDelete);
		
		JButton buttonClearAll = new JButton("Wyczyœæ wszystko");
		buttonClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModelCriteria.clear();
				listCriteria.setModel(listModelCriteria);
			}
		});
		buttonClearAll.setBounds(10, 135, 89, 23);
		add(buttonClearAll);
	}

}
