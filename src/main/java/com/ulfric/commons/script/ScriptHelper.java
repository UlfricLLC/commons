package com.ulfric.commons.script;

import javax.script.Compilable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ScriptHelper {

	public static <T extends Compilable & ScriptEngine> CompilableScriptEngine createNashornEngine() {
		ScriptEngineManager manager = new ScriptEngineManager();
		@SuppressWarnings("unchecked")
		T engine = (T) manager.getEngineByName("nashorn");
		return CompilableScriptEngine.of(engine);
	}

	private ScriptHelper() {
	}

}