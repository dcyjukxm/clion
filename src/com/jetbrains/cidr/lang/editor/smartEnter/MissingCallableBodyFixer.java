// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.lang.SmartEnterProcessorWithFixers;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;

public class MissingCallableBodyFixer extends OCFixer
{
    @Override
    public void apply(@NotNull final Editor editor, @NotNull final OCSmartEnterProcessor ocSmartEnterProcessor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/MissingCallableBodyFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocSmartEnterProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/editor/smartEnter/MissingCallableBodyFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/MissingCallableBodyFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        Label_0158: {
            try {
                if (psiElement instanceof OCFunctionDefinition) {
                    break Label_0158;
                }
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof OCMethod;
                if (!b) {
                    return;
                }
                break Label_0158;
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof OCMethod;
                if (!b) {
                    return;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
        }
        Label_0204: {
            if (psiElement instanceof OCMethod) {
                final OCClassDeclaration containingClass = ((OCMethod)psiElement).getContainingClass();
                try {
                    if (containingClass instanceof OCInterface) {
                        return;
                    }
                    final OCClassDeclaration ocClassDeclaration = containingClass;
                    final boolean b2 = ocClassDeclaration instanceof OCProtocol;
                    if (b2) {
                        return;
                    }
                    break Label_0204;
                }
                catch (IncorrectOperationException ex6) {
                    throw a(ex6);
                }
                try {
                    final OCClassDeclaration ocClassDeclaration = containingClass;
                    final boolean b2 = ocClassDeclaration instanceof OCProtocol;
                    if (b2) {
                        return;
                    }
                }
                catch (IncorrectOperationException ex7) {
                    throw a(ex7);
                }
            }
        }
        final OCCallable ocCallable = (OCCallable)psiElement;
        final OCBlockStatement body = ocCallable.getBody();
        final Document document = editor.getDocument();
        try {
            if (body != null) {
                OCFixer.fixBlockIfNeed(editor, (PsiElement)body, false);
                return;
            }
        }
        catch (IncorrectOperationException ex8) {
            throw a(ex8);
        }
        int endOffset = OCFixer.getRangeWithMacros((PsiElement)ocCallable).getEndOffset();
        try {
            if (StringUtil.endsWithChar((CharSequence)ocCallable.getText(), ';')) {
                document.deleteString(endOffset - 1, endOffset);
                --endOffset;
            }
        }
        catch (IncorrectOperationException ex9) {
            throw a(ex9);
        }
        document.insertString(endOffset, (CharSequence)"{\n}");
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
