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

        new GroovyLanguage().execute(new StringReader("x = 10\nx+=10\nprintln(x)"));
    }
}
