package com.xmlProject.model;

import java.util.InputMismatchException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Dimitar Bochev
 *@version 1.0
 */

@XmlRootElement(name = "homework")
@XmlAccessorType(XmlAccessType.FIELD)
public class Homework {

	
	private String title;
	private String task;
	private String platform;
	private String endDate;
	private String hint;
	
	
	/**
	 * an empty constructor for initialization
	 */
	public Homework(){
		
	}
        
	public Homework(String name, String task, String platform, String endDate, String hint) {
		setTitle(name);
		setTask(task);
		setPlatform(platform);
		setEndDate(endDate);
		setHint(hint);
	}
        
	public Homework(Homework homework) {
		this.title = homework.getTitle();
		this.task = homework.getTask();
		this.endDate = homework.getEndDate();
		this.hint = homework.getHint();
		this.platform = homework.getPlatform();
	}

	/**
	 * @return String with the name of the homework
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * this method sets the title, but validate it first
	 * @param title the title of the homework
	 */
	public void setTitle(String title) {
            this.title = title;
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
	 */
	public void setTask(String task) {
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
	 */
	public void setPlatform(String platform) {
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
	public void setEndDate(String endDate) {
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
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((hint == null) ? 0 : hint.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result
				+ ((platform == null) ? 0 : platform.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Homework other = (Homework) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (hint == null) {
			if (other.hint != null)
				return false;
		} else if (!hint.equals(other.hint))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (platform == null) {
			if (other.platform != null)
				return false;
		} else if (!platform.equals(other.platform))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		return true;
	}
	
}
