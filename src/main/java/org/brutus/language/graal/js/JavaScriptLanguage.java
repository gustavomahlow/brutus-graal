package org.brutus.language.graal.js;

import org.brutus.language.graal.GraalLanguage;
import org.brutus.script.definition.language.LanguageType;

public class JavaScriptLanguage extends GraalLanguage {

    public JavaScriptLanguage(String name) {
        super(LanguageType.JAVASCRIPT, name);
    }
}
