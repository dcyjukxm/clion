// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.jetbrains.cidr.lang.refactoring.move.handlers.OCPushDownRefactoringHandler;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.refactoring.move.handlers.OCPullUpRefactoringHandler;
import com.jetbrains.cidr.lang.refactoring.move.handlers.OCExtractSuperClassHandler;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.intellij.refactoring.RefactoringActionHandler;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.intellij.refactoring.changeSignature.ChangeSignatureHandler;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.lang.refactoring.RefactoringSupportProvider;

public class OCRefactoringSupportProvider extends RefactoringSupportProvider
{
    public boolean isSafeDeleteAvailable(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/OCRefactoringSupportProvider", "isSafeDeleteAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!OCCodeInsightUtil.isValid(psiElement)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return psiElement instanceof OCSymbolDeclarator;
    }
    
    public ChangeSignatureHandler getChangeSignatureHandler() {
        return (ChangeSignatureHandler)new OCChangeSignatureActionHandler();
    }
    
    public RefactoringActionHandler getExtractMethodHandler() {
        return (RefactoringActionHandler)new OCExtractMethodHandler();
    }
    
    public RefactoringActionHandler getExtractSuperClassHandler() {
        try {
            if (OCNewFileActionBase.isNewFileActionSupported()) {
                final OCExtractSuperClassHandler ocExtractSuperClassHandler = new OCExtractSuperClassHandler();
                return (RefactoringActionHandler)ocExtractSuperClassHandler;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCExtractSuperClassHandler ocExtractSuperClassHandler = null;
        return (RefactoringActionHandler)ocExtractSuperClassHandler;
    }
    
    @Nullable
    public RefactoringActionHandler getPullUpHandler() {
        return (RefactoringActionHandler)new OCPullUpRefactoringHandler();
    }
    
    @Nullable
    public RefactoringActionHandler getPushDownHandler() {
        return (RefactoringActionHandler)new OCPushDownRefactoringHandler();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
