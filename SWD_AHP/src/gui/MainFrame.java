package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import logic.AlgorithmListener;
import logic.SomeClass;

public class MainFrame extends JFrame implements AlgorithmListener{
	private boolean doneProducts = false;
	private boolean doneCriteria = false;
	private JTabbedPane tabbedPane;
	private PreferencesPanel preferencesPanel;
	private ResultPanel resultPanel;
	
	private ArrayList<String> criteriaList = new ArrayList<String>();
	private ArrayList<String> productsList = new ArrayList<String>();
	
	private SomeClass algorithm;
	private PreferencesManager manager;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 420);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		ProductsPanel p1 = new ProductsPanel(this);
		tabbedPane.addTab("Produkty", p1);
		
		CriteriaPanel p2 = new CriteriaPanel(this);
		tabbedPane.addTab("Kryteria", p2);
		
		preferencesPanel = new PreferencesPanel(this);
		tabbedPane.addTab("Preferencje", preferencesPanel);
		tabbedPane.setEnabledAt(2, false);
		
		resultPanel = new ResultPanel();
		tabbedPane.addTab("Ranking", resultPanel);
		tabbedPane.setEnabledAt(3, false);
	}
	
	private void preferencesPossibleTest() {
		if(doneProducts && doneCriteria) {
			tabbedPane.setEnabledAt(0, false);
			tabbedPane.setEnabledAt(1, false);
			tabbedPane.setEnabledAt(2, true);
			tabbedPane.setSelectedIndex(2);
			manager = new PreferencesManager(productsList.size(), criteriaList.size());
			preferencesPanel.setDataFromFrame(manager, productsList, criteriaList);
		}
	}
	
	public void onClickProductsDone(ArrayList<String> list) {
		doneProducts = true;
		if(!doneCriteria)
			tabbedPane.setSelectedIndex(1);
		productsList = list;
		preferencesPossibleTest();
	}
	
	public void onClickCriteriaDone(ArrayList<String> list) {
		doneCriteria = true;
		if(!doneProducts)
			tabbedPane.setSelectedIndex(0);
		criteriaList = list;
		preferencesPossibleTest();
	}
	
	public void onClickProductsClearAll() {
		doneProducts = false;
	}
	
	public void onClickCriteriaClearAll() {
		doneProducts = false;
	}
	
	public void onClickRun(ArrayList<Double[][]> matrices){
		algorithm = new SomeClass(preferencesPanel, this);
		algorithm.runAlgorithm(matrices);
	}
	
	public void testAlgorithm() {  //metoda do wywalenia jak bêdzie wszystko dzia³aæ
		Double[][] criteria = new Double[][]{
				  { 1.0,	7.0,	0.3333 },
				  { 0.1429,	1.0,	0.2 },
				  { 3.0,	5.0,	1.0 }
				};
		
		Double[][] products1 = new Double[][]{
				  { 1.0,	0.2 },
				  { 5.0,	1.0 }
				};
		
		Double[][] products2 = new Double[][]{
				  { 1.0,	7.0 },
				  { 0.1429,	1.0 }
				};
		
		Double[][] products3 = new Double[][]{
				  { 1.0,	5.0 },
				  { 0.2,	1.0 }
				};
		
		ArrayList<Double[][]> matrices = new ArrayList<Double[][]>();
		matrices.add(criteria);
		matrices.add(products1);
		matrices.add(products2);
		matrices.add(products3);
		
		algorithm = new SomeClass(preferencesPanel, this);
		algorithm.setPreferences(matrices);
		algorithm.normalizeMatrixes(matrices);
	}
	
	public PreferencesManager getPreferencesManager() {
		return manager;
	}
	
	public ArrayList<String> getProducts() {
		return productsList;
	}
	
	public ArrayList<String> getCriteria() {
		return criteriaList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {}

	@Override
	public void algorithmFinished(ArrayList<Integer> rank) {
		if(rank.get(0) != -1) {
			ArrayList<String> textRank = new ArrayList<String>();
			for(Integer index : rank)
				textRank.add(productsList.get(index));
			resultPanel.loadRank(textRank);
			tabbedPane.setEnabledAt(3, true);
			tabbedPane.setSelectedIndex(3);
		}
	}
}
