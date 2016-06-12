package com.dikaros.wowserver.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dikaros.wowserver.bean.Result;

/**
 * @author Dikaros
 *
 */
public class ResultUtil implements ServletContextListener{
	private static Map<Integer, Result> map = new HashMap<Integer, Result>();
	
	/**
	 * @param id
	 * @return
	 */
	public static Result getResultById(int id){
		return map.get(id);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(ResultUtil.class.getResourceAsStream("/result_mapping.xml"));
			Element element = document.getDocumentElement();
			NodeList results = element.getElementsByTagName("result");
			for (int i = 0; i < results.getLength(); i++) {
				Element result = (Element) results.item(i);
				NodeList child=result.getChildNodes();
				Result re = new Result();
				int key = Integer.parseInt(result.getAttribute("id"));
				for (int j = 0; j < child.getLength(); j++) {
					if (child.item(j).getNodeName().equals("code")) {
						re.setCode(Integer.parseInt(child.item(j).getFirstChild().getNodeValue()));
					}else if (child.item(j).getNodeName().equals("message")) {
						re.setMessage(child.item(j).getFirstChild().getNodeValue());
					}
				}
				map.put(key, re);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		System.out.println("信息： result mapping loaded success");
	}
}
