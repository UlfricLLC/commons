package com.ulfric.commons.script;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;

import java.io.Reader;
import java.util.Objects;

public final class CompilableScriptEngine implements Compilable, ScriptEngine {

	public static <T extends Compilable & ScriptEngine> CompilableScriptEngine of(T engine) {
		Objects.requireNonNull(engine, "engine");

		return new CompilableScriptEngine(engine, engine);
	}

	private final Compilable compilable;
	private final ScriptEngine engine;

	private CompilableScriptEngine(Compilable compilable, ScriptEngine engine) {
		this.compilable = compilable;
		this.engine = engine;
	}

	@Override
	public CompiledScript compile(Reader script) throws ScriptException {
		return compilable.compile(script);
	}

	@Override
	public CompiledScript compile(String script) throws ScriptException {
		return compilable.compile(script);
	}

	@Override
	public Bindings createBindings() {
		return engine.createBindings();
	}

	@Override
	public Object eval(String script) throws ScriptException {
		return engine.eval(script);
	}

	@Override
	public Object eval(Reader reader) throws ScriptException {
		return engine.eval(reader);
	}

	@Override
	public Object eval(String script, ScriptContext context) throws ScriptException {
		return engine.eval(script, context);
	}

	@Override
	public Object eval(Reader reader, ScriptContext context) throws ScriptException {
		return engine.eval(reader, context);
	}

	@Override
	public Object eval(String script, Bindings n) throws ScriptException {
		return engine.eval(script, n);
	}

	@Override
	public Object eval(Reader reader, Bindings n) throws ScriptException {
		return engine.eval(reader, n);
	}

	@Override
	public Object get(String key) {
		return engine.get(key);
	}

	@Override
	public Bindings getBindings(int scope) {
		return engine.getBindings(scope);
	}

	@Override
	public ScriptContext getContext() {
		return engine.getContext();
	}

	@Override
	public ScriptEngineFactory getFactory() {
		return engine.getFactory();
	}

	@Override
	public void put(String key, Object value) {
		engine.put(key, value);
	}

	@Override
	public void setBindings(Bindings bindings, int scope) {
		engine.setBindings(bindings, scope);
	}

	@Override
	public void setContext(ScriptContext context) {
		engine.setContext(context);
	}

}
