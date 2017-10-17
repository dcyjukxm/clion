// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import java.util.Map;

class OCSimplifyInspection$1 extends SimplifyVisitor {
    final /* synthetic */ Map val$map;
    
    @Override
    protected void simplify(@NotNull final PsiElement psiElement, final PsiElement psiElement2, final Simplifier simplifier) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$1", "simplify"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiElement2 != null) {
                this.val$map.put(psiElement, Pair.create((Object)psiElement2, (Object)simplifier));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}