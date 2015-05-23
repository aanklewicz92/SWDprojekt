package logic;

import java.util.ArrayList;

public class SomeClass {
	/*
	 * W tej paczce tw�rz sobie swoje klasy z wszelk� logik� oblicze�.
	 * Musimy ustali� w jakiej formie b�dziemy przesy�a� dane mi�dzy logik� a gui.
	 * 
	 * Ja to widz� tak, �e w gui b�d� tworzy� obiekt jakiej� klasy st�d i ten obiekt
	 * albo b�dzie mia� metody albo po prostu pola, do kt�rych ja przypisz� dane wzi�te
	 * u�ytkownika w takiej formie jak b�d� Ci potrzebne tutaj czyli lista/tablica/co� innego,
	 * po dostarczeniu wszystkich danych pod jakim� buttonem gui b�dzie si� kry�o
	 * wywo�anie metody run czy start czy co� na tym obiekcie i ta metoda po zrobieniu ahp
	 * zwr�ci jak�� list�/tablic�/co� innego z wynikami, co ja poka�� w gui.
	 */
	
	InconsistentMatrixListener inconsistentMarixListener;
	AlgorithmListener algorithmListener;
	
	public SomeClass(InconsistentMatrixListener incnsistentMatrixListener, AlgorithmListener algorithmListener) {
		this.inconsistentMarixListener = incnsistentMatrixListener;
		this.algorithmListener = algorithmListener;
	}

	public void setPreferences(ArrayList<Double[][]> matrices) {
		//System.out.println("Macierz kryteri�w");
		Double[][] criteriaMatix = matrices.get(0); //Zawsze na zerowym elemencie
		for(int i = 0; i < criteriaMatix.length; i++) {
			for(int j = 0; j < criteriaMatix.length; j++) {
				//System.out.print(criteriaMatix[i][j] + " ");
			}
			//System.out.println();
		}
		
		//System.out.println("Macierze produkt�w");
		for(int k = 1; k < matrices.size(); k++) { //Zaczynam od 1 bo na 0 by�a ta wy�ej
			//System.out.println("Macierz " + k);
			Double[][] productMatix = matrices.get(k); //Kolejny element z listy macierzy
			for(int i = 0; i < productMatix.length; i++) {
				for(int j = 0; j < productMatix.length; j++) {
					//System.out.print(productMatix[i][j] + " ");
				}
				//System.out.println();
			}
		}
		//System.out.println (criteriaMatix[1][2]);
		
		
	}
	public void normalizeMatrixes(ArrayList<Double[][]> matrices ){
		
		
		Double[][] criteriaMatixs = matrices.get(0); 
		Double[][] product=matrices.get(1);
		Double[] cMatrix = new Double [criteriaMatixs.length];
		Double[][]normalizeCriterialMatrix=new Double[criteriaMatixs.length][criteriaMatixs.length];
		Double[][]normalizeProductMatrixes=new Double[product.length][product.length];
		Double[][] cmatrixes=new Double[matrices.size()][matrices.size()];
		
		
		
		double c=0;
		for(int i=0; i<criteriaMatixs.length; i++){
			for(int j=0; j<criteriaMatixs.length; j++){
				c= c+criteriaMatixs[j][i];
				cMatrix[i]=c;
				}
			cMatrix[i]=c;
			//System.out.print(cMatrix[i] + " ");
			c=0;
		}//suma kolumn w kryteriach
		//System.out.println();
		double d=0;
		for(int k = 1; k < matrices.size(); k++) { 
			//System.out.println("cmacierz " + k);
			
			Double[][] productMatixs = matrices.get(k);
		
			
		
			for(int i = 0; i < productMatixs.length; i++) {
				for(int j = 0; j < productMatixs.length; j++) {
					d=d+productMatixs[j][i];
					
					}
				cmatrixes[k][i]=d;
				//System.out.println(cmatrixes[k][i] + " ");
				d=0;
				
			}
			//suma kolumn w produktach
			//System.out.println( " ");
		}
		//macierz znormalizowana kryteri�w
		//System.out.println( "Macierz znormalizowana kryteri�w");
		Double [] s=new Double[criteriaMatixs.length];
		
		for(int i=0; i<criteriaMatixs.length; i++){
			for(int j=0; j<criteriaMatixs.length; j++){
				normalizeCriterialMatrix[j][i]=(criteriaMatixs[j][i]/cMatrix[i]);
				//System.out.print(normalizeCriterialMatrix[j][i] + " ");
				}
			//System.out.println();
			}
		
		//obliczanie macierzy s dla kryteri�w
		double f=0;
		for(int i=0; i<criteriaMatixs.length; i++){
			for(int j=0; j<criteriaMatixs.length; j++){
				f+=normalizeCriterialMatrix[i][j];
				}
			
			//System.out.println ("f "+ i +" "+f);
			s[i]=((1.0/criteriaMatixs.length)*f);
			//System.out.println ("s "+ i +" "+s[i]);
			f=0;
			
		}
		double suma=0;
		for(int i=0; i<criteriaMatixs.length; i++){
			suma+=cMatrix[i]*s[i];
		}
		double CIProductMatrix=(suma-criteriaMatixs.length)/(criteriaMatixs.length-1);
		//System.out.println("CI product matrix" + CIProductMatrix);
		
		double RI= howLong(criteriaMatixs.length);
		double cr=CIProductMatrix/RI;
		
		//System.out.println("CR"+cr);//policzone Cr macierzy kryteri�w 
		
		ArrayList<Double[][]> NormalizedProduct = new ArrayList<Double[][]>();
		//normalizacja macierzy produkt�w
		for(int k = 1; k < matrices.size(); k++) {
			
			Double[][] productMatrixs = matrices.get(k);
			
			
			//System.out.println("Normalizowana macierz produkt�w:"+k);
			for(int i = 0; i < productMatrixs.length; i++) {
				for(int j = 0; j < productMatrixs.length; j++) {
					normalizeProductMatrixes[i][j]=(productMatrixs[j][i]/cmatrixes[k][i]);
					//System.out.print(normalizeProductMatrixes[i][j] + " ");
					
				}
						
			}
			NormalizedProduct.add(normalizeProductMatrixes);//dodaje element do tablicy
			
			
			
					
			}
		//s dla produkt�w
		System.out.println();
		Double[][] normalize = NormalizedProduct.get(2);
		for(int h = 0; h < normalize.length; h++) {
			for(int l= 0; l< normalize.length; l++) {
				
				System.out.print("tablica �adowana "+ 2+" " +normalize[h][l]  );
				System.out.println();
				
				}};
		
		//System.out.println(product.length);
		for(int k = 0; k < NormalizedProduct.size(); k++) { 
			Double[][] normalized = NormalizedProduct.get(k);
			for(int h = 0; h < normalized.length; h++) {
				for(int l= 0; l< normalize.length; l++) {
					
					System.out.print("tablica �adowana "+ k +" " +normalized[h][l]  );
					}};
			
			System.out.println();
		}
				
		}
	
