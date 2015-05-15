package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.util.ArrayList;

import logic.SomeClass;

public class MainFrame extends JFrame {
	private boolean doneProducts = false;
	private boolean doneCriteria = false;
	private JTabbedPane tabbedPane;
	
	private SomeClass algorithm = new SomeClass();
	
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
		setBounds(100, 100, 450, 300);
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
		
		PreferencesPanel p3 = new PreferencesPanel();
		tabbedPane.addTab("Preferencje", p3);
		tabbedPane.setEnabledAt(2, false);
		
		ResultPanel p4 = new ResultPanel();
		tabbedPane.addTab("Ranking", p4);
		tabbedPane.setEnabledAt(3, false);
		
		AboutPanel p5 = new AboutPanel();
		tabbedPane.addTab("O programie", p5);
	}
	
	public void onClickProductsDone(ArrayList<String> list) {
		doneProducts = true;
		if(!doneCriteria)
			tabbedPane.setSelectedIndex(1);
		algorithm.setProducts(list);
		preferencesPossibleTest();
	}
	
	public void onClickCriteriaDone(int n) {
		doneCriteria = true;
		if(!doneProducts)
			tabbedPane.setSelectedIndex(0);
		algorithm.setCriteria(n);
		preferencesPossibleTest();
	}
	
	public void onClickProductsClearAll() {
		doneProducts = false;
	}
	
	public void onClickCriteriaClearAll() {
		doneProducts = false;
	}
	
	private void preferencesPossibleTest() {
		if(doneProducts && doneCriteria) {
			tabbedPane.setEnabledAt(0, false);
			tabbedPane.setEnabledAt(1, false);
			tabbedPane.setEnabledAt(2, true);
			tabbedPane.setSelectedIndex(2);
		}
	}
}
