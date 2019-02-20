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
    protected void setCustomBinding(Bindings binding) {
        scriptEngine.setBindings(binding, ScriptContext.ENGINE_SCOPE);
    }

    @Override
    protected Bindings getBinding() {
        return scriptEngine.getBindings(ScriptContext.ENGINE_SCOPE);
    }

    @Override
    protected void addToBinding(String key, Object value) {
        getBinding().put(key, value);
    }

    @Override
    protected void removeFromBinding(String key) {
        getBinding().remove(key);
    }

    @Override
    protected void initialize() {
        ScriptEngineManager manager = new ScriptEngineManager();
        scriptEngine = manager.getEngineByName(getLanguageType().getLanguageId());
        scriptEngine.setBindings(new SimpleBindings(), ScriptContext.ENGINE_SCOPE);
    }

    @Override
    protected void close() {

    }

    @Override
    protected void forceClose() {

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
}
