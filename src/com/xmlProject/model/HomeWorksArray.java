package com.xmlProject.model;

import java.util.ArrayList;

/**
 * @author Dimitar Bochev
 *@version 1.0
 */
public class HomeWorksArray {

	private ArrayList<HomeWork> array;
	private int currentElement;
	
	/**
	 * empty constructor for initialization
	 */
	public HomeWorksArray(){
		array = new ArrayList<HomeWork>();
		currentElement = 0;
	}
	
	/**
	 * one parameter constructor... adds that parameter to array
	 * @param homeWork instance of class HomeWork
	 */
	public HomeWorksArray(HomeWork homeWork){
		array = new ArrayList<HomeWork>();
		addElement(homeWork);
	}
	
	/**
	 * adds parameter to array after initialization,
	 * if parameter exists it wont be added 
	 * @param homeWork instance of class HomeWork
	 */
	public void addElement(HomeWork homeWork){
		if (!this.array.contains(homeWork)) {
			array.add(homeWork);
		}
	}
	
	public HomeWork getNextElement(){
		currentElement += 1;
		return array.get(currentElement);
	}
	
	/**
	 * adds entire array
	 * @param array ArrayList<HomeWork>
	 */
	public void addArray(ArrayList<HomeWork> array){
		for (HomeWork homeWork : array) {
			this.array.add(homeWork);
		}
	}
	
	/**
	 * @return new array (deep copy) this method is slow!
	 */
	public ArrayList<HomeWork> getArray(){
		ArrayList<HomeWork> temp = new ArrayList<HomeWork>();
		for (HomeWork homeWork : array) {
			temp.add(homeWork);
		}
		return temp;
	}
}
