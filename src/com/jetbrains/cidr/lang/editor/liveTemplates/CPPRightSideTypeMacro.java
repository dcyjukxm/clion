// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.jetbrains.cidr.lang.editor.completion.OCCodeContextType;
import com.intellij.codeInsight.template.TemplateContextType;
import com.jetbrains.cidr.lang.psi.OCLocalScopeable;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.codeInsight.template.TextResult;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.Macro;

public class CPPRightSideTypeMacro extends Macro
{
    public static final String MACRO_NAME = "rightSideType";
    
    public String getName() {
        return "rightSideType";
    }
    
    public String getPresentableName() {
        return "rightSideType()";
    }
    
    @Nullable
    public Result calculateResult(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/CPPRightSideTypeMacro", "calculateResult"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiFile psiFile = PsiDocumentManager.getInstance(expressionContext.getProject()).getPsiFile(expressionContext.getEditor().getDocument());
        try {
            if (OCCompilerFeatures.supportsCxxAutoType(psiFile)) {
                return (Result)new TextResult("auto");
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCDeclaration a = a(psiFile.findElementAt(expressionContext.getStartOffset()));
        if (a != null) {
            final List<OCDeclarator> declarators = a.getDeclarators();
            if (!declarators.isEmpty()) {
                final OCExpression initializer = declarators.get(0).getInitializer();
                if (initializer != null) {
                    final OCType resolvedType = initializer.getResolvedType();
                    if (!resolvedType.isUnresolved((PsiElement)a)) {
                        return (Result)new TextResult(resolvedType.getBestNameInContext((PsiElement)a));
                    }
                }
            }
        }
        return null;
    }
    
    private static OCDeclaration a(PsiElement parent) {
        while (!(parent instanceof OCTypeElement)) {
            parent = parent.getParent();
            try {
                if (parent != null) {
                    if (!(parent instanceof OCLocalScopeable)) {
                        continue;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return null;
        }
        try {
            if (parent.getParent() instanceof OCDeclaration) {
                return (OCDeclaration)parent.getParent();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    public boolean isAcceptableInContext(final TemplateContextType templateContextType) {
        return templateContextType instanceof OCCodeContextType;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
