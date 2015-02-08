package com.xmlProject.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
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
	private static final String HTML_START = "<!DOCTYPE html><html><body><table border=\"1\" style=\"width:100%\"> <tr><td>name</td><td>task</td><td>end date</td><td>hint</td><td>platform</td></tr>";
	private static final String HTML_END = "</table></body></html>";

	/**
	 * empty constructor
	 */
	public XMLParcer() {

	}

	/**
	 * @param path
	 * @throws IOException
	 *             constructor with path for xml
	 */
	public XMLParcer(String path) throws IOException {
		stringBuilder = new StringBuilder();
		this.path = path;
	}

	/**
	 * @return returns ArrayList of homework objects by calling parceXML
	 * @throws IOException
	 * @throws JAXBException
	 */
	public ArrayList<Homework> readFile() throws IOException, JAXBException {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
				new File(path)))) {
			String line = bufferedReader.readLine();
			while (line != null) {
				stringBuilder.append(line);
				line = bufferedReader.readLine();
			}
		}
		return parceXML();
	}

	/**
	 * @return ArrayList of homework objects
	 * @throws IOException
	 * @throws JAXBException
	 */
	private ArrayList<Homework> parceXML() throws IOException, JAXBException {
		int start = 0;
		int fin = 0;
		ArrayList<Homework> array = new ArrayList<Homework>();
		while (stringBuilder.indexOf(START_TAG, start + 1) != -1) {
			start = stringBuilder.indexOf(START_TAG, start + 1);
			fin = stringBuilder.indexOf(END_TAG, fin + 1) + END_TAG.length()
					+ 1;
			array.add(XMLToObject(stringBuilder.substring(start, fin)));
		}
		return array;
	}

	/**
	 * @param xml
	 * @return homework Object from xml
	 * @throws JAXBException
	 */
	private Homework XMLToObject(String xml) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Homework.class);
		Unmarshaller un = context.createUnmarshaller();
		StringReader s = new StringReader(xml);
		Homework hw = (Homework) un.unmarshal(s);
		return hw;
	}

	/**
	 * @param array
	 * @throws JAXBException
	 * @throws IOException
	 *             writes objects to xml file
	 */
	public void writeToFile(ArrayList<Homework> array) throws JAXBException,
			IOException {
		JAXBContext context = JAXBContext.newInstance(Homework.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		OutputStream os = new FileOutputStream(this.path);
		for (Homework homework : array) {
			m.marshal(homework, os);
		}
		saveAsHTML(array);
	}

	private void saveAsHTML(ArrayList<Homework> array) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(HTML_START);
		for (Homework homework : array) {
			sb.append("<tr>");
			sb.append("<td>" + homework.getName() + "<//td>");
			sb.append("<td>" + homework.getTask() + "<//td>");
			sb.append("<td>" + homework.getEndDate() + "<//td>");
			sb.append("<td>" + homework.getHint() + "<//td>");
			sb.append("<td>" + homework.getPlatform() + "<//td>");
			sb.append("<//tr>");
		}
		sb.append(HTML_END);
		try (PrintStream out = new PrintStream(new FileOutputStream("web.html"))) {

			OutputStreamWriter o = new OutputStreamWriter(out, "Unicode");
			out.write(sb.toString().getBytes());
			o.flush();
			o.close();
		} catch (Exception e) {
			System.out.println("cannot save to html");
			e.printStackTrace();
		}
	}

	/**
	 * set path of xml
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
