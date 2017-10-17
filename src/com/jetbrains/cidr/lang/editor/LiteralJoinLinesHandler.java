// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Document;
import com.intellij.codeInsight.editorActions.JoinLinesHandlerDelegate;

public class LiteralJoinLinesHandler implements JoinLinesHandlerDelegate
{
    public int tryJoinLines(final Document document, final PsiFile psiFile, final int n, final int n2) {
        CharSequence charsSequence;
        int n3;
        for (charsSequence = document.getCharsSequence(), n3 = n; charsSequence.charAt(n3) == ' ' || charsSequence.charAt(n3) == '\t'; --n3) {}
        if (charsSequence.charAt(n3) == '\"') {
            --n3;
        }
        if (n3 < n) {
            ++n3;
        }
        int n4 = 0;
        int n5 = -1;
    Label_0203:
        for (int i = n3; i < document.getTextLength(); ++i) {
            switch (charsSequence.charAt(i)) {
                case '\"': {
                    if (n4 == 0) {
                        if (OCElementUtil.getElementType(psiFile.findElementAt(i)) != OCTokenTypes.STRING_LITERAL) {
                            return -1;
                        }
                        n4 = 1;
                        n5 = i;
                        break;
                    }
                    else {
                        if (n4 == 1) {
                            document.deleteString(n5, i + 1);
                            return n5;
                        }
                        break Label_0203;
                    }
                    break;
                }
                default: {
                    break Label_0203;
                }
            }
        }
        return -1;
    }
}
