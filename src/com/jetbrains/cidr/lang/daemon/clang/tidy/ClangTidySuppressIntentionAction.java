// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nls;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.util.DocumentUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.codeInspection.SuppressIntentionAction;

public class ClangTidySuppressIntentionAction extends SuppressIntentionAction
{
    public void invoke(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidySuppressIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidySuppressIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (editor == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        OCChangeUtil.changeText(project, psiElement.getContainingFile(), DocumentUtil.getLineEndOffset(psiElement.getTextOffset(), editor.getDocument()), 0, " // NOLINT", true);
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidySuppressIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidySuppressIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        Label_0114: {
            try {
                if (editor == null) {
                    return false;
                }
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2.isValid();
                if (!b) {
                    return false;
                }
                break Label_0114;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2.isValid();
                if (!b) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        final Document document = editor.getDocument();
        final String text = document.getText(DocumentUtil.getLineTextRange(document, document.getLineNumber(psiElement.getTextOffset())));
        try {
            if (!text.endsWith("\\")) {
                return true;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        return false;
    }
    
    @Nls
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Suppress for line";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidySuppressIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getText() {
        String string;
        try {
            string = "Suppress " + StringUtil.wrapWithDoubleQuote("ClangTidyInspection") + " for line";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidySuppressIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return string;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
