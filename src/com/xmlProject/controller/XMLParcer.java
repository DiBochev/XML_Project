package com.xmlProject.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.xmlProject.model.Homework;


public class XMLParcer {

	private String path;
	private StringBuilder stringBuilder;
	private static final String START_TAG = "<homework";
	private static final String END_TAG = "</homework";
	
	public XMLParcer(String path) throws IOException{
		stringBuilder = new StringBuilder();
		this.path = path;
	}

	public ArrayList<Homework> readFile() throws IOException {
		try (BufferedReader bufferedReader =  new BufferedReader(new FileReader(new File(path)))){
			String line = bufferedReader.readLine();
		    while (line != null) {
		    	stringBuilder.append(line);
		        line = bufferedReader.readLine();
		    }
		}
		return parceXML();
	}

	private ArrayList<Homework> parceXML() {
		int start = 0;
	    int fin = 0;
	    ArrayList<Homework> array = new ArrayList<Homework>();
	   	while (stringBuilder.indexOf(START_TAG, start + 1) != -1) {
			start = stringBuilder.indexOf(START_TAG, start + 1);
			fin =  stringBuilder.indexOf(END_TAG, fin + 1) + END_TAG.length() + 1;
			array.add(XMLToObject(stringBuilder.substring(start, fin)));
		}
	   	return array;
	}
	
    private Homework XMLToObject(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(Homework.class);
            Unmarshaller un = context.createUnmarshaller();
            StringReader s = new StringReader(xml);
            Homework hw = (Homework) un.unmarshal(s);
            return hw;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void writeToFile(ArrayList<Homework> array) throws JAXBException, FileNotFoundException{
    	JAXBContext context = JAXBContext.newInstance(Homework.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new FileOutputStream(this.path);
        
        for (Homework homework : array) {
			m.marshal(homework, os);
		}
       // m.marshal(homeWork, new File(this.path));
    }
}
