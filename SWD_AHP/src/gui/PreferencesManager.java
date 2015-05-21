package gui;

import java.util.ArrayList;

public class PreferencesManager {
	private ArrayList<Double[][]> matrices = new ArrayList<Double[][]>();
	
	private int activeMatrix = 0, activeRowNumber = 0, activeColNumber = 1;
	
	public PreferencesManager(int productsNumber, int criteriaNumber) {
		matrices.add(new Double[criteriaNumber][criteriaNumber]);
		for(int i = 0; i < criteriaNumber; i++)
			matrices.add(new Double[productsNumber][productsNumber]);
		
		for(int i = 0; i < matrices.size(); i++)
			for(int j = 0; j < matrices.get(i).length; j++)
				matrices.get(i)[j][j] = 1.0;
		
	}
	
	public Double[][] getMatrix(int n) {
		return matrices.get(n);
	}
	
	public int[] getQuestion() {
		int activeMatrixSize = matrices.get(activeMatrix).length - 1;
		if(activeColNumber < activeMatrixSize)
			activeColNumber++;
		else {
			if(activeRowNumber < activeMatrixSize - 1){
				activeRowNumber++;
				activeColNumber = activeRowNumber + 1;
			}
			else {
				if(activeMatrix < matrices.size()) {
					activeMatrix++;
					activeRowNumber = 0;
					activeColNumber = activeRowNumber + 1;
				}
				else
					return new int[] {-1, -1, -1};
			}
		}
		return new int[] {activeMatrix, activeRowNumber, activeColNumber};
	}
	
	public void setAnswer(Double answer) {
		matrices.get(activeMatrix)[activeRowNumber][activeColNumber] = answer;
		matrices.get(activeMatrix)[activeColNumber][activeRowNumber] = 1.0/answer;
	}
}
