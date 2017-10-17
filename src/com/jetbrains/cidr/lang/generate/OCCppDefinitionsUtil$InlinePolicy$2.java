// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import org.jetbrains.annotations.NotNull;

enum OCCppDefinitionsUtil$InlinePolicy$2
{
    @Override
    public boolean shouldInline(@NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCMembersContainer ocMembersContainer) {
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy$2", "shouldInline"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocMembersContainer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy$2", "shouldInline"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}