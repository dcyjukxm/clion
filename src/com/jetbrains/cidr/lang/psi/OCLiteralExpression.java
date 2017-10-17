// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;

public interface OCLiteralExpression extends OCExpression
{
    @NotNull
    String getUnescapedLiteralText();
    
    @NotNull
    String getEscapedLiteralText();
    
    @NotNull
    String getRawLiteralText();
    
    boolean isNSNumberLiteral();
    
    boolean isNSStringLiteral();
    
    boolean isStringLiteral();
}
