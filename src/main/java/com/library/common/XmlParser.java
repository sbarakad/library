package com.library.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParser {

	static List<Entry<String, String>> list;

	public static List<Map.Entry<String, String>> parse(String filePath) {
		HashMap<String, String> values = new HashMap<String, String>();
		list = new ArrayList<Map.Entry<String, String>>();
		Map.Entry pair = null;
		File xmlFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			// took code reference.
			// https://stackoverflow.com/questions/13356534/how-to-read-xml-child-node-values-by-using-a-loop-in-java
			dBuilder = dbFactory.newDocumentBuilder();
			Document xml = dBuilder.parse(xmlFile);
			Node user = xml.getFirstChild();
			NodeList childs = user.getChildNodes();
			Node child;
			for (int i = 0; i < childs.getLength(); i++) {
				child = childs.item(i);
				if (child.getNodeName() != "#text") {
					values.put(child.getNodeName(), child.getTextContent());
				}
			}
			// https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
			// User iterator pattern.
			Iterator it = values.entrySet().iterator();
			while (it.hasNext()) {
				pair = (Map.Entry) it.next();
				list.add(pair);
				it.remove(); // avoids a ConcurrentModificationException
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return list;
	}
}