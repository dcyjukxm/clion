// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCMoveTopLevelDialog;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCMoveMembersDialog;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCAbstractMoveDialog;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.refactoring.RefactoringBundle;
import com.intellij.refactoring.move.MoveCallback;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiReference;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.move.MoveHandlerDelegate;

public class OCMoveHandlerDelegate extends MoveHandlerDelegate
{
    private static OCMoveRefactoringHandler myMembersHandler;
    private static OCMoveTopLevelRefactoringHandler myTopLevelHandler;
    
    private static boolean a(final PsiElement psiElement) {
        return !(psiElement instanceof PsiFileSystemItem) && psiElement.getContainingFile() instanceof OCFile;
    }
    
    private static boolean b(final PsiElement psiElement) {
        final PsiNameIdentifierOwner psiNameIdentifierOwner = (PsiNameIdentifierOwner)PsiTreeUtil.getNonStrictParentOfType(psiElement, new Class[] { OCClassDeclaration.class, OCStruct.class });
        final PsiElement psiElement2 = (psiNameIdentifierOwner != null) ? psiNameIdentifierOwner.getNameIdentifier() : null;
        return psiElement2 != null && psiElement2.getTextRange().getEndOffset() < psiElement.getTextOffset();
    }
    
    @Override
    public boolean tryToMove(final PsiElement psiElement, final Project project, final DataContext dataContext, @Nullable final PsiReference psiReference, final Editor editor) {
        if (b(psiElement)) {
            OCMoveHandlerDelegate.myMembersHandler.invoke(project, editor, psiElement.getContainingFile(), dataContext);
            return true;
        }
        if (a(psiElement)) {
            OCMoveHandlerDelegate.myTopLevelHandler.invoke(project, editor, psiElement.getContainingFile(), dataContext);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isValidTarget(final PsiElement psiElement, final PsiElement[] array) {
        final PsiElement psiElement2 = array[0];
        if (!b(psiElement2)) {
            return a(psiElement2) && psiElement.getContainingFile() instanceof OCFile;
        }
        final PsiNameIdentifierOwner psiNameIdentifierOwner = (PsiNameIdentifierOwner)PsiTreeUtil.getNonStrictParentOfType(psiElement2, new Class[] { OCClassDeclaration.class, OCStruct.class });
        if (psiNameIdentifierOwner instanceof OCClassDeclaration) {
            return psiElement instanceof OCClassDeclaration;
        }
        return psiNameIdentifierOwner instanceof OCStruct && psiElement instanceof OCStruct;
    }
    
    @Override
    public boolean canMove(final PsiElement[] array, @Nullable final PsiElement psiElement) {
        final PsiElement psiElement2 = array[0];
        return b(psiElement2) || a(psiElement2);
    }
    
    @Override
    public void doMove(final Project project, final PsiElement[] array, @Nullable final PsiElement psiElement, @Nullable final MoveCallback moveCallback) {
        final PsiElement psiElement2 = array[0];
        if (b(psiElement2)) {
            OCMoveHandlerDelegate.myMembersHandler.invoke(project, array, null);
        }
        else if (a(psiElement2)) {
            OCMoveHandlerDelegate.myTopLevelHandler.invoke(project, array, null);
        }
    }
    
    static {
        OCMoveHandlerDelegate.myMembersHandler = new MoveMembersHandler();
        OCMoveHandlerDelegate.myTopLevelHandler = new MoveTopLevelHandler();
    }
    
    public static class MoveMembersHandler extends OCMoveRefactoringHandler
    {
        private String myTargetClassName;
        
        public MoveMembersHandler() {
        }
        
        public MoveMembersHandler(final String myTargetClassName) {
            this.myTargetClassName = myTargetClassName;
        }
        
        @Override
        protected String getTitle() {
            return RefactoringBundle.message("move.members.title");
        }
        
        @Override
        protected OCAbstractMoveDialog createDialog(final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition, final Editor editor) {
            return new OCMoveMembersDialog(ocSymbolDeclarator, ocSymbol, condition, this.myTargetClassName);
        }
        
        @NotNull
        @Override
        protected String getFeatureID() {
            String s;
            try {
                s = "refactoring.moveMembers";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveHandlerDelegate$MoveMembersHandler", "getFeatureID"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return s;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class MoveTopLevelHandler extends OCMoveTopLevelRefactoringHandler
    {
        private String myTargetFileName;
        
        public MoveTopLevelHandler() {
        }
        
        public MoveTopLevelHandler(final String myTargetFileName) {
            this.myTargetFileName = myTargetFileName;
        }
        
        @Override
        protected String getTitle() {
            return RefactoringBundle.message("move.members.title");
        }
        
        @Nullable
        @Override
        protected OCAbstractMoveDialog createDialog(final OCFile ocFile, final Condition<PsiElement> condition) {
            return new OCMoveTopLevelDialog(ocFile, condition, this.myTargetFileName);
        }
        
        @Nullable
        @Override
        protected OCAbstractMoveDialog createDialog(final OCCppNamespace ocCppNamespace, final Condition<PsiElement> condition) {
            return new OCMoveTopLevelDialog(ocCppNamespace, ocCppNamespace.getSymbol(), condition, this.myTargetFileName);
        }
    }
}
