package org.brutus.language.script;

import org.brutus.exception.language.execute.ExecutionFailedException;
import org.brutus.language.Language;
import org.brutus.script.definition.language.LanguageType;

import javax.script.*;
import java.io.Reader;

public abstract class ScriptLanguage extends Language<Bindings> {

    private ScriptEngine scriptEngine;

    public ScriptLanguage(LanguageType languageType) {
        super(languageType);
    }

    @Override
    public void setCustomBinding(Bindings binding) {
        scriptEngine.setBindings(binding, ScriptContext.ENGINE_SCOPE);
    }

    @Override
    public Bindings getBinding() {
        return scriptEngine.getBindings(ScriptContext.ENGINE_SCOPE);
    }

    @Override
    public void addToBinding(String key, Object value) {
        getBinding().put(key, value);
    }

    @Override
    public void removeFromBinding(String key) {
        getBinding().remove(key);
    }

    @Override
    protected void initialize() {
        ScriptEngineManager manager = new ScriptEngineManager();
        scriptEngine = manager.getEngineByName(getLanguageType().getLanguageId());
        scriptEngine.setBindings(new SimpleBindings(), ScriptContext.ENGINE_SCOPE);
    }

    @Override
    public void close() {

    }

    @Override
    public void forceClose() {

    }

    @Override
    public void execute(Reader source) throws ExecutionFailedException {
        try {
            scriptEngine.eval(source);
        } catch (ScriptException e) {
            throw new ExecutionFailedException(
                    String.format("Failed to execute ScriptLanguage %s", getLanguageType().getLanguageId()),
                    e
            );
        }
    }

    @Override
    public void executeMethod(String methodName, Object... args) throws ExecutionFailedException {
        try {
            ((Invocable) scriptEngine).invokeFunction(methodName, args);
        } catch (ScriptException e) {
            throw new ExecutionFailedException(String.format("Failed to execute method %s", methodName), e);
        } catch (NoSuchMethodException e) {
            throw new ExecutionFailedException(String.format("Failed to execute method %s, method not found", methodName), e);
        }
    }
}
