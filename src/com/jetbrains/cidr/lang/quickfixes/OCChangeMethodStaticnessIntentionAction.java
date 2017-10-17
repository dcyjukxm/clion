// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.FileModificationService;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;

public class OCChangeMethodStaticnessIntentionAction extends OCSymbolQuickFix<OCMethodSymbol>
{
    private boolean myMakeStatic;
    
    public OCChangeMethodStaticnessIntentionAction(final OCMethodSymbol ocMethodSymbol, final boolean myMakeStatic) {
        super(ocMethodSymbol);
        this.myMakeStatic = myMakeStatic;
    }
    
    @Override
    protected String getTextInternal(final OCMethodSymbol ocMethodSymbol) {
        StringBuilder append;
        try {
            append = new StringBuilder().append("Make '").append(ocMethodSymbol.getPresentableName());
            if (this.myMakeStatic) {
                final String s = "' a class method";
                return append.append(s).toString();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final String s = "' an instance method";
        return append.append(s).toString();
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Make method instance/class";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeMethodStaticnessIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected boolean isAvailable(final OCMethodSymbol ocMethodSymbol) {
        Label_0023: {
            try {
                if (!OCSearchScope.isInProjectSources(ocMethodSymbol)) {
                    return false;
                }
                final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                final OCPropertySymbol ocPropertySymbol = ocMethodSymbol2.getGeneratedFromProperty();
                if (ocPropertySymbol == null) {
                    break Label_0023;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                final OCPropertySymbol ocPropertySymbol = ocMethodSymbol2.getGeneratedFromProperty();
                if (ocPropertySymbol == null) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    protected void invoke(final OCMethodSymbol ocMethodSymbol) {
        this.a(ocMethodSymbol);
        this.a(ocMethodSymbol.getAssociatedSymbol());
    }
    
    private void a(final OCMethodSymbol ocMethodSymbol) {
        try {
            if (ocMethodSymbol == null) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final PsiElement locateDefinition = ocMethodSymbol.locateDefinition();
        try {
            if (!(locateDefinition instanceof OCMethod) || !FileModificationService.getInstance().prepareFileForWrite(locateDefinition.getContainingFile())) {
                return;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        ((OCMethod)locateDefinition).setStatic(this.myMakeStatic);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
