package com.ulfric.commons.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.common.escape.Escaper;
import com.google.common.xml.XmlEscapers;

import com.ulfric.tryto.TryTo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlHelper {

	private static final DocumentBuilder DOCUMENTS =
			TryTo.get(DocumentBuilderFactory.newInstance()::newDocumentBuilder);

	private static final Pattern NON_ESCAPED_QUOTE =
			Pattern.compile("(?<!{0})[{1}]".replace("{0}", Pattern.quote("\\")).replace("{1}", Pattern.quote("\""))); // TODO proper regex without using replace()

	public static Document parseHumanDocument(String xml) {
		return XmlHelper.parseDocument("<doc>" + escapeQuotedSpecialCharacters(xml) + "</doc>");
	}

	private static String escapeQuotedSpecialCharacters(String xml) {
		Escaper escaper = XmlEscapers.xmlAttributeEscaper();
		StringBuilder string = new StringBuilder();

		boolean escaped = false;
		int index = 0;
		Matcher matcher = NON_ESCAPED_QUOTE.matcher(xml);
		while (matcher.find()) {

			String substring = xml.substring(index, matcher.start());
			if (escaped) {
				string.append(escaper.escape(substring));
			} else {
				string.append(substring);
			}
			string.append('"');

			index = matcher.end();
			escaped = !escaped;
		}

		String substring = xml.substring(index, xml.length());
		if (escaped) {
			string.append(escaper.escape(substring));
		} else {
			string.append(substring);
		}

		return string.toString();
	}

	public static Document parseDocument(String xml) {
		InputSource source = new InputSource(new StringReader(xml));
		return TryTo.get(() -> DOCUMENTS.parse(source));
	}

	public static List<Node> getChildren(Node node) {
		if (node.hasChildNodes()) {
			return asList(node.getChildNodes());
		}

		return Collections.emptyList();
	}

	public static List<Node> getAttributes(Node node) {
		if (!node.hasAttributes()) {
			return Collections.emptyList();
		}

		NamedNodeMap attributes = node.getAttributes();
		int size = attributes.getLength();

		List<Node> nodes = new ArrayList<>(size);
		for (int x = 0; x < size; x++) {
			nodes.add(attributes.item(x));
		}

		return Collections.unmodifiableList(nodes);
	}

	public static List<Node> asList(NodeList nodes) {
		return new NodeListBridge(nodes);
	}

	public static String getNodeValue(Node node) {
		String value = node.getNodeValue();
		if (value != null) {
			return value;
		}

		if (!node.hasChildNodes()) {
			return null;
		}

		NodeList children = node.getChildNodes();
		if (children.getLength() != 1) {
			return null;
		}

		Node child = children.item(0);
		if (child.getNodeName().equals("#text")) {
			return child.getNodeValue();
		}

		return null;
	}

	private XmlHelper() {
	}

}
