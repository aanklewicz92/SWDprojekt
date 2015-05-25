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
		Double [][] sProducts=new Double[matrices.size()][product.length];
		Double[][] cmatrixes=new Double[matrices.size()][product.length];
		Double [] s=new Double[criteriaMatixs.length];
		ArrayList<Double[][]> NormalizedProduct = new ArrayList<Double[][]>();
		Double[] rMatrix = new Double [criteriaMatixs.length];
		Double [] CIProduct=new Double[matrices.size()];
		double RIPproduct=howLong(matrices.size());
		Double [] CRProduct=new Double[matrices.size()];
		
		
		double c=0;
		for(int i=0; i<criteriaMatixs.length; i++){
			for(int j=0; j<criteriaMatixs.length; j++){
				c= c+criteriaMatixs[j][i];
				cMatrix[i]=c;
				}
			cMatrix[i]=c; //suma kolumn w kryteriach
			//System.out.print(cMatrix[i] + " ");
			c=0;
		}
		//System.out.println();
		double d=0;
		for(int k = 1; k < matrices.size(); k++) { 
			//System.out.println("cmacierz " + k);
			Double[][] productMatixs = matrices.get(k);
			for(int i = 0; i < product.length; i++) {
				for(int j = 0; j < product.length; j++) {
					d=d+productMatixs[j][i];
					
					}
				cmatrixes[k][i]=d;//suma kolumn w produktach
				//System.out.println(cmatrixes[k][i] + " ");
				d=0;
			}
			//System.out.println( " ");
		}
		//macierz znormalizowana kryteri�w
		//System.out.println( "Macierz znormalizowana kryteri�w");
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
		if(cr>=0.1){
			//inconsistentMarixListener.inconsistentMatrix(0);
		}
		//System.out.println("CR"+cr);//policzone Cr macierzy kryteri�w 
		//normalizacja macierzy produkt�w
		for(int k = 1; k < matrices.size(); k++) {
			Double[][] productMatrixs = matrices.get(k);
			Double[][]normalizeProductMatrixes=new Double[product.length][product.length];
			//System.out.println("Normalizowana macierz produkt�w:"+k);
			for(int i = 0; i < productMatrixs.length; i++) {
				for(int j = 0; j < productMatrixs.length; j++) {
					normalizeProductMatrixes[i][j]=(productMatrixs[j][i]/cmatrixes[k][i]);
					//System.out.print(normalizeProductMatrixes[i][j] + " ");
				}	
			}
			NormalizedProduct.add(normalizeProductMatrixes);//dodaje element do tablicy
			}
		
		for(int k = 0; k < NormalizedProduct.size(); k++) { 
			
			Double[][] normalized = NormalizedProduct.get(k);
			double suma1=0;
			for(int h = 0; h < normalized.length; h++) {
				for(int l= 0; l< normalized.length; l++) {
					suma1+=normalized[l][h];
					}
				sProducts[k][h]=(1.0/normalized.length)*suma1;
				//System.out.println(sProducts[k][h]);
				suma1=0;
				};
			//System.out.println();
		}
		
		
		
				
		
		for(int i=0; i<criteriaMatixs.length; i++){
					
					double sumaci=0;
					for(int j=0; j<product.length; j++){
						//System.out.println("product "+sProducts[i][j]+ " * cmatrix "+cmatrixes[i+1][j] );
						
						sumaci+=(sProducts[i][j]*cmatrixes[i+1][j]);
					}
					//System.out.println(i+" "+sumaci);
					CIProduct[i]=(sumaci-product.length)/(product.length-1);
					sumaci=0;
					CRProduct[i]=CIProduct[i]/RIPproduct;
					//System.out.println("CR dla produkt�w"+i+" "+CRProduct[i]);
				if(CRProduct[i]>=0.1){
						inconsistentMarixListener.inconsistentMatrix(i);
					}
				//System.out.println(criteriaMatixs.length);
		}
					
		
			for(int h = 0; h < criteriaMatixs.length; h++) {
				double sum=0;
				double se=0;
				for(int j=0; j<product.length; j++){
					for(int i= 0; i< criteriaMatixs.length; i++) {
						se=sProducts[i][j];
						sum+=(s[i]*se);
					}
					rMatrix[j]=sum;
					sum=0;			
				}
			}
	}
			
			
		/*	b_sort(rMatrix); // sortuj
			 
			 
			// wyswietl posortowae liczby
			System.out.println("Posortowane liczby:");
			
			for(int i=0;i<product.length; i++){
				System.out.println("r"+i+" "+rMatrix[i]);
			}
			
	}
	public static void b_sort(Double[] tablica)
	{
	int mniejszaLiczba = tablica.length-1; // indeks pierwszej porownywanej liczby; wartosc poczatkowa
	int wiekszaLiczba = mniejszaLiczba-1; // indeks drugiej porownywanej liczby; wartosc poczatkowa
	 
	double tmp;
	while (wiekszaLiczba >= 0) // indeks drugiej porownywanej liczby musi byc nie mniejszy niz zero
	{
	if (tablica[wiekszaLiczba] > tablica[mniejszaLiczba]) // jesli druga liczba jest wieksza niz pierwsza
	{
	tmp = tablica[wiekszaLiczba];
	tablica[wiekszaLiczba] = tablica[mniejszaLiczba]; // zamien wartosci
	tablica[mniejszaLiczba] = tmp;
	 
	mniejszaLiczba = tablica.length-1;  // zresetuj licznik
	wiekszaLiczba = mniejszaLiczba-1;   // zresetuj licznik
	}
	else // jesli druga liczba jest mniejsza lub rowna pierwszej, zmniejsz licznik o 1
	{
	mniejszaLiczba--;
	wiekszaLiczba--;
	}
	}
	}
   
    
*/
	
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
