// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.selectWord;

import com.jetbrains.cidr.lang.psi.OCMacroParameterList;
import java.util.ArrayList;
import com.intellij.codeInsight.editorActions.SelectWordUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.text.CharArrayUtil;
import java.util.Collections;
import com.intellij.openapi.util.TextRange;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.Contract;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.editorActions.ExtendWordSelectionHandlerBase;

public class OCSelectorWordSelectioner extends ExtendWordSelectionHandlerBase
{
    @Override
    public boolean canSelect(final PsiElement psiElement) {
        return isCustomSelectionContext(psiElement);
    }
    
    public static boolean isCustomSelectionContext(final PsiElement psiElement) {
        return a(psiElement) != null || psiElement.getNode().getElementType() == OCTokenTypes.HEADER_PATH_LITERAL || psiElement instanceof OCDefineDirective;
    }
    
    @Contract("null -> null")
    private static OCSelectorExpression a(final PsiElement psiElement) {
        return (OCSelectorExpression)PsiTreeUtil.getParentOfType(psiElement, (Class)OCSelectorExpression.class);
    }
    
    @Override
    public List<TextRange> select(final PsiElement psiElement, final CharSequence charSequence, final int n, final Editor editor) {
        final OCSelectorExpression a = a(psiElement);
        if (a != null) {
            return Collections.singletonList(a.getSelectorRange().shiftRight(a.getTextRange().getStartOffset()));
        }
        if (psiElement.getNode().getElementType() == OCTokenTypes.HEADER_PATH_LITERAL) {
            final String text = psiElement.getText();
            final int shiftForward = CharArrayUtil.shiftForward((CharSequence)text, 0, " \t\n");
            final String substring = text.substring(shiftForward);
            final ArrayList arrayList = ContainerUtil.newArrayList();
            final TextRange from = TextRange.from(psiElement.getTextRange().getStartOffset() + shiftForward, psiElement.getTextLength() - shiftForward);
            arrayList.add(from);
            SelectWordUtil.addWordSelection(editor.getSettings().isCamelWords(), charSequence, n, arrayList);
            if ((from.getLength() > 1 && substring.startsWith("\"") && substring.endsWith("\"")) || (substring.startsWith("<") && substring.endsWith(">"))) {
                arrayList.add(from.shiftRight(1).grown(-2));
            }
            return (List<TextRange>)arrayList;
        }
        if (psiElement instanceof OCDefineDirective) {
            final OCMacroParameterList macroParameters = ((OCDefineDirective)psiElement).getMacroParameters();
            final PsiElement nameIdentifier = ((OCDefineDirective)psiElement).getNameIdentifier();
            if (nameIdentifier != null && macroParameters != null) {
                final int shiftForward2 = CharArrayUtil.shiftForward(charSequence, macroParameters.getTextRange().getEndOffset(), " \t");
                final ArrayList arrayList2 = ContainerUtil.newArrayList();
                arrayList2.add(TextRange.create(nameIdentifier.getTextRange().getStartOffset(), macroParameters.getTextRange().getEndOffset()));
                arrayList2.add(TextRange.create(shiftForward2, psiElement.getTextRange().getEndOffset()));
                return (List<TextRange>)arrayList2;
            }
        }
        return Collections.emptyList();
    }
}
