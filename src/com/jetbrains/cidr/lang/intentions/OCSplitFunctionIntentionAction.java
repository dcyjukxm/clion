// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.ide.util.EditorHelper;
import com.intellij.util.ObjectUtils;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.util.text.CharArrayUtil;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import java.util.Collection;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.Document;
import java.util.Set;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.util.OCCallableUtil;
import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import java.util.Collections;
import com.jetbrains.cidr.lang.generate.OCCppDefinitionsUtil;
import com.intellij.codeInsight.FileModificationService;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.codeInsight.intention.IntentionAction;

public class OCSplitFunctionIntentionAction implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Split function into declaration and definition";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCSplitFunctionIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCSplitFunctionIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCSplitFunctionIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (!OCCodeInsightUtil.isValid((PsiElement)psiFile)) {
                return false;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCFunctionDefinition function = getFunction(editor, psiFile);
        Label_0086: {
            try {
                if (function == null) {
                    return false;
                }
                final OCFunctionDefinition ocFunctionDefinition = function;
                final OCCppNamespaceQualifier ocCppNamespaceQualifier = ocFunctionDefinition.getNamespaceQualifier();
                if (ocCppNamespaceQualifier == null) {
                    break Label_0086;
                }
                return false;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final OCFunctionDefinition ocFunctionDefinition = function;
                final OCCppNamespaceQualifier ocCppNamespaceQualifier = ocFunctionDefinition.getNamespaceQualifier();
                if (ocCppNamespaceQualifier == null) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCSplitFunctionIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiDocumentManager instance = PsiDocumentManager.getInstance(project);
        try {
            if (!FileModificationService.getInstance().prepareFileForWrite(psiFile)) {
                return;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        instance.commitAllDocuments();
        final OCFunctionDefinition function = getFunction(editor, psiFile);
        OCFunctionSymbol symbol = null;
        Label_0097: {
            try {
                if (function != null) {
                    symbol = function.getSymbol();
                    break Label_0097;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            symbol = null;
        }
        final OCFunctionSymbol ocFunctionSymbol = symbol;
        try {
            if (ocFunctionSymbol == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        final Document document = instance.getDocument(function.getContainingFile());
        try {
            if (document == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        final TextRange a = a(function);
        try {
            if (a == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex6) {
            throw a(ex6);
        }
        final OCGenerateUtil.ReplacePosition outsidePreferredPosition = OCCppDefinitionsUtil.getOutsidePreferredPosition(psiFile, OCCppDefinitionsUtil.getFunctionParent(ocFunctionSymbol), Collections.singletonList(ocFunctionSymbol));
        try {
            if (outsidePreferredPosition == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex7) {
            throw a(ex7);
        }
        final Document document2 = instance.getDocument(outsidePreferredPosition.file);
        try {
            if (document2 == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex8) {
            throw a(ex8);
        }
        final RangeMarker rangeMarker = document2.createRangeMarker(outsidePreferredPosition.range);
        StringBuilder sb = null;
        String s = null;
        Label_0251: {
            try {
                sb = new StringBuilder();
                if (OCGenerateUtil.shouldInsertNewLineBefore(document2.getCharsSequence(), outsidePreferredPosition.range.getStartOffset())) {
                    s = "\n";
                    break Label_0251;
                }
            }
            catch (IncorrectOperationException ex9) {
                throw a(ex9);
            }
            s = "";
        }
        final String string = sb.append(s).append(OCCallableUtil.functionSignature(OCCallableUtil.removeDeclarationSpecifiers(ocFunctionSymbol), OCCallableUtil.getFunctionParentQualifier(ocFunctionSymbol, outsidePreferredPosition.context), outsidePreferredPosition.context)).toString();
        StringBuilder append = null;
        String s2 = null;
        Label_0336: {
            try {
                append = new StringBuilder().append(document.getText(a));
                if (OCGenerateUtil.shouldInsertNewLineAfter(document2.getCharsSequence(), outsidePreferredPosition.range.getEndOffset())) {
                    s2 = "\n";
                    break Label_0336;
                }
            }
            catch (IncorrectOperationException ex10) {
                throw a(ex10);
            }
            s2 = "";
        }
        final String string2 = append.append(s2).toString();
        final Set set = ContainerUtil.set((Object[])new PsiFile[] { psiFile, outsidePreferredPosition.file });
        new WriteCommandAction(project, this.getText(), (PsiFile[])set.toArray(new PsiFile[set.size()])) {
            protected void run(@NotNull final Result result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/intentions/OCSplitFunctionIntentionAction$1", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                try {
                    if (!FileModificationService.getInstance().preparePsiElementsForWrite((Collection)set)) {
                        return;
                    }
                }
                catch (Throwable t2) {
                    throw a(t2);
                }
                try {
                    instance.doPostponedOperationsAndUnblockDocument(document);
                    if (document != document2) {
                        instance.doPostponedOperationsAndUnblockDocument(document2);
                    }
                }
                catch (Throwable t3) {
                    throw a(t3);
                }
                document.replaceString(a.getStartOffset(), a.getEndOffset(), (CharSequence)";");
                final int startOffset = rangeMarker.getStartOffset();
                document2.deleteString(rangeMarker.getStartOffset(), rangeMarker.getEndOffset());
                final RangeMarker rangeMarker = document2.createRangeMarker(startOffset, startOffset);
                rangeMarker.setGreedyToRight(true);
                document2.insertString(startOffset, (CharSequence)string);
                rangeMarker.setGreedyToRight(false);
                final RangeMarker rangeMarker2 = document2.createRangeMarker(rangeMarker.getEndOffset(), rangeMarker.getEndOffset());
                rangeMarker2.setGreedyToRight(true);
                document2.insertString(rangeMarker2.getStartOffset(), (CharSequence)string2);
                rangeMarker2.setGreedyToRight(false);
                instance.commitDocument(document2);
                OCChangeUtil.reformatTextIfNotInjected(outsidePreferredPosition.file, rangeMarker.getStartOffset(), rangeMarker.getEndOffset());
                instance.commitDocument(document2);
                OCChangeUtil.reformatTextIfNotInjected(outsidePreferredPosition.file, rangeMarker2.getStartOffset(), rangeMarker2.getStartOffset() + 1);
                CodeStyleManager.getInstance(project).adjustLineIndent(outsidePreferredPosition.file, TextRange.create(rangeMarker2.getStartOffset(), rangeMarker2.getEndOffset()));
                OCChangeUtil.reformatTextIfNotInjected(outsidePreferredPosition.file, rangeMarker2.getEndOffset(), CharArrayUtil.shiftForward(document2.getCharsSequence(), rangeMarker2.getEndOffset(), " \t\n"));
                instance.commitDocument(document);
                final OCFunctionDeclaration ocFunctionDeclaration = (OCFunctionDeclaration)PsiTreeUtil.getParentOfType(outsidePreferredPosition.file.findElementAt(rangeMarker.getEndOffset()), (Class)OCFunctionDeclaration.class);
                PsiElement nameIdentifier = null;
                Label_0464: {
                    try {
                        if (ocFunctionDeclaration != null) {
                            nameIdentifier = ocFunctionDeclaration.getNameIdentifier();
                            break Label_0464;
                        }
                    }
                    catch (Throwable t4) {
                        throw a(t4);
                    }
                    nameIdentifier = null;
                }
                EditorHelper.openInEditor((PsiElement)ObjectUtils.notNull((Object)nameIdentifier, (Object)outsidePreferredPosition.file));
                rangeMarker.dispose();
                rangeMarker2.dispose();
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute();
        rangeMarker.dispose();
    }
    
    @Nullable
    private static TextRange a(@NotNull final OCFunctionDefinition ocFunctionDefinition) {
        try {
            if (ocFunctionDefinition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "function", "com/jetbrains/cidr/lang/intentions/OCSplitFunctionIntentionAction", "getBodyRange"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCConstructorInitializationList constructorInitializationList = ocFunctionDefinition.getConstructorInitializationList();
        Object declarator;
        if (constructorInitializationList != null) {
            PsiElement psiElement = constructorInitializationList.getPrevSibling();
            while (true) {
                Label_0080: {
                    try {
                        if (psiElement == null) {
                            break;
                        }
                        final PsiElement psiElement2 = psiElement;
                        final boolean b = OCElementUtil.isElementSignificant(psiElement2);
                        if (b) {
                            break Label_0080;
                        }
                        break Label_0080;
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final PsiElement psiElement2 = psiElement;
                        final boolean b = OCElementUtil.isElementSignificant(psiElement2);
                        if (b) {
                            if (OCElementUtil.getElementType(psiElement) != OCTokenTypes.COLON) {
                                break;
                            }
                        }
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                }
                psiElement = psiElement.getPrevSibling();
            }
            declarator = psiElement;
        }
        else {
            declarator = ocFunctionDefinition.getDeclarator();
        }
        try {
            if (declarator == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        return TextRange.create(OCElementUtil.getRangeWithMacros((PsiElement)declarator).getEndOffset(), ocFunctionDefinition.getTextRange().getEndOffset());
    }
    
    public boolean startInWriteAction() {
        return false;
    }
    
    @Nullable
    protected static OCFunctionDefinition getFunction(final Editor editor, final PsiFile psiFile) {
        return (OCFunctionDefinition)PsiTreeUtil.getParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), (Class)OCFunctionDefinition.class, false, new Class[] { OCBlockStatement.class });
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
