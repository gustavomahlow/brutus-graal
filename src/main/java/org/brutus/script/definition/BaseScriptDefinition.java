package org.brutus.script.definition;

import org.brutus.script.definition.language.LanguageType;

import java.util.Objects;

public abstract class BaseScriptDefinition {

    private LanguageType languageType;

    private String path;

    public BaseScriptDefinition(LanguageType languageType, String path) {
        this.languageType = languageType;
        this.path = path;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseScriptDefinition that = (BaseScriptDefinition) o;
        return languageType == that.languageType &&
                Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageType, path);
    }

    @Override
    public String toString() {
        return "BaseScriptDefinition{" +
                "languageType=" + languageType +
                ", path='" + path + '\'' +
                '}';
    }
}
