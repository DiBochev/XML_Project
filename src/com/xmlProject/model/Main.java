package com.xmlProject.model;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.xmlProject.controller.XMLParcer;

public class Main {

	public static void main(String[] args) {
		HomeWorksArray array = new HomeWorksArray();
		XMLParcer p;
		try {
			p = new XMLParcer("D:\\XML.xml");
			array.addArray(p.readFile());
			XMLParcer p2 = new  XMLParcer("D:\\XML2.xml");
			p2.writeToFile(array.getArray());
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
	}

}
