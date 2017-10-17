// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.openapi.editor.ScrollType;
import com.intellij.util.text.CharArrayUtil;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.FileModificationService;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCArgumentSelector;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.openapi.editor.ex.EditorEx;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentList;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.openapi.util.TextRange;
import java.util.List;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.codeInsight.completion.TemplateParameterTraversalPolicy;

public class OCTemplateParameterTraversalPolicy implements TemplateParameterTraversalPolicy
{
    @Nullable
    private static List<TextRange> a(final Editor editor, final PsiFile psiFile) {
        if (LookupManager.getActiveLookup(editor) != null) {
            return null;
        }
        final int offset = editor.getCaretModel().getOffset();
        PsiElement psiElement = psiFile.findElementAt(offset);
        if (psiElement instanceof PsiWhiteSpace) {
            final int index = psiElement.getText().indexOf(10);
            if (index >= 0 && offset > psiElement.getTextRange().getStartOffset() + index) {
                return null;
            }
        }
        final OCStatement ocStatement = (OCStatement)PsiTreeUtil.getParentOfType(psiElement, (Class)OCStatement.class);
        if (ocStatement != null) {
            final List<TextRange> a = a(editor, ocStatement.getRangeWithMacros());
            if (a.size() > 1) {
                return a;
            }
            if (a.size() == 1) {
                psiElement = psiFile.findElementAt(a.get(0).getStartOffset());
            }
        }
        PsiElement psiElement2 = a(offset, psiElement);
        final ArrayList<Object> list = new ArrayList<Object>();
        while (psiElement2 != null) {
            final List<TextRange> a2 = a(psiElement2);
            if (a2 != null) {
                list.addAll(a2);
            }
            psiElement2 = a(offset, psiElement2);
        }
        Collections.sort(list, (textRange, textRange2) -> textRange.getStartOffset() - textRange2.getStartOffset());
        return (List<TextRange>)(list.isEmpty() ? null : list);
    }
    
    @Nullable
    private static List<TextRange> a(final PsiElement psiElement) {
        if (psiElement instanceof OCSendMessageExpression) {
            return a((OCSendMessageExpression)psiElement);
        }
        if (psiElement instanceof OCCallExpression) {
            return a((OCCallExpression)psiElement);
        }
        if (psiElement instanceof OCMacroCall) {
            return a((OCMacroCall)psiElement);
        }
        if (psiElement instanceof OCTemplateArgumentList) {
            return a((OCTemplateArgumentList)psiElement);
        }
        return null;
    }
    
    private static List<TextRange> a(final OCTemplateArgumentList list) {
        final ArrayList<TextRange> list2 = new ArrayList<TextRange>();
        final Iterator<? extends OCElement> iterator = list.getArguments().iterator();
        while (iterator.hasNext()) {
            list2.add(((OCElement)iterator.next()).getRangeWithMacros());
        }
        return list2;
    }
    
