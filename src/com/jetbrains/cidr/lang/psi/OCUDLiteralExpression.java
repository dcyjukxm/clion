// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface OCUDLiteralExpression extends OCUnaryExpression, PsiNameIdentifierOwner
{
    public static final String UDL_OPERATOR_PREFIX = "\"\"";
    
    @Contract(pure = true)
    default boolean isUDLOperator(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sign", "com/jetbrains/cidr/lang/psi/OCUDLiteralExpression", "isUDLOperator"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s.startsWith("\"\"");
    }
    
    default IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
