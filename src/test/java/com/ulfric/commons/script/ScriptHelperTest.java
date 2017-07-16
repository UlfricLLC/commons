package com.ulfric.commons.script;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.google.common.truth.Truth;

import com.ulfric.commons.test.HelperTestSuite;
import com.ulfric.veracity.Veracity;

@RunWith(JUnitPlatform.class)
class ScriptHelperTest extends HelperTestSuite {

	ScriptHelperTest() {
		super(ScriptHelper.class);
	}

	@Test
	void testCreateNashornEngineIsUnique() {
		Truth.assertThat(ScriptHelper.createNashornEngine()).isNotSameAs(ScriptHelper.createNashornEngine());
	}

	@Test
	void testCreateNashornEngineIsNashorn() {
		Veracity.assertThat(ScriptHelper.createNashornEngine()).isNamed("Oracle Nashorn");
	}

	@Test
	void testCreateNashornEngineLanguageIsEcmaScript() {
		Veracity.assertThat(ScriptHelper.createNashornEngine()).isUsingLanguage("ECMAScript");
	}

}