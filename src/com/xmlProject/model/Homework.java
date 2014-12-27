package com.xmlProject.model;

import java.util.InputMismatchException;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * @author Dimitar Bochev
 *@version 1.0
 */

@XmlRootElement(name = "homework")
@XmlType(propOrder = {"task", "platform", "endDate", "hint"})
public class Homework {

	private String name;
	private String task;
	private String platform;
	private String endDate;
	private String hint;
	
	/**
	 * an empty constructor for initialization
	 */
	public Homework(){
		
	}
	
	/**
	 * full set constructor for initialization class Homework
	 * 
	 * @param name the name of the homework
	 * @param task question for the homework
	 * @param endDate the deadline
	 * @param hint array of hints (if user want to give any)
	 */
	public Homework(String name, String task, String endDate, String hint){
		setName(name);
		setTask(task);
		setEndDate(endDate);
		setHint(hint);
	}

	
	/**
	 * @return String with the name of the homework
	 */
	public String getName() {
		return name;
	}

	/**
	 * this method sets the name, but validate it first
	 * @param name the name of the homework
	 * @throws InputMismatchException if fails the verification
	 */
    @XmlAttribute
	public void setName(String name) throws InputMismatchException {
		setGeneral(name);
		this.name = name;
	}

	/**
	 * @return String with task (same reference)
	 */
	public String getTask() {
		return task;
	}
	
	/**
	 * this method sets the task, but validate it first
	 * @param task question for the homework
	 * @throws InputMismatchException if fails the verification
	 */
	@XmlElement
	public void setTask(String task) throws InputMismatchException {
		//setGeneral(task);
		this.task = task;
	}
	
	/**
	 * @return String with platform (same reference)
	 */
	public String getPlatform() {
		return platform;
	}
	
	/**
	 * this method sets the platform, but validate it first
	 * @param platform the name of IDE to use
	 * @throws InputMismatchException if fails the verification
	 */
	@XmlElement(name = "platform")
	public void setPlatform(String platform) throws InputMismatchException {
	//	setGeneral(platform);
		this.platform = platform;
	}

	/**
	 * @return String with endDate (same reference)
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * this method sets the endDate, but validate it first
	 * the date must be in format like: "11.11.1111"
	 * @param endDate the deadline
	 * @throws InputMismatchException if fails the verification
	 */
	@XmlElement
	public void setEndDate(String endDate) throws InputMismatchException {
		if(endDate.isEmpty()){
			throw new InputMismatchException();
		}
		if(!endDate.matches("[0-9]+.[0-9]+.[0-9]+")){			
			throw new InputMismatchException();
		}
		this.endDate = endDate;
	}
	
	/**
	 * @return String array with hints (same reference)
	 */
	public String getHint() {
		return hint;
	}
	
	/**
	* this method sets the hint, but validate it first one by one
	 * @param hint hint (can be empty)
	 * @throws InputMismatchException if fails the verification
	 */
	@XmlElement
	public void setHint(String hint) throws InputMismatchException {
		//setGeneral(hint);
		this.hint = hint;
	}
	
	/**
	 * this method is used for validate the fields 
	 * @param test using for some String to validate
	 * @throws InputMismatchException if fails the verification
	 */
	private void setGeneral(String test) throws InputMismatchException {
		test = test.trim();
		
		if (test.isEmpty()) {
			throw new InputMismatchException();
		}
		
		if(!test.matches("[а-яА-Я]+")){			
			throw new InputMismatchException();
		}
	}
}
