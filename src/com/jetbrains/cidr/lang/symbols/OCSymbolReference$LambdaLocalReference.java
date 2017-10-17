// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import java.util.Collections;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import java.util.List;
import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.expression.OCLambdaExpressionSymbol;

public static class LambdaLocalReference extends OCSymbolReference
{
    private OCLambdaExpressionSymbol myLambda;
    private long myOffset;
    private OCSymbolReference myParentScopeReference;
    
    public LambdaLocalReference() {
    }
    
    public LambdaLocalReference(@NotNull final OCQualifiedName ocQualifiedName, final long myOffset, @NotNull final OCLambdaExpressionSymbol myLambda, @NotNull final SymbolFilter symbolFilter, @NotNull final OCSymbolReference myParentScopeReference) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "<init>"));
        }
        if (myLambda == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lambda", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "<init>"));
        }
        if (symbolFilter == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filter", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "<init>"));
        }
        if (myParentScopeReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentScopeReference", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "<init>"));
        }
        super(ocQualifiedName, symbolFilter);
        this.myOffset = myOffset;
        this.myLambda = myLambda;
        this.myParentScopeReference = myParentScopeReference;
    }
    
    @Override
    public OCSymbolReference getSymbolReferenceToQualifier() {
        return new LambdaLocalReference(this.getQualifiedName().getQualifier(), this.myOffset, this.myLambda, this.getFilterForQualifier(), this.myParentScopeReference);
    }
    
    @NotNull
    @Override
    public OCSymbolReference createReferenceInSameContext(@NotNull final OCQualifiedName ocQualifiedName) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "createReferenceInSameContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        LambdaLocalReference lambdaLocalReference;
        try {
            lambdaLocalReference = new LambdaLocalReference(ocQualifiedName, this.myOffset, this.myLambda, this.myFilter, this.myParentScopeReference);
            if (lambdaLocalReference == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "createReferenceInSameContext"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return lambdaLocalReference;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCSymbolReference ocSymbolReference, @NotNull final OCSymbolReference ocSymbolReference2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocSymbolReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocSymbolReference2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, ocSymbolReference, ocSymbolReference2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final LambdaLocalReference lambdaLocalReference = (LambdaLocalReference)ocSymbolReference;
        final LambdaLocalReference lambdaLocalReference2 = (LambdaLocalReference)ocSymbolReference2;
        try {
            if (!Comparing.equal((Object)lambdaLocalReference.myLambda, (Object)lambdaLocalReference2.myLambda)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!Comparing.equal((Object)lambdaLocalReference.myOffset, (Object)lambdaLocalReference2.myOffset)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int hashCode = super.hashCode();
        int n = 0;
        int hashCode2 = 0;
        Label_0031: {
            try {
                n = 31 * hashCode;
                if (this.myLambda != null) {
                    hashCode2 = this.myLambda.hashCode();
                    break Label_0031;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            hashCode2 = 0;
        }
        final int n2 = (int)(31 * (n + hashCode2) + this.myOffset);
        int n3;
        try {
            n3 = 31 * n2;
            if (this.myParentScopeReference != null) {
                final int hashCode3 = this.myParentScopeReference.hashCode();
                return n3 + hashCode3;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final int hashCode3 = 0;
        return n3 + hashCode3;
    }
    
    @NotNull
    @Override
    public List<OCSymbol> doResolve(@NotNull final OCResolveContext ocResolveContext, final boolean b, final boolean b2, final boolean b3) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "doResolve"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List<OCSymbol> doResolve = null;
        Label_0144: {
            try {
                if (this.myLambda == null || this.myQualifiedName.getQualifier() != null) {
                    break Label_0144;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final OCSymbol resolveLambdaLocalSymbolInTable = OCResolveUtil.resolveLambdaLocalSymbolInTable(this.myLambda.getLocalVarsAndParams(), this.myQualifiedName, this.myOffset);
            List<OCSymbol> list = null;
            Label_0109: {
                try {
                    if (resolveLambdaLocalSymbolInTable == null) {
                        break Label_0144;
                    }
                    final OCSymbol ocSymbol = resolveLambdaLocalSymbolInTable;
                    list = Collections.singletonList(ocSymbol);
                    if (list == null) {
                        break Label_0109;
                    }
                    return (List<OCSymbol>)list;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCSymbol ocSymbol = resolveLambdaLocalSymbolInTable;
                    list = Collections.singletonList(ocSymbol);
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "doResolve"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return (List<OCSymbol>)list;
            try {
                doResolve = this.myParentScopeReference.doResolve(ocResolveContext, b, b2, b3);
                if (doResolve == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "doResolve"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return doResolve;
    }
    
    @Override
    public String toString() {
        return "LAMBDA LOCAL (" + this.getQualifiedName() + ")";
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
