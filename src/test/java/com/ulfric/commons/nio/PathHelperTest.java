package com.ulfric.commons.nio;

import org.junit.jupiter.api.Test;

import com.ulfric.veracity.Veracity;
import com.ulfric.veracity.suite.HelperTestSuite;

import java.nio.file.Paths;

class PathHelperTest extends HelperTestSuite {

	PathHelperTest() {
		super(PathHelper.class);
	}

	@Test
	void testDefaultPath() {
		Veracity.assertThat(PathHelper.defaultPath()).isEqualTo(Paths.get(""));
	}

}