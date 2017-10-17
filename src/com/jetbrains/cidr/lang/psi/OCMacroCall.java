// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.util.Processor;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;

public interface OCMacroCall extends OCElement
{
    @Nullable
    OCMacroSymbol resolveToSymbol();
    
    List<OCMacroCallArgument> getArguments();
    
    @NotNull
    String getReplacementText();
    
    @Nullable
    OCReferenceElement getMacroReferenceElement();
    
    @Nullable
    OCExpression getExpansionExpression();
    
    @Nullable
    PsiElement getLastExpansionLeaf();
    
    @Nullable
    PsiElement getFirstExpansionLeaf();
    
    boolean processExpansionDependencies(final Processor<PsiElement> p0);
    
    boolean processExpansionDependenciesWithIdent(final String p0, final Processor<PsiElement> p1);
    
    @NotNull
    ParameterCheckResult checkParameters(@NotNull final OCMacroSymbol p0);
    
    public static class ParameterCheckResult
    {
        private final int myActual;
        private final int myAllowed;
        
        public ParameterCheckResult(final int myActual, final int myAllowed) {
            this.myActual = myActual;
            this.myAllowed = myAllowed;
        }
        
        public int getActualCount() {
            return this.myActual;
        }
        
        public int getAllowedCount() {
            return this.myAllowed;
        }
    }
}
