package org.brutus.script.definition.language;

public enum LanguageType {

    JAVASCRIPT("js"),
    GROOVY("groovy");

    private String languageId;

    LanguageType(String languageId) {
        this.languageId = languageId;
    }

    public String getLanguageId() {
        return languageId;
    }

    @Override
    public String toString() {
        return "LanguageType{" +
                "languageId='" + languageId + '\'' +
                '}';
    }
}
