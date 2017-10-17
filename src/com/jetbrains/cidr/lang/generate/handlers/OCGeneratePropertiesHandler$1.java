// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.intentions.OCMoveToPrivateCategoryIntentionAction;
import com.jetbrains.cidr.lang.psi.OCProperty;
import java.util.Collection;
import java.util.ArrayList;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.Nullable;
import com.intellij.usageView.UsageInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.MultiMap;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.quickfixes.OCReleaseVariablesIntentionAction;
import java.util.Map;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.refactoring.OCConvertMemberRefactoringProcessor;

class OCGeneratePropertiesHandler$1 extends OCConvertMemberRefactoringProcessor<OCInstanceVariableSymbol> {
    final /* synthetic */ boolean val$putToPrivateCategory;
    
    @Override
    protected boolean showConflicts(@NotNull final MultiMap<PsiElement, String> multiMap, @Nullable final UsageInfo[] array) {
        try {
            if (multiMap == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "conflicts", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler$1", "showConflicts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ApplicationManager.getApplication().isUnitTestMode()) {
                OCGeneratePropertiesHandler.this.passConflictsToTest(new ArrayList<String>(multiMap.values()));
                this.prepareSuccessful();
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return super.showConflicts(multiMap, array);
    }
    
    @Override
    protected void performRefactoring(@NotNull final UsageInfo[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "usages", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler$1", "performRefactoring"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        PsiElement psiElement = null;
        Label_0091: {
            try {
                super.performRefactoring(array);
                if (this.myNewDeclarations.isEmpty()) {
                    psiElement = null;
                    break Label_0091;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            psiElement = this.myNewDeclarations.values().iterator().next();
        }
        final PsiElement psiElement2 = psiElement;
        try {
            if (!this.val$putToPrivateCategory || !(psiElement2 instanceof OCProperty)) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final OCMoveToPrivateCategoryIntentionAction ocMoveToPrivateCategoryIntentionAction = new OCMoveToPrivateCategoryIntentionAction() {
            @Override
            protected OCMemberSymbol locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler$1$1", "locateCandidate"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                return ((OCProperty)psiElement2).getDeclaration().getDeclarators().get(0).getSymbol();
            }
            
            private static IllegalArgumentException c(final IllegalArgumentException ex) {
                return ex;
            }
        };
        try {
            if (ocMoveToPrivateCategoryIntentionAction.isAvailable(this.myProject, null, psiElement2.getContainingFile())) {
                ocMoveToPrivateCategoryIntentionAction.invoke(this.myProject, null, psiElement2.getContainingFile());
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}