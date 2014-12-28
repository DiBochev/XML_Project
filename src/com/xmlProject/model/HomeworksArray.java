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
        
        public Homework getFirstElement(){
            return array.get(0);
        }
	
	public Homework getNextElement(){
            if(currentElement + 1 >= array.size()){
                currentElement = 0;
            }else{
		currentElement += 1;
            }
            return array.get(currentElement);
	}
        
        public Homework getPreviousElement(){
            if (currentElement -1 < 0) {
                currentElement = array.size() - 1;
            }else{
                currentElement -= 1;
            }
            return this.array.get(currentElement);
        }
	
        public void deleteElement(){
            array.remove(currentElement);
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
        
        public ArrayList<Homework> getArray(){
            return array;
        }
}
