package logic;

import java.util.ArrayList;

public class Algorithm {
	static final Double MIN_CONSISTENCY_RATIO = 0.1;
	
	InconsistentMatrixListener inconsistentMarixListener;
	AlgorithmListener algorithmListener;
	
	public Algorithm(InconsistentMatrixListener incnsistentMatrixListener, AlgorithmListener algorithmListener) {
		this.inconsistentMarixListener = incnsistentMatrixListener;
		this.algorithmListener = algorithmListener;
	}
	
	public void runAlgorithm(ArrayList<Double[][]> matrices) {
		ArrayList<Integer> productsRank = calculate(matrices);
		algorithmListener.algorithmFinished(productsRank);
	}
	
	/*public void testAlgorithm(ArrayList<Double[][]> matrices) {
		ArrayList<Integer> productsRank = calculate(matrices);
		for(Integer el : productsRank)
			System.out.print(el + " ");
	}*/
	
	private ArrayList<Integer> calculate(ArrayList<Double[][]> matrices ){
		int criteriaMatrixLength = matrices.get(0).length, productMatrixLength = matrices.get(1).length;
		
		Double[][] criteriaMatrix = matrices.get(0);
		Double[] criteriaCVector = new Double [criteriaMatrixLength];
		Double[][] productsCVectors = new Double[matrices.size()][productMatrixLength];
		Double[][] normalizedCriteriaMatrix = new Double[criteriaMatrixLength][criteriaMatrixLength];
		Double[] criteriaSVector = new Double[criteriaMatrixLength];
		ArrayList<Double[][]> normalizedProductsMatrices = new ArrayList<Double[][]>();
		Double[][] productsSVectors = new Double[matrices.size()][productMatrixLength];
		Double[] rankVector = new Double [matrices.get(1).length];
		
		//Obliczanie wektora C dla mcierzy kryteriów
		for(int i = 0; i < criteriaMatrixLength; i++) {
			criteriaCVector[i] = 0.0;
			for(int j = 0; j < criteriaMatrixLength; j++)
				criteriaCVector[i] += criteriaMatrix[j][i];
		}
		
		//Obliczanie wektorów C dla macierzy produktów
		for(int k = 1; k < matrices.size(); k++) {
			Double[][] productMatixs = matrices.get(k);
			for(int i = 0; i < productMatrixLength; i++) {
				productsCVectors[k][i] = 0.0;
				for(int j = 0; j < productMatrixLength; j++)
					productsCVectors[k][i] += productMatixs[j][i];
			}
		}
		
		//Normalizacja macierzy kryteriów
		for(int i = 0; i < criteriaMatrixLength; i++)
			for(int j = 0; j < criteriaMatrixLength; j++)
				normalizedCriteriaMatrix[j][i] = (criteriaMatrix[j][i] / criteriaCVector[i]);
		
		//Normalizacja macierzy produktów
		for(int k = 1; k < matrices.size(); k++) {
			Double[][] productsMatrix = matrices.get(k);
			Double[][] normalizedProductMatrix = new Double[productMatrixLength][productMatrixLength];
			for(int i = 0; i < productMatrixLength; i++)
				for(int j = 0; j < productMatrixLength; j++)
					normalizedProductMatrix[i][j] = (productsMatrix[j][i] / productsCVectors[k][i]);	
			normalizedProductsMatrices.add(normalizedProductMatrix);
		}
		
		//Obliczanie wektora S macierzy kryteriów
		for(int i = 0; i < criteriaMatrixLength; i++) {
			Double elementsSum = 0.0;
			for(int j = 0; j < criteriaMatrixLength; j++)
				elementsSum += normalizedCriteriaMatrix[i][j];
			criteriaSVector[i] = elementsSum / criteriaMatrixLength;
		}
		
		//Obliczanie wektorów S macierzy produktów
		for(int k = 0; k < normalizedProductsMatrices.size(); k++) {
			Double[][] normalizedMatrix = normalizedProductsMatrices.get(k);
			for(int i = 0; i < normalizedMatrix.length; i++) {
				Double elementsSum = 0.0;
				for(int j= 0; j < normalizedMatrix.length; j++)
					elementsSum+=normalizedMatrix[j][i];
				productsSVectors[k][i] = elementsSum / productMatrixLength;
			}
		}
		
		//Sprawdzanie spójnoœci macierzy kryteriów
		Double lambda = 0.0;
		for(int i = 0; i < criteriaMatrixLength; i++)
			lambda += criteriaCVector[i]*criteriaSVector[i];
		Double consistencyRatio = (lambda - criteriaMatrixLength) / (criteriaMatrixLength - 1) / getConsistencyRatio(criteriaMatrixLength);
		if(consistencyRatio > MIN_CONSISTENCY_RATIO)
			inconsistentMarixListener.inconsistentMatrix(0);
		
		//Sprawdzanie spójnoœci macierzy produktów
		Double randomConsistencyIndex = getConsistencyRatio(productMatrixLength);
		for(int i = 0; i < criteriaMatrixLength; i++) {
			lambda = 0.0;
			for(int j = 0; j < productMatrixLength; j++)
				lambda += (productsSVectors[i][j]*productsCVectors[i+1][j]);
			consistencyRatio = (lambda - productMatrixLength) / (productMatrixLength - 1) / randomConsistencyIndex;
			if(consistencyRatio > MIN_CONSISTENCY_RATIO)
				inconsistentMarixListener.inconsistentMatrix(i);
		}
		
		//Obliczenie ostatecznego rankingu
		for(int k = 0; k < criteriaMatrixLength; k++)
			for(int j=0; j<productMatrixLength; j++) {
				rankVector[j] = 0.0;
				for(int i= 0; i< criteriaMatrixLength; i++) 
					rankVector[j] += (criteriaSVector[i] * productsSVectors[i][j]);
			}
			
		//Sortowanie rankingu
		ArrayList<Integer> rank = new ArrayList<Integer>();
		Double tempMax = Double.MIN_VALUE;
		while(rank.size() < rankVector.length) {
			int index = 0;
			for(int i = 0; i < rankVector.length; i++) {
				if(rankVector[i] > tempMax) {
					tempMax = rankVector[i];
					index = i;
				}
			}
			rank.add(index);
			rankVector[index] = Double.MIN_VALUE;
			tempMax = Double.MIN_VALUE;
		}
		
		return rank;
	}
	
	public double getConsistencyRatio(int matrixSize) {
			switch (matrixSize) {
					case 2:  return 0.52;
					case 3:  return 0.58;
					case 4:  return 0.89;
					case 5: return 1.11;
					case 6: return 1.25;
					case 7: return 1.37;
					case 8: return 1.4;
					case 9: return 1.45;
					case 10: return 1.49;
					case 11: return 1.51;
					case 12: return 1.54;
					case 13: return 1.56;
					case 14: return 1.57;
					case 15: return 1.58;
					default: return 0.0;
			}
		}
}