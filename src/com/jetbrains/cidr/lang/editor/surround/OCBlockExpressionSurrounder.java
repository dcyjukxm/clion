// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.jetbrains.cidr.lang.OCLanguageKind;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCExpression;

public class OCBlockExpressionSurrounder extends OCTextExpressionSurrounder
{
    @Override
    public boolean isApplicable(final OCExpression ocExpression) {
        return true;
    }
    
    @NotNull
    @Override
    protected String getBeforeText() {
        String s;
        try {
            s = "^{ return ";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/surround/OCBlockExpressionSurrounder", "getBeforeText"));
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
            s = "; }";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/surround/OCBlockExpressionSurrounder", "getAfterText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public String getTemplateDescription() {
        return "^{ return expr; }";
    }
    
    @Override
    protected OCLanguageKind getLanguageKind() {
        return OCLanguageKind.OBJ_C;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
