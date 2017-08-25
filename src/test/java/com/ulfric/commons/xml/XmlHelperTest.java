package com.ulfric.commons.xml;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

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

}