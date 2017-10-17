// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.jetbrains.cidr.lang.editor.completion.OCCodeContextType;
import com.intellij.codeInsight.template.TemplateContextType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Expression;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.template.Macro;

public class OCGuessElementTypeMacro extends Macro
{
    public String getName() {
        return "guessElementType";
    }
    
    public String getPresentableName() {
        return "guessElementType()";
    }
    
    @NotNull
    public String getDefaultValue() {
        String s;
        try {
            s = "id";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/OCGuessElementTypeMacro", "getDefaultValue"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public Result calculateResult(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/OCGuessElementTypeMacro", "calculateResult"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final Project project = expressionContext.getProject();
        final Editor editor = expressionContext.getEditor();
        if (editor != null) {
            final PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
            PsiElement element = null;
            Label_0106: {
                try {
                    if (psiFile != null) {
                        element = psiFile.findElementAt(expressionContext.getStartOffset());
                        break Label_0106;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                element = null;
            }
            final PsiElement psiElement = element;
            if (psiElement != null) {
                final OCForeachStatement ocForeachStatement = (OCForeachStatement)PsiTreeUtil.getParentOfType(psiElement, (Class)OCForeachStatement.class);
                if (ocForeachStatement != null) {
                    final OCExpression collectionExpression = ocForeachStatement.getCollectionExpression();
                    if (collectionExpression instanceof OCReferenceExpression) {
                        final OCSymbol resolveToSymbol = ((OCReferenceExpression)collectionExpression).resolveToSymbol();
                        if (resolveToSymbol != null) {
                            final OCType guessedType = resolveToSymbol.getResolvedType().getGuessedType();
                            if (guessedType.isPointerToObjectCompatible()) {
                                final OCType collectionItemType = OCTypeUtils.getCollectionItemType(((OCPointerType)guessedType).getRefType(), (PsiElement)ocForeachStatement);
                                try {
                                    if (collectionItemType != null) {
                                        return (Result)new TextResult(collectionItemType.getName());
                                    }
                                }
                                catch (IllegalStateException ex3) {
                                    throw a(ex3);
                                }
                            }
                        }
                    }
                }
            }
        }
        return (Result)new TextResult("id");
    }
    
    public boolean isAcceptableInContext(final TemplateContextType templateContextType) {
        return templateContextType instanceof OCCodeContextType;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
