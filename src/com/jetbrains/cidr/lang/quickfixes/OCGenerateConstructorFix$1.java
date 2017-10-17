// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateConstructorContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.Collection;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.generate.handlers.OCGenerateConstructorHandler;

class OCGenerateConstructorFix$1 extends OCGenerateConstructorHandler {
    @NotNull
    @Override
    protected List<? extends OCStructSymbol> getParents(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "at", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix$1", "getParents"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        List<OCStructSymbol> singletonList;
        try {
            singletonList = Collections.singletonList(OCGenerateConstructorFix.access$000(OCGenerateConstructorFix.this));
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix$1", "getParents"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return singletonList;
    }
    
    @Override
    protected boolean addParametersForBaseClasses() {
        return OCGenerateConstructorFix.access$100(OCGenerateConstructorFix.this);
    }
    
    @Override
    protected boolean enableChooseDialog(final Collection<OCDeclaratorSymbol> collection) {
        return false;
    }
    
    @NotNull
    @Override
    protected Condition<OCDeclaratorSymbol> getCandidatesFilter(@NotNull final OCGenerateConstructorContext ocGenerateConstructorContext) {
        try {
            if (ocGenerateConstructorContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix$1", "getCandidatesFilter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Condition alwaysFalse;
        try {
            alwaysFalse = Conditions.alwaysFalse();
            if (alwaysFalse == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix$1", "getCandidatesFilter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return (Condition<OCDeclaratorSymbol>)alwaysFalse;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}