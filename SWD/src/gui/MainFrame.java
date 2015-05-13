package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JButton button;
	private JLabel label;
	
	private boolean buttonSwitch = true;

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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button = new JButton("Click!");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonSwitch = !buttonSwitch;
				label.setText(getLabelText());
			}
		});
		button.setBounds(28, 93, 89, 23);
		contentPane.add(button);
		
		label = new JLabel(getLabelText());
		label.setBounds(199, 97, 46, 14);
		contentPane.add(label);
	}
	
	private String getLabelText(){
		if(buttonSwitch)
			return "true true";
		else
			return "false false";
	}
}
