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
	
	public ArrayList<Double[][]> getMatrices () {
		return matrices;
	}
	
	public int[] getQuestion(boolean next, Double answer) {
		matrices.get(activeMatrix)[activeRowNumber][activeColNumber] = answer;
		matrices.get(activeMatrix)[activeColNumber][activeRowNumber] = 1.0/answer;
		
		int activeMatrixLastIndex = matrices.get(activeMatrix).length - 1, matrixToShow = -1;
		if(next) {
			if(activeColNumber < activeMatrixLastIndex)
				activeColNumber++;
			else {
				if(activeRowNumber < activeMatrixLastIndex - 1){
					activeRowNumber++;
					activeColNumber = activeRowNumber + 1;
				}
				else {
					if(activeMatrix < matrices.size() - 1) {
						activeMatrix++;
						activeRowNumber = 0;
						activeColNumber = activeRowNumber + 1;
						matrixToShow = activeMatrix - 1;
					}
					else
						return new int[] {-1, 0, 0, 4, matrices.size() - 1};
				}
			}
		} else {
			if(activeColNumber > activeRowNumber + 1)
				activeColNumber--;
			else {
				if(activeRowNumber > 0){
					activeRowNumber--;
					activeColNumber = activeMatrixLastIndex;
				}
				else {
					if(activeMatrix > 0) {
						activeMatrix--;
						activeMatrixLastIndex = matrices.get(activeMatrix).length - 1;
						activeRowNumber = activeMatrixLastIndex - 1;
						activeColNumber = activeMatrixLastIndex;
					}
					else
						return new int[] {-2, 0, 0, 4, -1};
				}
			}
		}
		
		Double answerInMatrix = matrices.get(activeMatrix)[activeRowNumber][activeColNumber];
		
		return new int[] {activeMatrix, activeRowNumber, activeColNumber, selectedRadio(answerInMatrix), matrixToShow};
	}
	
	private void printMatrix(int n) {
		Double[][] matrix = matrices.get(n);
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + "   ");
			}
			System.out.println();
		}
	}
	
	private int selectedRadio(Double answer) {
		if(answer != null) {
			if(answer == 9.0)
				return 0;
			if(answer == 7.0)
				return 1;
			if(answer == 5.0)
				return 2;
			if(answer == 3.0)
				return 3;
			if(answer == 1.0)
				return 4;
			if(answer == 1.0/3.0)
				return 5;
			if(answer == 0.2)
				return 6;
			if(answer == 1.0/7.0)
				return 7;
			if(answer == 1.0/9.0)
				return 8;
		}
		return 4;
	}
}
