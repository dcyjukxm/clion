// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.codeInsight.completion.CompletionLocation;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.CompletionWeigher;

public class OCSymbolKindWeigher extends CompletionWeigher
{
    public Comparable weigh(@NotNull final LookupElement lookupElement, @NotNull final CompletionLocation completionLocation) {
        try {
            if (lookupElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCSymbolKindWeigher", "weigh"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (completionLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/editor/completion/OCSymbolKindWeigher", "weigh"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Object object = lookupElement.getObject();
        if (object instanceof OCSymbol) {
            return OCCompletionPriority.getPriority((OCSymbol)object).getValue() - ((OCSymbol)object).getKind().ordinal();
        }
        Label_0189: {
            Label_0168: {
                Label_0158: {
                    try {
                        if (!(object instanceof String)) {
                            break Label_0168;
                        }
                        final OCSymbol ocSymbol = (OCSymbol)object;
                        final String s = ocSymbol.toString();
                        final String s2 = ".xib\"";
                        final boolean b = s.endsWith(s2);
                        if (b) {
                            break Label_0158;
                        }
                        break Label_0168;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final OCSymbol ocSymbol = (OCSymbol)object;
                        final String s = ocSymbol.toString();
                        final String s2 = ".xib\"";
                        final boolean b = s.endsWith(s2);
                        if (b) {
                            return -1.0f;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    if (object instanceof String) {
                        break Label_0189;
                    }
                    final OCSymbol ocSymbol2 = (OCSymbol)object;
                    final boolean b2 = ocSymbol2 instanceof TemplateInsertHandler.TemplateObject;
                    if (b2) {
                        break Label_0189;
                    }
                    return null;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            try {
                final OCSymbol ocSymbol2 = (OCSymbol)object;
                final boolean b2 = ocSymbol2 instanceof TemplateInsertHandler.TemplateObject;
                if (b2) {
                    return OCCompletionPriority.NORMAL_PRIORITY.getValue() - OCSymbolKind.STRUCT_FIELD.ordinal() - 0.5f;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
