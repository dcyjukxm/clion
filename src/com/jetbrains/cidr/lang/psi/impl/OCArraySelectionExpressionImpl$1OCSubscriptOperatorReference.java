// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Collections;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.ArrayList;
import com.intellij.openapi.util.TextRange;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.psi.MultiRangeReference;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;

class 1OCSubscriptOperatorReference extends OCOperatorReference implements MultiRangeReference
{
    public 1OCSubscriptOperatorReference(final OCElement ocElement, final OCExpression... array) {
        super(ocElement, "[]", OperatorPlacement.POSTFIX, null, array);
    }
    
    @NotNull
    public List<TextRange> getRanges() {
        final ArrayList<TextRange> list = new ArrayList<TextRange>();
        final PsiElement access$000 = OCArraySelectionExpressionImpl.access$000(OCArraySelectionExpressionImpl.this, OCTokenTypes.LBRACKET);
        try {
            if (access$000 != null) {
                list.add(OCElementUtil.getRangeInParent(access$000));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final PsiElement access$2 = OCArraySelectionExpressionImpl.access$100(OCArraySelectionExpressionImpl.this, OCTokenTypes.RBRACKET);
        try {
            if (access$2 != null) {
                list.add(OCElementUtil.getRangeInParent(access$2));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        ArrayList<TextRange> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "getRanges"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return list2;
    }
    
    @NotNull
    @Override
    public List<OCSymbol> resolveToSymbols(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "resolveToSymbols"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final List<OCSymbol> resolveToSubscript = this.resolveToSubscript(ocResolveContext);
        List<OCSymbol> resolveToSymbols = null;
        Label_0101: {
            List<OCSymbol> list = null;
            Label_0066: {
                try {
                    if (resolveToSubscript == null) {
                        break Label_0101;
                    }
                    list = resolveToSubscript;
                    if (list == null) {
                        break Label_0066;
                    }
                    return list;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    list = resolveToSubscript;
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "resolveToSymbols"));
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            return list;
            try {
                resolveToSymbols = super.resolveToSymbols(ocResolveContext);
                if (resolveToSymbols == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "resolveToSymbols"));
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        return resolveToSymbols;
    }
    
    @NotNull
    @Override
    public List<OCSymbol> resolveToSymbols() {
        final List<OCSymbol> resolveToSubscript = this.resolveToSubscript(new OCResolveContext((PsiElement)OCArraySelectionExpressionImpl.this));
        List<OCSymbol> resolveToSymbols = null;
        Label_0067: {
            List<OCSymbol> list = null;
            Label_0032: {
                try {
                    if (resolveToSubscript == null) {
                        break Label_0067;
                    }
                    list = resolveToSubscript;
                    if (list == null) {
                        break Label_0032;
                    }
                    return list;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    list = resolveToSubscript;
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "resolveToSymbols"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return list;
            try {
                resolveToSymbols = super.resolveToSymbols();
                if (resolveToSymbols == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "resolveToSymbols"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return resolveToSymbols;
    }
    
    @Nullable
    protected List<OCSymbol> resolveToSubscript(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "resolveToSubscript"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        if (OCCompilerFeatures.supportsSubscripting((PsiFile)OCArraySelectionExpressionImpl.this.getContainingOCFile())) {
            final OCMethodSymbol arraySubscriptMethod = OCArraySelectionExpressionImpl.this.getArraySubscriptMethod(ocResolveContext);
            try {
                if (arraySubscriptMethod != null) {
                    return (List<OCSymbol>)Collections.singletonList(arraySubscriptMethod);
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
