// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.handlers.OCDeclareActionContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import org.jetbrains.annotations.NotNull;

public class OCDeclareMethodInPrivateCategoryIntentionAction extends OCDeclareMethodInInterfaceIntentionAction
{
    @NotNull
    @Override
    public String getText() {
        String s;
        try {
            s = "Declare method in private category";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCDeclareMethodInPrivateCategoryIntentionAction", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @NotNull
    @Override
    protected OCDeclareActionContext evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        final OCDeclareActionContext evaluateActionContext = super.evaluateActionContext(ocClassSymbol, psiElement);
        OCDeclareActionContext ocDeclareActionContext;
        try {
            evaluateActionContext.setTarget(OCDeclareActionContext.Target.PRIVATE_CATEGORY);
            ocDeclareActionContext = evaluateActionContext;
            if (ocDeclareActionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCDeclareMethodInPrivateCategoryIntentionAction", "evaluateActionContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return ocDeclareActionContext;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
