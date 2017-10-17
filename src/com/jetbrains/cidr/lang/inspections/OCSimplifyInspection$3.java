// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.intentions.OCConvertIfToTernaryIntentionAction;

static final class OCSimplifyInspection$3 extends OCConvertIfToTernaryIntentionAction {
    final /* synthetic */ String val$subject;
    final /* synthetic */ PsiElement val$element;
    final /* synthetic */ PsiElement val$simplified;
    
    @NotNull
    @Override
    public String getText() {
        String string;
        try {
            string = "Simplify " + this.val$subject;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$3", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @Override
    protected Converter createConverter(final PsiElement psiElement) {
        return new Converter(this, this.val$element);
    }
    
    @Override
    protected OCExpression getNewExpression(final Converter converter) {
        return (OCExpression)this.val$simplified;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}