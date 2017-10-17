// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.jetbrains.cidr.lang.editor.completion.OCCodeContextType;
import com.intellij.codeInsight.template.TemplateContextType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.codeInsight.template.TextResult;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Expression;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.template.Macro;

public class OCExpressionFormatCodeMacro extends Macro
{
    public String getName() {
        return "expressionFormatCode";
    }
    
    public String getPresentableName() {
        return "expressionFormatCode()";
    }
    
    @NotNull
    public String getDefaultValue() {
        String s;
        try {
            s = "%@";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/OCExpressionFormatCodeMacro", "getDefaultValue"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/OCExpressionFormatCodeMacro", "calculateResult"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (array.length != 1) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final Result calculateResult = array[0].calculateResult(expressionContext);
        if (calculateResult != null) {
            final String string = calculateResult.toString();
            if (string.length() > 0) {
                final OCExpression expressionFromText = OCElementFactory.expressionFromText(string, OCTemplatesUtil.getInsertionPlace(expressionContext), false);
                if (expressionFromText != null) {
                    final String formatString = expressionFromText.getResolvedType().getFormatString();
                    try {
                        if (formatString != null) {
                            final TextResult textResult = new TextResult(formatString);
                            return (Result)textResult;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                    final TextResult textResult = null;
                    return (Result)textResult;
                }
            }
        }
        return null;
    }
    
    public boolean isAcceptableInContext(final TemplateContextType templateContextType) {
        return templateContextType instanceof OCCodeContextType;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
