package com.ulfric.commons.xml;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ulfric.veracity.Veracity;
import com.ulfric.veracity.suite.HelperTestSuite;

class XmlHelperTest extends HelperTestSuite {

	XmlHelperTest() {
		super(XmlHelper.class);
	}

	@Test
	void testParseHumanDocumentWrapsInDocTag() {
		Element primary = XmlHelper.parseHumanDocument("hello").getDocumentElement();
		Veracity.assertThat(primary).isNamed("doc");
	}

	@Test
	void testEscapesAttributes() {
		Node primary = XmlHelper.parseHumanDocument("<hello test=\"<world>\"></hello>")
				.getDocumentElement()
				.getFirstChild();
		Veracity.assertThat(primary).hasAttributeWithValue("test", "<world>");
	}

	@Test
	void testEscapesAttributesNoSecondQuote() {
		Node primary = XmlHelper.parseHumanDocument("hello\"<")
				.getDocumentElement()
				.getFirstChild();
		Veracity.assertThat(primary).hasNodeValue("hello\"<");
	}

}