    private static List<TextRange> a(final OCMacroCall ocMacroCall) {
        final ArrayList<TextRange> list = new ArrayList<TextRange>();
        final Iterator<OCMacroCallArgument> iterator = ocMacroCall.getArguments().iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().getRangeWithMacros());
        }
        return list;
    }
    
    private static List<TextRange> a(final OCCallExpression ocCallExpression) {
        final ArrayList<TextRange> list = new ArrayList<TextRange>();
        final Iterator<OCExpression> iterator = ocCallExpression.getArguments().iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().getRangeWithMacros());
        }
        return list;
    }
    
    private static List<TextRange> a(final Editor editor, final TextRange textRange) {
        final ArrayList<TextRange> list = new ArrayList<TextRange>();
        final HighlighterIterator iterator = ((EditorEx)editor).getHighlighter().createIterator(textRange.getStartOffset());
        int start = -1;
        while (!iterator.atEnd() && iterator.getStart() < textRange.getEndOffset()) {
            final IElementType tokenType = iterator.getTokenType();
            if (tokenType == OCTokenTypes.TEMPLATE_START_MARK) {
                start = iterator.getStart();
            }
            if (tokenType == OCTokenTypes.TEMPLATE_STOP_MARK && start >= 0) {
                list.add(TextRange.create(start, iterator.getEnd()));
                start = -1;
            }
            iterator.advance();
        }
        return list;
    }
    
    private static List<TextRange> a(final OCSendMessageExpression ocSendMessageExpression) {
        final ArrayList<TextRange> list = new ArrayList<TextRange>();
        for (final OCMessageArgument ocMessageArgument : ocSendMessageExpression.getArguments()) {
            final OCArgumentSelector argumentSelector = ocMessageArgument.getArgumentSelector();
            final OCExpression argumentExpression = ocMessageArgument.getArgumentExpression();
            if (argumentExpression != null) {
                if (argumentExpression instanceof OCLiteralExpression && "<#".equals(argumentExpression.getFirstChild().getText())) {
                    list.add(argumentExpression.getRangeWithMacros());
                }
                else {
                    list.add(new TextRange(argumentSelector.getTextRange().getEndOffset(), argumentExpression.getRangeWithMacros().getEndOffset()));
                }
            }
        }
        return list;
    }
    
    @Nullable
    private static PsiElement a(final int n, final PsiElement psiElement) {
        final PsiElement contextOfType = PsiTreeUtil.getContextOfType(psiElement, new Class[] { OCSendMessageExpression.class, OCCallExpression.class, OCBlockStatement.class, OCMacroCall.class, OCTemplateArgumentList.class });
        if (contextOfType == null || contextOfType instanceof OCBlockStatement) {
            return null;
        }
        Object o;
        if (contextOfType instanceof OCCallExpression) {
            o = ((OCCallExpression)contextOfType).getArguments();
        }
        else if (contextOfType instanceof OCSendMessageExpression) {
            o = ((OCSendMessageExpression)contextOfType).getArguments();
        }
        else if (contextOfType instanceof OCMacroCall) {
            o = ((OCMacroCall)contextOfType).getArguments();
        }
        else if (contextOfType instanceof OCTemplateArgumentList) {
            o = ((OCTemplateArgumentList)contextOfType).getArguments();
        }
        else {
            o = null;
        }
        final PsiElement psiElement2 = (o != null && ((List)o).size() > 0) ? ((List<PsiElement>)o).get(((List)o).size() - 1) : null;
        if (psiElement2 != null && n >= psiElement2.getTextRange().getEndOffset()) {
            return a(n, contextOfType);
        }
        if (n == contextOfType.getTextRange().getStartOffset()) {
            return a(n, contextOfType);
        }
        return contextOfType;
    }
    
    public boolean isValidForFile(final Editor editor, final PsiFile psiFile) {
        return psiFile instanceof OCFile && a(editor, psiFile) != null;
    }
    
    private static TextRange a(final int n, final List<TextRange> list, final boolean b) {
        if (b) {
            for (final TextRange textRange : list) {
                if (textRange.getStartOffset() > n) {
                    return textRange;
                }
            }
            return list.get(0);
        }
        Collections.reverse(list);
        for (final TextRange textRange2 : list) {
            if (textRange2.getEndOffset() < n) {
                return textRange2;
            }
        }
        return list.get(0);
    }
    
    public void invoke(final Editor editor, final PsiFile psiFile, final boolean b) {
        if (!FileModificationService.getInstance().prepareFileForWrite(psiFile)) {
            return;
        }
        PsiDocumentManager.getInstance(psiFile.getProject()).commitAllDocuments();
        final List<TextRange> a = a(editor, psiFile);
        if (a == null || a.isEmpty()) {
            return;
        }
        final TextRange a2 = a(editor.getCaretModel().getOffset(), a, b);
        final int endOffset = a2.getEndOffset();
        final int startOffset = a2.getStartOffset();
        final CharSequence charsSequence = editor.getDocument().getCharsSequence();
        final int shiftForward = CharArrayUtil.shiftForward(charsSequence, startOffset, " \t\n");
        final int n = CharArrayUtil.shiftBackward(charsSequence, endOffset - 1, " \t\n") + 1;
        editor.getCaretModel().moveToOffset(n);
        editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
        editor.getCaretModel().moveToOffset(shiftForward);
        editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
        if (charsSequence.charAt(shiftForward) == '<' && charsSequence.charAt(shiftForward + 1) == '#') {
            editor.getSelectionModel().setSelection(shiftForward, n);
        }
        else {
            editor.getSelectionModel().removeSelection();
        }
    }
}
