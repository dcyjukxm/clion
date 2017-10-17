// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lang;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.LiteralTextEscaper;

class GDBExpressionPlaceholder$1 extends LiteralTextEscaper<GDBExpressionPlaceholder> {
    public boolean decode(@NotNull final TextRange textRange, @NotNull final StringBuilder sb) {
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rangeInsideHost", "com/jetbrains/cidr/execution/debugger/backend/lang/GDBExpressionPlaceholder$1", "decode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "outChars", "com/jetbrains/cidr/execution/debugger/backend/lang/GDBExpressionPlaceholder$1", "decode"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        sb.append(GDBExpressionPlaceholder.this.getText().substring(textRange.getStartOffset(), textRange.getEndOffset()));
        return true;
    }
    
    public int getOffsetInHost(final int n, @NotNull final TextRange textRange) {
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rangeInsideHost", "com/jetbrains/cidr/execution/debugger/backend/lang/GDBExpressionPlaceholder$1", "getOffsetInHost"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return n;
    }
    
    public boolean isOneLine() {
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}