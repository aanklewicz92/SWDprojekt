package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.Insets;

import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import logic.InconsistentMatrixListener;

public class PreferencesPanel extends JPanel implements InconsistentMatrixListener{
	private PreferencesManager manager;
	private MainFrame mainFrame;
	private ArrayList<String> productsList, criteriaList;
	
	private JRadioButton[] radioPreferences = new JRadioButton[9];
	private JLabel labelCriterionValue, labelProductAValue, labelProductBValue, labelProductA, labelProductB;
	private JButton buttonNextQuestion, buttonPreviousQuestion;
	
	private Double answer;
	private int[] active = {0, 0, 1};
	private boolean runPossible = false;

	/**
	 * Create the panel.
	 */
	public PreferencesPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{2.0, 1.0, 1.0, 2.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel labelCriterion = new JLabel("W kategorii:");
		GridBagConstraints gbc_labelCriterion = new GridBagConstraints();
		gbc_labelCriterion.insets = new Insets(0, 0, 5, 5);
		gbc_labelCriterion.gridx = 0;
		gbc_labelCriterion.gridy = 0;
		add(labelCriterion, gbc_labelCriterion);
		
		labelCriterionValue = new JLabel("---");
		GridBagConstraints gbc_labelCriterionValue = new GridBagConstraints();
		gbc_labelCriterionValue.gridwidth = 2;
		gbc_labelCriterionValue.insets = new Insets(0, 0, 5, 0);
		gbc_labelCriterionValue.gridx = 2;
		gbc_labelCriterionValue.gridy = 0;
		add(labelCriterionValue, gbc_labelCriterionValue);
		
		labelProductA = new JLabel("Kategoria");
		GridBagConstraints gbc_labelProductA = new GridBagConstraints();
		gbc_labelProductA.insets = new Insets(0, 0, 5, 5);
		gbc_labelProductA.gridx = 0;
		gbc_labelProductA.gridy = 1;
		add(labelProductA, gbc_labelProductA);
		
		JLabel labelPreferedThan = new JLabel("jest wzglêdem");
		GridBagConstraints gbc_labelPreferedThan = new GridBagConstraints();
		gbc_labelPreferedThan.gridwidth = 2;
		gbc_labelPreferedThan.insets = new Insets(0, 0, 5, 5);
		gbc_labelPreferedThan.gridx = 1;
		gbc_labelPreferedThan.gridy = 1;
		add(labelPreferedThan, gbc_labelPreferedThan);
		
		labelProductB = new JLabel("Kategoria");
		GridBagConstraints gbc_labelProductB = new GridBagConstraints();
		gbc_labelProductB.insets = new Insets(0, 0, 5, 0);
		gbc_labelProductB.gridx = 3;
		gbc_labelProductB.gridy = 1;
		add(labelProductB, gbc_labelProductB);
		
		labelProductAValue = new JLabel();
		GridBagConstraints gbc_labelProductAValue = new GridBagConstraints();
		gbc_labelProductAValue.gridheight = 3;
		gbc_labelProductAValue.insets = new Insets(0, 0, 5, 5);
		gbc_labelProductAValue.gridx = 0;
		gbc_labelProductAValue.gridy = 5;
		add(labelProductAValue, gbc_labelProductAValue);
		
		labelProductBValue = new JLabel();
		GridBagConstraints gbc_radioProductBValue = new GridBagConstraints();
		gbc_radioProductBValue.gridheight = 3;
		gbc_radioProductBValue.insets = new Insets(0, 0, 5, 0);
		gbc_radioProductBValue.gridx = 3;
		gbc_radioProductBValue.gridy = 5;
		add(labelProductBValue, gbc_radioProductBValue);
		
		radioPreferences[0] = new JRadioButton("Bezwzglêdnie wa¿niejsza");
		radioPreferences[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedPreference(0);
			}
		});
		GridBagConstraints gbc_radioButton1 = new GridBagConstraints();
		gbc_radioButton1.anchor = GridBagConstraints.WEST;
		gbc_radioButton1.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton1.gridx = 2;
		gbc_radioButton1.gridy = 2;
		add(radioPreferences[0], gbc_radioButton1);
		
		radioPreferences[1] = new JRadioButton("Zdecydowanie wa¿niejsza");
		radioPreferences[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedPreference(1);
			}
		});
		GridBagConstraints gbc_radioButton3 = new GridBagConstraints();
		gbc_radioButton3.anchor = GridBagConstraints.WEST;
		gbc_radioButton3.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton3.gridx = 2;
		gbc_radioButton3.gridy = 3;
		add(radioPreferences[1], gbc_radioButton3);
		
		radioPreferences[2] = new JRadioButton("WyraŸnie wa¿niejsza");
		radioPreferences[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedPreference(2);
			}
		});
		GridBagConstraints gbc_radioButton5 = new GridBagConstraints();
		gbc_radioButton5.anchor = GridBagConstraints.WEST;
		gbc_radioButton5.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton5.gridx = 2;
		gbc_radioButton5.gridy = 4;
		add(radioPreferences[2], gbc_radioButton5);
		
		radioPreferences[3] = new JRadioButton("Nieznacznie wa¿niejsza");
		radioPreferences[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedPreference(3);
			}
		});
		GridBagConstraints gbc_radioButton7 = new GridBagConstraints();
		gbc_radioButton7.anchor = GridBagConstraints.WEST;
		gbc_radioButton7.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton7.gridx = 2;
		gbc_radioButton7.gridy = 5;
		add(radioPreferences[3], gbc_radioButton7);
		
		radioPreferences[4] = new JRadioButton("Równowa¿na");
		radioPreferences[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedPreference(4);
			}
		});
		GridBagConstraints gbc_radioButton9 = new GridBagConstraints();
		gbc_radioButton9.anchor = GridBagConstraints.WEST;
		gbc_radioButton9.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton9.gridx = 2;
		gbc_radioButton9.gridy = 6;
		add(radioPreferences[4], gbc_radioButton9);
		
		radioPreferences[5] = new JRadioButton("Nieznacznie mniej wa¿na");
		radioPreferences[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedPreference(5);
			}
		});
		GridBagConstraints gbc_radioButton10 = new GridBagConstraints();
		gbc_radioButton10.anchor = GridBagConstraints.WEST;
		gbc_radioButton10.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton10.gridx = 2;
		gbc_radioButton10.gridy = 7;
		add(radioPreferences[5], gbc_radioButton10);
		
		radioPreferences[6] = new JRadioButton("WyraŸnie mniej wa¿na");
		radioPreferences[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedPreference(6);
			}
		});
		GridBagConstraints gbc_radioButton11 = new GridBagConstraints();
		gbc_radioButton11.anchor = GridBagConstraints.WEST;
		gbc_radioButton11.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton11.gridx = 2;
		gbc_radioButton11.gridy = 8;
		add(radioPreferences[6], gbc_radioButton11);
		
		radioPreferences[7] = new JRadioButton("Zdecydowanie mniej wa¿na");
		radioPreferences[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedPreference(7);
			}
		});
		GridBagConstraints gbc_radioButton12 = new GridBagConstraints();
		gbc_radioButton12.anchor = GridBagConstraints.WEST;
		gbc_radioButton12.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton12.gridx = 2;
		gbc_radioButton12.gridy = 9;
		add(radioPreferences[7], gbc_radioButton12);
		
		radioPreferences[8] = new JRadioButton("Bezwzglêdmnie mniej wa¿na");
		radioPreferences[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedPreference(8);
			}
		});
		GridBagConstraints gbc_radioButton13 = new GridBagConstraints();
		gbc_radioButton13.anchor = GridBagConstraints.WEST;
		gbc_radioButton13.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton13.gridx = 2;
		gbc_radioButton13.gridy = 10;
		add(radioPreferences[8], gbc_radioButton13);
		
		buttonNextQuestion = new JButton("Nast\u0119pne");
		buttonNextQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!runPossible)
					getQuestion(true);
				else {
					mainFrame.onClickRun(manager.getMatrices());
				}
			}
		});
		
		buttonPreviousQuestion = new JButton("Poprzednie");
		buttonPreviousQuestion.setEnabled(false);
		buttonPreviousQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getQuestion(false);
			}
		});
		GridBagConstraints gbc_buttonPreviousQuestion = new GridBagConstraints();
		gbc_buttonPreviousQuestion.insets = new Insets(0, 0, 0, 5);
		gbc_buttonPreviousQuestion.gridx = 0;
		gbc_buttonPreviousQuestion.gridy = 11;
		add(buttonPreviousQuestion, gbc_buttonPreviousQuestion);
		GridBagConstraints gbc_buttonNextQuestion = new GridBagConstraints();
		gbc_buttonNextQuestion.gridx = 3;
		gbc_buttonNextQuestion.gridy = 11;
		add(buttonNextQuestion, gbc_buttonNextQuestion);
		
		selectedPreference(4);
	}

	private void showMatrix(Double[][] matrix) {
		String message = "Macierz <tu nazwa>\n\n";
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				message += matrix[i][j] + "   ";
			}
			message += "\n";
		}
		JOptionPane.showMessageDialog(mainFrame, message);
	}
	
	private void selectedPreference(int n) {
		for(int i = 0; i < radioPreferences.length; i++) {
			if(i != n)
				radioPreferences[i].setSelected(false);
			else
				radioPreferences[i].setSelected(true);
		}
		
		switch(n) {
		case 0:
			answer = 9.0;
			break;
		case 1:
			answer = 7.0;
			break;
		case 2:
			answer = 5.0;
			break;
		case 3:
			answer = 3.0;
			break;
		case 4:
			answer = 1.0;
			break;
		case 5:
			answer = 1.0/3.0;
			break;
		case 6:
			answer = 0.2;
			break;
		case 7:
			answer = 1.0/7.0;
			break;
		case 8:
			answer = 1.0/9.0;
			break;
		}
	}
	
	private void getQuestion(boolean next) {
		active = manager.getQuestion(next, answer);
		
		if(active[0] == -1) {
			buttonNextQuestion.setText("Uruchom AHP");
			runPossible = true;
		} else {
			buttonNextQuestion.setText("Nastêpne");
			runPossible = false;
		}
		if(active[0] == -2)
			buttonPreviousQuestion.setEnabled(false);
		else
			buttonPreviousQuestion.setEnabled(true);
		
		if(active[0] > 0 ) {
			labelCriterionValue.setText(criteriaList.get(active[0]-1));
			labelProductAValue.setText(productsList.get(active[1]));
			labelProductBValue.setText(productsList.get(active[2]));
			labelProductA.setText("Produkt");
			labelProductB.setText("Produktu");
			radioPreferences[0].setText("Bezwzglêdnie lepszy");
			radioPreferences[1].setText("Zdecydowanie lepszy");
			radioPreferences[2].setText("WyraŸnie lepszy");
			radioPreferences[3].setText("Nieznacznie lepszy");
			radioPreferences[4].setText("Równowa¿ny");
			radioPreferences[5].setText("Nieznacznie gorszy");
			radioPreferences[6].setText("WyraŸnie gorszy");
			radioPreferences[7].setText("Zdecydowanie gorszy");
			radioPreferences[8].setText("Bezwzglêdnie gorszy");
		}
		
		if(active[0] == 0) {
			labelCriterionValue.setText("---");
			labelProductAValue.setText(criteriaList.get(active[1]));
			labelProductBValue.setText(criteriaList.get(active[2]));
			labelProductA.setText("Kategoria");
			labelProductB.setText("Kategorii");
			radioPreferences[0].setText("Bezwzglêdnie wa¿niejsza");
			radioPreferences[1].setText("Zdecydowanie wa¿niejsza");
			radioPreferences[2].setText("WyraŸnie wa¿niejsza");
			radioPreferences[3].setText("Nieznacznie wa¿niejsza");
			radioPreferences[4].setText("Równowa¿na");
			radioPreferences[5].setText("Nieznacznie mniej wa¿na");
			radioPreferences[6].setText("WyraŸnie mniej wa¿na");
			radioPreferences[7].setText("Zdecydowanie mniej wa¿na");
			radioPreferences[8].setText("Bezwzglêdnie mniej wa¿na");
		}
		
		selectedPreference(active[3]);
		
		if(active[4] != -1)
			showMatrix(manager.getMatrix(active[4]));
	}
	
	public void setDataFromFrame(PreferencesManager manager, ArrayList<String> productsList, ArrayList<String> criteriaList) {
		this.manager = manager;
		this.productsList = productsList;
		this.criteriaList = criteriaList;

		labelProductAValue.setText(criteriaList.get(active[1]));
		labelProductBValue.setText(criteriaList.get(active[2]));
	}

	@Override
	public void actionPerformed(ActionEvent e) {}

	@Override
	public void inconsistentMatrix(Integer number) {
		JOptionPane.showMessageDialog(mainFrame, "Macierz " + number + " jest niespójna");
	}
}
