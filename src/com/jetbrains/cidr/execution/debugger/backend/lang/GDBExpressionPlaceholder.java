// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lang;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.impl.source.tree.LeafElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class GDBExpressionPlaceholder extends ASTWrapperPsiElement implements PsiLanguageInjectionHost
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public GDBExpressionPlaceholder(final ASTNode node) {
        super(node);
    }
    
    public boolean isValidHost() {
        return true;
    }
    
    public PsiLanguageInjectionHost updateText(@NotNull final String newText) {
        try {
            if (newText == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/lang/GDBExpressionPlaceholder", "updateText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ASTNode firstChildNode = this.getNode().getFirstChildNode();
        Label_0074: {
            try {
                if (GDBExpressionPlaceholder.$assertionsDisabled) {
                    break Label_0074;
                }
                final ASTNode astNode = firstChildNode;
                final boolean b = astNode instanceof LeafElement;
                if (!b) {
                    break Label_0074;
                }
                break Label_0074;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final ASTNode astNode = firstChildNode;
                final boolean b = astNode instanceof LeafElement;
                if (!b) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        ((LeafElement)firstChildNode).replaceWithText(newText);
        return (PsiLanguageInjectionHost)this;
    }
    
    @NotNull
    public LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
        LiteralTextEscaper<GDBExpressionPlaceholder> literalTextEscaper;
        try {
            literalTextEscaper = new LiteralTextEscaper<GDBExpressionPlaceholder>(this) {
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
            };
            if (literalTextEscaper == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lang/GDBExpressionPlaceholder", "createLiteralTextEscaper"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (LiteralTextEscaper<? extends PsiLanguageInjectionHost>)literalTextEscaper;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!GDBExpressionPlaceholder.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
