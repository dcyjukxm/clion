// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.util.OCElementsRange;

public class OCMacroRange extends OCElementsRange
{
    private OCMacroCall myMacroCall;
    
    public OCMacroRange(final OCMacroCall myMacroCall, final PsiElement psiElement, final PsiElement psiElement2) {
        super(psiElement, psiElement2);
        this.myMacroCall = myMacroCall;
    }
    
    public OCMacroRange(final OCMacroCall myMacroCall) {
        super(null, null);
        this.myMacroCall = myMacroCall;
    }
    
    public boolean mapsToArguments() {
        return this.myFirstElement != null && this.myLastElement != null;
    }
    
    @Override
    public PsiElement getFirstElement() {
        return this.myFirstElement;
    }
    
    @Override
    public PsiElement getLastElement() {
        return this.myLastElement;
    }
    
    public OCMacroCall getMacroCall() {
        return this.myMacroCall;
    }
    
    @Nullable
    public TextRange getArgumentRange() {
        if (this.mapsToArguments()) {
            return this.getTextRange();
        }
        return null;
    }
}
