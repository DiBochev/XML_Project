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

import com.xmlProject.model.HomeWork;


public class XMLParcer {

	private String path;
	private StringBuilder stringBuilder;
	private static final String START_TAG = "<homeWork";
	private static final String END_TAG = "</homeWork";
	
	public XMLParcer(String path) throws IOException{
		stringBuilder = new StringBuilder();
		this.path = path;
	}

	public ArrayList<HomeWork> readFile() throws IOException {
		try (BufferedReader bufferedReader =  new BufferedReader(new FileReader(new File(path)))){
			String line = bufferedReader.readLine();
		    while (line != null) {
		    	stringBuilder.append(line);
		        line = bufferedReader.readLine();
		    }
		}
		return parceXML();
	}

	private ArrayList<HomeWork> parceXML() {
		int start = 0;
	    int fin = 0;
	    ArrayList<HomeWork> array = new ArrayList<HomeWork>();
	   	while (stringBuilder.indexOf(START_TAG, start + 1) != -1) {
			start = stringBuilder.indexOf(START_TAG, start + 1);
			fin =  stringBuilder.indexOf(END_TAG, fin + 1) + END_TAG.length() + 1;
			array.add(XMLToObject(stringBuilder.substring(start, fin)));
		}
	   	return array;
	}
	
    private HomeWork XMLToObject(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(HomeWork.class);
            Unmarshaller un = context.createUnmarshaller();
            StringReader s = new StringReader(xml);
            HomeWork hw = (HomeWork) un.unmarshal(s);
            return hw;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void writeToFile(ArrayList<HomeWork> array) throws JAXBException{
    	JAXBContext context = JAXBContext.newInstance(HomeWork.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = null;
		try {
			os = new FileOutputStream(this.path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (HomeWork homeWork : array) {
			m.marshal(homeWork, os);
		}
       // m.marshal(homeWork, new File(this.path));
    }
}
