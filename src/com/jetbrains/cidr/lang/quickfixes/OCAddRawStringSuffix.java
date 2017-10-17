// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.editor.smartEnter.OCFixer;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;

public class OCAddRawStringSuffix extends OCPsiElementQuickFix<PsiElement>
{
    private final PsiElement myRawString;
    
    public OCAddRawStringSuffix(final PsiElement myRawString) {
        super(myRawString);
        this.myRawString = myRawString;
    }
    
    @Override
    protected String getTextInternal() {
        return "Add suffix";
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Check raw string";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCAddRawStringSuffix", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return s;
    }
    
    @Override
    public boolean isAvailable() {
        try {
            if (OCElementUtil.getElementType(this.myRawString) == OCTokenTypes.WRONG_RAW_STRING_LITERAL) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return false;
    }
    
    @Override
    public void invoke(@NotNull final Project project, @Nullable final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCAddRawStringSuffix", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        Label_0067: {
            try {
                if (editor == null) {
                    return;
                }
                final OCAddRawStringSuffix ocAddRawStringSuffix = this;
                final PsiElement psiElement = ocAddRawStringSuffix.myRawString;
                final ASTNode astNode = psiElement.getNode();
                if (astNode != null) {
                    break Label_0067;
                }
                return;
            }
            catch (IncorrectOperationException ex2) {
                throw c(ex2);
            }
            try {
                final OCAddRawStringSuffix ocAddRawStringSuffix = this;
                final PsiElement psiElement = ocAddRawStringSuffix.myRawString;
                final ASTNode astNode = psiElement.getNode();
                if (astNode != null) {
                    OCFixer.needCorrectionLiteral(editor, this.myRawString.getNode(), true);
                }
            }
            catch (IncorrectOperationException ex3) {
                throw c(ex3);
            }
        }
    }
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
}
