// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.TokenType;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public abstract class CMakeCommandArgumentsImplMixin extends CMakeElementBase implements CMakeCommandArgumentsMixin
{
    public CMakeCommandArgumentsImplMixin(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandArgumentsImplMixin", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public boolean canAppendArguments() {
        try {
            if (!PsiTreeUtil.hasErrorElements((PsiElement)this)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public void appendArgument(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentText", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandArgumentsImplMixin", "appendArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCLog.LOG.assertTrue(this.canAppendArguments());
        final ASTNode childByType = this.getNode().findChildByType(CMakeTokenTypes.RPAR);
        Logger log = null;
        boolean b = false;
        Label_0084: {
            try {
                log = OCLog.LOG;
                if (childByType != null) {
                    b = true;
                    break Label_0084;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            b = false;
        }
        log.assertTrue(b);
        final PsiElement psi = childByType.getPsi();
        if (psi.getPrevSibling().getNode().getElementType() != TokenType.WHITE_SPACE) {
            this.addBefore(CMakeElementFactory.createWhitespace(this.getProject()), psi);
        }
        this.addBefore((PsiElement)CMakeElementFactory.createArgument(this.getProject(), s), psi);
    }
    
    @Override
    public boolean isFunctionDeclarationParameters() {
        return this.getParent() instanceof CMakeFunctionCommand;
    }
    
    @Override
    public boolean isEndFunctionParameters() {
        return this.getParent() instanceof CMakeEndFunctionCommand;
    }
    
    @Override
    public boolean isMacroDeclarationParameters() {
        return this.getParent() instanceof CMakeMacroCommand;
    }
    
    @Override
    public boolean isEndMacroParameters() {
        return this.getParent() instanceof CMakeEndMacroCommand;
    }
    
    @Override
    public boolean isEndRoutineParameters() {
        Label_0021: {
            try {
                if (this.isEndFunctionParameters()) {
                    break Label_0021;
                }
                final CMakeCommandArgumentsImplMixin cMakeCommandArgumentsImplMixin = this;
                final boolean b = cMakeCommandArgumentsImplMixin.isEndMacroParameters();
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final CMakeCommandArgumentsImplMixin cMakeCommandArgumentsImplMixin = this;
                final boolean b = cMakeCommandArgumentsImplMixin.isEndMacroParameters();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    public CMakeArgument getFirstArgument() {
        return (CMakeArgument)PsiTreeUtil.findChildOfType((PsiElement)this, (Class)CMakeArgument.class);
    }
    
    @Override
    public CMakeCommand getCommand() {
        return (CMakeCommand)this.getParent();
    }
    
    @Override
    public CMakeCommandName getCommandName() {
        return this.getCommand().getCMakeCommandName();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
