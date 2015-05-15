package logic;

import java.util.ArrayList;

public class SomeClass {
	/*
	 * W tej paczce twórz sobie swoje klasy z wszelk¹ logik¹ obliczeñ.
	 * Musimy ustaliæ w jakiej formie bêdziemy przesy³aæ dane miêdzy logik¹ a gui.
	 * 
	 * Ja to widzê tak, ¿e w gui bêdê tworzyæ obiekt jakiejœ klasy st¹d i ten obiekt
	 * albo bêdzie mia³ metody albo po prostu pola, do których ja przypiszê dane wziête
	 * u¿ytkownika w takiej formie jak bêd¹ Ci potrzebne tutaj czyli lista/tablica/coœ innego,
	 * po dostarczeniu wszystkich danych pod jakimœ buttonem gui bêdzie siê kry³o
	 * wywo³anie metody run czy start czy coœ na tym obiekcie i ta metoda po zrobieniu ahp
	 * zwróci jak¹œ listê/tablicê/coœ innego z wynikami, co ja poka¿ê w gui.
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

	public void setPreferences() {
	
	}
	
	public void runAlgorithm() {
		
	}
}
