// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.jetbrains.cidr.lang.editor.completion.OCCodeContextType;
import com.intellij.codeInsight.template.TemplateContextType;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Expression;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.template.Macro;

public class OCExecutionContextMacro extends Macro
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public String getName() {
        return "executionContext";
    }
    
    public String getPresentableName() {
        return "executionContext()";
    }
    
    @NotNull
    public String getDefaultValue() {
        String s;
        try {
            s = "";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/OCExecutionContextMacro", "getDefaultValue"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/OCExecutionContextMacro", "calculateResult"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final PsiElement insertionPlace = OCTemplatesUtil.getInsertionPlace(expressionContext);
        final PsiFile containingFile = insertionPlace.getContainingFile();
        Label_0075: {
            try {
                if (OCExecutionContextMacro.$assertionsDisabled) {
                    break Label_0075;
                }
                final PsiFile psiFile = containingFile;
                if (psiFile == null) {
                    break Label_0075;
                }
                break Label_0075;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final PsiFile psiFile = containingFile;
                if (psiFile == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)PsiTreeUtil.getContextOfType(insertionPlace, new Class[] { OCFunctionDefinition.class });
        try {
            if (ocFunctionDefinition != null) {
                return (Result)new TextResult("__PRETTY_FUNCTION__");
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        final OCMethod ocMethod = (OCMethod)PsiTreeUtil.getContextOfType(insertionPlace, new Class[] { OCMethod.class });
        Label_0164: {
            try {
                if (ocMethod == null) {
                    return null;
                }
                final OCMethod ocMethod2 = ocMethod;
                final OCBlockStatement ocBlockStatement = ocMethod2.getBody();
                if (ocBlockStatement != null) {
                    break Label_0164;
                }
                return null;
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            try {
                final OCMethod ocMethod2 = ocMethod;
                final OCBlockStatement ocBlockStatement = ocMethod2.getBody();
                if (ocBlockStatement != null) {
                    return (Result)new TextResult("sel_getName(_cmd)");
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        return null;
    }
    
    public boolean isAcceptableInContext(final TemplateContextType templateContextType) {
        return templateContextType instanceof OCCodeContextType;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCExecutionContextMacro.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
