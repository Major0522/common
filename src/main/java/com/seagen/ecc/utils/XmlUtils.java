package com.seagen.ecc.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author kuangjianbo
 */
public class XmlUtils {
    private static final Logger log = LoggerFactory.getLogger(XmlUtils.class);

    /**
     * @param xml
     * @param nodeName
     * @return
     */
    public static Map<String, String> xml2Map(String xml, String nodeName) {
        Map<String, String> ret = new HashMap<String, String>();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
            NodeList nodeList = StringUtils.isEmpty(nodeName) ? doc.getChildNodes() : doc.getElementsByTagName(nodeName).item(0).getChildNodes();
            int len = nodeList.getLength();
            for (int i = 0; i < len; i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String name = node.getNodeName();
                    String text = node.getTextContent();
                    ret.put(name, text);
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.error("xml2Map failed,xml=" + xml, e);
        }
        return ret;
    }

    public static Map<String, String> xml2Map(String xml, String nodeName, String attrName) {
        Map<String, String> ret = new HashMap<String, String>();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
            NodeList nodeList = StringUtils.isEmpty(nodeName) ? doc.getChildNodes() : doc.getElementsByTagName(nodeName).item(0).getChildNodes();
            int len = nodeList.getLength();
            for (int i = 0; i < len; i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // String name = node.getNodeName();
                    String key = node.getAttributes().item(0).getNodeValue();
                    String text = node.getTextContent();
                    ret.put(key, text);
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.error("xml2Map failed,xml=" + xml, e);
        }
        return ret;
    }

    public static List<String> xml2List(String xml, String nodeName) {
        List<String> ret = new ArrayList<String>();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
            NodeList nodeList = StringUtils.isEmpty(nodeName) ? doc.getChildNodes() : doc.getElementsByTagName(nodeName).item(0).getChildNodes();
            int len = nodeList.getLength();
            for (int i = 0; i < len; i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String text = node.getTextContent();
                    ret.add(text);
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.error("xml2List failed,xml=" + xml, e);
        }
        return ret;
    }

    public static <T> T xml2Bean(String xml, String nodeName, Class<T> cla) {
        return JsonUtil.map2Bean(xml2Map(xml, nodeName), cla);
    }

    public static <T> T bean2Xml(Object bean) {
        return null;
    }

    public static void main(String[] args) {
    }
}
