package com.ulfric.commons.nio;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.ulfric.commons.test.HelperTestSuite;
import com.ulfric.veracity.Veracity;

import java.nio.file.Paths;

@RunWith(JUnitPlatform.class)
class PathHelperTest extends HelperTestSuite {

	PathHelperTest() {
		super(PathHelper.class);
	}

	@Test
	void testDefaultPath() {
		Veracity.assertThat(PathHelper.defaultPath()).isEqualTo(Paths.get(""));
	}

}