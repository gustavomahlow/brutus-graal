package org.brutus.language;

import org.brutus.exception.language.execute.ExecutionFailedException;
import org.brutus.script.definition.language.LanguageType;

import java.io.Reader;

public abstract class Language<B> {

    private LanguageType languageType;

    public Language(LanguageType languageType) {
        this.languageType = languageType;

        this.initialize();
    }

    public abstract void setCustomBinding(B binding);
    public abstract B getBinding();

    public abstract void addToBinding(String key, Object value);
    public abstract void removeFromBinding(String key);

    protected abstract void initialize();

    public abstract void close();
    public abstract void forceClose();

    public abstract void execute(Reader source) throws ExecutionFailedException;
    public abstract void executeMethod(String methodName, Object ...args) throws ExecutionFailedException;

    public LanguageType getLanguageType() {
        return languageType;
    }
}
