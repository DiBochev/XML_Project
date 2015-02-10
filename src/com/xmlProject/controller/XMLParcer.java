package com.xmlProject.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import com.xmlProject.model.Homework;
import com.xmlProject.model.HomeworksArray;

public class XMLParcer {

	private String path;
	private StringBuilder stringBuilder;
	private static final String HTML_START = "<!DOCTYPE html><html><body><table border=\"1\" style=\"width:100%\"> <tr bgcolor=\"green\"><td>name</td><td>task</td><td>end date</td><td>hint</td><td>platform</td></tr>";
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
	public HomeworksArray readFile() throws IOException, JAXBException {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)))) {
			String line = bufferedReader.readLine();
			while (line != null) {
				stringBuilder.append(line);
				line = bufferedReader.readLine();
			}
		}
		return XMLToObject(stringBuilder.toString());
	}
	
	/**
	 * @param xml
	 * @return homework Object from xml
	 * @throws JAXBException
	 */
	private HomeworksArray XMLToObject(String xml) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(HomeworksArray.class);
		Unmarshaller un = context.createUnmarshaller();
		StringReader s = new StringReader(xml);
		HomeworksArray hwarr = (HomeworksArray) un.unmarshal(s);
		return hwarr;
	}

	/**
	 * @param array
	 * @throws JAXBException
	 * @throws IOException
	 *             writes objects to xml file
	 */
	public void writeToFile(HomeworksArray array) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(HomeworksArray.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		OutputStream os = new FileOutputStream(this.path);
		m.marshal(array, os);
		saveAsHTML(array.getArray());
		try {
			saveAsPDF();
		} catch (FOPException | TransformerException e) {
			e.printStackTrace();
		}
	}

	private void saveAsPDF() throws FOPException, TransformerException, IOException {
		File xsltfile = new File("data\\homework.xsl");
		StreamSource source = new StreamSource(new File(this.path));
		StreamSource transformSource = new StreamSource(xsltfile);
		FopFactory fopFactory = FopFactory.newInstance();
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Transformer xslfoTransformer;
		net.sf.saxon.TransformerFactoryImpl impl = new net.sf.saxon.TransformerFactoryImpl();
		xslfoTransformer = impl.newTransformer(transformSource);
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, outStream);
		Result res = new SAXResult(fop.getDefaultHandler());
		xslfoTransformer.transform(source, res);
		File pdffile = new File("data\\Result" + System.currentTimeMillis() + ".pdf");
		OutputStream out = new FileOutputStream(pdffile);
		out = new BufferedOutputStream(out);
		FileOutputStream str = new FileOutputStream(pdffile);
		str.write(outStream.toByteArray());
		str.close();
		out.close();
		
	}

	private void saveAsHTML(ArrayList<Homework> array) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(HTML_START);
		for (Homework homework : array) {
			sb.append("<tr>");
			sb.append("<td>" + homework.getTitle() + "<//td>");
			sb.append("<td>" + homework.getTask() + "<//td>");
			sb.append("<td>" + homework.getEndDate() + "<//td>");
			sb.append("<td>" + homework.getHint() + "<//td>");
			sb.append("<td>" + homework.getPlatform() + "<//td>");
			sb.append("<//tr>");
		}
		sb.append(HTML_END);
		try (PrintStream out = new PrintStream(new FileOutputStream("data\\web.html"))) {
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
