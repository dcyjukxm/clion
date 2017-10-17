// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers.implement;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.generate.actions.OCOverrideImplementCppActionContext;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCBundle;

public class OCImplementCppFunctionsHandler extends OCOverrideImplementCppFunctionsHandlerBase
{
    @Nullable
    @Override
    public String getActionName() {
        return OCBundle.message("implement.cpp.action.name", new Object[0]);
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return OCBundle.message("implement.cpp.action.memberChooserTitle", new Object[0]);
    }
    
    @Override
    protected String getNoMembersMessage(@NotNull final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext) {
        try {
            if (ocOverrideImplementCppActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementCppFunctionsHandler", "getNoMembersMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return OCBundle.message("implement.cpp.action.noMember", ocOverrideImplementCppActionContext.getParentNameUppercase());
    }
    
    @NotNull
    @Override
    protected String getNoParentsMessage() {
        String message;
        try {
            message = OCBundle.message("implement.cpp.action.noParent", new Object[0]);
            if (message == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementCppFunctionsHandler", "getNoParentsMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return message;
    }
    
    @Override
    protected OCOverrideImplementCppActionContext evaluateActionContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement) {
        return new OCOverrideImplementCppActionContext(ocStructSymbol, psiElement, true);
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
