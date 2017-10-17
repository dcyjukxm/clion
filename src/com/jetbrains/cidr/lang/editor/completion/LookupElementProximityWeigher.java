// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.codeInsight.completion.CompletionLocation;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.CompletionWeigher;

public class LookupElementProximityWeigher extends CompletionWeigher
{
    public Comparable weigh(@NotNull final LookupElement lookupElement, @NotNull final CompletionLocation completionLocation) {
        try {
            if (lookupElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/editor/completion/LookupElementProximityWeigher", "weigh"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (completionLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/editor/completion/LookupElementProximityWeigher", "weigh"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Object object = lookupElement.getObject();
        try {
            if (object instanceof OCSymbol) {
                return (Comparable)SymbolProximityComparator.getProximity((OCSymbol)object, completionLocation.getCompletionParameters().getPosition(), completionLocation.getProcessingContext());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