	public double howLong (int lenght){
	double RI=0;
		switch (lenght){
				case 2:  RI=0.52;
				break;////NIE MAM WARTO�CI, nie wiem co tu wpisa� 
				case 3:  RI=0.58;
				break;
				case 4:  RI=0.89;
				break;
				case 5: RI=1.11;
				break;
				case 6: RI=1.25;
				break;
				case 7: RI=1.37;
				break;
				case 8: RI=1.4;
				break;
				case 9: RI=1.45;
				break;
				case 10: RI=1.49;
				break;
				case 11: RI=1.51;
				break;
				case 12: RI=1.54;
				break;
				case 13: RI=1.56;
				break;
				case 14: RI=1.58;
				break;
				default: 
                break;
		}
		
		return RI;
		
		
		
	}
	
	
	public void runAlgorithm(ArrayList<Double[][]> matrices) {
		inconsistentMarixListener.inconsistentMatrix(0);
		//po prostu daj tak� linijk� tam gdzie wykryjesz
		//niesp�jn� macierz a w argumencie daj numer tablicy z matrices
		
		System.out.println("Liczba produkt�w: " + matrices.get(1).length);
		System.out.println("Liczba kryteriow: " + matrices.get(0).length);
		//to do wywalenia b�dzie oczywi�cie
		
		ArrayList<Integer> productsRank = new ArrayList<Integer>();
		for(int i = 0; i < matrices.get(1).length; i++)
			productsRank.add(i);
		//Ty do tej listy dasz indeksy produkt�w w takiej kolejno�ci jak Ci w rankingu wyjdzie
		
		//nie wiem w ko�cu czy jak jest jaka� macierz niesp�jna to czy robi si� dalej czy nie
		//ale zostawiam furtk�, �e jakby si� nie robi�o to wy�lij mi w tym productsRank
		//na pierwszym elemencie -1
		
		algorithmListener.algorithmFinished(productsRank);
		//i potem odpal t� linijk� z t� list� w argumencie
	}
}
