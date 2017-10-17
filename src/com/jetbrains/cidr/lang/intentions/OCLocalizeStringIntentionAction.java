// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.intellij.psi.PsiReference;
import com.intellij.openapi.actionSystem.DataContext;
import com.jetbrains.cidr.lang.refactoring.rename.OCInplaceRenameHandler;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.featureStatistics.FeatureUsageTracker;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.command.CommandProcessor;
import com.jetbrains.cidr.lang.quickfixes.OCProvideStringLocalizationsIntentionAction;
import com.jetbrains.cidr.lang.resolve.references.OCStringResourceReference;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.FileModificationService;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.codeInsight.intention.IntentionAction;

public class OCLocalizeStringIntentionAction implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Localize the string";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCLocalizeStringIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw b((RuntimeException)ex);
        }
        return s;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCLocalizeStringIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw b((RuntimeException)ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCLocalizeStringIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw b((RuntimeException)ex);
        }
        final OCLiteralExpression a = a(editor, psiFile);
        try {
            if (!OCCodeInsightUtil.isValid((PsiElement)a)) {
                return false;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw b((RuntimeException)ex2);
        }
        final String stringLiteral = OCElementUtil.getStringLiteral((PsiElement)a);
        Label_0098: {
            try {
                if (stringLiteral == null) {
                    return false;
                }
                final String s = stringLiteral;
                final boolean b = s.isEmpty();
                if (b) {
                    return false;
                }
                break Label_0098;
            }
            catch (IncorrectOperationException ex3) {
                throw b((RuntimeException)ex3);
            }
            try {
                final String s = stringLiteral;
                final boolean b = s.isEmpty();
                if (b) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw b((RuntimeException)ex4);
            }
        }
        final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)PsiTreeUtil.getParentOfType((PsiElement)a, (Class)OCSendMessageExpression.class);
        Label_0143: {
            try {
                if (ocSendMessageExpression == null) {
                    break Label_0143;
                }
                final OCSendMessageExpression ocSendMessageExpression2 = ocSendMessageExpression;
                final String s2 = ocSendMessageExpression2.getMessageSelector();
                final String s3 = "localizedStringForKey:value:table:";
                final boolean b2 = s2.equals(s3);
                if (b2) {
                    return false;
                }
                break Label_0143;
            }
            catch (IncorrectOperationException ex5) {
                throw b((RuntimeException)ex5);
            }
            try {
                final OCSendMessageExpression ocSendMessageExpression2 = ocSendMessageExpression;
                final String s2 = ocSendMessageExpression2.getMessageSelector();
                final String s3 = "localizedStringForKey:value:table:";
                final boolean b2 = s2.equals(s3);
                if (b2) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex6) {
                throw b((RuntimeException)ex6);
            }
        }
        final OCMacroCall ocMacroCall = (OCMacroCall)PsiTreeUtil.getParentOfType((PsiElement)a, (Class)OCMacroCall.class);
        if (ocMacroCall != null) {
            final OCReferenceElement macroReferenceElement = ocMacroCall.getMacroReferenceElement();
            try {
                if (macroReferenceElement == null) {
                    return true;
                }
                final OCReferenceElement ocReferenceElement = macroReferenceElement;
                final String s4 = ocReferenceElement.getName();
                final String s5 = "NSLocalizedString";
                final boolean b3 = s4.startsWith(s5);
                if (b3) {
                    return false;
                }
                return true;
            }
            catch (IncorrectOperationException ex7) {
                throw b((RuntimeException)ex7);
            }
            try {
                final OCReferenceElement ocReferenceElement = macroReferenceElement;
                final String s4 = ocReferenceElement.getName();
                final String s5 = "NSLocalizedString";
                final boolean b3 = s4.startsWith(s5);
                if (b3) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex8) {
                throw b((RuntimeException)ex8);
            }
        }
        return true;
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCLocalizeStringIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw b((RuntimeException)ex);
        }
        try {
            if (!FileModificationService.getInstance().prepareFileForWrite(psiFile)) {
                return;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw b((RuntimeException)ex2);
        }
        PsiDocumentManager.getInstance(psiFile.getProject()).commitAllDocuments();
        final OCLiteralExpression a = a(editor, psiFile);
        try {
            if (a == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw b((RuntimeException)ex3);
        }
        a.getEscapedLiteralText();
        final OCProvideStringLocalizationsIntentionAction ocProvideStringLocalizationsIntentionAction = new OCProvideStringLocalizationsIntentionAction(new OCStringResourceReference((PsiElement)a, "Localizable"));
        try {
            if (!ocProvideStringLocalizationsIntentionAction.checkFilesForWrite()) {
                return;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw b((RuntimeException)ex4);
        }
        final IllegalArgumentException ex5;
        final OCProvideStringLocalizationsIntentionAction ocProvideStringLocalizationsIntentionAction2;
        final Object o;
        final Object o2;
        CommandProcessor.getInstance().executeCommand(project, () -> {
            try {
                if (project == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCLocalizeStringIntentionAction", "lambda$invoke$1"));
                    throw ex5;
                }
            }
            catch (IncorrectOperationException ex6) {
                throw b((RuntimeException)ex6);
            }
            try {
                if (ocProvideStringLocalizationsIntentionAction2.invokeBool(psiFile)) {
                    WriteAction.run(() -> {
                        try {
                            if (project == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCLocalizeStringIntentionAction", "lambda$null$0"));
                            }
                        }
                        catch (RuntimeException ex) {
                            throw b(ex);
                        }
                        FeatureUsageTracker.getInstance().triggerFeatureUsed("refactoring.localize");
                        final String s2 = "NSLocalizedString(@\"";
                        final String keyString = getKeyString(o);
                        final int n = ((OCExpression)OCChangeUtil.replaceHandlingMacros((PsiElement)o2, (PsiElement)OCElementFactory.expressionFromText(s2 + keyString + "\", @\"" + o + "\")", (PsiElement)o2))).getRangeWithMacros().getStartOffset() + s2.length();
                        editor.getCaretModel().moveToOffset(n);
                        editor.getSelectionModel().setSelection(n, n + keyString.length());
                        final OCMacroCall ocMacroCall = (OCMacroCall)PsiTreeUtil.getParentOfType(psiFile.findElementAt(n), (Class)OCMacroCall.class);
                        if (ocMacroCall != null) {
                            final PsiReference reference = ocMacroCall.findReferenceAt(n - ocMacroCall.getTextOffset());
                            PsiElement resolve = null;
                            Label_0228: {
                                try {
                                    if (reference != null) {
                                        resolve = reference.resolve();
                                        break Label_0228;
                                    }
                                }
                                catch (RuntimeException ex2) {
                                    throw b(ex2);
                                }
                                resolve = null;
                            }
                            final PsiElement psiElement = resolve;
                            try {
                                if (psiElement != null) {
                                    PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument(editor.getDocument());
                                    new OCInplaceRenameHandler().doRename(psiElement, editor, null);
                                }
                            }
                            catch (RuntimeException ex3) {
                                throw b(ex3);
                            }
                        }
                    });
                }
            }
            catch (IncorrectOperationException ex7) {
                throw b((RuntimeException)ex7);
            }
        }, this.getText(), (Object)null);
    }
    
    @NotNull
    public static String getKeyString(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/intentions/OCLocalizeStringIntentionAction", "getKeyString"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw b((RuntimeException)ex);
        }
        try {
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCLocalizeStringIntentionAction", "getKeyString"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw b((RuntimeException)ex2);
        }
        return s;
    }
    
    public boolean startInWriteAction() {
        return false;
    }
    
    @Nullable
    private static OCLiteralExpression a(final Editor editor, final PsiFile psiFile) {
        return OCElementUtil.getAdjacentParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), (Class<? extends OCLiteralExpression>)OCLiteralExpression.class);
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
