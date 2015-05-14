package gui;

import javax.management.modelmbean.ModelMBean;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ProductsPanel extends JPanel {
	
	private JTextField textFieldProductName;
	private DefaultListModel<String> listModelProducts = new DefaultListModel<String>();
	private JList<String> listProducts;
	private JButton buttonAdd, buttonDelete, buttonClearAll;

	/**
	 * Create the panel.
	 */
	public ProductsPanel() {
		setLayout(null);
		
		JLabel labelDescription = new JLabel("description");
		labelDescription.setBounds(10, 11, 46, 14);
		add(labelDescription);
		
		textFieldProductName = new JTextField();
		textFieldProductName.setBounds(10, 36, 86, 20);
		add(textFieldProductName);
		textFieldProductName.setColumns(10);
		textFieldProductName.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void removeUpdate(DocumentEvent arg0) {
						uniqeProductsTest();
					}
					@Override
					public void insertUpdate(DocumentEvent arg0) {
						uniqeProductsTest();
					}
					@Override
					public void changedUpdate(DocumentEvent arg0) {
						uniqeProductsTest();
					}
				});
		textFieldProductName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(uniqeProductsTest())
					onClickButtonAdd();
			}
		});
		
		buttonAdd = new JButton("Dodaj");
		buttonAdd.setEnabled(false);
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickButtonAdd();
			}
		});
		buttonAdd.setBounds(10, 67, 89, 23);
		add(buttonAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(176, 11, 209, 166);
		add(scrollPane);
		
		listProducts = new JList<String>(listModelProducts);
		listProducts.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!listProducts.isSelectionEmpty())
					buttonDelete.setEnabled(true);
				else
					buttonDelete.setEnabled(false);
			}
		});
		scrollPane.setViewportView(listProducts);
		
		buttonDelete = new JButton("Usuñ");
		buttonDelete.setEnabled(false);
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    listModelProducts.remove(listProducts.getSelectedIndex());
			}
		});
		buttonDelete.setBounds(10, 101, 89, 23);
		add(buttonDelete);
		
		buttonClearAll = new JButton("Wyczyœæ wszystko");
		buttonClearAll.setEnabled(false);
		buttonClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModelProducts.clear();
				listProducts.setModel(listModelProducts);
				buttonClearAll.setEnabled(false);
			}
		});
		buttonClearAll.setBounds(10, 135, 89, 23);
		add(buttonClearAll);
	}
	
	private boolean listCountTest() {
		if(listModelProducts.size() >= 2)
			return true;
		else
			return false;
	}
	
	private boolean uniqeProductsTest() {
		if (textFieldProductName.getText().length() == 0)
			return false;
		for(int i = 0; i < listModelProducts.size(); i++){
			if(textFieldProductName.getText().equals(listModelProducts.getElementAt(i))){
				buttonAdd.setEnabled(false);
				return false;
			}
		}
		buttonAdd.setEnabled(true);
		return true;
	}
	
	private void onClickButtonAdd() {
		listModelProducts.addElement(textFieldProductName.getText());
		listProducts.setModel(listModelProducts);
		listCountTest();
		uniqeProductsTest();
		buttonClearAll.setEnabled(true);
		textFieldProductName.setText("");
	}
}
