package com.ulfric.commons.script;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.script.Compilable;
import javax.script.ScriptEngine;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(JUnitPlatform.class)
class CompilableScriptEngineTest {

	@Test
	void testDoesDelegate() throws Exception {
		CompilableAndScriptEngine mock = Mockito.mock(CompilableAndScriptEngine.class);
		CompilableScriptEngine engine = CompilableScriptEngine.of(mock);

		List<Method> methods = Stream.of(ScriptEngine.class.getDeclaredMethods(), Compilable.class.getDeclaredMethods())
			.flatMap(Arrays::stream)
			.collect(Collectors.toList());

		for (Method method : methods) {
			Object[] parameters = new Object[method.getParameterCount()];
			Class<?>[] parameterTypes = method.getParameterTypes();
			for (int x = 0; x < parameterTypes.length; x++) {
				Class<?> type = parameterTypes[x];
				if (type.isPrimitive()) {
					parameters[x] = Array.get(Array.newInstance(type, 1), 0);
				}
			}
			method.invoke(engine, parameters);
			method.invoke(Mockito.verify(mock, Mockito.times(1)), parameters);
		}
	}

	interface CompilableAndScriptEngine extends Compilable, ScriptEngine {
	}

}