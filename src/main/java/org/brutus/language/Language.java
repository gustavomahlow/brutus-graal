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

    protected abstract void setCustomBinding(B binding);
    protected abstract B getBinding();

    protected abstract void addToBinding(String key, Object value);
    protected abstract void removeFromBinding(String key);

    protected abstract void initialize();

    protected abstract void close();
    protected abstract void forceClose();

    public abstract void execute(Reader source) throws ExecutionFailedException;

    public LanguageType getLanguageType() {
        return languageType;
    }
}
