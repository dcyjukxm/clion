// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.search.PsiElementProcessor;

class OCIncludeHelpers$FirstSegmentCache$1 implements PsiElementProcessor<PsiFileSystemItem> {
    public boolean execute(@NotNull final PsiFileSystemItem psiFileSystemItem) {
        try {
            if (psiFileSystemItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/OCIncludeHelpers$FirstSegmentCache$1", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String name = psiFileSystemItem.getName();
        List<PsiFileSystemItem> list = FirstSegmentCache.access$200(FirstSegmentCache.this).get(name);
        if (list == null) {
            list = new ArrayList<PsiFileSystemItem>();
            FirstSegmentCache.access$200(FirstSegmentCache.this).put(name, list);
        }
        list.add(psiFileSystemItem);
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}