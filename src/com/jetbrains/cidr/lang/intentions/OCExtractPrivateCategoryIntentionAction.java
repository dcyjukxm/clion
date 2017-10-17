// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.generate.handlers.OCDeclareActionContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.handlers.OCDeclareMembersHandler;

public class OCExtractPrivateCategoryIntentionAction extends OCDeclareMembersHandler implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Extract private category of this class";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCExtractPrivateCategoryIntentionAction", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCExtractPrivateCategoryIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return text;
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCExtractPrivateCategoryIntentionAction", "evaluateActionContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocDeclareActionContext;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCExtractPrivateCategoryIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return OCExtractCategoryIntentionAction.isClassNameWithoutCategory(psiFile.findElementAt(editor.getCaretModel().getOffset()));
    }
    
    protected boolean allowEmptySelection(final OCDeclareActionContext ocDeclareActionContext) {
        return true;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
