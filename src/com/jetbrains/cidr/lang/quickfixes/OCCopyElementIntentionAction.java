// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.psi.PsiElement;

public class OCCopyElementIntentionAction extends OCPsiElementQuickFix<PsiElement>
{
    private String myIntentionName;
    private SmartPsiElementPointer mySourceElementPtr;
    private String myFamilyName;
    
    public OCCopyElementIntentionAction(final PsiElement psiElement, final PsiElement psiElement2, final String s) {
        this(psiElement, psiElement2, s, s);
    }
    
    public OCCopyElementIntentionAction(final PsiElement psiElement, final PsiElement psiElement2, final String myIntentionName, final String myFamilyName) {
        super(psiElement2);
        this.mySourceElementPtr = OCElementUtil.createPsiElementPointer(psiElement);
        this.myIntentionName = myIntentionName;
        this.myFamilyName = myFamilyName;
    }
    
    @Override
    protected String getTextInternal() {
        return this.myIntentionName;
    }
    
    @NotNull
    public String getFamilyName() {
        String myFamilyName;
        try {
            myFamilyName = this.myFamilyName;
            if (myFamilyName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCCopyElementIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return myFamilyName;
    }
    
    @Override
    protected boolean isAvailable(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "destinationElement", "com/jetbrains/cidr/lang/quickfixes/OCCopyElementIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        try {
            if (OCElementUtil.getPsiElementByPointer((com.intellij.psi.SmartPsiElementPointer<PsiElement>)this.mySourceElementPtr) != null) {
                return true;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw c(ex2);
        }
        return false;
    }
    
    @Nullable
    public PsiElement getElementToMakeWritable(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentFile", "com/jetbrains/cidr/lang/quickfixes/OCCopyElementIntentionAction", "getElementToMakeWritable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return this.myElementPtr.getElement();
    }
    
    @Override
    public void invoke(@NotNull final Project project, @Nullable final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCCopyElementIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        this.myElementPtr.getElement().replace(OCElementUtil.getPsiElementByPointer((com.intellij.psi.SmartPsiElementPointer<PsiElement>)this.mySourceElementPtr));
    }
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
}
