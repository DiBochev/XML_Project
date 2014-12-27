package com.xmlProject.model;

import java.util.ArrayList;

/**
 * @author Dimitar Bochev
 *@version 1.0
 */
public class HomeworksArray {

	private ArrayList<Homework> array;
	private int currentElement;
	
	/**
	 * empty constructor for initialization
	 */
	public HomeworksArray(){
		array = new ArrayList<Homework>();
		currentElement = 0;
	}
	
	/**
	 * one parameter constructor... adds that parameter to array
	 * @param homeWork instance of class HomeWork
	 */
	public HomeworksArray(Homework homeWork){
		array = new ArrayList<Homework>();
		addElement(homeWork);
	}
	
	/**
	 * adds parameter to array after initialization,
	 * if parameter exists it wont be added 
	 * @param homeWork instance of class HomeWork
	 */
	public void addElement(Homework homeWork){
		if (!this.array.contains(homeWork)) {
			array.add(homeWork);
		}
	}
	
	public Homework getNextElement(){
		currentElement += 1;
		return array.get(currentElement);
	}
	
	/**
	 * adds entire array
	 * @param array ArrayList<HomeWork>
	 */
	public void addArray(ArrayList<Homework> array){
		for (Homework homeWork : array) {
			this.array.add(homeWork);
		}
	}
	
	/**
	 * @return new array (deep copy) this method is slow!
	 */
	public ArrayList<Homework> getArray(){
		ArrayList<Homework> temp = new ArrayList<Homework>();
		for (Homework homeWork : array) {
			temp.add(homeWork);
		}
		return temp;
	}
}
