package logic;

import java.sql.Ref;
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
	
	public SomeClass() {}
	
	public void setProducts(ArrayList<String> list) {
		System.out.println("Produkty:");
		for(String product : list)
			System.out.println(product);
	}
	
	public void setCriteria(int n) {
		System.out.println("Liczba kryteriow: " + n);
		//Wydaje mi sie ze wystarczy Ci liczba kryteriow.
		//Jak jednak nie to pisz.
	}

	public void setPreferences(ArrayList<Double[][]> matrices ) {
		System.out.println("Macierz kryteri�w");
		Double[][] criteriaMatix = matrices.get(0); //Zawsze na zerowym elemencie
		for(int i = 0; i < criteriaMatix.length; i++) {
			for(int j = 0; j < criteriaMatix.length; j++) {
				System.out.print(criteriaMatix[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("Macierze produkt�w");
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
		Double[] cMatrix = new Double [criteriaMatixs.length];
		Double[][]normalizeCriterialMatrix=new Double[criteriaMatixs.length][criteriaMatixs.length];
		Double[][]normalizeProductMatrixes=new Double[criteriaMatixs.length][criteriaMatixs.length];
		Double[][] cmatrixes=new Double[matrices.size()][matrices.size()];
		
		int lenght = matrices.size();
		System.out.println("cmatrix");
		System.out.println( "Macierz znormalizowana kryteri�w");
		double c=0;
		for(int i=0; i<criteriaMatixs.length; i++){
			for(int j=0; j<criteriaMatixs.length; j++){
				c= c+criteriaMatixs[j][i];
				cMatrix[i]=c;
				}
			cMatrix[i]=c;
			System.out.print(cMatrix[i] + " ");
			c=0;
		}//suma kolumn w kryteriach
		System.out.println();
		double d=0;
		for(int k = 1; k < matrices.size(); k++) { 
			System.out.println("cmacierz " + k);
			
			Double[][] productMatixs = matrices.get(k);
		
			
		
			for(int i = 0; i < productMatixs.length; i++) {
				for(int j = 0; j < productMatixs.length; j++) {
					d=d+productMatixs[j][i];
					
					}
				cmatrixes[k][i]=d;
				System.out.println(cmatrixes[k][i] + " ");
				d=0;
				
			}
			//suma kolumn w produktach
			System.out.println( " ");
		}
		//macierz znormalizowana kryteri�w
		System.out.println( "Macierz znormalizowana kryteri�w");
		Double [] s=new Double[criteriaMatixs.length];
		
		for(int i=0; i<criteriaMatixs.length; i++){
			for(int j=0; j<criteriaMatixs.length; j++){
				normalizeCriterialMatrix[j][i]=(criteriaMatixs[j][i]/cMatrix[i]);
				System.out.print(normalizeCriterialMatrix[j][i] + " ");
				}
			System.out.println();
			}
		//normalizacja macierzy produkt�w
		for(int k = 1; k < matrices.size(); k++) { 
			
			Double[][] productMatixs = matrices.get(k);
			
			System.out.println("Normalizowana macierz produkt�w:"+k);
			for(int i = 0; i < productMatixs.length; i++) {
				for(int j = 0; j < productMatixs.length; j++) {
					normalizeProductMatrixes[j][i]=(productMatixs[j][i]/cmatrixes[k][i]);
					System.out.print(normalizeProductMatrixes[j][i] + " ");
					
				}
			System.out.println();
				
			}	
			}
		//obliczanie macierzy s dla kryteri�w
		double f=0;
		for(int i=0; i<criteriaMatixs.length; i++){
			for(int j=0; j<criteriaMatixs.length; j++){
				f+=normalizeCriterialMatrix[i][j];
				}
			
			//System.out.println ("f "+ i +" "+f);
			s[i]=((1.0/criteriaMatixs.length)*f);
			System.out.println ("s "+ i +" "+s[i]);
			f=0;
			
		}
		double suma=0;
		for(int i=0; i<criteriaMatixs.length; i++){
			suma+=cMatrix[i]*s[i];
		}
		double CIProductMatrix=(suma-criteriaMatixs.length)/(criteriaMatixs.length-1);
		System.out.println("CI product matrix" + CIProductMatrix);
		
		double RI= howLong(criteriaMatixs.length);
		double cr=CIProductMatrix/RI;
		
		System.out.println("CR"+cr);//policzone Cr macierzy kryteri�w 
		
		
		//s dla produkt�w
		//Double[][] sproducts=new Double[matrices.size()][matrices.size()];
		
		
			double sg=0;
		
			for(int i = 0; i < lenght; i++) {
				//System.out.println("wektor s:"+i);
				for(int j = 0; j < lenght; j++) {
					//sg+=normalizeProductMatrixes[i][j];
				}
					//System.out.println(sg);
				}
					
					
					
				
		}
	
	
		
		
		
	
	public double howLong (int lenght){
	double RI=0;
		switch (lenght){
				case 2:  RI=0.52;
				break;////NIE MAM WARTO�CI
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
	
	
	public void runAlgorithm() {
		
	}
}
