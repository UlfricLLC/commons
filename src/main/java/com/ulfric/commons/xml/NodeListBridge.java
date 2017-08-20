package com.ulfric.commons.xml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.AbstractList;
import java.util.Objects;
import java.util.RandomAccess;

public class NodeListBridge extends AbstractList<Node> implements RandomAccess {

	private final NodeList backing;

	public NodeListBridge(NodeList backing) {
		Objects.requireNonNull(backing, "backing");

		this.backing = backing;
	}

	@Override
	public Node get(int index) {
		return backing.item(index);
	}

	@Override
	public int size() {
		return backing.getLength();
	}

}
