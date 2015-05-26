package gui;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ProductsPanel extends JPanel {
	private JTextField textFieldName;
	private JList<String> list;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JButton buttonAdd, buttonDelete, buttonClearAll, buttonDone;

	/**
	 * Create the panel.
	 */
	public ProductsPanel(MainFrame parentFrame) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{79, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{2.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel labelDescription = new JLabel("Zdefiniuj listê produktów");
		GridBagConstraints gbclabelDescription = new GridBagConstraints();
		gbclabelDescription.gridwidth = 2;
		gbclabelDescription.insets = new Insets(0, 0, 5, 5);
		gbclabelDescription.gridx = 0;
		gbclabelDescription.gridy = 0;
		add(labelDescription, gbclabelDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbcScrollPane = new GridBagConstraints();
		gbcScrollPane.gridheight = 3;
		gbcScrollPane.gridwidth = 2;
		gbcScrollPane.insets = new Insets(0, 0, 5, 5);
		gbcScrollPane.fill = GridBagConstraints.BOTH;
		gbcScrollPane.gridx = 2;
		gbcScrollPane.gridy = 0;
		add(scrollPane, gbcScrollPane);
		
		list = new JList<String>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!list.isSelectionEmpty())
					buttonDelete.setEnabled(true);
				else
					buttonDelete.setEnabled(false);
			}
		});
		list.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE
						|| e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    onClickButtonDelete();
                }
			}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		scrollPane.setViewportView(list);
		
		JLabel labelTextFieldName = new JLabel("Dodaj produkt:");
		GridBagConstraints gbcLabelTextFieldName = new GridBagConstraints();
		gbcLabelTextFieldName.anchor = GridBagConstraints.WEST;
		gbcLabelTextFieldName.gridwidth = 2;
		gbcLabelTextFieldName.insets = new Insets(0, 0, 5, 5);
		gbcLabelTextFieldName.gridx = 0;
		gbcLabelTextFieldName.gridy = 1;
		add(labelTextFieldName, gbcLabelTextFieldName);
		
		textFieldName = new JTextField();
		textFieldName = new JTextField();
		textFieldName.setBounds(10, 36, 86, 20);
		add(textFieldName);
		textFieldName.setColumns(10);
		textFieldName.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void removeUpdate(DocumentEvent arg0) {
						buttonAdd.setEnabled(listTest());
					}
					@Override
					public void insertUpdate(DocumentEvent arg0) {
						buttonAdd.setEnabled(listTest());
					}
					@Override
					public void changedUpdate(DocumentEvent arg0) {
						buttonAdd.setEnabled(listTest());
					}
				});
		textFieldName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listTest())
					onClickButtonAdd();
			}
		});
		GridBagConstraints gbcTextFieldName = new GridBagConstraints();
		gbcTextFieldName.gridwidth = 2;
		gbcTextFieldName.insets = new Insets(0, 0, 5, 5);
		gbcTextFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldName.gridx = 0;
		gbcTextFieldName.gridy = 2;
		add(textFieldName, gbcTextFieldName);
		
		buttonAdd = new JButton("Dodaj");
		buttonAdd.setEnabled(false);
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickButtonAdd();
			}
		});
		GridBagConstraints gbcButtonAdd = new GridBagConstraints();
		gbcButtonAdd.insets = new Insets(0, 0, 0, 5);
		gbcButtonAdd.gridx = 0;
		gbcButtonAdd.gridy = 3;
		add(buttonAdd, gbcButtonAdd);
		
		buttonDelete = new JButton("Usuñ");
		buttonDelete.setEnabled(false);
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    onClickButtonDelete();
			}
		});
		GridBagConstraints gbcbuttonDelete = new GridBagConstraints();
		gbcbuttonDelete.insets = new Insets(0, 0, 0, 5);
		gbcbuttonDelete.gridx = 1;
		gbcbuttonDelete.gridy = 3;
		add(buttonDelete, gbcbuttonDelete);
		
		buttonClearAll = new JButton("Wyczyœæ wszystko");
		buttonClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				list.setModel(listModel);
				textFieldName.setEnabled(true);
				buttonDone.setEnabled(false);
				parentFrame.onClickProductsClearAll();
			}
		});
		GridBagConstraints gbcbuttonClearAll = new GridBagConstraints();
		gbcbuttonClearAll.insets = new Insets(0, 0, 0, 5);
		gbcbuttonClearAll.gridx = 2;
		gbcbuttonClearAll.gridy = 3;
		add(buttonClearAll, gbcbuttonClearAll);
		
		buttonDone = new JButton("Gotowe");
		//buttonDone.setEnabled(false); //do odkomentowania
		buttonDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*textFieldName.setEnabled(false);
				list.setEnabled(false);
				buttonAdd.setEnabled(false);
				buttonDelete.setEnabled(false);
				buttonDone.setEnabled(false);
				ArrayList<String> list = new ArrayList<>();
				for(int i = 0; i < listModel.size(); i++)
					list.add(listModel.getElementAt(i));
				parentFrame.onClickProductsDone(list);*/
				parentFrame.testAlgorithm(); //linijka do wywalenia jak bêdzie wszystko dzia³aæ
			}
		});
		GridBagConstraints gbcbuttonDone = new GridBagConstraints();
		gbcbuttonDone.gridx = 3;
		gbcbuttonDone.gridy = 3;
		add(buttonDone, gbcbuttonDone);
	}
	
	private boolean minListCountTest() {
		if(listModel.size() >= 2)
			return true;
		else
			return false;
	}
	
	private boolean listTest() {
		if (textFieldName.getText().trim().length() == 0)
			return false;
		if (listModel.size() >= 15)
			return false;
		for(int i = 0; i < listModel.size(); i++){
			if(textFieldName.getText().equals(listModel.getElementAt(i))){
				return false;
			}
		}
		return true;
	}
	
	private void onClickButtonAdd() {
		listModel.addElement(textFieldName.getText().trim());
		list.setModel(listModel);
		buttonDone.setEnabled(minListCountTest());
		buttonAdd.setEnabled(false);
		textFieldName.setText("");
	}
	
	private void onClickButtonDelete() {
		if(!list.isSelectionEmpty())
			listModel.remove(list.getSelectedIndex());
		buttonAdd.setEnabled(listTest());
		buttonDone.setEnabled(minListCountTest());
	}
}