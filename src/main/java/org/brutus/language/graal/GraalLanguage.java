package org.brutus.language.graal;

import org.brutus.exception.language.execute.ExecutionFailedException;
import org.brutus.language.Language;
import org.brutus.script.definition.language.LanguageType;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.io.Reader;
import java.util.Set;

public abstract class GraalLanguage extends Language<Value> {

    private Context context;

    private String name;

    public GraalLanguage(LanguageType languageType, String name) {
        super(languageType);

        this.name = name;
    }

    @Override
    public void setCustomBinding(Value binding) {
        Value oldBinding = getBinding();

        Set<String> oldMembers = oldBinding
                .getMemberKeys();

        Set<String> newMembers = binding
                .getMemberKeys();

        for (String member : oldMembers)
            oldBinding.removeMember(member);

        for (String member : newMembers)
            oldBinding.putMember(member, binding.getMember(member));
    }

    @Override
    public Value getBinding() {
        return context.getBindings(getLanguageType().getLanguageId());
    }

    @Override
    public void addToBinding(String key, Object value) {
        getBinding().putMember(key, value);
    }

    @Override
    public void removeFromBinding(String key) {
        getBinding().removeMember(key);
    }

    @Override
    protected void initialize() {
        this.context = Context
                .newBuilder(getLanguageType().getLanguageId())
                .allowAllAccess(true)
                .build();

        this.context.initialize(getLanguageType().getLanguageId());
    }

    @Override
    public void close() {
        context.close();
    }

    @Override
    public void forceClose() {
        context.close(true);
    }

    @Override
    public void execute(Reader source) throws ExecutionFailedException {
        Source.Builder sourceBuilder = Source.newBuilder(getLanguageType().getLanguageId(), source, name);

        try {
            context.eval(sourceBuilder.build());
        } catch (IOException e) {
            throw new ExecutionFailedException(
                    String.format("Failed to execute GraalLanguage %s", getLanguageType().getLanguageId()),
                    e
            );
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
