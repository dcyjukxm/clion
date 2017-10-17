// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.search;

import com.intellij.psi.ResolveResult;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiPolyVariantReference;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.util.Processor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.application.ReadAction;

class CMakeDefinitionsSearch$1 extends ReadAction<Object> {
    final /* synthetic */ PsiElement val$queryParametersElement;
    final /* synthetic */ Processor val$consumer;
    
    protected void run(@NotNull final Result<Object> result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/search/CMakeDefinitionsSearch$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        final PsiReference access$000 = CMakeDefinitionsSearch.access$000(this.val$queryParametersElement);
        if (access$000 instanceof PsiPolyVariantReference) {
            final ResolveResult[] multiResolve = ((PsiPolyVariantReference)access$000).multiResolve(false);
            for (int length = multiResolve.length, i = 0; i < length; ++i) {
                final PsiElement element = multiResolve[i].getElement();
                try {
                    if (element != null) {
                        this.val$consumer.process((Object)element.getParent().getParent());
                    }
                }
                catch (Throwable t2) {
                    throw a(t2);
                }
            }
        }
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}