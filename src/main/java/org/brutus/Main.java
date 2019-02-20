package org.brutus;

import org.brutus.exception.BrutusException;
import org.brutus.exception.language.execute.ExecutionFailedException;
import org.brutus.language.graal.js.JavaScriptLanguage;
import org.brutus.language.script.groovy.GroovyLanguage;

import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws ExecutionFailedException {
        try {
            new JavaScriptLanguage("teste").execute(new StringReader("const x = 10\nconsole.log(x)"));
        } catch (BrutusException e) {
            e.printStackTrace();
        }

        GroovyLanguage language = new GroovyLanguage();
        language.addToBinding("teste", 10);

        language.execute(new StringReader("println(teste)"));
    }
}
