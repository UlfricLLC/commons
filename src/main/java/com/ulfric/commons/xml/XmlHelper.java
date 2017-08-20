package com.ulfric.commons.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ulfric.tryto.TryTo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XmlHelper {

	private static final DocumentBuilder DOCUMENTS =
			TryTo.get(DocumentBuilderFactory.newInstance()::newDocumentBuilder);

	public static Document parseHumanDocument(String xml) {
		return XmlHelper.parseDocument("<doc>" + escapeQuotedSpecialCharacters(xml) + "</doc>");
	}

	private static String escapeQuotedSpecialCharacters(String xml) { // TODO allow escaping chars
		StringBuilder cleaned = new StringBuilder();
		boolean quoted = false;
		for (char character : xml.toCharArray()) {
			if (character == '"') {
				quoted = !quoted;
			} else if (quoted) {
				String escaped = escape(character);
				if (escaped != null) {
					cleaned.append(escaped);
					continue;
				}
			}

			cleaned.append(character);
		}
		return cleaned.toString();
	}

	private static String escape(char character) {
		if (character == '"') {
			return "&quot;";
		}

		if (character == '<') {
			return "&lt;";
		}

		if (character == '>') {
			return "&gt;";
		}

		if (character == '&') {
			return "&amp;";
		}

		if (character == '\'') {
			return "&apos;";
		}

		return null;
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

	public static List<Node> getAttributes(Node node) { // TODO bridge type
		if (node.hasAttributes()) {
			NamedNodeMap attributes = node.getAttributes();
			int size = attributes.getLength();

			List<Node> nodes = new ArrayList<>(size);
			for (int x = 0; x < size; x++) {
				nodes.add(attributes.item(x));
			}

			return Collections.unmodifiableList(nodes);
		}

		return Collections.emptyList();
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

	public static Node getDefaultAttribute(Node node) {
		Node attribute = getNodeAttribute(node, "value");

		if (attribute != null) {
			return attribute;
		}

		if (node.hasAttributes()) {
			return node.getAttributes().item(0);
		}

		return null;
	}

	public static Node getNodeAttribute(Node node, String attribute) {
		if (!node.hasAttributes()) {
			return null;
		}

		NamedNodeMap attributes = node.getAttributes();
		return attributes.getNamedItem(attribute);
	}

	public static String getNodeAttributeAsString(Node node, String attribute) {
		Node found = getNodeAttribute(node, attribute);

		if (found == null) {
			return null;
		}

		return getNodeValue(found);
	}

	private XmlHelper() {
	}

}
