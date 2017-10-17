// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.psi.OCExpression;

public class OCNotExpressionSurrounder extends OCTextExpressionSurrounder
{
    @Override
    public boolean isApplicable(final OCExpression ocExpression) {
        return OCIntType.isBool(ocExpression.getResolvedType(), (PsiElement)ocExpression);
    }
    
    @NotNull
    @Override
    protected String getBeforeText() {
        String s;
        try {
            s = "!(";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/surround/OCNotExpressionSurrounder", "getBeforeText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    @Override
    protected String getAfterText() {
        String s;
        try {
            s = ")";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/surround/OCNotExpressionSurrounder", "getAfterText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public String getTemplateDescription() {
        return "!(expr)";
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
