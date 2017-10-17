// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.util.SmartList;
import com.intellij.psi.search.SpecificNameItemProcessor;

static final class OCIncludeHelpers$1 extends SpecificNameItemProcessor {
    final /* synthetic */ SmartList val$result;
    
    public boolean execute(@NotNull final PsiFileSystemItem psiFileSystemItem) {
        try {
            if (psiFileSystemItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/OCIncludeHelpers$1", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.val$result.add((Object)psiFileSystemItem);
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}