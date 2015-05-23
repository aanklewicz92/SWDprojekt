package gui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.util.ArrayList;

public class MatrixDialog extends JDialog {
	
	/**
	 * Launch the application.
	 */
	public static void main(ArrayList<String> list, Double[][] matrix, String label) {
		try {
			MatrixDialog dialog = new MatrixDialog(list, matrix, label);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MatrixDialog(ArrayList<String> list, Double[][] matrix, String label) {
		
		int height = 27*(list.size() + 1);
		
		Object[] columns = new Object[list.size()+1];
		for(int i = 0; i < list.size()+1; i++)
			columns[i] = "";
		
		setBounds(100, 100, 31*(list.size() + 1), height + 14);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(label);
		lblNewLabel.setBounds(0, 0, 30*(list.size() + 1), 14);
		getContentPane().add(lblNewLabel);
		
		JTable table = new JTable(getData(list, matrix), columns);
		table.setEnabled(false);
		table.setBounds(0, 14, 30*(list.size() + 1), height);
		getContentPane().add(table);
		
	}
	
	private Object[][] getData(ArrayList<String> list, Double[][] matrix) {
		Object[][] data = new Object[list.size() + 1][list.size() + 1];
		for(int i = 0; i < list.size(); i++)
			data[0][i+1] = list.get(i);
		for(int i = 1; i < data.length; i++) {
			data[i][0] = list.get(i-1);
			for(int j = 1; j < data.length; j++) {
				String dataCell = "";
				Double matrixCell = matrix[i-1][j-1];
				if(matrixCell == 1.0/9.0)
					dataCell = "1/9";
				else if(matrixCell == 1.0/7.0)
					dataCell = "1/7";
				else if(matrixCell == 0.2)
					dataCell = "1/5";
				else if(matrixCell == 1.0/3.0)
					dataCell = "1/3";
				else
					dataCell = String.format("%1.0f", matrixCell);
				data[i][j] = dataCell;
			}
		}
		return data;
	}
}
