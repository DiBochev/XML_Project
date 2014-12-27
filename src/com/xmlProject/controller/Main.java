package com.xmlProject.controller;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.xmlProject.model.HomeworksArray;

public class Main {

	public static void main(String[] args) {
		HomeworksArray array = new HomeworksArray();
		XMLParcer p;
		try {
			p = new XMLParcer("D:\\XML.xml");
			array.addArray(p.parceXML());
			System.out.println("patform is: " + array.getNextElement().getPlatform());
			XMLParcer p2 = new  XMLParcer("D:\\XML2.xml");
			p2.writeToFile(array.getArray());
			System.out.println("parsna go");
			//System.out.println(array.getNextElement());
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
	}

}
